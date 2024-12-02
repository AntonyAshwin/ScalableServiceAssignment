const express = require("express");
const router = express.Router();
const Achievement = require("../models/Achievement");
const PlayerAchievement = require("../models/PlayerAchievement");
const axios = require("axios");
require('dotenv').config();

// POST /achievements: Create a new achievement
router.post("/", async (req, res) => {
  try {
    const { name, description, gameId, criteria } = req.body;

    // Validate criteria
    if (!criteria || (!criteria.level && !criteria.points)) {
      return res.status(400).json({ message: "Criteria must include either level or points or both." });
    }

    const achievement = new Achievement({ name, description, gameId, criteria });
    await achievement.save();

    // Call the updateProgressByGameId endpoint
    const playerServiceUrl = process.env.PLAYER_SERVICE_URL;
    axios.post(`${playerServiceUrl}/players/updateProgressByGameId?gameId=${gameId}`);

    res.status(201).json({ message: "Achievement created!", achievement });
  } catch (error) {
    res.status(500).json({ message: "Error creating achievement", error });
  }
});

// GET /achievements/:playerId: Retrieve the achievements earned by a player
router.get("/:gameId", async (req, res) => {
  try {
    const { gameId } = req.params;
    console.log(gameId);
    const achievements = await Achievement.find({ gameId });
    if (!achievements || achievements.length === 0) {
      return res.status(404).json({ message: "No achievements found for this gameId!" });
    }
    res.status(200).json({ achievements });
  } catch (error) {
    res.status(500).json({ message: "Error retrieving achievements", error });
  }
});

// GET /achievements/:gameId: Retrieve the achievements for a game
router.get("/:gameId", async (req, res) => {
  try {
    const { gameId } = req.params;

    // Find achievements for the given gameId
    const achievements = await Achievement.find({ gameId });

    if (!achievements || achievements.length === 0) {
      return res.status(404).json({ message: "No achievements found for this gameId!" });
    }

    res.status(200).json(achievements);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving achievements", error });
  }
});



// POST /achievements/match: Match achievements based on criteria
router.post("/match", async (req, res) => {
  try {
    const { gameId, level, points } = req.body;

    // Find achievements for the given gameId
    const achievements = await Achievement.find({ gameId });

    // Filter achievements based on criteria
    const matchedAchievements = achievements.filter(achievement => {
      const { criteria } = achievement;
      const levelMatch = criteria.level ? (level === -1 || level >= criteria.level) : true;
      const pointsMatch = criteria.points ? (points === -1 || points >= criteria.points) : true;
      return levelMatch && pointsMatch;
    });

    // Map to only return names and descriptions
    const result = matchedAchievements.map(achievement => ({
      name: achievement.name,
      description: achievement.description,
    }));

    res.status(200).json(result);
  } catch (error) {
    res.status(500).json({ message: "Error matching achievements", error });
  }
});

module.exports = router;
