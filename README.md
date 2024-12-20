# Franquicia

Este proyecto implementa una API de gestión para una franquicia utilizando Spring Boot y Docker. En este archivo se detalla el paso a paso para ejecutar el proyecto en un entorno local o contenerizado.

# Requisitos previos

Asegúrate de tener instaladas las siguientes herramientas:

Java 23
Maven
Docker

#Configuración y Ejecución

# 1. Clonar el repositorio
   
https://github.com/Juandavid08/Franquicia



# 2. Preparar proyecto

en la ruta \Franquicia\demo\src\main\resources estará el archivo application.properties, en este archivo está la ruta del almacenamiento en la nube, para este proyecto se utilizó mongodb.


# 3. Ejecutar la aplicacion

cd mvn clean install

mvn spring-boot:run

Con estos comando se ejecutará localmente desde el puerto 8080.


# NOTA: He dejado una coleccion de Endpoints que seran util para saber como utilizar cada uno, se puede importar en Postman o en Bruno.

# ejemplo de como se almacena en mongoDB
![image](https://github.com/user-attachments/assets/1590b9f7-f867-4e5d-8390-e72ca50d232a)



