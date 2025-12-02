# -------- STAGE 1: Build -------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# IMPORTANTE: Copiamos TODO el repositorio (Contexto raíz)
# Al copiar todo, Maven encuentra el pom.xml padre y los hijos en su lugar correcto.
COPY . .

# Compilamos SOLO este módulo y sus dependencias (-am)
# CAMBIA "ModuloAgregador" POR EL NOMBRE DE LA CARPETA DEL MODULO CORRESPONDIENTE
RUN mvn clean package -pl ModuloAVD -am -DskipTests

# -------- STAGE 2: Runtime -------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el JAR generado. Asegúrate que la ruta de origen coincida con el módulo.
COPY --from=build /app/ModuloAVD/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]