
# Smart Virtual Queue & Token Manager

A GUI-based Java project to manage user registration and login with MySQL database connectivity.  
Created as part of **Review 1** submission for college project evaluation.

## Technologies Used

- **Language:** Java (Swing)
- **IDE:** IntelliJ IDEA (on macOS)
- **JDK:** Java 24 (Latest Version)
- **Database:** MySQL using XAMPP (phpMyAdmin)
- **Connectivity:** JDBC
- **Architecture:** MVC (Model-View-Controller)

## Project Structure

- SmartQueueProject/
    - db/
      - DBConnection.java – Handles database connectivity
    - dao/
      - UserDAO.java – Data access operations for users
    - model/
      - User.java – User entity/model class
    - ui/
      - RegisterForm.java – GUI for user registration
      - LoginForm.java – GUI for user login
    - smart_queue.sql – SQL file to create database and table
    - README.md – Project documentation


## Database Info

- **Database Name:** `smart_queue`
- **Table:** `users`

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100),
  role VARCHAR(20)
);
```

## Implemented Features (as per Review 1)
- JDK & IDE Setup – Java 24, IntelliJ IDEA
- Project Structure Defined – Folders for model, dao, db, ui
- Database Schema Designed – Table users
- MySQL Table Created – Using phpMyAdmin
- JDBC Connectivity Done – DBConnection.java
- Model & DAO Classes – User.java & UserDAO.java
- UI Forms Built – RegisterForm.java & LoginForm.java
- UI Design Polished – Aligned, Aesthetic, and Clean Interface
- Role-Based Registration – Displays success message based on user role (Admin/User)

## How to Run

    1. Open the project in IntelliJ IDEA.
    2. Start XAMPP and run both Apache & MySQL.
    3. Import smart_queue.sql in phpMyAdmin to create the database and table.
    4. Run RegisterForm.java or LoginForm.java from the ui package to launch the application.



