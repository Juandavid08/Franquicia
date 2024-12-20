from eclipse-temurin:23.0.1_11-jdk

workdir /root

# Copiar archivos necesarios para la construcción
copy ./pom.xml /root
copy ./.mvn /root/.mvn
copy ./mvnw /root


# Descargar dependencias en modo offline
run ./mvnw dependency:go-offline

# Compilar la aplicación
run ./mvnw clean install -DskipTests

# Exponer el puerto de la aplicación
expose 8080

# Comando para ejecutar la aplicación
entrypoint ["java", "-jar", "/root/target/demo-0.0.1-SNAPSHOT.jar"]
