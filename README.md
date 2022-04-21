# Library Project
A website for a library to handle library book management operations, such as managing user accounts, adding new books, and checking out and returning books.

Technoloiges Utilized:
- JDBC
- Servlets
- JSPs
- Maven

# Features Implemented:
## Users
Users are defined as either a librarian or patron
- Log into their account
- Change their username and password

## Patron
- Checkout books that are available (not currently checked out)
- View a history of the books they checked out as well as their current checked out books
- View the list of all available books in the library
- In addition to general user functionality, can also update their name
- Sign up for an account (upon creation, will initially be frozen until a librarian unfreezes their account)
  - NOTE: A frozen account is unable to checkout any books

## Librarian
- Create new librarian accounts
- Add new books to the library
- Edit the title and description of a book currently in the library
- Freeze/un-freeze patron accounts


## To Run
- Start MySQL server with Microsoft Services
- Run Library_Database.sql to initialize database
- Start Apache Tomcat
- RC Project -> run as -> run on server
- Go to http://localhost:8080/LibraryProject/