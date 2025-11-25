# Password Generator Web Application
### Java Web-Based Project (Servlets + JSP + JDBC + MySQL)

**Made by: Kirti Raj**  


---

### Project Title
**Secure Password Generator with User Authentication**

---

### Features
- User Registration & Login System
- Secure session management
- Strong random password generator with customizable options:
    - Choose password length (8–50)
    - Include uppercase letters
    - Include numbers
    - Include special characters
- Copy-to-clipboard functionality
- Responsive and modern UI
- MySQL database integration using JDBC
- Proper error handling and user feedback

---

### Technologies Used
- **Java Servlets** – Backend logic
- **JSP (JavaServer Pages)** – Dynamic views
- **JDBC** – Database connectivity
- **MySQL** – Database storage
- **HTML5, CSS3, JavaScript** – Frontend
- **Tomcat Server** – Web container

---

### Project Structure
PasswordGeneratorWeb/
├── src/
│   └── com.passwordgen/
│       ├── dao/          → UserDAO.java
│       ├── model/        → User.java
│       ├── servlet/      → All servlets (Login, Register, Generate, Logout)
│       └── util/         → DBConnection.java
├── web/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.jsp
│   └── WEB-INF/
│       └── web.xml
├── README.md          
└── (MySQL Connector JAR in classpath)


---

### Database Setup (MySQL)
Run these commands in MySQL:
```sql
CREATE DATABASE passwordgen_db;
USE passwordgen_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

---

### How to Run the Project

Install Apache Tomcat (v9 or v10)
Add MySQL Connector/J JAR to your project classpath
Create a Dynamic Web Project in Eclipse/IntelliJ
Copy all files into correct folders
Deploy and run on Tomcat server
Open browser → http://localhost:8080/PasswordGeneratorWeb/web/index.html
