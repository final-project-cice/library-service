INSERT INTO address_publishing_house (id, city, country, house_number, postcode, street)
VALUES (1, 'Madrid', 'Spain', '1A', 1111, 'Calle Uno');
INSERT INTO publishing_house (id, name, address_id)
VALUES (1, 'Publication House', 1);
INSERT INTO email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (1, 'email_1_publicationHouse9@email.com', 'Office', 1);
INSERT INTO email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (2, 'email_2_publicationHouse9@email.com', 'Office 2', 1);
INSERT INTO phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (1, '11111', '0111111111111111', 'Office', 1);
INSERT INTO phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (2, '222222', '02222222222222', 'Fax', 1);



INSERT INTO book (id, name, path_file, publication_date, publishing_house_id)
VALUES (1, 'My first Book', 'path/path/book', '2019-10-30', 1);

INSERT INTO comment_book (id, date, text, user_id, book_id)
VALUES (1, '2019-11-02', 'Text Comment', 1, 1);

INSERT INTO sub_comment_comment (id, date, test, user_id, comment_id)
VALUES (1, '2019-11-02', 'test sub comment', 1, 1);

INSERT INTO genre_book (id, name, book_id)
VALUES (1, 'Genre Book Uno', 1);
INSERT INTO genre_book (id, name, book_id)
VALUES (2, 'Genre Book Dos', 1);



INSERT INTO author (id, birthday, first_name, last_name)
VALUES (1, '2019-10-30', 'Author_1 FirstName', 'Author_1 LastName');
INSERT INTO address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (1, 'Madrid', 'Spain', '1A', 111111, 'Calle Uno', 1);
INSERT INTO address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (2, 'Barcelona', 'Spain', '2A', 222222, 'Calle Dos', 1);
INSERT INTO email_author (id, email, email_type, author_id)
VALUES (1, 'email_1.author_19@email.com', 'Personal', 1);
INSERT INTO email_author (id, email, email_type, author_id)
VALUES (2, 'email_2.author_19@email.com', 'Personal', 1);
INSERT INTO genre_author (id, name, author_id)
VALUES (1, 'Genre Uno', 1);
INSERT INTO genre_author (id, name, author_id)
VALUES (2, 'Genre Dos', 1);
INSERT INTO phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (1, '111', '01111111111111', 'Personal', 1);
INSERT INTO phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (2, '222', '0222222222222222', 'Work', 1);



INSERT INTO author_book (author_id, book_id)
VALUES (1, 1);
INSERT INTO book_author (book_id, author_id)
VALUES (1, 1);


-- =====================================================================================================================
INSERT INTO address_publishing_house (id, city, country, house_number, postcode, street)
VALUES (2, 'Madrid', 'Spain', '1A', 1111, 'Calle Uno');
INSERT INTO publishing_house (id, name, address_id)
VALUES (2, 'Publication House', 2);
INSERT INTO email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (3, 'email_1_publicationHouse92@email.com', 'Office', 2);
INSERT INTO email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (4, 'email_2_publicationHouse92@email.com', 'Office 2', 2);
INSERT INTO phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (3, '11111', '0111111111111111', 'Office', 2);
INSERT INTO phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (4, '222222', '02222222222222', 'Fax', 2);


INSERT INTO book (id, name, path_file, publication_date, publishing_house_id)
VALUES (2, 'My first Book', 'path/path/book', '2019-10-30', 2);

INSERT INTO comment_book (id, date, text, user_id, book_id)
VALUES (2, '2019-11-02', 'Text Comment', 1, 2);

INSERT INTO sub_comment_comment (id, date, test, user_id, comment_id)
VALUES (2, '2019-11-02', 'test sub comment', 1, 2);

INSERT INTO genre_book (id, name, book_id)
VALUES (3, 'Genre Book Uno', 2);
INSERT INTO genre_book (id, name, book_id)
VALUES (4, 'Genre Book Dos', 2);


INSERT INTO author (id, birthday, first_name, last_name)
VALUES (2, '2019-10-30', 'Author_1 FirstName', 'Author_1 LastName');
INSERT INTO address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (3, 'Madrid', 'Spain', '1A', 111111, 'Calle Uno', 2);
INSERT INTO address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (4, 'Barcelona', 'Spain', '2A', 222222, 'Calle Dos', 2);
INSERT INTO email_author (id, email, email_type, author_id)
VALUES (3, 'email_1.author_194@email.com', 'Personal', 2);
INSERT INTO email_author (id, email, email_type, author_id)
VALUES (4, 'email_2.author_194@email.com', 'Personal', 2);
INSERT INTO genre_author (id, name, author_id)
VALUES (3, 'Genre Uno', 2);
INSERT INTO genre_author (id, name, author_id)
VALUES (4, 'Genre Dos', 2);
INSERT INTO phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (3, '111', '01111111111111', 'Personal', 2);
INSERT INTO phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (4, '222', '0222222222222222', 'Work', 2);


INSERT INTO author_book (author_id, book_id)
VALUES (2, 2);
INSERT INTO book_author (book_id, author_id)
VALUES (2, 2);