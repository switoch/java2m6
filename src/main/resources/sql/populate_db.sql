insert into level(POSITION)
values ('Junior'),
       ('Trainee'),
       ('Middle'),
       ('Senior');

insert into WORKER(NAME, BIRTHDAY, SALARY, LEVEL)
VALUES ('Tom Jones', '1987-10-01', 2230, 'Trainee'),
       ('Lisa Barrow', '1992-12-12', 75100, 'Senior'),
       ('Kathleen Sparkles', '1976-08-15', 50000, 'Middle'),
       ('George Sparkles', '1974-08-15', 80000, 'Senior'),
       ('Winona Lane', '2000-01-10', 5000, 'Trainee'),
       ('Adam Smith', '1995-04-10', 10500, 'Junior'),
       ('Arnold Faith', '1988-07-31', 45000, 'Middle'),
       ('Irene Lee', '2000-05-25', 4500, 'Trainee'),
       ('Patricia Adams', '1980-11-25', 95000, 'Senior'),
       ('Bruce Wayne', '1982-06-13', 16789, 'Junior');

insert into CLIENT(NAME)
VALUES ('Lisa Barrow'),
       ('Arnold Faith'),
       ('Bruce Wayne'),
       ('Lisa Barrow'),
       ('George Sparkles');

insert into PROJECT(CLIENT_ID, START_DATE, FINISH_DATE)
VALUES (1, '2024-01-15','2024-02-19'),
       (2, '2022-06-13','2024-05-22'),
       (4, '2005-10-24','2010-10-10'),
       (4, '2020-03-08','2022-02-24'),
       (3, '2021-09-01','2022-02-24'),
       (3, '2020-03-08','2022-02-24'),
       (5, '2018-01-01','2022-01-01' );

insert into PROJECT_WORKER(PROJECT_ID, WORKER_ID)
VALUES (1, 1),
       (1, 4),
       (2, 2),
       (3, 10),
       (3, 1),
       (3, 5),
       (3, 6),
       (3, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (5, 3),
       (5, 1);

insert into PROJECT_WORKER(PROJECT_ID, WORKER_ID)
VALUES (6, 3),
       (7, 1);