# RESTful-Library

RESTful Spring Boot application simulating a Library API

## Prerequisites
* Docker
* Docker compose (docker-compose)

## Installation
```bash
git clone https://github.com/ismayilkarimli/RESTful-Library.git
```

## Starting

```bash
cd RESTful-Library/
docker-compose up -d
```

## Shutting down
```bash
docker-compose down
```

## Example Usages
Registering:
```bash
curl \
-X POST \
-H "Content-Type: application/json" \
--data \
    '{
        "firstName": "Jadon", 
        "lastName": "Sancho", 
        "dob": "1999-09-09", 
        "email": "je@bvb.de", 
        "password": "jsancho"
    }' \
http://localhost:8080/api/v0.1/auth/register
```
Login:
```bash
curl \
-X POST \
--header "email: js@bvb.de" \
--header "password: jsancho" \
http://localhost:8080/api/v0.1/auth/login
```
