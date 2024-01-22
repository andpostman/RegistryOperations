
create user db_admin superuser password 'admin';

create schema if not exists bank;

create table if not exists bank.organization_priority
(
    contract_number text primary key ,
    green_field bool not null
);

CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg
    MINVALUE 1  NO MAXVALUE
    START WITH 1;

create table if not exists bank.package
(
    id int primary key default nextval('bank.operations_pkg' :: regclass),
    contract_number text not null ,
    organization text not null ,
    total_amount numeric not null,
    green_field bool not null
);

CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg_row
    MINVALUE 1  NO MAXVALUE
    START WITH 1;

create table if not exists bank.package_row
(
    id int primary key default nextval('bank.operations_pkg_row' :: regclass),
    req_id int REFERENCES bank.package (id) ON DELETE NO ACTION,
    account text not null ,
    fio text not null ,
    amount numeric not null ,
    green_field bool not null,
    state int not null
);

CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg_row_result
    MINVALUE 1  NO MAXVALUE
    START WITH 1;

create table if not exists bank.row_result
(
    id int primary key default nextval('bank.operations_pkg_row_result' :: regclass),
    row_id int REFERENCES bank.package_row (id) ON DELETE NO ACTION,
    amount numeric not null
);

CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg_check_job
    MINVALUE 1 NO MAXVALUE
    START WITH 1;

create table if not exists bank.check_job
(
    id int primary key default nextval('bank.operations_pkg_check_job' :: regclass),
    package_id int REFERENCES bank.package (id) ON DELETE NO ACTION,
    insertion_time time without time zone not null,
    state int not null
);


CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg_row_error
    MINVALUE 1  NO MAXVALUE
    START WITH 1;

create table if not exists bank.row_error
(
    id int primary key default nextval('bank.operations_pkg_row_error' :: regclass) ,
    row_id int REFERENCES bank.package_row (id) ON DELETE NO ACTION,
    description text
);

CREATE SEQUENCE IF NOT EXISTS bank.operations_pkg_workers
    MINVALUE 1  NO MAXVALUE
    START WITH 1;

create table if not exists bank.worker
(
    id int primary key default nextval('bank.operations_pkg_workers' :: regclass) ,
    account text not null ,
    fio text not null
);

create table if not exists bank.package_report
(
    id serial primary key,
    send_amount numeric not null,
    credited_amount numeric not null,
    not_credited_amount numeric not null
);

alter table bank.package_report add column insertion_time time without time zone;

insert into bank.worker(account, fio) values ('2313 1313 1232 6544','Vlasov Ivan Ivanovich');
insert into bank.worker(account, fio) values ('4838 3448 3434 3434','Grigoriev Artem Pavlovich');
insert into bank.organization_priority(contract_number, green_field) values ('112323',true);