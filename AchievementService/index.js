const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
require("dotenv").config();

const app = express();
app.use(bodyParser.json());

// Connect to MongoDB
mongoose.connect(process.env.MONGO_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

// Schema and Model
const achievementSchema = new mongoose.Schema({
  name: String, // Name of the achievement
  description: String, // Description of the achievement
  criteria: Object, // Criteria to unlock the achievement
});

const playerAchievementSchema = new mongoose.Schema({
  playerId: String, // Player's unique ID
  achievements: [String], // List of achievement names
});

const Achievement = require("./models/Achievement");
const PlayerAchievement = require("./models/PlayerAchievement");

// Health Check
app.get("/", (req, res) => res.send("Achievement Service is running!"));

// Routes
const achievementRoutes = require("./routes/achievementRoutes");
app.use("/achievements", achievementRoutes);

// Start the server
const PORT = 8081;
app.listen(PORT, () => {
  console.log(`Achievement Service running on http://localhost:${PORT}`);
});
