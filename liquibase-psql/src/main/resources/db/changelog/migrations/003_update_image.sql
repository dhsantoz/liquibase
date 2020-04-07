--liquibase formatted sql

--changeset douglas:003_update_image
ALTER TABLE image ADD COLUMN IF NOT EXISTS description VARCHAR(1024);

--rollback ALTER TABLE image DROP COLUMN description;