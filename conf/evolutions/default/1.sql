# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  user_id                   varchar(255),
  nick_name                 varchar(255),
  password                  varchar(255),
  type                      boolean,
  delete_flag               boolean,
  constraint uq_user_user_id unique (user_id),
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

