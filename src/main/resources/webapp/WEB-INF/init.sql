CREATE SCHEMA SYS;

create table INFORMATION_SCHEMA.API 
(URL VARCHAR(255) PRIMARY KEY,
REMARK VARCHAR(255),
RETURN_LOGIC VARCHAR(10240),
RETURN_TYPE VARCHAR(36),
ENABLED BOOLEAN )