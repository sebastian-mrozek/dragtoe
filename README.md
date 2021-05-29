TODO
-----
- implement add/list customers APIs - define persistence models - import ebean
- import javalin libs and create rest endpoints for add/list customers API
- create integration tests for rest api for add/list api (import jackson and annotate api)
- use jib to generate docker image
- craft docker-compose with the app and postgres, configure
- create unit tests for remaining customer service APIs
- create unit tests for notes service API
- implement remaining rest endpoints
- add versioning for notes (maybe updated timestamp?) to showcase model upgrade - use it for optimistic locking when updating notes
- review logging
- review configuration
- add metrics - explain options

Notes
-----
Planning to start with creating mocks and basic scaffolding for the app.
Next planning to build a thin slice through all layers first, including tests, i.e.: single endpoint for adding customers only. 
After that implement remaining functionality, complete a feature at a time.

API entity classes are immutable.
All fields are assumed to be required.

ContactDetails class has a single field for address for simplicity. Normally I'd assume separate fields for city, country, postcode etc are desirable for filtering and/or analytics purposes.



Optional
-----
The following might be applicable depending on project size and domain complexity. They might not provide useful for smaller domains that don't change often.
- Use Lombok for API classes to reduce boilerplate code (getters, setters, constructors etc).
- Define REST API using OpenAPI and generate API classes and endpoints (can generate java rest client etc).
