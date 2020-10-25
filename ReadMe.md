# Getting Started

### Prerequisites
To run this code, you would need Java 8, Gradle and Docker installed on your machine. Please refer to the official documentation links below

* [How do I install Java](https://java.com/en/download/help/download_options.xml)
* [Official Gradle documentation](https://docs.gradle.org)
* [Official Docker documentation](https://docs.docker.com/get-docker/)

### Steps to run the code
* Clone the project from github.com. Open terminal and enter command:  
    ``git clone https://github.com/nmahaja1/account-api-demo.git``
* cd into the project directory
* Create dockerized mysql container (NOTE: Following steps take a while to execute):  
    * Open a new tab on the terminal and start a mysql container on docker using command below:   
        ``docker run --name docker-mysql -it -p 3306:3306 --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=test" mysql``
    * Open a new tab on the terminal and Import data from the sql file  
        ``docker exec -i docker-mysql mysql -uroot -proot test < demo_data.sql;``
* Open a new tab on terminal and build the project using gradle  
    ``gradle clean build``         
* Run the application while linking it to mysql  
    ``docker run -t --name spring-boot-docker --link docker-mysql:mysql -p 8080:8080 spring-boot-docker``
* For testing open browser and access swagger to access the endpoints  
  ``http://localhost:8080/swagger-ui.html``
* Two endpoints are exposed  
    ``getUserAccounts (GET /accounts)``  
    ``getAccountTransactions (GET /accounts/{accountNumber}/transactions)``
* To access the `getUserAccounts` endpoint, click `Try it Out!`. The endpoint can also be accessed via curl command on terminal:  
    ``curl -X GET --header 'Accept: application/json' 'http://localhost:8080/accounts'``
* To access the `getAccountTransactions` endpoint enter account number (1 or 2 or 3) and click `Try it Out!`. The endpoint can also be accessed via curl command on terminal:  
    ``curl -X GET --header 'Accept: application/json' 'http://localhost:8080/accounts/1/transactions'``      


