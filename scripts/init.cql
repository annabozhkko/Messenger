create keyspace if not exists messenger with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

create table messenger.users
(
    username    text,
    password    text,
    first_name  text,
    last_name   text,
    patronymic   text,
    birthday    date,
    primary key (username)
);

create index if not exists on messenger.users(first_name);
create index if not exists on messenger.users(last_name);
create index if not exists on messenger.users(patronymic);
create index if not exists on messenger.users(birthday);

create table if not exists messenger.messages
(
    from_user       text,
    to_user         text,
    message         text,
    message_date    date,
    message_time    time,
    primary key ( (from_user, to_user), message_date, message_time)
) with clustering order by ( message_date desc, message_time desc);

create table if not exists messenger.group_list
(   admin       text,
    title       text,
    users       set<text>,
    group_id    uuid,
    primary key (group_id)
);

create table if not exists messenger.group_messages
(
    group_id        uuid,
    user_from       text,
    message         text,
    message_date    date,
    message_time    time,
    primary key ( (group_id), message_date, message_time )
) with clustering order by ( message_date desc, message_time desc);


