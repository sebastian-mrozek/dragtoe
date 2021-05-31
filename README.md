TODO
=====
- create unit tests for notes service API
- create integration tests for notes service API
- implement notes api
- add versioning for notes (maybe updated timestamp?) to showcase model upgrade - use it for optimistic locking when updating notes
- enable filtering on 'getAll'
- write mapper tests
- review logging
- review configuration
- add metrics - explain options

Notes
=====
Planning to start with creating mocks and basic scaffolding for the app.
Next planning to build a thin slice through all layers first, including tests, i.e.: single endpoint for adding customers only. 
After that implement remaining functionality, complete a feature at a time.

API entity classes are immutable.
All fields are assumed to be required.

ContactDetails class has a single field for address for simplicity. Normally I'd assume separate fields for city, country, postcode etc are desirable for filtering and/or analytics purposes.

Persistence
---
eBean features used:
- db migration,
- generated query beans for easy querying,
- eBean-test provides ability to run unit tests against Postgres in a docker container. This can be changed to use in memory H2 if the build does not support launching containers.

Web app
---
Using Javalin with avaje-http (for compile-time generated routes and endpoints) and avaje-inject (for dependency injection).
It's a lightweight web app framework with fast startup.


If I had more time or what could be done differently
-----
The following might be applicable depending on project size and domain complexity. They might not provide useful for smaller domains that don't change often.
- Use Lombok for API classes to reduce boilerplate code (getters, setters, constructors etc).
- Define REST API using OpenAPI and generate API classes and endpoints (can generate java rest client etc).
- Use MapStruct for mapping
- Persistence - eBean has support for storing data as JSON string or jsonb (for jsonb, querying support is provided too). This potentially simplifies persistence layer, if API classes are simply persisted as JSON objects. The consequences are that the API entities are now same as the persistence model, this could cause problems with evolving the data.
- Maybe enable paging on the 'list' API, depending on use cases.