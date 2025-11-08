# Use Eclipse Temurin (official OpenJDK builds)
FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

EXPOSE 9012
CMD ["java", "-jar", "target/your-app.jar"]
