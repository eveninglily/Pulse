# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table threads (
  id                        double not null,
  title                     varchar(255),
  constraint pk_threads primary key (id))
;

create table user (
  id                        varchar(255) not null,
  username                  varchar(255),
  password                  varchar(255),
  rank                      integer,
  profile_image             varchar(255),
  post_count                integer,
  rep_count                 integer,
  constraint ck_user_rank check (rank in (0,1,2,3,4)),
  constraint pk_user primary key (id))
;

create sequence threads_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists threads;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists threads_seq;

drop sequence if exists user_seq;

