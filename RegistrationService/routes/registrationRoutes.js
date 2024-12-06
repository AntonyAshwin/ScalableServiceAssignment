require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const axios = require('axios');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const User = require('../models/User');

const app = express();
app.use(express.json());
const playerServiceUrl = process.env.PLAYER_SERVICE_URL || 'http://localhost:8080';
const mongoUri = process.env.MONGO_URI;
if (!mongoUri) {
  console.error('MONGO_URI is not defined in the .env file');
  process.exit(1);
}

// Connect to MongoDB
mongoose.connect(mongoUri, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.error('MongoDB connection error:', err));

// JWT authentication middleware
const authenticate = (req, res, next) => {
  const token = req.header('Authorization').replace('Bearer ', '');
  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch (err) {
    res.status(401).send({ message: 'Unauthorized' });
  }
};

// Register route
app.post('/register', async (req, res) => {
  const { name, password, gameId, email } = req.body;

  // Validate request body
  if (!name || !password || !gameId || !email) {
    return res.status(400).send({ message: 'All fields are required' });
  }

  // Hash the password
  const hashedPassword = await bcrypt.hash(password, 10);

  // Create a new user
  const user = new User({ name, password: hashedPassword, gameId, email });

  try {
    await user.save();

    // Notify PlayerService
    const response = await axios.post(`${playerServiceUrl}/players`, {
      name,
      email,
      gameId
    });

    console.log('PlayerService response:', response.data);

    res.status(201).send({ message: 'User registered successfully', user });
  } catch (error) {
    if (error.code === 11000) {
      // Duplicate key error
      const duplicateField = error.keyPattern.name ? 'name' : 'email';
      res.status(409).send({ message: `User with this ${duplicateField} already exists` });
    } else {
      res.status(500).send({ message: 'Error registering user', error });
    }
  }
});

// Login route
app.post('/login', async (req, res) => {
  const { email, password } = req.body;

  // Validate request body
  if (!email || !password) {
    return res.status(400).send({ message: 'Email and password are required' });
  }

  // Find the user by email
  const user = await User.findOne({ email });
  if (!user) {
    return res.status(401).send({ message: 'Invalid email or password' });
  }

  // Check the password
  const isPasswordValid = await bcrypt.compare(password, user.password);
  if (!isPasswordValid) {
    return res.status(401).send({ message: 'Invalid email or password' });
  }

  // Generate JWT token with expiry time
  const token = jwt.sign({ id: user._id, email: user.email, name: user.name, gameId: user.gameId }, process.env.JWT_SECRET, { expiresIn: '1h' });

  res.send({ token });
});

// Change password route
app.post('/changepassword', authenticate, async (req, res) => {
  const { currentPassword, newPassword } = req.body;
  const userId = req.user.id;

  // Validate request body
  if (!currentPassword || !newPassword) {
    return res.status(400).send({ message: 'Current password and new password are required' });
  }

  // Find the user by ID
  const user = await User.findById(userId);
  if (!user) {
    return res.status(404).send({ message: 'User not found' });
  }

  // Check the current password
  const isPasswordValid = await bcrypt.compare(currentPassword, user.password);
  if (!isPasswordValid) {
    return res.status(401).send({ message: 'Invalid current password' });
  }

  // Hash the new password
  const hashedNewPassword = await bcrypt.hash(newPassword, 10);

  // Update the user's password
  user.password = hashedNewPassword;
  await user.save();

  res.send({ message: 'Password changed successfully' });
});

// Protected route example
app.get('/protected', authenticate, (req, res) => {
  res.send({ message: 'This is a protected route', user: req.user });
});

// Start the server
const PORT = process.env.PORT || 8085;
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}/`);
});