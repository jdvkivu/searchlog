# SearchLog
Show and search your logfiles. If you have (application) logfiles on your server and find yourself regularly grepping and viewing them to debug or otherwise get information you may find this tool helpful.

## Features
SearchLog is a small Java web application that will take a logfile path and show the contents in your browser. It is heavily based on the idea of a very nice looking tool [LogHappens](https://loghappens.com/).

* Set the logfile by adding the query parameter `logfile=/var/log/myapplication.log`.
* Using pagination SearchLog will not hog down your browser when handling large files.
* It has a search box but easiest is to `Alt-click` a word in the log contents to quickly search for that word. This could be a sessionid for example or some other distinct word.
* All features are available as query parameters.
* Handles any text based logfile format as it does not try to parse anything.

## Query parameters

parameter | description
----------|------------
**logfile** | Path to the logfile to be read, e.g. `/var/log/application.log`. Make sure the user under which SearchLog application runs has rights to read the file.
**search** | The string to search for. Searches are case insensitive.
**size** | Page size, how many lines are displayed on one page, defaults to 10.000 lines per page.
**page** | What page to display, defaults to 1


## How to build, install and run SearchLog
SearchLog is built in Java using Spring Boot with Thymeleaf. You will need Java 8 and Maven to build and run SearchLog. 

1. `git clone git@github.com:jdvkivu/searchlog.git`
1. `cd searchlog`
1. `mvn spring-boot:run`
1. open SearchLog in your browser <http://localhost:18080>

To generate an executable jar run `mvn package`. You can then copy the jar from the `target` directory to your server (also with Java) and run it there using `java -jar searchlog.jar`.

The default port it will run the web server on is `18080`. This can be changed in the `application.porperties` by changing the value of the `server.port` property.

## Run as service
See the Spring Boot documentation <https://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html> on how to install SearchLog as a service on your server.

## License
This project is licensed under MIT License see [license](LICENSE)
