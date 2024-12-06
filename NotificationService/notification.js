const WebSocket = require('ws');
const amqp = require('amqplib/callback_api');

const wss = new WebSocket.Server({ port: 8084 });
let messages = [];

wss.on('connection', (ws) => {
    console.log('Client connected');
    console.log(messages);
    // Send all stored messages to the newly connected client
    messages.forEach((message) => {
        if (ws.readyState === WebSocket.OPEN) {
            ws.send(message);
        }
    });

    ws.on('close', () => {
        console.log('Client disconnected');
    });
});

amqp.connect('amqp://rabbitmq', (error0, connection) => {
    if (error0) {
        throw error0;
    }
    connection.createChannel((error1, channel) => {
        if (error1) {
            throw error1;
        }

        const queue = 'achievement_notifications';

        channel.assertQueue(queue, {
            durable: false
        });

        console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);

        channel.consume(queue, (msg) => {
            if (msg !== null) {
                const message = msg.content.toString();
                console.log(" [x] Received '%s'", message);

                // Store the message
                messages.push(message);

                // Send the message to all connected WebSocket clients
                wss.clients.forEach((client) => {
                    if (client.readyState === WebSocket.OPEN) {
                        client.send(message);
                    }
                });

                channel.ack(msg);
            }
        }, {
            noAck: false
        });
    });
});

console.log('WebSocket server is running on ws://notification:8084');