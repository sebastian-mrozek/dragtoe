create table dcustomer (
  id                            uuid not null,
  nick_name                     varchar(255),
  address                       varchar(255),
  phone_number                  varchar(255),
  twitter_handle                varchar(255),
  status                        integer,
  creation_date_time            timestamptz not null,
  constraint ck_dcustomer_status check ( status in (0,1,2)),
  constraint pk_dcustomer primary key (id)
);

create table dnote (
  id                            uuid not null,
  customer_id                   uuid,
  text                          varchar(255),
  version                       bigint default 1 not null,
  constraint pk_dnote primary key (id)
);

create index if not exists ix_dnote_customer_id on dnote (customer_id);
