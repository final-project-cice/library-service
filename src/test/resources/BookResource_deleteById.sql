INSERT INTO library_test.public.address_publishing_house (id, city, country, house_number, postcode, street)
VALUES (1, 'Madrid', 'Spain', '1A', 1111, 'Calle Uno');
INSERT INTO library_test.public.publishing_house (id, name, address_id)
VALUES (1, 'Publication House', 1);
INSERT INTO library_test.public.email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (1, 'email_1_publicationHouse9@email.com', 'Office', 1);
INSERT INTO library_test.public.email_publishing_house (id, email, email_type, publishing_house_id)
VALUES (2, 'email_2_publicationHouse9@email.com', 'Office 2', 1);
INSERT INTO library_test.public.phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (1, '11111', '0111111111111111', 'Office', 1);
INSERT INTO library_test.public.phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
VALUES (2, '222222', '02222222222222', 'Fax', 1);



INSERT INTO library_test.public.book (id, name, path_file, publication_date, publishing_house_id)
VALUES (1, 'My first Book', 'path/path/book', '2019-10-30', 1);

INSERT INTO library_test.public.usr (id, birthday, email, first_name, last_name)
VALUES (1, '2019-10-30', 'email_user9@email.com', 'User FirstName', 'User LastName');
INSERT INTO library_test.public.comment_book (id, date, text, book_id, user_id)
VALUES (1, '2019-10-30', 'Text Comment', 1, 1);

INSERT INTO library_test.public.usr (id, birthday, email, first_name, last_name)
VALUES (2, '2019-10-30', 'email_user911@email.com', 'User FirstName', 'User LastName');
INSERT INTO library_test.public.sub_comment_comment (id, date, test, comment_id, user_id)
VALUES (1, '2019-10-30', 'test sub comment', 1, 2);

INSERT INTO library_test.public.genre_book (id, name, book_id)
VALUES (1, 'Genre Book Uno', 1);
INSERT INTO library_test.public.genre_book (id, name, book_id)
VALUES (2, 'Genre Book Dos', 1);



INSERT INTO library_test.public.author (id, birthday, first_name, last_name)
VALUES (1, '2019-10-30', 'Author_1 FirstName', 'Author_1 LastName');
INSERT INTO library_test.public.address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (1, 'Madrid', 'Spain', '1A', 111111, 'Calle Uno', 1);
INSERT INTO library_test.public.address_author (id, city, country, house_number, postcode, street, author_id)
VALUES (2, 'Barcelona', 'Spain', '2A', 222222, 'Calle Dos', 1);
INSERT INTO library_test.public.email_author (id, email, email_type, author_id)
VALUES (1, 'email_1.author_19@email.com', 'Personal', 1);
INSERT INTO library_test.public.email_author (id, email, email_type, author_id)
VALUES (2, 'email_2.author_19@email.com', 'Personal', 1);
INSERT INTO library_test.public.genre_author (id, name, author_id)
VALUES (1, 'Genre Uno', 1);
INSERT INTO library_test.public.genre_author (id, name, author_id)
VALUES (2, 'Genre Dos', 1);
INSERT INTO library_test.public.phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (1, '111', '01111111111111', 'Personal', 1);
INSERT INTO library_test.public.phone_number_author (id, country_code, phone_number, type, author_id)
VALUES (2, '222', '0222222222222222', 'Work', 1);



INSERT INTO library_test.public.author_book (author_id, book_id)
VALUES (1, 1);
INSERT INTO library_test.public.book_author (book_id, author_id)
VALUES (1, 1);
