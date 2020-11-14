CREATE TABLE beverage_boot
(
    id integer NOT NULL,
    manufacturer character varying(255),
    name character varying(255),
    quantity integer,
    price double precision,
    version integer,
    incentive_boot_id integer,
    CONSTRAINT beverage_boot_pkey PRIMARY KEY (id),
    CONSTRAINT fk_beverage_incentive_boot_id FOREIGN KEY (incentive_boot_id)
        REFERENCES incentive_boot (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
 OIDS=FALSE
);
ALTER TABLE beverage_boot
    OWNER to tester;