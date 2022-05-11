create table facsimile (facsimile_id  bigserial not null,
facsimile_doctype varchar(255),
facsimile_file_base64 bytea,
facsimile_name varchar(255),
facsimile_status varchar(255),
primary key (facsimile_id))