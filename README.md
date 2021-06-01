Introduction
=====
A simple web application to showcase use of various libraries and technologies.
Demonstrates how to build a fast web service with minimal dependencies and extensive tests without the need to mock persistence.
(See more in the Persistence section below)

Build & Run
=====

Building the project
-----
Project has been built with Java 11 and gradle, so it can be built by either running the following command:
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

Since dockerized application is easier to test with external dependencies (like postgres)
I haven't included ability to create a fat Jar but it can be done easily by importing 'om.github.johnrengelman.shadow' plugin.
Application can obviously be run from within the IDE, simply execute the `Application` class.

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
- Use Lombok for API classes to reduce boilerplate code (getters, setters, constructors etc).
- Define REST API using OpenAPI and generate API classes and endpoints (can generate java rest client etc).
- Use MapStruct for mapping
- Persistence - eBean has support for storing data as JSON string or jsonb (for jsonb, querying support is provided too). This potentially simplifies persistence layer, if API classes are simply persisted as JSON objects. The consequences are that the API entities are now same as the persistence model, this could cause problems with evolving the data.
- Maybe enable paging on the 'list' API, depending on use cases.

TODO
=====
- write mapper tests
- review logging
- review configuration
- add metrics - explain options

