##TreeTop Engineering Solution

This project solves a problem provided by TreeTop Commons as part of the hiring evaluation process

## Synopsis

This project implements  a REST API with one GET endpoint that returns a list of organizations filtered by field names(case insensitive). 

## API specification

GET /organizations?category=Greek&city=Washington

Fields:

Id: numeric id

Name: string //organization name

City: string //US city name

State: string //US state name

Postal: string //US postal code

Category: string //categorization of org

Additional query params:

Orderby: string //fieldname to order the results by

Direction: string //ASC or DSC

The expected response is a JSON collection of organizations:

{
"organizations": [

{

"id":"102",

"name":"Sigma Kappa Zeta Chapter",

"city":"Washington",

"state":"DC",

"postal":"20052",

"category":"Greek"

},

...

]

}

All query parameters are optional.

## Approach
 
* This project works with a Glassfish server and uses Jersey to implement a JAX-RS service.
* The required filters are very similar to simple SQL statements, so I imported the data from the .csv file into a postgres database, and used JDBC to query it.
I used Jackson for JSON conversion.
* Due to time constraints, I was not able to mock classes properly, so I isolated the code to be tested into methods with few dependencies and changed them from private to protected.
* Also due to time constraints, I was not able to build end to end tests using Postman. This should be done to ensure the database queries actually work properly and consistently.
* I built simple DTO objects with JAXB annotations to deal with the return values, and then subclassed them with objects with extra methods for actually dealing with the database.
* The DTOs used public fields because of time constraints in dealing with JAXB annotations; this should be improved for production code.
* Due to time constraints, no validation of input parameters is done. The validation should be added for negative ids and invalid orderby fields and directions. 
## Installation
Aside from downloading the code and setting up Intellij to run the project, you will need to setup the postgres database and import the data.
### Create an empty table in PostgreSql
Create a TreeTop PostgreSql server and add a TreeTopDB database with an organizations table(User postgres with password postgres):

CREATE TABLE public.organizations

(

  id integer,

  name character varying(256),

  city character varying(256),

  state character varying(256),

  postal character varying(256),

  category character varying(256)

)

WITH (

  OIDS=FALSE

);

ALTER TABLE public.organizations

  OWNER TO postgres;
### Import the .csv file into the organizations Table
* Download the data file from
https://s3-us-west-2.amazonaws.com/sample-coding-dataset/organization_sample_data.csv
* Use PGAdmin3 to import the csv file into the organizations table

## Tests

The unit tests can be run in Intellij or on the command line.

You can test the API using Postman as follows:

GET http://localhost:8080/treetop4_war_exploded/organizations?name=Timeless

Headers: Content-Type   application/json

Body returned:

{

  "organizations": [
  
    {
    
      "id": 22,
      
      "name": "Timeless",
      
      "city": "Port Saint Lucie West",
      
      "state": "FL",
      
      "postal": "34986",
      
      "category": "Community"
      
    }
    
  ]
  
}
## Contributors

James Owen

## License

??