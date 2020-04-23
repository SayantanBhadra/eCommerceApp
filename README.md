# eCommerceApp
eCommarceApp is my first try to create and test microservice architecture using **Spring Boot** Micro Service Architecture. The purpose is fully educational and you are free to pull/fork it and update according to your requirement.
## What have been covered here:
* Spring Rest Service
* Spring Data JPA with MySQL as backend
* Spring Config Server using GIT Repo
* Ribbon client side load balancer
* Open Feign Rest Service Proxy
* Eureka Naming Server
* Zuul API Gateway
* Request Tracing using Spring Sleuth, Zipkin Client and Rabbit MQ
* Fault tolerance using Hystrix

## List of Microservices:
I have created a total of three Microservices here.
1. **AccountService:** <br/>
Deals with all REST CRUD operation for User and User related other info like Address and Payment
2. **InventoryService :** <br/>
Deals with all REST CRUD operation for :
    *  Product Category creation/retrieval/modification/deletion
    *  Seller registration/modification/retrieval/deletion
    *  Product record creation
3. **DeliveryService:** <br/>
It tracks the following:
    *  Product Purchase Information
    *  Keeping a delivery status of the sold product
## Description:
* All the REST service will accept input in json/xml format and Response will be in similar manner. 
* The backend database MySQL will be interacted through JPA where Hibernate is used as JPA Provider.
* All the inter-service communication and also external requests should pass through Zuul API Gateway. 
* Ribbon load balancer is enabled in each service for client side load balancing. To test the feature, create more than one instance of each service. 
* Eureka Naming server is playing the role of service discovery.Zuul will consult with Eureka to delegate particular REST request to its correct destination. 
* Tracing service will enable to trace an inter service communication and point out the place where failure occurred in case of unsuccessful communication. 
* If any service goes down, Hystrix will act as a fault tolerance component which will reply with a pre-defined response.
* Openfeign makes writing inter-service REST calls more easy,structured and less line of code compare to RestTemplate.

## System Requirements:
I have done the whole project in Windows 10. But it can also be done in Linux. I have personally migrated the same project in Ubuntu 19.10 and worked on some parts.
But in this section I will concentrate fully on Windows System.

I have used following tools for developing this project:
* Oracle Java 8(1.8.0_251)
* Eclipse IDE for Java/J2EE(2020-03)
* Apache Maven(3.6.3)
* GIT(2.26.2)
* Postman
* Rabbit MQ
* Zipkin Server
* Mysql Server(8.0.19)
* MySQL Workbench(8.0.19)

## Data Migration:
I have prepared some sample data to load the database tables with initial data.

**eCommerceApp/DB-Script/DDL.sql:**<br/> 
It contains DDL query for creating all 3 databases and corrsponding tables<br/><br/>
Other CSV files contains the sample data which can be imported through MySQL Workbench's Data Import Wizard once all the tables have been created. The name of the csv files are same as tables to avoid confusion.

## Setting up Rabbit MQ and Zipkin Server:
**Rabbit MQ:**<br/>
After installing Rabbit MQ, open command prompt and type ```set RABBIT_URI=amqp://localhost```

**Zipkin Server:**<br/>
A jar will be downloaded which you need to run from command prompt using ```java -jar zipkin-server-2.21.1-exec```

## Application Start Order and Default Port:

Start the applications in the following order:
Application Order | Application Name | Default Port
-----------------|----------------|------------
1 | Zipkin Server | 9411
2 | ConfigServer | 8888
3 | EurekaServer | 8761
4 | ZuulAPIGateway | 8765
5 | AccountService | 8081
6 | InventoryService | 8082
7 | DeliveryService | 8083

## How to run the Applications: 
Since I have used GIT Config Server, all the microservices and corresponding auxiliary applications will request ConfigServer module for application parameter during start up. Which will in turn fetch then from CentralConfigRepo and hand them over. So create your own Git repo and update ```spring.cloud.config.server.git.uri```,```spring.cloud.config.server.git.username``` and ```spring.cloud.config.server.git.password``` according to your GIT Repo settings.<br/>
While executing, one option is to run the applications from eclipse except the Zenkins Server. But alternatively you can generate jar files by executing following maven command ```mvn clean install -DskipTests``` and run the jar files from command-line tool like cmd with following command ```java -Xms64M -Xmx512M -jar <jar file name>```. Don't forget to execute them in the order mentioned above.


 
