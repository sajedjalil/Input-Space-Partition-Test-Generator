create table if not exists PROJECT
(
    project_id    integer primary key autoincrement ,
    date_time TEXT not null,
    category text not null,
    input text not null,
    generated_tests text not null,
    possible_tests text not null
);