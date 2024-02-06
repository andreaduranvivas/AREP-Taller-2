# AREP-Taller-2

This project is a simple HTTP server application that allows users to search for information about movies using the OMDb API. The server responds to requests with details about a specific movie based on the provided movie title.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run this project, you need to have the following installed:

- Java
- Maven
- Git

### Installing

Follow these steps to get a development environment running:

1. Clone the repository to your local machine:

```
git clone https://github.com/andreaduranvivas/AREP-Taller1/tree/master
```

2. Navigate to the project directory:

```
cd arep-taller1
```

3. Build the project using Maven:

```
mvn clean install
```
4. Now you can use your favorite IDE to review and run the project. But it can also be run from the terminal using the following command:

```
mvn exec:java
```
Once all the project dependencies have loaded, you can run the main method of the HttpServer class. The terminal will send a message indicating that it is ready to listen, and that way you will know that you can start testing the server. To do this, you must enter from a browser to the URL http://localhost:3500/movie, where you will find the page that allows you to search for movies and will return the data of the movie entered if it exists.
To search, you can press the search button or press the Enter key.


## Running the tests

To run the automated tests for this system, use the following Maven command:

```
mvn test
```


### Break down into end to end tests

The project includes tests to ensure the correctness and reliability of its components. Follow the instructions below to run the tests.

#### HttpServer Tests

The HttpServer class is responsible for handling HTTP requests and interacting with the OMDb API. To run the tests for this class, execute the following command:

```
mvn test -Dtest=HttpServerTest
```

This command will run the unit tests for the HttpServer class. These tests validate the functionality of processing HTTP requests, extracting movie titles from URIs, and generating HTTP responses.

#### MovieApi Tests

The MovieApi class is responsible for interacting with the OMDb API to fetch information about movies. Run the tests for the MovieApi class with the following command:
```
mvn test -Dtest=MovieApiTest
```
These tests validate the correctness of making API requests, handling API responses, and caching movie information.


#### MovieCache Tests

The MovieCache class is responsible for caching movie information to improve performance.
These tests cover adding and retrieving movie information from the cache, ensuring that the cache works as expected.


## Design Overview

The project consists of a simple HTTP server (HttpServer), a movie information API client (MovieApi), and a movie information cache (MovieCache). The server responds to HTTP requests related to movie information by interacting with the OMDb API through the MovieApi and caching the results using MovieCache.

###  Extensibility

The design is extensible as it follows the principles of modularization and separation of concerns. Each component (HttpServer, MovieApi, and MovieCache) has a specific responsibility, making it easy to extend or modify one component without affecting the others.

###  Use of Patterns

The project employs the following design patterns:

**1. Singleton Pattern:** The MovieCache class is implemented as a singleton, ensuring that only one instance of the cache exists throughout the application. This pattern is useful for maintaining a single cache shared among different parts of the application.

**2. Strategy Pattern:** The separation of the HttpServer and MovieApi components allows for flexibility in choosing or extending the movie information source. By implementing a different movie information provider and conforming to a common interface, the system can easily switch or include additional providers.

**3. Three Layer Design Pattern:** The project architecture follows a three-layer design pattern, providing a clear separation of concerns and promoting modularity. The layers are:

- **HTTP Server Layer (HttpServer):**
  - Responsible for handling incoming HTTP requests.
  - Communicates with the Movie API layer to retrieve movie information.
  - Does not directly interact with the Movie Cache layer.

- **Movie API Layer (MovieApi):**
  - Acts as an intermediary between the HTTP Server layer and the Movie Cache layer.
  - Communicates with the Movie Cache to retrieve cached movie information.
  - Communicates with external APIs (e.g., OMDb API) to fetch movie data if not found in the cache.

- **Movie Cache Layer (MovieCache):**
  - Manages the caching of movie information to improve performance.
  - Acts as a data store for frequently requested movie details.
  - Provides a centralized cache for both the HTTP Server and Movie API layers.

###  Modularity

The code is organized into distinct modules, each responsible for a specific aspect of the application:

- **HttpServer:** Handles incoming HTTP requests and dispatches them based on the URI.
- **MovieApi:** Interacts with the OMDb API to fetch movie information.
- **MovieCache:** Caches movie information to improve performance.

###  Organization

The classes and methods are organized logically, providing clarity on their purpose and usage. The project follows a layered architecture with clear separation of concerns, enhancing readability and maintainability.

## Extending the System

### Adding a New Movie Information Provider

To add a new movie information provider, follow these steps:

1. Modify or extend the MovieApi class by changing the URL as required by the new service provider
2. Change the graphical interface by extending the HttpServer class and overriding the httpMovie method. In this way, the HTML page will be changed, adapting it to the new information providers.

### Enhancing the Cache

To enhance the cache, consider the following:

1. Improve cache eviction policies for better memory management.
2. Implement cache expiration mechanisms to refresh information after a certain period.
3. Extend the MovieCache class to support different storage backends (e.g., database, distributed cache) for scalability.

## Documentation

The project includes Javadoc documentation to provide detailed information about the classes and methods. To generate the Javadoc documentation, execute the following command:

```
mvn javadoc:javadoc
```
After running this command, you can find the generated Javadoc in the target/site/apidocs/ directory. Open the index.html file in a web browser to browse the documentation.


## Built With

* [OMDb API](https://www.omdbapi.com/) - RESTful Web Service to Obtain Movie Information
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java](https://www.java.com/es/) - Programming Language


## Versioning

Version 1.0.0

## Authors

* **Andrea Durán Vivas**  [Usuario de GitHub](https://github.com/andreaduranvivas)

## License

Open Source License