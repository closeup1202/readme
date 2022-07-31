insert into member(member_id, activated, email, password, username)
values (1, 1, 'admin@admin.com', 'admin', 'admin');

insert into readme.authority(authority_name) values('ROLE_USER');
insert into readme.authority(authority_name) values('ROLE_ADMIN');

insert into user_authority(member_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority(member_id, authority_name) values (1, 'ROLE_ADMIN');