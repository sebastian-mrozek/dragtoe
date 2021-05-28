TODO
-----
- define API entities
- create CRUD interface for customers + mock impl
- create CRUD interface for notes + mock impl
- create unit tests for both services 
- import javalin libs and create rest endpoints
- create integration tests for rest api
- implement customers and notes services - define persistence models - import ebean
- implement rest endpoints
- add hash for notes to showcase model upgrade - use it for optimistic locking when updating notes
- craft docker-compose with the app and postgres, configure
- review logging
- review configuration
- add metrics - explain options

Notes
-----
Planning to start with creating mocks and basic scaffolding for the app.
Next planning to build a thin slice through all layers first, including tests, i.e.: single endpoint for adding customers only. 
After that implement remaining functionality, complete a feature at a time.

