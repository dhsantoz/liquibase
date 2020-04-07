--liquibase formatted sql

--changeset elfz:002_create_image
CREATE TABLE IF NOT EXISTS image (
  id BIGINT NOT NULL CONSTRAINT image_pkey PRIMARY KEY,
  type VARCHAR(255),
  product_id BIGINT CONSTRAINT product_image_fkey REFERENCES product
);
--rollback ALTER TABLE image DROP CONSTRAINT product_image_fkey;
--rollback DROP TABLE image;