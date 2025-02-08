# 🏥 Clinia - Smart Medical Scheduling
### *Your Health, Your Time.* ⏳

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.3-green?style=for-the-badge&logo=spring-boot)  
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)  
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-red?style=for-the-badge&logo=spring)  
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge&logo=mysql)  

---

## 📌 **What is Clinia?**
**Clinia** is a modern and intuitive **medical scheduling system** designed to simplify **appointment booking**, **patient management**, and **treatment planning** for doctors, clinics, and hospitals. It offers a seamless experience with **advanced security, cloud integration, and an easy-to-use interface**.

🚀 **Why Clinia?**
- ✅ **Smart scheduling** with automated reminders 📅
- ✅ **User-friendly interface** for doctors and patients 🩺
- ✅ **High security with JWT + Spring Security** 🔐
- ✅ **Cloud-based for multi-device access** ☁️
- ✅ **Optimized for time management** ⏳

---

## 📌 **Core Features**
✅ **Appointment scheduling system** with real-time availability  
✅ **Patient profile and history tracking**  
✅ **Customizable treatment and nutrition plans**  
✅ **Role-based access control (Doctors & Patients)**  
✅ **Secure authentication & authorization (JWT, Spring Security)**  
✅ **Multi-platform accessibility (Web & Mobile)**  

---

## 📌 **Technology Stack**
| Technology      | Usage |
|---------------|------------------------------------------------|
| **Spring Boot** | Main framework |
| **Spring Security + JWT** | Secure authentication & authorization |
| **Spring Data JPA** | Database access |
| **MySQL** | Database |
| **Lombok** | Reduces boilerplate code |
| **Swagger** | API documentation |
| **Docker**  | Containerized deployment |

---

## 🚀 **Installation & Setup**
### **1️⃣ Clone the Repository**
```bash
git clone [https://github.com/your-username/clinia.git](https://github.com/juansefdz/CLINIA-BACKEND.git)
cd CLINIA-BACKEND
```

### **2️⃣ Configure the Database**
Create a database in **MySQL** and update the `application.properties` file with your credentials.

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/clinia_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```


The API will be available at **http://localhost:8080/** 📡

---

## 📌 **Endpoints Overview**
### **Authentication**
| Method | Endpoint | Description |
|--------|--------------------|------------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | User login |

### **Patients**
| Method | Endpoint | Description |
|--------|--------------------|------------------|
| `GET` | `/api/patients` | List all patients |
| `POST` | `/api/patients` | Register a new patient |
| `GET` | `/api/patients/{id}` | Get patient by ID |
| `PUT` | `/api/patients/{id}` | Update patient details |
| `DELETE` | `/api/patients/{id}` | Delete a patient |

### **Appointments**
| Method | Endpoint | Description |
|--------|--------------------|------------------|
| `GET` | `/api/appointments` | List all appointments |
| `POST` | `/api/appointments` | Schedule an appointment |
| `PUT` | `/api/appointments/{id}` | Modify appointment details |
| `DELETE` | `/api/appointments/{id}` | Cancel an appointment |

🔹 **For full API documentation, visit:**  
📌 `http://localhost:8080/swagger-ui.html`

---

## 🔐 **Security with JWT**
Clinia uses **Spring Security with JWT** for authentication.  
🔹 **Authentication Flow:**
1. User **registers** (`/api/auth/register`).
2. User logs in at **`/api/auth/login`** and receives a **JWT token**.
3. JWT must be included in every request:
   ```http
   Authorization: Bearer <JWT_TOKEN>
   ```

---

## 🐳 **Run with Docker**
If you want to use Docker, execute:
```bash
docker-compose up -d
```
📌 This will create a MySQL container and run the application.

---

## 📌 **Author**
🏢 **Company** KiwiDevsoft
👨‍💻 **Developer:** 
*[juansefdz](https://github.com/juansefdz)*  
*[JhonatanTH7](https://github.com/JhonatanTH7)*  
📧 **Contact:** 

---

## 📜 **License**
### **⚠️ Proprietary Software – Usage Restrictions**
> 🔴 **This software is for exclusive use by its owner and may NOT be distributed, copied, or used commercially without explicit authorization.**  
> 🔒 **Unauthorized use may result in legal action.**

📌 For custom licensing or usage permissions, contact the developer.

---

## 📌 **Contributions**
🚫 **External contributions, forks, and unauthorized distributions are NOT permitted.**
---

