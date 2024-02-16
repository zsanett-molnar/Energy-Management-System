FROM amazoncorretto:17.0.9

# Copy your JAR file to the container
COPY target/chat-microservice-0.0.1-SNAPSHOT.jar chat-microservice-0.0.1-SNAPSHOT.jar

# Install necessary packages (e.g., curl)
RUN yum install -y curl

# Expose the port
EXPOSE 8080

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "chat-microservice-0.0.1-SNAPSHOT.jar"]