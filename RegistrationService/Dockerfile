FROM node:23

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the application code to the working directory
COPY . .

# Copy the .env file from the routes directory to the working directory
COPY .env ./

# Expose the port the app runs on
EXPOSE 8085

# Remove node_modules and reinstall dependencies to ensure compatibility
RUN rm -rf node_modules && npm install

# Start the application
CMD ["npm", "start"]