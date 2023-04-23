Readme File:
-----------------------
Title: 
Demo Customer Project.
-----------------------
About: 
1. Customer-service contains list of crud operation of customer

2. The authentication an authorization is handled by spring security (In memory authentication) with harcoded user roles and password to keep it simple. 
As per requirement we can also use authentication by connecting to db using a datasource.

3. It contains swagger, which is handful to get APIs documentation and APIs can be tested as well using swagger.

4. It has integrated H2 database as a in memory DB for keeping the project db configuration.
   The H2 data files are located inside: customer-service\customer-service\src\main\resources\db2
   the db migration scripts are located at: \customer-service\customer-service\src\main\resources\sql-migration-script

5. The Customer PII data is encrypted using Advanced Encryption Standard (AES) algorithm.


-----------------------
How to Install and Run the Project:

to start the poject: 
   1. mvn clean install
   2. go to customer-service\customer-service\target
   C:\Users\pragrawal\Downloads\customer-service\customer-service\target>java -jar customer-service-0.0.1-SNAPSHOT.jar

Postman APIS exports are attached with this file.
Sample responses are also attached.

---------------------------
Screenshot attached for Swagger


