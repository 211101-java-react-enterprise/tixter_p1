# Tixter: A Ticket Managing Service

## About Tixter
  Tixter is a simple showcase web application for our custom Object Relational Mapping (ORM) framework called [Nimble](https://github.com/211101-java-react-enterprise/nimble_p1). This ticket management app allows CRUD operations such as creating and getting tickets. Users can make requests for registering an account. Publishers can post new tickets to the pool, and cutomers can obtain them.
  
Tixter has no UI components, but offers multiple Java Http Servlet endpoints. To utilize tixter, we used Postman to send requests to the API.

## Several API Endpoints
### http://localhost:8080/tixter/users
Post Method: requires a JSON request body with the following fields:
<pre>
{
    "firstname":"Foo",
    "lastname":"Bar",
    "email":"FooBar@revature.com",
    "password":"password",
    "age": 21
}
</pre>

### http://localhost:8080/tixter/session
Post Method: (Logging In) requires a JSON request body with the following fields:
<pre>
{
    "email": "FooBar@revature.com",
    "password": "password"
}
</pre>

Delete Method: deletes the session, and effectively logs the user out.

### http://localhost:8080/tixter/tickets
Post Method: for creating tickets
Requires a JSON request body with the following fields:
<pre>
{
    "name": "Fast Life Tickets",
    "price": 10,
    "available": 100
}
</pre>

Get Method: for getting a list of tickets the user owns
Requires the user_id query parameter. EX:
<pre>
localhost:8080/tixter/tickets?user_id=[user_id_here]
</pre>

## Technologies Used
- Java8
- JavaEE-Servlet
- Maven-4 TomCat
- Junit
- Mockito

## Features
  - post tickets
  - get tickets
 
## Getting Started
Please include a "db.properties" file in the "/main/resources" package with the following fields configured with a PostgeSQl database:
<pre>
url={URL to database here}
username={username}
password={password}
</pre>

## Run Commands
### `mvn clean package`
### `mvn tomcat7:deploy`

## Contributors
- Michael Chau
- Qi Zhang

