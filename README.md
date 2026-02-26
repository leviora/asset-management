# Asset Management System

A small IT asset management application built with Spring Boot.  
Tracks rooms, assets, and users, with a simple REST API.

## Technologies

- Java 17
- Spring Boot
- Maven

## Features

- Create, read, update, delete assets and rooms
- Search assets by manufacturer or type
- Validation and basic error handling

## How to Run

1. Clone the repository:

git clone https://github.com/leviora/asset-management.git

2. Enter the project folder:

cd asset-management

3. Build and run:

mvn spring-boot:run

## Example API Requests

- Get all asset models:

GET http://localhost:8080/api/asset-models

- Search asset models by manufacturer:

GET http://localhost:8080/api/asset-models/search?manufacturer=Lenovo