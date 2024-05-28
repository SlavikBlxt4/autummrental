# Autumn Car Rental

Welcome to the Autumn Car Rental, a website where users can search, rent, and manage car rentals, and employees can manage the inventory and bookings. This README provides an overview of the project, including its implementation, main features, installation instructions, and usage guidelines.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Backend Implementation](#backend-implementation)
3. [Frontend Implementation](#frontend-implementation)
4. [Key Features](#key-features)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Environment Variables](#environment-variables)
8. [License](#license)

## Project Overview

The Car Rental Platform offers a user-friendly interface for users to search for available cars, make bookings, and manage their reservations. Additionally, employees have access to features for managing the inventory and bookings.

## Backend Implementation

The backend of the Autumn Car Rental is developed using Java, following a hexagonal architecture with layers for DAO, Service, Action, and Controller. PostgreSQL is used as the database for storing user and booking data. Passwords are encrypted using bcrypt for enhanced security.

## Frontend Implementation

The frontend of the Autumn Car Rental is built using HTML, CSS, and JavaScript, following an organized architecture of Component, Repository, Service, and Main JS to ensure a structured and modular codebase.

## Key Features

- **User Registration and Login:** Users can register and log in to access booking functionalities securely.
- **Car Search:** Users can search for available cars based on their desired dates.
- **Bookings:** Users can make bookings for cars for specific dates.
- **Booking History:** Users can view their previous booking history.
- **Employee Management:** Employees can manage the inventory and bookings from a dedicated area.

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/SlavikBlxt4/autummrental.git
    cd autummrental
    ```

2. **Set up the backend**:
    - Configure the PostgreSQL database and update the connection settings in the backend configuration files.
    - Use Maven to install dependencies and start the backend server.

3. **Set up the frontend**:
    - Ensure you have a web server configured (e.g., Apache or Nginx) to serve the frontend files.
    - Start the web server and open the page in your browser.

## Usage

Once the application is set up and running, users can access it in their browsers. They can register, log in, search for cars, make bookings, and manage their reservations. Employees can access additional functionalities for managing the inventory and bookings.

## Environment Variables

Ensure that the necessary environment variables are configured for the backend, such as database connection settings and encryption keys for bcrypt.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Thank you for choosing the Autumn Car Rental! We hope you find it useful for managing your car rental business. If you have any questions or encounter any issues, feel free to reach out to us.

Happy renting! 🚗
