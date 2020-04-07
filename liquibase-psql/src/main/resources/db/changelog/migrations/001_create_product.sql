--liquibase formatted sql

--changeset elfz:001_create_product
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE TABLE IF NOT EXISTS product (
  id BIGINT NOT NULL CONSTRAINT product_pkey PRIMARY KEY ,
  description VARCHAR(255),
  name VARCHAR(255),
  parent_product_id BIGINT CONSTRAINT product_parent_fkey references product
);
--rollback ALTER TABLE product DROP CONSTRAINT product_parent_fkey;
--rollback DROP TABLE product;
--rollback DROP SEQUENCE hibernate_sequence;


