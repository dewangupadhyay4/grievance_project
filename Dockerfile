# Use Eclipse Temurin (official OpenJDK builds)
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy all files into the container
COPY . .

# Give mvnw executable permission (fixes "exit code: 126")
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose your app port
EXPOSE 9012

# Run the Spring Boot app
CMD ["java", "-jar", "target/your-app.jar"]
