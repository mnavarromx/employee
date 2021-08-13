# Employe CRUD application

This application performs CRUD Operations on employee data:

Creation:  
http://localhost:8080/api/employee/create  
Example:  
curl --location --request POST 'http://localhost:8080/api/employee/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "First Employee",
    "email": "EMP",
    "office": "400F",
    "phone": "12222222",
    "role": "superv"
}'

Update:  
http://localhost:8080/api/employee/{id}
Example:  
curl --location --request PUT 'http://localhost:8080/api/employee/update/4' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "nameEmployeeUpdated",
    "office": "100B",
    "email": "ssdsdC",
    "phone": "12222111",
    "role": "roleEmployeeU"
}'

Get Single employee by Id:  
http://localhost:8080/api/employee/{id}  
Example:  
curl --location --request GET 'http://localhost:8080/api/employee/1'

Get All Employees:  
http://localhost:8080/api/employee/all  
Example:  
curl --location --request GET 'http://localhost:8080/api/employee/all'  

Delete:  
http://localhost:8080/api/employee/delete/{id}
Example:  
curl --location --request DELETE 'http://localhost:8080/api/employee/delete/3'


To run this application with dev profile, navigate to project folder and run:  
mvn clean install  
mvn spring-boot:run  

To run it as production profile, set the following environment variables:  
DB_EMPLOYEE_URL  
DB_EMPLOYEE_USERNAME  
DB_EMPLOYEE_PASSWORD  

And execute the following command:  
mvn spring-boot:run -Dspring-boot.run.profiles=prd  