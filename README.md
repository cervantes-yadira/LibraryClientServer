
# Library Client Server

The Library Client Server allows users to check out (or GET) books from a 
library, return books, and view all books within their own library.

| Client Request | Server Response       | Description                       |
|----------------|-----------------------|-----------------------------------|
| GET b l        | b l and a book        | Get a book b from the library and adds it to private library p |
| RETURN b p     | b p and a message     | Moves a book b from private library p into the library|
| SHOW p         | p and all owned books | Displays all books in private library p|
| QUIT           | Quit the connection   |                                   |
