# Music Service Application

Full-Stack music database system developed for CSCE548.

## System Architecture

AWS RDS MySQL Database

^

DAO Layer

^

Business Layer (Business Manager)

^

Spring Boot REST API

^

Client (HTML + JavaScript)

## Features

- View all Artists, Albums and Songs
- Retrieve individual records
- Insert new records
- Update existing records

## Running the project

### 1. Clone the repository


### 2. Configure Databse Connection
Edit: src/main/resources/application.properties

Set the AWS RDS connection: 
spring.datasource.url=jdbc:mysql://musicdb-rds.cji0kuwiwjcj.us-east-2.rds.amazonaws.com:3306/musicdb
spring.datasource.username=admin
spring.datasource.password=YOUR_PASSWORD

### 3. Run the Backend
mvn spring-boot:run

Or run 'MusicServiceApplication.java' in Eclipse by right Clicking and doing Run as -> Java Application.

### 4. Open the client

http://localhost:8080/
Paste this into your browser to use the database.

## API Endpoints

### Artists
GET /api/artists

GET /api/artists/{id}

POST /api/artists

PUT /api/artists/{id}

### Albums
GET /api/albums

GET /api/albums/{id}

POST /api/albums

PUT /api/albums/{id}

### Songs
GET /api/songs

GET /api/songs/{id}

POST /api/songs

PUT /api/songs/{id}

## Database 

The Data Layer is hosted on **AWS RDS MySQL**.





