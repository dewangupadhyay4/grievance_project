# Use Eclipse Temurin (official OpenJDK builds)
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Make the Maven wrapper executable
RUN chmod +x mvnw

# Build the Spring Boot project without running tests
RUN ./mvnw clean package -DskipTests

# Expose your application port
EXPOSE 9012

# Automatically detect and run the generated JAR file
CMD ["sh", "-c", "java -jar target/*.jar"]
