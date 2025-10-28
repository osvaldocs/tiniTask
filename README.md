# 📝 TinyTasks — Proyecto Riwi

Aplicación sencilla de lista de tareas desarrollada como práctica para el módulo de **Spring Boot** en Riwi.  
El objetivo del proyecto es integrar un backend en **Java + Spring Boot** con un frontend en **HTML, JavaScript y Bootstrap**, utilizando peticiones **Fetch API** para la comunicación entre ambos.

---

## 🚀 Tecnologías utilizadas

**Backend**
- Java 17  
- Spring Boot 3  
- Maven  
- Spring Web  
- Spring Boot DevTools  

**Frontend**
- HTML5  
- JavaScript (Fetch API)  
- Bootstrap 5 (CDN)

---

## ⚙️ Funcionalidades principales

- Crear nuevas tareas ✅  
- Listar todas las tareas 📋  
- Marcar una tarea como completada o pendiente 🔄  
- Eliminar tareas 🗑️  

---

## 📁 Estructura del proyecto

SpringBoot_Riwi/
│
├── backend/
│   ├── src/main/java/com/example/demo/
│   │   ├── controller/
│   │   │   └── TodoController.java
│   │   ├── model/
│   │   │   └── Todo.java
│   │   ├── service/
│   │   │   └── TodoService.java
│   │   └── DemoApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/
    ├── index.html
    └── app.js

---

## 🧩 Endpoints principales

| Método | Endpoint             | Descripción                      |
|---------|----------------------|----------------------------------|
| GET     | `/api/todos`         | Obtiene todas las tareas         |
| POST    | `/api/todos`         | Crea una nueva tarea             |
| PUT     | `/api/todos/{id}/toggle` | Cambia el estado de una tarea  |
| DELETE  | `/api/todos/{id}`    | Elimina una tarea por su ID      |

---

## 💻 Cómo ejecutar el proyecto

### 1️⃣ Backend
1. Abrí el proyecto en IntelliJ IDEA.  
2. Ejecutá el comando:  
   ```
   mvn spring-boot:run
   ```
3. El backend quedará disponible en:  
   👉 `http://localhost:8080/api/todos`

### 2️⃣ Frontend
1. Abrí una terminal en la carpeta `/frontend`.  
2. Iniciá un servidor local:  
   ```
   python3 -m http.server 5500
   ```
3. Abrí tu navegador en:  
   👉 `http://localhost:5500`

---
