FROM clojure:latest

# Set the working directory
WORKDIR /usr/src/app

# Copy the current directory contents into the container at /app
COPY . /usr/src/app

# Make port 3010 available to the world outside this container
EXPOSE 3010

# Run app when the container launches
CMD ["lein", "run"]
