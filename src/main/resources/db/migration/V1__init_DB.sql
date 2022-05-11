create table facsimile
(facsimile_id  bigserial not null,
 facsimile_file_base64 bytea,
 facsimile_name varchar(255),
 primary key (facsimile_id))