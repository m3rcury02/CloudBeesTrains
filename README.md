
# CloudBees Train Assignment


## Features

- API where you can submit a purchase for a ticket. Details included in the receipt are:
    From, To, User, price paid.
- An API that shows the details of the receipt for the user
- An API that lets you view the users and seat they are allocated by the requested section
- An API to remove a user from the train
- An API to modify a user's seat

## Run Locally

Clone the project

```bash
  git clone https://github.com/m3rcury02/CloudBeesTrains
```

Go to the project directory

```bash
  cd trains
```

Install dependencies

```bash
  mvn clean install
```

Start the server

```bash
  java -jar target/trains-0.0.1-SNAPSHOT.jar 
```
Test the API:

http://localhost:8080/swagger-ui.html

(Port 8080 may vary)

## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## API Reference

#### Purchase

```http
  POST /purchase
```

#### Get receipt

```http
  GET /receipt/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userid`      | `string` | Id of user to fetch |

#### Get users by section

```http
  GET /users/{section}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `section`      | `string` | Section A or B |

#### Delete user

```http
  DELETE /users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userid`      | `string` | Id of user to delete |


#### Modify seat

```http
  PUT /users/{userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userid`      | `string` | Id of user to modify seat |


## Feedback

If you have any feedback, please let me know - gunal2002@gmail.com


## Tech Stack

Spring Boot, Swagger UI, Java
