FROM amazoncorretto:21-alpine

# Copia el archivo JAR de la aplicación al contenedor
COPY target/payment-screenshot-processor-api-0.0.1-SNAPSHOT.jar /app/payment-screenshot-processor-api.jar

# Establece el directorio de trabajo en /app
WORKDIR /app

# Ejecuta la aplicación al iniciar el contenedor
CMD ["java", "-jar", "payment-screenshot-processor-api.jar"]