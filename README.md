# AREP-Taller-2

This project is a simple HTTP server application that allows users to search for information about movies using the OMDb API. 
The server responds to requests with details about a specific movie based on the provided movie title.
The server is capable of handling multiple requests in a row (non-concurrent) and serves static files, including HTML pages, JavaScript files, CSS, and images. Additionally, the web application incorporates asynchronous communication with REST services in the backend to obtain movie information from the OMDB API.

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
git clone https://github.com/andreaduranvivas/AREP-Taller-2
```

2. Build the project using Maven:

```
mvn clean install
```
3. Now you can use your favorite IDE to review and run the project. But it can also be run from the terminal using the following command:

```
mvn exec:java
```
Once all the project dependencies have loaded, you can run the main method of the HttpServer class. The terminal will send a message indicating that it is ready to listen, and that way you will know that you can start testing the server. To do this, you must enter from a browser to the URL http://localhost:3500/formulario.html, where you will find the page that allows you to search for movies and will return the data of the movie entered if it exists.
To search, you can press the search button or press the Enter key.

The image below shows an example of what the top of the page should look like.

![Image Description](ss/inicio.png)

In the search bar, you can enter the name of the movie. If the title exists within the external OMBD Api database, then it will return a result similar to the following image.

![Image Description](ss/busqueda.png)

Otherwise, that is, when the image does not exist, then the following page will appear

![Image Description](ss/error.png)



## Architecture

This project implements a simple Client-Server architecture for a web server capable of handling multiple consecutive (non-concurrent) requests. The application allows asynchronous communication with REST services on the backend, specifically using the OMDB API to obtain movie information.

### Project Structure

The project is organized as follows:

**1. src/main/java/arep/taller:** Contains the HttpServer and NetworkWrapper classes, which form the basis of the web server.

**2. resources/public:** Stores static resources served by the server, such as HTML files, images, CSS style sheets, and JavaScript scripts.

#### Main components

- **HttpServer (Java):** The main class that configures a socket server and handles incoming requests. Uses the NetworkWrapper class for network manipulation.


- **NetworkWrapper (Java):** Interface that provides abstract methods for network manipulation, such as obtaining readers and output streams.


- **movieSearch.js (JavaScript):** Contains functions to interact with the OMDB API and dynamically update the user interface with movie information.


- **formulario.html:** A simple HTML page that includes a form to search for movies and view the results.


- **movieStyles.css:** CSS style sheet to improve the appearance and layout of the user interface.

### Use of Patterns and Modularity

**1. Client-Server Pattern:** The architecture follows the Client-Server model, where the server (HttpServer) manages the backend logic, while the client (movieSearch.js) handles the user interface and user interactions.

**2. Network Abstraction:** The NetworkWrapper interface provides an abstraction that facilitates testing and the ability to change the underlying network management implementation without affecting the rest of the system.


### Extensibility and Maintainability

**1. Separation of Responsibilities:** The separation between server and client allows each component to be developed, tested, and maintained independently.

**2. Modularity in Static Resources:** Static resources (HTML, CSS, images, JavaScript) are organized in the public folder, making it easy to expand and modify without affecting other parts of the system.


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

No more tests were added because this workshop is based more on the use of styles with css, functions with javascript and images. And to do this, the reading of the page content in the HttpServer class was modified. So to verify its correct functioning, it is more advisable to access the page and see the styles and images loaded, as well as the correct functioning of the use of REST services.



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
* [JavaScript](https://developer.mozilla.org/en-US/docs/Web/javascript) - Programming Language
* [CSS](https://www.w3.org/Style/CSS/Overview.en.html) - stylesheet Language
* [HTML](https://html.com/) - HyperText Markup Language
 


## Versioning

Version 1.0.0

## Authors

* **Andrea Durán Vivas**  [Usuario de GitHub](https://github.com/andreaduranvivas)

## License

MIT License 

## Acknowledgments