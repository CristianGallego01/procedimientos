# Usar una imagen de Java 21
FROM eclipse-temurin:21-jdk

# Crear directorio dentro del contenedor
WORKDIR /app

# Copiar el .jar generado a la imagen
COPY target/procedimientos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
