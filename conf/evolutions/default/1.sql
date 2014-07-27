# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table threads (
  id                        bigint not null,
  title                     varchar(255),
  is_locked                 boolean,
  initial_message           varchar(255),
  constraint pk_threads primary key (id))
;

create table posts (
  poster_id                 varchar(255),
  message                   varchar(255),
  title                     varchar(255),
  id                        bigint,
  forum_thread_id           bigint)
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

alter table posts add constraint fk_posts_forumThread_1 foreign key (forum_thread_id) references threads (id) on delete restrict on update restrict;
create index ix_posts_forumThread_1 on posts (forum_thread_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists threads;

drop table if exists posts;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists threads_seq;

drop sequence if exists user_seq;

