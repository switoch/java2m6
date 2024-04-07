create table level
(
    ID       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    POSITION varchar(7) not null unique
);
create table worker
(
    ID       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME     varchar(1000) not null,
    BIRTHDAY date,
    SALARY   int           not null,
    LEVEL    varchar(7)    not null,
    FOREIGN KEY (LEVEL) REFERENCES level (POSITION),
    CHECK (length(NAME) >= 2),
    CHECK (BIRTHDAY > '1900-01-01'),
    CHECK (SALARY >= 100 and SALARY <= 100000)
);

create table client
(
    ID   int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NAME varchar(1000) not null,
    check (length(NAME) > 2)
);

create table project
(
    ID          int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    CLIENT_ID   int not null references client (ID),
    START_DATE  date,
    FINISH_DATE date,
    check (FINISH_DATE > project.START_DATE)
);
create table project_worker
(
    PROJECT_ID int,
    WORKER_ID  int,
    FOREIGN KEY (PROJECT_ID) REFERENCES project (ID),
    FOREIGN KEY (WORKER_ID) REFERENCES worker (ID),
    PRIMARY KEY (PROJECT_ID, WORKER_ID)

);
