-- Table: REVINFO

create table revinfo (
        rev integer NOT NULL default nextval('upd_seq'),
		revtstmp bigint,
        primary key (rev)
    )
WITH (
  OIDS=FALSE
);
ALTER TABLE revinfo OWNER TO postgres;

drop table revinfo;

-- Table: TIPODOCUMENTO
CREATE TABLE tipodocumento_aud
(
  id integer NOT NULL default nextval('tipodocumento_id_seq_2'),
  rev integer not null default nextval('upd_seq'),
  revtype int2,
  revend  integer,
  revend_tstmp timestamp ,
  codigo integer,
  descripcion character varying(45),
  CONSTRAINT tipodocumento_aud_pk PRIMARY KEY (id,rev)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipodocumento_aud OWNER TO postgres;

drop table tipodocumento_aud;