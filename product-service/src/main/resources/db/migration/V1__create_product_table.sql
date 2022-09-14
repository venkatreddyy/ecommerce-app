-- 'ossp' is not loaded by default, hence the extension must be explicitly enabled in case it does not exist
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS PRODUCT (
  PRODUCT_ID SERIAL PRIMARY KEY,
  ID UUID DEFAULT uuid_generate_v4() NOT NULL,
  NAME VARCHAR(512) NOT NULL,
  BRAND VARCHAR(256) NOT NULL,
  CATEGORY VARCHAR(256) NOT NULL,
  PRICE REAL NOT NULL,
  CURRENCY VARCHAR(3) NOT NULL CHECK (CURRENCY in ('EUR', 'USD')),
  QUANTITY INTEGER NOT NULL,
  DAT_INS TIMESTAMP NOT NULL,
  DAT_UPD TIMESTAMP,
  USR_INS VARCHAR(256) NOT NULL,
  USR_UPD VARCHAR(256),
  STAT CHAR(1) NOT NULL CHECK (STAT in ('A', 'I', 'D'))
);

COMMENT ON COLUMN PRODUCT.PRODUCT_ID IS 'Auto-generated internal id (i.e. primary key)';
COMMENT ON COLUMN PRODUCT.ID IS 'Product id used as external identifier';
COMMENT ON COLUMN PRODUCT.NAME IS 'Product name';
COMMENT ON COLUMN PRODUCT.BRAND IS 'Price brand';
COMMENT ON COLUMN PRODUCT.CATEGORY IS 'Product category';
COMMENT ON COLUMN PRODUCT.PRICE IS 'Product price';
COMMENT ON COLUMN PRODUCT.CURRENCY IS 'Product currency';
COMMENT ON COLUMN PRODUCT.QUANTITY IS 'Product quantity';
COMMENT ON COLUMN PRODUCT.DAT_INS IS 'Creation date of the record';
COMMENT ON COLUMN PRODUCT.DAT_UPD IS 'Modification date of the record';
COMMENT ON COLUMN PRODUCT.USR_INS IS 'Identity of the user that created the record';
COMMENT ON COLUMN PRODUCT.USR_UPD IS 'Identity of the user that modified the record';
COMMENT ON COLUMN PRODUCT.STAT IS 'Status of the record A = active, I = inactive, D = deleted';

CREATE INDEX PRODUCT_IDX1 ON PRODUCT (ID);