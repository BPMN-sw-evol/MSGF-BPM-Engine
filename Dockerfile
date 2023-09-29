# Establece la imagen base del contenedor como una imagen Alpine Linux con Java 17.
FROM khipu/openjdk17-alpine:latest

# Copia el archivo JAR de la aplicación desde el sistema local al directorio raíz del contenedor.
COPY /target/MSGF-BPM-Engine-1.0.0-SNAPSHOT.jar MSGF-BPM-Engine-1.0.0-SNAPSHOT.jar

# Expone el puerto 9000 en el contenedor. Esto permite conexiones entrantes en el puerto 9000.
EXPOSE 9000

# Configura el punto de entrada del contenedor para ejecutar la aplicación Java.
# Cuando el contenedor se inicia, se ejecutará este comando.
ENTRYPOINT ["java", "-jar", "MSGF-BPM-Engine-1.0.0-SNAPSHOT.jar"]
