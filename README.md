## 🚕 👮‍♀️ Smart Vehicle Parking Management System 🚓
### 🕵️ Slot Check Process 👀
![Loading... slot check image](https://5.imimg.com/data5/SELLER/Default/2025/7/530736372/QK/UZ/GV/7092090/parking-management-system-500x500.jpg)
# 🚗 Vehicle Parking Management System

A robust console-based (or GUI) application designed to automate the management of vehicle entries, exits, and slot allocations in a parking facility.

## 🚀 Features
* **Real-time Slot Tracking:** Monitor available and occupied spaces.
* **Automated Billing:** Calculates charges based on vehicle type and duration.
* **Data Persistence:** Integrated with MySQL to store logs and history.
* **Search Functionality:** Quickly find a vehicle using its registration number.
* **Role-based Access:** Secure login for Admin/Staff.

## 🛠️ Tech Stack
* **Language:** Java / C
* **Database:** MySQL
* **Concepts:** Data Structures (Linked Lists/Queues), JDBC (for Java), File Handling.

## 🏗️ System Architecture


## 📋 Database Schema
The system uses the following core tables:
1. `Vehicles`: Stores plate numbers, type, and owner info.
2. `Slots`: Tracks slot status (Available/Booked).
3. `Transactions`: Stores entry/exit timestamps and total fare.

## 🔧 Installation & Setup
1. **Clone the repository:**
   `git clone https://github.com/yourusername/vehicle-parking-system.git`
2. **Setup Database:** Import the `database.sql` file into your MySQL Workbench.
3. **Configure Connection:** Update the DB URL, username, and password in the source code.
4. **Compile & Run:** `javac Main.java` -> `java Main`

## 📸 Screenshots
*(Add images of your terminal or application interface here)*
