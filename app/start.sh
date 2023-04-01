#bin/bash

/apache-maven-3.9.1/bin/mvn -v

/apache-maven-3.9.1/bin/mvn clean package

java -jar /app/target/AuthService-0.0.1-SNAPSHOT.jar