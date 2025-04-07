# Etapa 1: build da aplicação
FROM eclipse-temurin:17-jdk as builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# Etapa 2: runtime da aplicação
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar kanban.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "kanban.jar"]