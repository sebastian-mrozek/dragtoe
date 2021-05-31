-- apply changes
alter table dnote add column version bigint default 1 not null;

