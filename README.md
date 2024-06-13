### Task 1: Blog application

- [x] Design a data model for a blog application and implement the corresponding JPA entities.
- [x] Write a Spring Data JPA repository query to find all articles published before a certain date.
- [x] Create a REST API that enables CRUD operations for articles.
- [x] Implement exception handling in Spring Boot to return custom error messages for a REST API.

---

Current implementation is done for `Author` and `Articles` entities

Keep in mind that after start up embedded DB will be empty. Thus, you have to create some entities. 

REST API for articles looks like below.

Create:
````
POST localhost:8083/v1/articles
Content-Type: application/json

{
"post": "First post is simple the best!",
"authorName": "harry"
}
````

get all articles:
````
GET localhost:8083/v1/articles
````

all articles published before certain date:
````
GET localhost:8083/v1/articles?date=2024-06-12T15:00:00
````

get one:
````
GET localhost:8083/v1/articles/1
````

update:
````
PUT localhost:8083/v1/articles/1
Content-Type: application/json

{
"post": "UPDATED post message"
}
````

delete:
````
DELETE localhost:8083/v1/articles/8
````

H2 DB will be available at:

````
http://localhost:8083/h2-console/
````

Credentials:

    DB name: blog_db
    username: sa

password field should be empty.