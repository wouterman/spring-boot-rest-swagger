Test project for using Spring Boot Rest with https, swagger and spring security.

1. Get a JWT token via the /authenticate endpoint. (Post basic authentication credentials. Anything
   with as password 'password' is accepted.)
2. Add the JWT token as bearer in the Authorization header for any other request. (e.g. add a header
   called 'Authorization' with a value of 'Bearer <TOKEN>'.)


