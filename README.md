InsuranceManagementApplication

This is a Spring Boot application to manage activities such as maintaining Client details, Insurance Policy details and Insurance Claim details.

Description

    The Technology used to build this project is Java, Spring Framework, and MySQL. The main features of this Application include Add Client, Creating Insurance Policy for a         client, Claiming Insurance and several other features like getting client, policy and claim details. Developed REST APIs to implement the project's                     functionalities. Moreover, I made use of Spring Data JPA dependency to create tables in the database and to interact with the database using queries. Used             MySQLDriver to connect SpringBoot Application with MySQL Database.

    This application is aimed at providing a simple and easy way to manage and store Insurance policies and other policy related information.

Getting Started:

    -> Knowledge on Java, SpringBoot Framework is required. Should be able to use MySQL.
    -> Libraries Used: Spring Web, Spring Data JPA, MySQL Driver, Lombok.
    -> Used Maven Project Management Tool to download and integrate the dependencies into the SpringBoot Application. 

How to Run the Project:

    -> Clone the repository to your local machine and import it to your IDE of choice.
    -> You will need to have Java 8 or above version and Maven installed on your computer.
    -> Next, configure the application's database connection by editing the application.properties file located in the src/main/resources directory. You will need to            provide your database credentials.
    -> Once you have configured the database connection, build the application by running the following command in the terminal: mvn clean install.
    -> Once the build is successful, you can run the application by running the following command: mvn spring-boot:run The application will start up on port 8080.
    -> Finally, Test the APIs using Postman.
    
Structure Of The Project: 

    -> Entities/Models created in this project :- Client, Claim, InsurancePolicy. 
    -> I used layered approach to develop this project. The three layers are Controller layer, Service layer, Repository layer. Each Entity consists of these 3 layers.
    -> Controller layer handles the requests from the user and sends the responses to the user.
    -> Service Layer consists of Business Logic. The actual implementation of the API functionality happens here.
    -> Repository Layer interacts with the database.
    -> Used Data Transfer Objects (DTOs) to get requests from the user and also to send responses to the user. 
    -> Created a Converters package to convert DTOs to Entities and viceversa.  
   
Features or (APIs) of the Application in Detail:
    
    Client: 1) Add Client
            2) Get Client By Id
            3) Get All Clients
            4) Delete Client By Id
            5) Update Client Details.
            
    Insurance Policy : 1) Create an Insurance Policy for a Client.
                       2) Get Policy By Id.
                       3) Get All Policies
                       4) Delete Policy By Id.
                       5) Update Policy Details
    
    Insurance Claim: 1) Make an Insurance Claim
                     2) Get Claim By Id
                     3) Get All Claims made.
                     4) Delete Claim By Id.
                     5) Update Claim Details.
                     
Relationship between Entities: 
      
      -> Client - InsurancePolicy (OneToMany Relationship)
      -> Client - InsuranceClaims (OneToMany Relationship)
      -> InsurancePolicy - InsuranceClaims (OneToMany Relationship)
      
          
