-- apply changes
create table dnote (
  id                            uuid not null,
  customer_id                   uuid,
  text                          varchar(255),
  constraint pk_dnote primary key (id)
);

create index ix_dnote_customer_id on dnote (customer_id);
