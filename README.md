# backups
Repository for project on Development of Information Systems - automatic backups of sites

*How to use the system:*

First, it is necessary to check the config.xml file to see if all the information such as the storage server, the locations we want to exclude... are correct.

Then, if we are using IntelliJ we just have to run the main function.

Otherwise, 
To compile:

go to the main folder of the project and run

mvn compile

to produce a jar-file run

mvn package

To run:

- one way to do it is to add all JAR files that are found in ~/.m2/repository
  to CLASSPATH environment variable, including current directory and then run
 
    java main
