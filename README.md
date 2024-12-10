# API para Gestión de Tópicos en ForoHub

## Descripción

Esta API permite gestionar los **tópicos** de un foro, proporcionando endpoints para registrar, listar, actualizar, eliminar y detallar los tópicos. Es parte del proyecto **ForoHub**, implementado con **Spring Boot** y diseñado para manejar validaciones y operaciones en un entorno de foro.

## Endpoints

### **1. Crear un Tópico**
- **Método:** `POST`
- **URL:** `/topicos`
- **Descripción:** Crea un nuevo tópico.
- **Requiere Autenticación:** ✅
- **Cuerpo de la solicitud:**
  ```json
  {
    "titulo": "Título del tópico",
    "mensaje": "Mensaje del tópico",
    "autor": 1,
    "curso": 1
  }
**Respuestas:**
- 201 Created: Tópico creado exitosamente
- 400 Bad Request: Datos inválidos o errores de validación

### **2. Listar Tópicos**
- **Método:** `GET`
- **URL:** `/topicos`
- **Descripción:** Lista los tópicos en formato paginado.
- **Requiere Autenticación:** ✅
- **Parametros Opcionales**
- - ***Page***: Número de la páginacion
- - ***Size***: Número de elementos por página (por defecto 8)
- **Respuestas**
- - **200 OK**: Devuelve los tópicos paginados.
  ```json
  {
    "content": [
    {
      "id": 1,
      "titulo": "Título del tópico",
      "mensaje": "Mensaje del tópico",
      "autor": 1,
      "curso": 1
    }
  ],
  "pageable": { ... },
  "totalPages": 1,
  "totalElements": 1
  }

### **3. Actualizar un Tópico**
- **Método:** `PUT`
- **URL:** `/topicos/{id}`
- **Descripción:** Actualiza un tópico existente.
- **Requiere Autenticación:** ✅
- **Cuerpo de la solicitud:**
  ```json
  {
    "titulo": "Nuevo título",
    "mensaje": "Nuevo mensaje",
    "autor": 1,
    "curso": 1
  }
**Respuestas:**
- ***200 OK:*** Tópico actualizado exitosamente
- ***400 Bad Request:*** Datos inválidos o errores de validación
- ***404 Not Found:*** Tópico no encontrado

### **4. Eliminar un Tópico**
- **Método:** `DELETE`
- **URL:** `/topicos/{id}`
- **Descripción:** Elimina un tópico por su ID.
- **Requiere Autenticación:** ✅
- **Respuestas:**
- - ***200 OK:*** Tópico actualizado exitosamente
- - ***404 Not Found:*** Tópico no encontrado

### **5. Detallar un Tópico**
- **Método:** `GET`
- **URL:** `/topicos/{id}`
- **Descripción:** Obtiene los detalles de un tópico por su ID.
- **Requiere Autenticación:** ✅
- **Respuestas:**
- ***200 OK:*** Tópico actualizado exitosamente
    ```json
  {
	"titulo": "Titulo topico",
	"mensaje": "Mensaje del topico",
	"fechaCreacion": "2024-12-06T22:13:15",
	"status": "ABIERTO",
	"autor": 1,
	"curso": 1
}
- ***404 Not Found:*** Tópico no encontrado

### **Validaciones**
***1. Usuario y Curso Existente:*** Los IDs de usuario y curso deben existir en sus respectivos repositorios.
***2.Tópico Único:*** No se permite registrar un tópico con el mismo título y mensaje.
***3.Existencia del Tópico:*** Los métodos de actualización, eliminación y detalle validan si el tópico existe.

## Seguridad

### **Protección de Rutas con JWT**
La API utiliza **JSON Web Tokens (JWT)** para proteger las rutas. Solo los usuarios autenticados pueden acceder a los endpoints protegidos.

#### **Autenticación**
- **Endpoint:** `POST /auth/login`
- **Descripción:** Genera un token JWT válido para acceder a los recursos protegidos.
- **Cuerpo de la solicitud:**
  ```json
  {
    "correoElectronico": "usuario",
    "contrasena": "contraseña"
  }
- **Respuesta:**
  ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR..."
    }

#### **Autenticación**
Incluye el token JWT en el encabezado de las solicitudes protegidas de la siguiente manera:
   ```makefile
   Authorization: Bearer <token>
   ```

### **Validaciones de Seguridad**
El token JWT es validado en cada solicitud protegida para garantizar que:
- Es auténtico.
- No está expirado.
- Está asociado con un usuario válido.

Rutas no autenticadas, como el endpoint de login, están explícitamente configuradas para no requerir validación de token.

## Estructura del Proyecto

- **Controller:** Maneja las solicitudes HTTP (`TopicoController`).
- **Service:** Encapsula la lógica de negocio (`ManejoDeTopicos`).
- **Repository:** Proporciona acceso a datos mediante Spring Data JPA (`TopicoRepository`, `UsuarioRepository`, `CursoRepository`).

## Tecnologías Utilizadas

- **Spring Boot**
- **Jakarta Validation**
- **Spring Data JPA**
- **Hibernate**
- **Java 17**  
