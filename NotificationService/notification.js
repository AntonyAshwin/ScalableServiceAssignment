const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8084 });

wss.on('connection', (ws) => {
  console.log('Client connected');

  // Send a welcome message when a client connects
  ws.send('Welcome! You are now connected to the notification server.');

  // Handle incoming messages from the client (if needed)
  ws.on('message', (message) => {
    console.log('Received:', message);
  });

  // Simulate sending a notification after 5 seconds
  setTimeout(() => {
    ws.send('You have a new notification!');
  }, 5000);

  // Handle client disconnect
  ws.on('close', () => {
    console.log('Client disconnected');
  });
});

console.log('WebSocket server is running on ws://localhost:8084');
