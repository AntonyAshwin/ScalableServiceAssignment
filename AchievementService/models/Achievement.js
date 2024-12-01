const mongoose = require("mongoose");

const achievementSchema = new mongoose.Schema({
  name: String, // Name of the achievement
  description: String, // Description of the achievement
  criteria: Object, // Criteria to unlock the achievement
});

module.exports = mongoose.models.Achievement || mongoose.model("Achievement", achievementSchema);
