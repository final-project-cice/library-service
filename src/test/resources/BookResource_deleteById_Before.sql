insert into address_publishing_house (id, city, country, house_number, postcode, street)
values (1, 'Madrid', 'Spain', '1A', 1111, 'Calle Uno');
insert into publishing_house (id, name, address_id)
values (1, 'Publication House', 1);
insert into email_publishing_house (id, email, email_type, publishing_house_id)
values (1, 'email_1_publicationHouse9@email.com', 'Office', 1);
insert into email_publishing_house (id, email, email_type, publishing_house_id)
values (2, 'email_2_publicationHouse9@email.com', 'Office 2', 1);
insert into phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
values (1, '11111', '0111111111111111', 'Office', 1);
insert into phone_number_publishing_house (id, country_code, phone_number, type, publishing_house_id)
values (2, '222222', '02222222222222', 'Fax', 1);



insert into book (id, name, path_file, publication_date, publishing_house_id)
values (1, 'My first Book', 'path/path/book', '2019-10-30', 1);

insert into comment_book (id, date, text, user_id, book_id)
values (1, '2019-11-02', 'Text Comment', 1, 1);

insert into sub_comment_comment (id, date, test, user_id, comment_id)
values (1, '2019-11-02', 'test sub comment', 1, 1);

insert into genre_book (id, name, book_id)
values (1, 'Genre Book Uno', 1);
insert into genre_book (id, name, book_id)
values (2, 'Genre Book Dos', 1);



insert into author (id, birthday, first_name, last_name)
values (1, '2019-10-30', 'Author_1 FirstName', 'Author_1 LastName');
insert into address_author (id, city, country, house_number, postcode, street, author_id)
values (1, 'Madrid', 'Spain', '1A', 111111, 'Calle Uno', 1);
insert into address_author (id, city, country, house_number, postcode, street, author_id)
values (2, 'Barcelona', 'Spain', '2A', 222222, 'Calle Dos', 1);
insert into email_author (id, email, email_type, author_id)
values (1, 'email_1.author_19@email.com', 'Personal', 1);
insert into email_author (id, email, email_type, author_id)
values (2, 'email_2.author_19@email.com', 'Personal', 1);
insert into genre_author (id, name, author_id)
values (1, 'Genre Uno', 1);
insert into genre_author (id, name, author_id)
values (2, 'Genre Dos', 1);
insert into phone_number_author (id, country_code, phone_number, type, author_id)
values (1, '111', '01111111111111', 'Personal', 1);
insert into phone_number_author (id, country_code, phone_number, type, author_id)
values (2, '222', '0222222222222222', 'Work', 1);



insert into author_book (author_id, book_id)
values (1, 1);
insert into book_author (book_id, author_id)
values (1, 1);