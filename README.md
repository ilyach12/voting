Default port: 8080

Default context path for API: /server

Default context path for client: /

----------

**Endpoints:**

**API:**

 - */server (GET)* — returns votes JSON
 - */server/{id} (GET)* — returns vote JSON by ID
 - */server/agree/{id} (POST)* — counts the agreement vote by ID
 - */server/disagree/{id} (POST)* — counts the disagree vote by ID
 - */server/create/{name} (POST)* — create new vote
 - */server/delete/{id} (POST)* — delete vote by ID

**Client:**

 - */ (GET)* — returns all votes
 - */vote/{id} (GET)* — returns vote by ID

----------

**Technologies:**

 - Spring(Boot, Test, MVC, JDBC), H2 database(in-memory), Jackson, Thymeleaf
 - JUnit, Mockito
 - Maven