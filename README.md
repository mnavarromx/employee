# Employe CRUD application

This application performs CRUD Operations on employee data:

Creation:  
POST  
http://localhost:8080/api/employee/create  

Update:  
PUT  
http://localhost:8080/api/employee/{id}

Get Single employee by Id:  
GET  
http://localhost:8080/api/employee/{id}  

Get All Employees:  
GET  
http://localhost:8080/api/employee/all  

Delete:  
DELETE  
http://localhost:8080/api/employee/delete/{id}

Example:  
curl --location --request POST 'http://localhost:8080/api/employee/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "First Employee",
    "email": "emp@mail.com",
    "office": "400F",
    "phone": "12222222",
    "role": "superv"
}'

To run this application with dev profile, navigate to project folder and run:  
mvn clean install  
mvn spring-boot:run  

To run it as production profile, set the following environment variables:  
DB_EMPLOYEE_URL  
DB_EMPLOYEE_USERNAME  
DB_EMPLOYEE_PASSWORD  

And execute the following command:  
mvn spring-boot:run -Dspring-boot.run.profiles=prd  