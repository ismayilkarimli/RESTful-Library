# RESTful-Library

RESTful Spring Boot application simulating a Library API

## Prerequisites
- Docker
- Docker compose (docker-compose)

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
## How to use
Any software that can transmit GET and POST requests can be used (e.g. Postman, cURL etc.)  
## API options
### Authentication:
#### Registering:
- *Usage:*

```bash
curl \
-X POST \
-H "Content-Type: application/json" \
--data \
    '{
        "firstName": (String), 
        "lastName": (String), 
        "dob": (String, YYYY-MM-DD), 
        "email": (String), 
        "password": (String)
    }' \
http://localhost:8080/api/v0.1/auth/register
```
- *Example:*
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
#### Login:
- *Usage:*
```bash
curl \
-X POST \
--header "email: {email}" \
--header "password: {password}" \
http://localhost:8080/api/v0.1/auth/login
```
- *Example:*
```bash
curl \
-X POST \
--header "email: js@bvb.de" \
--header "password: jsancho" \
http://localhost:8080/api/v0.1/auth/login
```
### Library:
#### Get all books:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/
```
- *Example:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/
```
#### Get book by name:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/{name}
```
- *Example:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/Principles
```
#### Get books by category:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/categories/{category}
```
- *Example:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/categories/Sci-Fi
```
#### Get books by author:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/authors/{author}
```
- *Example:*
```bash
curl http://localhost:8080/api/v0.1/lib/authors/Ray%20Dalio
```
#### Get books by author and category:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/lib/authors/{author}?category={category}
```
- *Example:*
```bash
curl http://localhost:8080/api/v0.1/lib/authors/Tolkien\?category=Sci-Fi
```
### User:
#### Take a book:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/user/{id}/take/{bookName}
```
- *Example:*
```bash
curl -X GET http://localhost:8080/api/v0.1/user/1/take/Clean%20Code
```
#### Drop a book:
- *Usage:*
```bash
curl -X GET http://localhost:8080/api/v0.1/user/{id}/drop/{bookName}
```
- *Example:*
```bash
curl -X GET http://localhost:8080/api/v0.1/user/1/drop/Clean%20Code
```
#### Get current books of a user:
- *Usage:*
```bash
curl -X GET --header "token: {token}" http://localhost:8080/api/v0.1/user/books
```
- *Example:*
```bash
curl -X GET --header "token: EhWbohIvNFmIDUGABKdTuNNuzYqIuQhz" http://localhost:8080/api/v0.1/user/books
```
#### Get book history of all taken books for a user:
- *Usage:*
```bash
curl -X GET --header "token: {token}" http://localhost:8080/api/v0.1/user/history
```
- *Example:*
```bash
curl -X GET --header "token: EhWbohIvNFmIDUGABKdTuNNuzYqIuQhz" http://localhost:8080/api/v0.1/user/history
```
