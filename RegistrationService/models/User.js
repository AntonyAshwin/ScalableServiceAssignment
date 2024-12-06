const mongoose = require('mongoose');

const userSchema = new mongoose.Schema({
  name: { type: String, unique: true },
  password: String,
  gameId: String,
  email: { type: String, unique: true },
});

const User = mongoose.model('User', userSchema);

module.exports = User;