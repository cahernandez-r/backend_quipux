# Prerrequisitos
Asegúrate de tener los siguientes programas instalados en tu máquina:
 * Git instalado en tu sistema
 * Docker instalado y en ejecución
 * Java JDK 17 o superior (opcional, solo si deseas ejecutar la aplicación sin Docker)
 * Maven instalado (opcional, solo si deseas ejecutar la aplicación sin Docker)
### Docker
Si no tienes Docker, puedes instalarlo siguiendo las instrucciones oficiales en docker.com.

### Git
Si aún no tienes Git instalado, puedes obtenerlo desde git-scm.com.

# Pasos para ejecutar la aplicación con Docker

## 1. Clonar el repositorio
Primero, clona el repositorio en tu máquina local:

#### git clone <URL_DEL_REPOSITORIO>

Navega al directorio del proyecto:

#### cd <nombre-del-proyecto>

## 2. Construir la imagen Docker
El proyecto ya incluye un archivo Dockerfile configurado para construir y ejecutar la aplicación. Para crear la imagen Docker, ejecuta el siguiente comando dentro del directorio del proyecto:

#### docker build -t spring-app .

## 3. Ejecutar el contenedor
Una vez creada la imagen, puedes ejecutar la aplicación con el siguiente comando:

#### docker run -p 7001:7001 spring-app, En este caso se debe conservar ese puerto ya que es el que esta configurado en Front
