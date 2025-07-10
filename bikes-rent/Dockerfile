# Use uma imagem Maven com JDK
FROM maven:3.8.5-openjdk-17 AS builder

# Copia os arquivos da aplicação 
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Cria imagem final com Tomcat
FROM tomcat:9.0-jdk17-temurin

# Remove as apps padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o WAR gerado
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Copia o arquivo .env para dentro do container na pasta /usr/local/tomcat
COPY --from=builder /app/.env /usr/local/tomcat/.env

# Expõe a porta do Tomcat
EXPOSE 8080

CMD ["catalina.sh", "run"]
