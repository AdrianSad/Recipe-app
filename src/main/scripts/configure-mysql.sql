CREATE DATABASE sf_dev;
CREATE DATABASE sf_prod;

CREATE USER 'sf_dev_user'@'localhost' IDENTIFIED BY 'adrian';
CREATE USER 'sf_prod_user'@'localhost' IDENTIFIED BY 'adrian';

GRANT SELECT ON sf_dev.* to 'sf_dev_user'@'localhost';
GRANT INSERT ON sf_dev.* to 'sf_dev_user'@'localhost';
GRANT UPDATE ON sf_dev.* to 'sf_dev_user'@'localhost';
GRANT DELETE ON sf_dev.* to 'sf_dev_user'@'localhost';
GRANT SELECT ON sf_prod.* to 'sf_prod_user'@'localhost';
GRANT INSERT ON sf_prod.* to 'sf_prod_user'@'localhost';
GRANT UPDATE ON sf_prod.* to 'sf_prod_user'@'localhost';
GRANT DELETE ON sf_prod.* to 'sf_prod_user'@'localhost';