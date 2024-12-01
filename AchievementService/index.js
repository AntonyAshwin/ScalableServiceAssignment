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

// Health Check
app.get("/", (req, res) => res.send("Achievement Service is running!"));

// Routes
const achievementRoutes = require("./routes/achievementRoutes");
app.use("/achievements", achievementRoutes);

// Start the server
const PORT = process.env.PORT || 8081;
app.listen(PORT, () => {
  console.log(`Achievement Service running on http://localhost:${PORT}`);
});
