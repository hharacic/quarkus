# Quarkus Test Application #

The project consist of two application:
Backend written in Quarkus: `accountmanager`
Frontend written in Angular: `accountmanager-front`

To test and play with the application perform following steps:
1. For backend application, you need Java JDK 8 or 11+ Installed
2. For frontend application you need Node.js/npm and Angular/Angular CLI 10
3. Clone the repository: `git clone https://github.com/hharacic/quarkustest`
4. Go to `accountmanager` directory and run `mvn quarkus:dev`
5. Go to `accountmanager-front` directory and run `npm install` and then `ng serve`

If you want to execute Unit/Integration tests run `mvn verify`

If you wnat to test backend only part using Postman, than skip step 5. You can import Postman project: `Account Management API.postman_collection.json`

The application for test purpose uses H2 database, if you want ot use something different, just change configuration in the `accountmanager/src/resources/application.proporties`

If you want to add other dependencies/packages to the backend application, you can do it in the `accountmanager/pom.xml` or using Maven. For help, please visit `https://quarkus.io` 
