#  MSGF-BPM-ENGINE

This repository integrates the Camunda Engine with a Spring Boot application using a PostgreSQL database to persist the structure and resources.


## Index

1. [Description](#description)
2. [Prerequisites](#prerequisites)
3. [Usage](#usage)


## Description

[Camunda Automation Platform 7 Initializr](https://start.camunda.com/) is a tool that facilitates the creation of Spring Boot projects that integrate the Camunda engine, an open source business process engine. Spring Boot is a development framework that simplifies the creation of Java applications, and the Camunda engine enables the execution and management of workflows and business processes. This tool preconfigures the project structure and necessary dependencies, allowing developers to start working on business process automation applications efficiently and quickly.


## Prerequisites

To use this program you need the following:

1. **Version control system**: Install GIT from the [GIT official website](https://git-scm.com/downloads).

2. **IntelliJ IDEA**: To run and/or modify the project, you can download it from the [IntelliJ official website](https://www.jetbrains.com/es-es/idea/download/?section=windows).

3. **Java 17 or higher**: You can get help to download and install the java version by following [this link](https://www.youtube.com/watch?v=oAin-q1oTDw&pp=ygUXY29tbyBjb25maWd1cmFyIGphdmEgMTc%3D)

4. **Maven 3.9**: You can get help to download and install the maven version by following [this link](https://www.youtube.com/watch?v=1QfiyR_PWxU&pp=ygUSaW5zdGFsYXIgbWF2ZW4gMy45)    

5. Docker Desktop: To use Docker correctly follow the steps below:

 - Step 1: Install Docker from the official website.(If you already have Docker Desktop installed, skip this step).
 - Step 2: Create a Docker Hub account from the official website, and log in to Docker Desktop. (If you already have an account, log in directly to Docker Desktop)
WSL2: According to your need:

6. Use the following command to install WSL2 wsl --list --o to know the available distributions. wsl --install -d "distribution-version" to install WSL2 with a specific distribution.
Use the following command for upgrade to WSL2 wsl --version if you want to know the installed WSL version wsl --set-default-version 2 to change the version to WSL2.
## Usage

There are two ways to use this program, in your local machine:

1. Open a terminal in the folder where you want to download the program and clone it with:

   ```
   git clone https://github.com/BPMN-sw-evol/MSGF-BPM-Engine.git
   ```
2. Create a .env file with the database connection info:
   ```
   DB_USERNAME=postgres
   DB_PASSWORD=admin
   ```
2. Open the **MSGF-BPM-Engine** folder and find the **Application** file containing the main method
3. If you want to use it locally run the program with the start option (upper green triangle).
4. If you want to use it with Docker, execute the following command in the root folder of the project:
   ```
   docker-compose up
   ```
5. The program works on port 9000. To access open the browser of your choice and type in the search box: 
   ```
   http://localhost:9000/
   ```
6. In the Camunda login, use as **username** and **password** "demo".

7. Ready! You can use the Camunda Engine.
