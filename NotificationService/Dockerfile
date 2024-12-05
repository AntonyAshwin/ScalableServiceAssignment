# Use the official Node.js image as the base image
FROM node:23

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the application code to the working directory
COPY . .

# Expose the port the app runs on
EXPOSE 8084

# Ensure dependencies are installed before starting the application
CMD ["sh", "-c", "npm install && node notification.js"]