require('dotenv').config();
const fastify = require('fastify')({ logger: true });
const mongoose = require('mongoose');
const axios = require('axios');
const bcrypt = require('bcrypt');
const fastifyJwt = require('fastify-jwt');

console.log("a123 ", process.env.MONGO_URI);

// Connect to MongoDB
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('MongoDB connected'))
  .catch(err => console.error('MongoDB connection error:', err));

// Define the User schema
const userSchema = new mongoose.Schema({
  name: { type: String, unique: true },
  password: String,
  gameId: String,
  email: { type: String, unique: true },
});

const User = mongoose.model('User', userSchema);

// Register JWT plugin
fastify.register(fastifyJwt, {
  secret: process.env.JWT_SECRET
});

// JWT authentication middleware
fastify.decorate("authenticate", async function(request, reply) {
  try {
    await request.jwtVerify();
  } catch (err) {
    reply.send(err);
  }
});

// Register route
fastify.post('/register', async (request, reply) => {
  const { name, password, gameId, email } = request.body;

  // Validate request body
  if (!name || !password || !gameId || !email) {
    return reply.status(400).send({ message: 'All fields are required' });
  }

  // Hash the password
  const hashedPassword = await bcrypt.hash(password, 10);

  // Create a new user
  const user = new User({ name, password: hashedPassword, gameId, email });

  try {
    await user.save();

    // Notify PlayerService
    const response = await axios.post('http://localhost:8080/players', {
      name,
      email,
      gameId
    });

    console.log('PlayerService response:', response.data);

    reply.status(201).send({ message: 'User registered successfully', user });
  } catch (error) {
    if (error.code === 11000) {
      // Duplicate key error
      const duplicateField = error.keyPattern.name ? 'name' : 'email';
      reply.status(409).send({ message: `User with this ${duplicateField} already exists` });
    } else {
      reply.status(500).send({ message: 'Error registering user', error });
    }
  }
});

// Login route
fastify.post('/login', async (request, reply) => {
  const { email, password } = request.body;

  // Validate request body
  if (!email || !password) {
    return reply.status(400).send({ message: 'Email and password are required' });
  }

  // Find the user by email
  const user = await User.findOne({ email });
  if (!user) {
    return reply.status(401).send({ message: 'Invalid email or password' });
  }

  // Check the password
  const isPasswordValid = await bcrypt.compare(password, user.password);
  if (!isPasswordValid) {
    return reply.status(401).send({ message: 'Invalid email or password' });
  }

  // Generate JWT token with expiry time
  const token = fastify.jwt.sign({ id: user._id, email: user.email, name: user.name, gameId: user.gameId }, { expiresIn: '1h' });

  reply.send({ token });
});

// Protected route example
fastify.get('/protected', { preValidation: [fastify.authenticate] }, async (request, reply) => {
  reply.send({ message: 'This is a protected route', user: request.user });
});

// Start the server
const start = async () => {
  try {
    await fastify.listen({ port: 8082 });
    fastify.log.info(`Server running at http://localhost:8082/`);
  } catch (err) {
    fastify.log.error(err);
    process.exit(1);
  }
};
start();