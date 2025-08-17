# Etapa 1: Build com Gradle
FROM gradle:8.10.2-jdk23 AS builder
WORKDIR /app
# Copia os arquivos do projeto
COPY . .
# Gera o JAR do Spring Boot
RUN gradle bootJar --no-daemon



# Etapa 2: Imagem final só com o JAR
FROM eclipse-temurin:23-jre AS runtime
WORKDIR /app
# Copia o JAR gerado
COPY --from=builder /app/build/libs/*.jar app.jar
COPY .env .env
# Expõe a porta padrão do Spring Boot
EXPOSE 8080
# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]