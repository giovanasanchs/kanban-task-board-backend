FROM maven:3.9.2-eclipse-temurin-17 AS build

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/*.jar kanban.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kanban.jar"]
