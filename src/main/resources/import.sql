INSERT INTO course_category (course_category_name) VALUES ('Школьники');
INSERT INTO course_category (course_category_name) VALUES ('Взрослые');
INSERT INTO course_category (course_category_name) VALUES ('Дошкольники');

INSERT INTO course_language (course_language_name) VALUES ('Английский');
INSERT INTO course_language (course_language_name) VALUES ('Немецкий');
INSERT INTO course_language (course_language_name) VALUES ('Китайский');

INSERT INTO course_level (course_level_name) VALUES ('А1');
INSERT INTO course_level (course_level_name) VALUES ('А2');
INSERT INTO course_level (course_level_name) VALUES ('B1');
INSERT INTO course_level (course_level_name) VALUES ('B2');
INSERT INTO course_level (course_level_name) VALUES ('C1');
INSERT INTO course_level (course_level_name) VALUES ('C2');

INSERT INTO lesson_type (lesson_type_name) VALUES ('Практика');
INSERT INTO lesson_type (lesson_type_name) VALUES ('Носитель');
INSERT INTO lesson_type (lesson_type_name) VALUES ('Лекция');

INSERT INTO lesson_weekday (lesson_weekday_name) VALUES ('Пн');
INSERT INTO lesson_weekday (lesson_weekday_name) VALUES ('Вт');
INSERT INTO lesson_weekday (lesson_weekday_name) VALUES ('Ср');
INSERT INTO lesson_weekday (lesson_weekday_name) VALUES ('Чт');
INSERT INTO lesson_weekday (lesson_weekday_name) VALUES ('Пт');

INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 1', 2, 1, 1);
INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 2', 2, 1, 2);
INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 3', 2, 2, 3);
INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 4', 2, 3, 4);
INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 5', 1, 1, 5);
INSERT INTO course (course_title, course_category_id, course_language_id, course_level_id) VALUES ('Course 6', 1, 1, 6);

INSERT INTO account (account_login, account_password, account_role) VALUES ('admin', 'admin', 'ROLE_ADMIN');
INSERT INTO account (account_login, account_password, account_role) VALUES ('teacher', 'teacher', 'ROLE_TEACHER');

INSERT INTO account (account_login, account_password, account_role) VALUES ('KrutinAI', 'a174deb3f', 'ROLE_USER');
INSERT INTO account (account_login, account_password, account_role) VALUES ('LaptevaYD', 'b5f16b066', 'ROLE_USER');
INSERT INTO account (account_login, account_password, account_role) VALUES ('KamenevPV', '2229cd752', 'ROLE_USER');
INSERT INTO account (account_login, account_password, account_role) VALUES ('GrishkinaSK', 'adf247800', 'ROLE_USER');
INSERT INTO account (account_login, account_password, account_role) VALUES ('VeselovNF', 'ec1e061c5', 'ROLE_USER');

INSERT INTO teacher (teacher_birthdate, teacher_email, teacher_experience, teacher_firstname, teacher_lastname, teacher_middle_name, teacher_phone_number) VALUES ('06.02.1994', 'prohor6610@outlook.com', 3, 'Прохор', 'Ишутин', 'Прокльев  ', '+7 (976) 364-85-87');
INSERT INTO teacher (teacher_birthdate, teacher_email, teacher_experience, teacher_firstname, teacher_lastname, teacher_middle_name, teacher_phone_number, teacher_account_id) VALUES ('10.13.1978', 'gerasim.belov@outlook.com', 20, 'Герасим', 'Белов', 'Феликсович', '+7 (927) 459-84-84', 'admin');
INSERT INTO teacher (teacher_birthdate, teacher_email, teacher_experience, teacher_firstname, teacher_lastname, teacher_middle_name, teacher_phone_number, teacher_account_id) VALUES ('10.28.1996', 'nadejda1996@ya.ru', 4, 'Надежда', 'Хабенская', 'Саввановна', '+7 (949) 612-47-52', 'teacher');

INSERT INTO student (student_birthdate, student_email, student_firstname, student_lastname, student_middle_name, student_phone_number, student_account_id) VALUES ('05.27.1998', 'anton.krutin@hotmail.com', 'Антон', 'Крутин', 'Ираклиевич', '+7 (931) 506-27-41', 'KrutinAI');
INSERT INTO student (student_birthdate, student_email, student_firstname, student_lastname, student_middle_name, student_phone_number, student_account_id) VALUES ('09.06.1999', 'yaroslava.lapteva@hotmail.com', 'Ярослава', 'Лаптева', 'Давидовна', '+7 (974) 833-51-65', 'LaptevaYD');
INSERT INTO student (student_birthdate, student_email, student_firstname, student_lastname, student_middle_name, student_phone_number, student_account_id) VALUES ('03.11.2000', 'petr65@yandex.ru', 'Петр', 'Каменев', 'Валерьевич', '+7 (993) 349-20-39', 'KamenevPV');
INSERT INTO student (student_birthdate, student_email, student_firstname, student_lastname, student_middle_name, student_phone_number, student_account_id) VALUES ('06.08.2001', 'serafima06021962@ya.ru', 'Серафима', 'Гришкина', 'Кирилловна', '+7 (998) 391-79-64', 'GrishkinaSK');
INSERT INTO student (student_birthdate, student_email, student_firstname, student_lastname, student_middle_name, student_phone_number, student_account_id) VALUES ('06.02.1998', 'nikita22041978@outlook.com', 'Никита', 'Веселов', 'Феодосивич', '+7 (986) 660-85-83', 'VeselovNF');

INSERT INTO lesson (lesson_end_time, lesson_start_time, lesson_course_id, lesson_type_id, lesson_teacher_id, lesson_weekday_id) VALUES ('13:30', '12:30', 1, 1, 1, 1);
INSERT INTO lesson (lesson_end_time, lesson_start_time, lesson_course_id, lesson_type_id, lesson_teacher_id, lesson_weekday_id) VALUES ('13:30', '12:30', 1, 1, 2, 2);