CREATE TABLE resume (
  uuid      character varying PRIMARY KEY NOT NULL,
  full_name TEXT                 NOT NULL
);

CREATE TABLE contact (
  id          SERIAL,
  resume_uuid CHARACTER VARYING NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  value       TEXT     NOT NULL
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON contact (resume_uuid, type);

CREATE TABLE section (
  id          SERIAL   PRIMARY KEY NOT NULL,
  resume_uuid CHARACTER VARYING NOT NULL REFERENCES resume (uuid) ON DELETE CASCADE,
  type        TEXT     NOT NULL,
  content       TEXT     NOT NULL
);

CREATE UNIQUE INDEX section_uuid_type_uindex ON section (resume_uuid, type);