Introduction
=====
A simple web application to showcase use of various libraries and technologies.
Demonstrates how to build a fast web service with minimal dependencies and extensive tests without the need to mock persistence.
(See more in the Persistence section below)

Build & Run
=====

Building the project
-----
Project has been built with Java 11 and gradle, so it can be built (including docker image) by either running the following command:
```
./gradlew clean build jibDockerBuild

```
Or importing into an IDE of choice (I used IntelliJ).

Running the service
-----
Once the app has been built, it can be run using docker:
```
cd docker
docker compose up
```
The above method launches to containers, one with the web app and one with postgres and is configured to use the postgres for persistence.

Since dockerized application is easier to test with external dependencies (like postgres)
I haven't included ability to create a fat Jar but it can be done easily by importing 'om.github.johnrengelman.shadow' plugin.
Application can obviously be run from within the IDE, simply execute the `Application` class - default persistence configuration in that case is an H2 database, the data will be stored in `db/dragtoe.mv.db` file.

Calling the API
-----
Once the application starts, the ReST API will be available on port 7000.
Below are sample curl commands to interact with the service:

Create new customer:
```
curl -X POST -d '{"nickName":"Patrick","contactDetails":{"address":"somewhere","phoneNumber":"0212333","twitterHandle":"@patrickThePresident"}}' http://localhost:7000/customers
```
The call will return newly created object, its ID is important for further interactions (below commands use {ID} as a placeholder)

List customers:
```
curl http://localhost:7000/customers
```

List customers with a filter:
```
curl http://localhost:7000/customers?addressFilter=Auckland
curl http://localhost:7000/customers?addressFilter=some
```

Update customer status:
```
curl -X PATCH -d '{"status":"CURRENT"}' http://localhost:7000/customers/{ID}
```

Add a note:
```
curl -X POST -d '{"text":"My important note"}' http://localhost:7000/customers/{ID}/notes
```

List notes:
curl http://localhost:7000/customers/{ID}/notes

Delete a note: (needs a note id as well, from previous curl command outputs)
```
curl -X DELETE http://localhost:7000/customers/{ID}/notes/{NOTE_ID} 
```

Update a note: (beware of optimistic locking, first example will fail with 400 status code)
```
curl -X PUT -d '{"id":"{NOTE_ID}","text":"My updated note","version":1234}' http://localhost:7000/customers/{ID}/notes
curl -X PUT -d '{"id":"{NOTE_ID}","text":"My updated note","version":1}' http://localhost:7000/customers/{ID}/notes
```

Comments
=====

Time spent on tests
-----
Approximate breakdown of time spent into 3 areas:
- 20% : setup (build, dependencies, docker, etc)
- 45% : setting up and writing tests
- 35% : coding production classes

Building the app
-----
I have developed the project in an incremental way, getting to a point where I had a running application, including automated generation of a docker image, as early as possible, and mocked services.
Each iteration after that was taking another thin slice of functionality and implementing all layers (persistence, java service, ReST API).

I did write tests before implementing the functionality.

Persistence
-----
eBean features used:
- db migration,
- generated query beans for easy querying,
- eBean-test provides ability to run unit tests against Postgres in a docker container. This can be changed to use in memory H2 if the build does not support launching containers. Bottom line: the web app can be tested in production-like environment during build time.

Web app
-----
Using Javalin with avaje-http (for compile-time generated routes and endpoints) and avaje-inject (for dependency injection).
It's a lightweight web app framework with fast startup.

Other comments
-----
- API entity classes are immutable.
- All fields are assumed to be required (not null) for simplicity sake
- ContactDetails class has a single field for address for simplicity. Normally I'd assume separate fields for city, country, postcode etc are desirable for filtering and/or analytics purposes.
- PUT vs PATCH semantics: PUT is for replacing the resource, PATCH is better for partial updates.

If I had more time or what could be done differently
-----
The following might be applicable depending on project size and domain complexity. They might not provide useful for smaller domains that don't change often.
- Complete logging, not all classes have logging but the ones having it should be enough as an example
- Better exception handling instead of a fairly broad-stroke persistence exception mapping to http status codes - would depend on use cases
- Use Lombok for API classes to reduce boilerplate code (getters, setters, constructors etc).
- Define REST API using OpenAPI and generate API classes and endpoints (can generate java rest client etc).
- Use MapStruct for mapping
- Persistence - eBean has support for storing data as JSON string or jsonb (for jsonb, querying support is provided too). This potentially simplifies persistence layer, if API classes are simply persisted as JSON objects. The consequences are that the API entities are now same as the persistence model, this could cause problems with evolving the data.
- Maybe enable paging on the 'list' API, depending on use cases.

TODO
=====
- write mapper tests
- review logging
- add metrics - explain options
