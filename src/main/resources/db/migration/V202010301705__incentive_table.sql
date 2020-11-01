CREATE TABLE incentive_boot
(
    id integer NOT NULL,
    dtype character varying(31),
    name character varying(255),
    version integer,
    CONSTRAINT incentive_boot_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS=FALSE
    );
ALTER TABLE incentive_boot
    OWNER to tester;
