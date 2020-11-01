CREATE TABLE customer_order_boot
(
    id integer NOT NULL,
    issue_date timestamp without time zone,
    order_amount integer,
    version integer,
    beverage_boot_id integer,
    CONSTRAINT customer_order_boot_pkey PRIMARY KEY (id),
    CONSTRAINT fk_customer_order_boot_beverage_boot_id FOREIGN KEY (beverage_boot_id)
        REFERENCES beverage_boot (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    WITH (
        OIDS=FALSE
    );
ALTER TABLE customer_order_boot
    OWNER to tester;