Default port: 8080

Default path for API: /server

Default path for client: /

----------

**Endpoints:**

**API:**

 - */vote (GET)* — returns votes JSON
 - */vote/{id} (GET)* — returns vote JSON by ID
 - */agree/{id} (PUT)* — counts the agreement vote by ID
 - */disagree/{id} (PUT)* — counts the disagree vote by ID
 - */vote/{name} (POST)* — create new vote
 - */vote/{id} (DELETE)* — delete vote by ID

**Client:**

 - */ (GET)* — returns votes
 - */vote/{id} (GET)* — returns vote by ID

----------

**Technologies:**

 - Spring(Boot, Test, MVC, JDBC), H2 database(in-memory), Jackson, Thymeleaf
 - JUnit, Mockito
 - Maven