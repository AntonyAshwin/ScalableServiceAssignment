const mongoose = require("mongoose");

const achievementSchema = new mongoose.Schema({
  name: String, // Name of the achievement
  description: String, // Description of the achievement
  gameId: String, // ID of the game the achievement is for
  criteria: {
    level: { type: Number, required: false },
    points: { type: Number, required: false },
  }, // Criteria to unlock the achievement
});

module.exports = mongoose.models.Achievement || mongoose.model("Achievement", achievementSchema);
