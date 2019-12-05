# telecom-challenge

## Capabilities

1. Get All Telephone Numbers - http://localhost:8080/telephoneInquiry/all/services
2. Get All Telephone Numbers for a Customer - http://localhost:8080/telephoneInquiry/{customerId}/services
3. Activate/Deactivate a Telephone number 
    - http://localhost:8080/telephoneInquiry/activate/service
    - http://localhost:8080/telephoneInquiry/deactivate/service

## Interface Specifications & Data Model
Interface specifications are provided through Swagger. 
* Swagger API docs - http://localhost:8080/telephoneInquiry/v2/api-docs
* Swagger UI - http://localhost:8080/telephoneInquiry/swagger-ui.html

## Data Model
Data models can also be accessed from the Swagger UI link under Models. Simple Data model (white boarded) can be accessed from [here](https://github.com/aarunbala/telecom-challenge/blob/master/src/main/resources/DataModel.jpg)

```
CustomerProfile{
    customerId	string
    name	string
    services	[Service{
                            activated	boolean
                            activatedTime	string($date-time)
                            phoneNumber	string
                 }]
}
```

## Data Load
On Application load, data from data.sql gets loaded into the In_memory H2 database.

## To start
Application can be started using the below command. Application runs on port 8080.
```
mvn spring-boot:run 
```

## Other URls
* H2 In memory DB - http://localhost:8080/telephoneInquiry/h2
* Actuator - http://localhost:8080/telephoneInquiry/actuator



