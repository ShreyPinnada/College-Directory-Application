# College Directory Management System

## Project Description

This project is a full-stack web application designed to help colleges manage and organize their student, faculty, and administrator data efficiently.

### What the Application Does

- Provides a platform for students to access personal and academic information, search for peers, and contact faculty advisors
- Enables faculty members to manage class lists and update their profiles
- Allows administrators to handle student and faculty records and view analytics through dashboards

### Technologies Used

- **Spring Boot**: For a robust, scalable, and secure backend, enabling RESTful API development
- **PostgreSQL**: A reliable relational database system to store structured data securely
- **HTML, CSS, JavaScript**: Simple and effective technologies for creating an intuitive frontend user interface
- **Lombok**: To reduce boilerplate code in the backend, improving development efficiency
- **JPA**: For seamless interaction with the database, reducing manual query writing

### Challenges Faced

- Developing and testing the backend to handle edge cases effectively
- Ensuring the system meets the needs of three distinct user roles

### Future Features

- Attendance tracking system
- Marks management and analytics for students
- Leave management system
- Assignment sharing and submission functionality

## Installation and Setup

### Prerequisites

- Java (JDK 11 or above)
- PostgreSQL and pgAdmin
- IDE (IntelliJ IDEA or Eclipse)

### Installation Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/college-directory-management.git
   cd college-directory-management
   ```

2. **Set Up the Backend**

   - Open the project in your IDE
   - Navigate to `Springboot-first-app/src/main/java/com/College_directory/Springboot_first_app`
   - Ensure `pom.xml` is properly loaded to resolve dependencies
   - Update the database configuration in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/college_directory
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Set Up the Database**

   - Open pgAdmin and create a database named `college_directory`
   - Use the SQL schema from the project document to create tables and relationships
   - Insert sample data for initial testing

4. **Run the Application**
   - Execute the main method in `Springboot_first_app` to start the backend server
   - Open the `login.html` file in any web browser to access the application

## Usage Guide

### Login Page

- Select your role (Student/Faculty/Administrator)
- Enter your username and password to log in

### Students Can

- View personal and academic details (courses, grades, attendance)
- Search for other students using filters
- Contact faculty advisors directly

### Faculty Members Can

- Manage student lists in their classes
- Update their profiles with office hours and contact information

### Administrators Can

- Add, update, or delete student and faculty records
- View dashboards with graphical data for better decision-making

## How to Contribute

We welcome contributions to improve this project!

1. **Fork the Repository**

   - Click the "Fork" button on the top-right of this repository

2. **Clone Your Fork**

   ```bash
   git clone https://github.com/your-username/college-directory-management.git
   ```

3. **Make Changes**

   - Improve JavaScript modularity or enhance the UI
   - Fix bugs or add features like attendance tracking or leave management

4. **Test Your Changes**

   - Ensure the changes don't break existing functionality

5. **Submit a Pull Request**
   - Push your changes to your forked repository
   - Open a pull request on the original repository

## Wrap Up

The College Directory Management System is a step toward simplifying how colleges manage their data. While the project addresses several key requirements, there is room for improvement and expansion. Contributions are welcome to make this system even better and more useful for educational institutions.

## License

This project is licensed under the MIT License. Feel free to use, modify, and distribute it.

![Intellij Ultimate](https://www.jetbrains.com/idea/download/?section=windows)
![pgAdmin](https://www.pgadmin.org/)
![VsCode](https://code.visualstudio.com/)
