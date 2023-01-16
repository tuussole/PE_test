# PE_test

1.Java version - 1.8 (Pls note there is a problem with Java 9 due to some modules are not resolved by default)
https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-F640FA9D-FB66-4D85-AD2B-D931174C09A3

2.Gradle > 4.3

3.MySql > 5.5
Port = 3306
username = root
password = root

To run application:
  1. Set up MySQL
    - Check application.properties port, username and password
    - create database(DB dump PE_test/src/main/resources/)
  2. start application using bootrun
  3. Enter URL http://localhost:8080/clients
  

