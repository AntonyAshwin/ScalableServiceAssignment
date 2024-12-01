const express = require("express");
const router = express.Router();
const Achievement = require("../models/Achievement");
const PlayerAchievement = require("../models/PlayerAchievement");

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
    res.status(201).json({ message: "Achievement created!", achievement });
  } catch (error) {
    res.status(500).json({ message: "Error creating achievement", error });
  }
});

// GET /achievements/:playerId: Retrieve the achievements earned by a player
router.get("/:playerId", async (req, res) => {
  try {
    const { playerId } = req.params;
    const playerAchievements = await PlayerAchievement.findOne({ playerId });
    if (!playerAchievements) {
      return res.status(404).json({ message: "Player not found!" });
    }
    res.status(200).json({ achievements: playerAchievements.achievements });
  } catch (error) {
    res.status(500).json({ message: "Error retrieving achievements", error });
  }
});

// POST /achievements/award: Award an achievement to a player
router.post("/award", async (req, res) => {
  try {
    const { playerId, achievementName } = req.body;

    // Check if the achievement exists
    const achievement = await Achievement.findOne({ name: achievementName });
    if (!achievement) {
      return res.status(404).json({ message: "Achievement not found!" });
    }

    // Add achievement to the player's record
    let playerAchievements = await PlayerAchievement.findOne({ playerId });
    if (!playerAchievements) {
      playerAchievements = new PlayerAchievement({ playerId, achievements: [] });
    }

    if (!playerAchievements.achievements.includes(achievementName)) {
      playerAchievements.achievements.push(achievementName);
      await playerAchievements.save();
      return res.status(201).json({
        message: `Achievement "${achievementName}" awarded to player ${playerId}!`,
      });
    } else {
      return res.status(200).json({
        message: `Player ${playerId} already has the achievement "${achievementName}".`,
      });
    }
  } catch (error) {
    res.status(500).json({ message: "Error awarding achievement", error });
  }
});

module.exports = router;
