const mongoose = require("mongoose");

const playerAchievementSchema = new mongoose.Schema({
  playerId: String, // Player's unique ID
  achievements: [String], // List of achievement names
});

module.exports = mongoose.models.PlayerAchievement || mongoose.model("PlayerAchievement", playerAchievementSchema);
