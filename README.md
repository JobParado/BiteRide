# BiteRide: Eat While You Ride  
**Java Desktop App | NetBeans + MySQL **

## 📘 Project Overview

**BiteRide** is a desktop application built in Java using Apache NetBeans IDE 25. I was the one developed it as a collaborative school project for research based on our classmate project leader idea:
a hybrid service that lets users book transportation and order food simultaneously—so meals arrive while they’re on the ride.

This project demonstrates:
- GUI development with Java Swing  
- Backend integration using MySQL (via XAMPP)  
- Multi-role workflows (Customer, Driver, Merchant)  
- Real-time booking and food order management  
- Configuration management using `.env` and `dotenv-java`  
- JDBC connectivity using MySQL Connector

---

## 🛠 Technologies Used

| Component            | Stack/Tool                  |
|----------------------|-----------------------------|
| IDE                  | Apache NetBeans IDE 25      |
| Language             | Java 24 (JDK 24.0.1)         |
| GUI Framework        | Java Swing                  |
| Database             | MySQL (via XAMPP)           |
| Configuration        | `.env` + `dotenv-java.jar`  |
| JDBC Driver          | `mysql-connector-java.jar`  |
| Runtime              | Java SE Runtime Environment |
| OS Compatibility     | Windows 11 (amd64)          |

---

## 🧩 Core Features

### User Roles
- **Customer**: Create account, book rides, order food, confirm booking  
- **Driver**: View and accept transportation + food delivery jobs  
- **Merchant**: Manage food orders, accept bookings  

> 🧠 **Role-Based Homepage Logic**  
When a user applies as a Driver or Merchant, their application is stored in the database. Once approved by the database manager, their login will redirect them to their respective homepage instead of the default user homepage.

### Functional Modules
- Multi-step registration forms for drivers and merchants  
- Food ordering interface with variant selection and dynamic pricing  
- Transportation setup with pickup/dropoff and vehicle type  
- Booking confirmation with cost breakdown and payment method  
- Transaction dashboards for drivers and merchants  
- Settings page for account updates and feedback


---
## 🚀 Getting Started

clone the project on your desired folder location cd

```git clone https://github.com/JobParado/BiteRide```

## 🗃 Database Setup

Navigate to folder: Bite Ride\dist\Database xampp netbeans (Setup)

and either use executable commands of mysql database
1 table at a time

Or use it on netbeans recreate table files are provided on the folder 

## ⚙️ Configuration with `.env`

This project uses a `.env` file to store internal configuration values such as admin credentials and database connection strings.

### Example `.env` file:
```
Admin_Name=root
Admin_Password=
Database_URL=jdbc:mysql://localhost:3306/biteride?zeroDateTimeBehavior=CONVERT_TO_NULL
```
Edit the following file (turn this into ( .env) .TXT) based on your credentials and save it as .env


## 🚀 Running the App

1. Open the BiteRide.jar make sure u have jdk libs are already included
2. And the app should be working as usual



---

## 📚 Learning Outcomes

This project helped us explore:
- GUI design and layout structuring in Java  
- Connecting NetBeans to MySQL using XAMPP  
- Handling user input and form validation  
- Managing multi-role workflows  
- Using `.env` for secure configuration management  
- Structuring readable, maintainable Java code  

---
## 🖼️ Images of Application

| Section | Suggested Screenshot |
|--------|-----------------------|
| Account Creation | <img src="Bite Ride/img/create account.png" width="300"/> |
| Login Page | <img src="Bite Ride/img/login.png"  page" width="300"/> |
| Driver Registration | <img src="Bite Ride/img/driver page 1.png" width="300"/> <img src="Bite Ride/img/driver page 2.png"  width="300"/> <img src="Bite Ride/img/driver page 3.png" width="300"/> |
| Merchant Registration | <img src="Bite Ride/img/merchant page 1.png" width="300"/> |
| Home Page | <img src="Bite Ride/img/user homepage.png"  width="300"/> |
| Booking Setup | <img src="Bite Ride/img/user package part 1.png"  width="300"/> |
| Food Ordering | <img src="Bite Ride/img/user package part 2.png"  width="300"/> |
| Driver Dashboard | <img src="Bite Ride/img/driver home page.png"  width="300"/> |
| Merchant Dashboard | <img src="Bite Ride/img/merchant home page.png"  width="300"/> |
| Transaction Page | <img src="Bite Ride/img/user transaction.png"  width="300"/> |
| Settings Page | <img src="Bite Ride/img/settings.png"  width="300"/> <img src="Bite Ride/img/setting update.png"  width="300"/> <img src="Bite Ride/img/settings apply.png"  width="300"/> |
| `.env` Setup | <img src="Bite Ride/img/env setup.png" width="300"/> |

---
