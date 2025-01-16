#Baixando a imagem do OpenJDK 21
FROM openjdk:21

#Definindo a pasta de trabalho do container
WORKDIR /app

#Copiando todos os arquivos do projeto para esta pasta de trabalho
COPY . /app

#Comando para gerar o DEPLOY do projeto
RUN ./mvnw -B clean package -DskipTests

#Definir a porta em que projeto irá rodar
EXPOSE 8081

#Comando para executando o projeto
CMD ["java", "-jar", "target/productsApi-0.0.1-SNAPSHOT.jar"]