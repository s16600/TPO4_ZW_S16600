-- CREATE TABLE author (author_id numeric(10) NOT NULL, author_name varchar(50) NOT NULL,  author_surname varchar(50) NOT NULL, 
-- PRIMARY KEY (author_id) );

-- CREATE TABLE book (book_id numeric(10) NOT NULL, book_author_id numeric(10) NOT NULL,  book_title varchar(50) NOT NULL, book_year varchar(4),
-- PRIMARY KEY (book_id), FOREIGN KEY (book_author_id) REFERENCES author(author_id) );

-- INSERT INTO author VALUES(1,"Zygmunt","Freud");
-- INSERT INTO author VALUES(2,"Filip","Zimbardo");

-- INSERT INTO book VALUES(1,1,"Wstęp do psychoanalizy","1917");
-- INSERT INTO book VALUES(2,1,"Totem i Tabu","1913");
-- INSERT INTO book VALUES(3,1,"Objaśnianie marzeń sennych","1900");
-- INSERT INTO book VALUES(4,2,"Psychologia i życie","2005");
-- INSERT INTO book VALUES(5,2,"Psychology and You","1978");

-- SELECT * FROM book INNER JOIN author ON book_author_id=author_id;

SELECT author_surname, author_name, book_title, book_year FROM book INNER JOIN author ON book_author_id=author_id ORDER BY author_name DESC, book_year;



