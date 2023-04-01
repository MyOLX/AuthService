# Base image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Set the container name
ENV CONTAINER_NAME auth-service

# Expose the application port
EXPOSE 8000

COPY ./apache-maven-3.9.1 /apache-maven-3.9.1

# Set the entry point
ENTRYPOINT ["bash", "/app/start.sh"]
