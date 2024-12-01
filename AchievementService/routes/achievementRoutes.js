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
