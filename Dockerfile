# Dockerfile.dev
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Instala o Gradle e constrói o projeto
RUN ./gradlew clean build -x test

# Imagem final
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copia o JAR gerado para o contêiner
COPY --from=builder /app/build/libs/expense-api-*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]