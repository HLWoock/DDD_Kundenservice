# Basis-Image
FROM adoptopenjdk:11-jdk-hotspot

# Arbeitsverzeichnis
WORKDIR /app

# Kopieren des JAR-Datei in das Arbeitsverzeichnis
COPY build/libs/Kundenservice-0.0.1-SNAPSHOT.jar app.jar

# Port, der von der Anwendung verwendet wird
EXPOSE 8081

# Startbefehl für die Anwendung
CMD ["java", "-jar", "app.jar"]
