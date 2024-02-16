FROM amazoncorretto:17.0.9

# Copy your JAR file to the container
COPY target/monitoring-microservice-0.0.1-SNAPSHOT.jar monitoring-microservice-0.0.1-SNAPSHOT.jar

# Install necessary packages (e.g., curl)
RUN yum install -y curl

# Expose the port
EXPOSE 8082

# Define the entry point for your application
ENTRYPOINT ["java", "-jar", "monitoring-microservice-0.0.1-SNAPSHOT.jar"]