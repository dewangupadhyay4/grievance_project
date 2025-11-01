# Use the official OpenJDK 21 base image
FROM openjdk:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy everything into the container
COPY . .

# Give execute permission to mvnw (important!)
RUN chmod +x mvnw

# Build the project using Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Expose your app port (update if different)
EXPOSE 9012

# Run the JAR file (update name if needed)
CMD ["java", "-jar", "target/grievance-0.0.1-SNAPSHOT.jar"]
