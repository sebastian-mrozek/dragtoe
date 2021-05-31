-- apply changes
create table dcustomer (
  id                            uuid not null,
  nick_name                     varchar(255),
  address                       varchar(255),
  phone_number                  varchar(255),
  twitter_handle                varchar(255),
  status                        integer,
  creation_date_time            timestamp not null,
  constraint ck_dcustomer_status check ( status in (0,1,2)),
  constraint pk_dcustomer primary key (id)
);

