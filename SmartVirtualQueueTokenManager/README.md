
# Smart Virtual Queue & Token Manager

A complete Java-based GUI desktop application that manages virtual token queues for users and provides queue control features for admins by login with MySQL database connectivity.  

## Review 1 Overview:
Created as per of **Review 1**  for college project evaluation.

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
- JDK & IDE Setup 
  - Java 24, IntelliJ IDEA
- Project Structure Defined
  - Folders for model, dao, db, ui
- Database Schema Designed
  - Table users
- MySQL Table Created
  - Using phpMyAdmin
- JDBC Connectivity Done
  - DBConnection.java
- Model & DAO Classes
  - User.java & UserDAO.java
- UI Forms Built
  - RegisterForm.java & LoginForm.java
- UI Design Polished
  - Aligned, Aesthetic, and Clean Interface
- Role-Based Registration
  - Displays success message based on user role (Admin/User)

## How to Run

1. Open the project in IntelliJ IDEA.
2. Start XAMPP and run both Apache & MySQL.
3. Import smart_queue.sql in phpMyAdmin to create the database and table.
4. Run RegisterForm.java or LoginForm.java from the ui package to launch the application.

===============================================================================
## Review 2 Overview:
Created as per of **Review 2**  for college project evaluation.

## Project Structure

- SmartQueueProject/
    - db/
        - DBConnection.java – Handles database connectivity
    - dao/
        - UserDAO.java – Data access operations for users
        - TokenDAO.java
    - model/
        - User.java – User entity/model class
        - Token.java
    - ui/
        - RegisterForm.java – GUI for user registration
        - LoginForm.java – GUI for user login
        - UserDashboard.java
        - AdminDashboard.java
    - smart_queue.sql – SQL file to create database and table
    - README.md – Project documentation

## Database Info

**Database Name:** `smart_queue_system`
- **Tables:**
    - `users` – Stores registered users
    - `tokens` – Stores generated tokens
    - `counters` (optional) – Admin control data

```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100),
  role VARCHAR(20)
);

CREATE TABLE tokens (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  token_number INT, (set Default=null)
  status ENUM('waiting', 'served') DEFAULT 'waiting',
  timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

## Features Implemented (as per Review 2)
###  Core Feature Implementation (5 marks)
-  User registration with role (user/admin)
- Role-based login → Opens different dashboards
- Users can generate tokens, see queue
- Admin can view & serve next token
###  Error Handling & Robustness (5 marks)
-  Proper try-catch used in DAO methods
- All SQL operations handled with feedback
- GUI shows meaningful dialogs on failure
###  Component Integration (5 marks)
- Full working integration between:
  - RegisterForm → LoginForm → UserDashboard / AdminDashboard
  - UserDAO and TokenDAO with shared DB connection
- Modular code using MVC
###  Event Handling & Processing (5 marks)
- ActionListeners implemented for all buttons
- Serve Token updates queue in real-time
- Button click = proper backend processing
###  Data Validation (5 marks)
- Checks for empty fields
- Password and email validations
- Duplicate user prevention (using SQL UNIQUE + logic)
###  Code Quality & Innovation (3 marks)
- Follows MVC and DAO patterns
- Fully separated GUI, logic, and database layers
- Easy to maintain and expand
- Use of JComboBox, JOptionPane, and other Swing components

###  Documentation (2 marks)
- README file (this)
- SQL schema provided
- Clear GitHub structure with folder and code organization

## How to Run 
1. Start XAMPP → Start Apache and MySQL
2. Open browser → go to: http://localhost/phpmyadmin
3. Create database smart_queue_system and import smart_queue_schema.sql
4. Open the project in IntelliJ IDEA
5. Run RegisterForm.java to create user or admin
6. Then run LoginForm.java to log in and use system 

## Notes:

-  Make sure your MySQL password is set to empty or updated in DBConnection.java
-  Use root as user unless configured otherwise
-  Tested on IntelliJ IDEA on macOS with MySQL 8+
