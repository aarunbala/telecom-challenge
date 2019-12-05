# telecom-challenge

## Capabilities

1. Get All Telephone Numbers - http://localhost:8080/telephoneInquiry/all/services
2. Get All Telephone Numbers for a Customer - http://localhost:8080/telephoneInquiry/{customerId}/services
3. Activate/Deactivate a Telephone number 
    - http://localhost:8080/telephoneInquiry/activate/service
    - http://localhost:8080/telephoneInquiry/deactivate/service

## Interface Specifications & Data Model
Interface specifications are provided through Swagger. Data models can be accessed from the Swagger UI link under Models.
* Swagger API docs - http://localhost:8080/telephoneInquiry/v2/api-docs
* Swagger UI - http://localhost:8080/telephoneInquiry/swagger-ui.html

## Other URls
* H2 In memory DB - http://localhost:8080/telephoneInquiry/h2
* Actuator - http://localhost:8080/telephoneInquiry/actuator

CustomerProfile{
    customerId	string
    name	string
    services	[Service{
                            activated	boolean
                            activatedTime	string($date-time)
                            phoneNumber	string
                 }]
}
