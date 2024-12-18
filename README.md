# Energy Management System (EMS)

The **Energy Management System (EMS)** is a microservices-based platform designed to monitor, manage, and visualize energy consumption data. It is built to enable real-time communication between devices, simulators, and the user interface using a modular architecture for scalability and performance.

## Key Components

- **User Management Microservice**  
  Handles user authorization and stores user data in the **User Database**.

- **Device Management Microservice**  
  Manages device configurations and maintains the **Device Database**.

- **Message Producer**  
  A simple console application that reads device data values from a **CSV file**. It simulates real-time device value readings and publishes the data to **RabbitMQ**, acting as the bridge for metering devices.

- **Monitoring and Communication Microservice**  
  Collects, processes, and stores metering data in the **Monitoring Database**, enabling real-time monitoring of devices.

- **Chat Microservice**  
  Facilitates **WebSocket**-based real-time communication for user interactions.

- **Device Simulators**  
  Simulate energy devices generating data, which are published via **RabbitMQ** for message brokering.

## Key Features

- **JWT Authorization**  
- **WebSockets**  
- **RabbitMQ**  
- **Docker Deployment**  
