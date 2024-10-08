# Flash-work

This application is designed for freelancers, where they can offer their services or hire others.

## About

This is a project developed for my final course work (TCC). The objective of this project is to enable users to offer their services. Any contribution is welcome!

## Structure
```

├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── rafaelaugustor
│       │           └── flash-work
│       │               ├── broker
│       │               │   ├── consumers
│       │               │   └── producers
│       │               ├── config
│       │               ├── domain
│       │               │   ├── entities
│       │               │   └── enums
│       │               ├── rest
│       │               │   ├── controllers 
│       │               │   └── dtos
│       │               │       ├── request
│       │               │       └── response
│       │               ├── repositories
│       │               ├── services
│       │               └── utils
│       └── resources
│           └── db
│               └── migration
│         
│         
├── target
├── mvnw
├── mvnw.cmd
└── pom.xml

```

The project architecture follows a typical layered structure, consisting of the following modules:
```
- `broker: Contains modules related to RabbitMQ, such as consumers and producers.`
    - `consumers: Contains consumers that receive messages from RabbitMQ.`
    - `producers: Contains producers that send messages to RabbitMQ.`

- `config: Contains application configurations, such as database and security settings.`

- `domain: Contains classes that represent the domain of your application, such as entities and enums.`
    - `entities: Contains classes that represent domain entities.`
    - `enums: Contains classes that represent enums used in the domain.`

- `rest: Contains your application's RESTful resources, such as controllers and DTOs.`
    - `controllers: Contains controllers responsible for handling HTTP requests and responses.`
    - `dtos: Contains DTOs (Data Transfer Objects) that represent the objects transferred between the client and the server.`
        - `request: Contains DTOs used to represent data received from the client.`
        - `response: Contains DTOs used to represent data sent to the client.`

- `repositories: Contains repository interfaces responsible for database interaction.`

- `services: Contains service classes that implement your application's business logic.`

- `utils: Contains utility classes that provide auxiliary functionality for the entire application.`

- `src/main/resources/db/migration: Contains database migration files used to version and maintain the database schema.`
```
Additionally, there are other files and directories, such as license files, environment variables, build files, and README. Overall, the project architecture is clear, with well-defined responsibilities for each module, making it easier to understand and maintain.

## Technologies Used

- **Spring Framework**
- **RabbitMQ**
- **PostgreSQL**
- **Redis**
- **H2**
