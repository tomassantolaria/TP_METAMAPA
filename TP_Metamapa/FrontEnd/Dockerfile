# ---------- STAGE 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build

# 1. INSTALACIÓN DE NODE.JS V20 (ACTUALIZADO)
# Instalamos curl, obtenemos el setup de NodeSource y luego instalamos Node
RUN apt-get update && apt-get install -y curl \
    && curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

WORKDIR /app

# Copiamos todo el repositorio (Contexto Raíz)
COPY . .

# 2. COMPILACIÓN DE ESTILOS (TAILWIND)
WORKDIR /app/FrontEnd
RUN npm install

# ¡ATENCIÓN AQUÍ!
# Cambié "npm run build" por "npm run build:css".
# Si tu script en package.json tiene otro nombre, cámbialo aquí.
RUN npm run build:css

# 3. EMPAQUETADO DEL JAR (JAVA)
WORKDIR /app
RUN mvn clean package -pl FrontEnd -am -DskipTests

# ---------- STAGE 2: Runtime ----------
FROM eclipse-temurin:17-jre

RUN apt-get update && apt-get install -y curl

WORKDIR /app

# Copiamos el JAR generado
COPY --from=build /app/FrontEnd/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]