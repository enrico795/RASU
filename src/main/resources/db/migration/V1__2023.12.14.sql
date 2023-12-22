CREATE TABLE IF NOT EXISTS rasu_role (
    id serial NOT NULL PRIMARY KEY,
    name varchar(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS rasu_user (
    id uuid NOT NULL PRIMARY KEY,
    username varchar(64) NOT NULL,
    password varchar(64) DEFAULT NULL,
    first_name varchar(32) DEFAULT NULL,
    middle_name varchar(32) DEFAULT NULL,
    last_name varchar(32) DEFAULT NULL,
    locale varchar(2) DEFAULT NULL,
    avatar_url varchar(2048) DEFAULT NULL,
    active boolean DEFAULT false NOT NULL,
    created_at timestamp without time zone NOT NULL
);

CREATE TABLE IF NOT EXISTS rasu_authority (
    id serial NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS rasu_role_authority (
    role_id integer NOT NULL,
    authority_id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id uuid NOT NULL,
    role_id integer NOT NULL
);


INSERT INTO rasu_role (id, name) VALUES (1, 'ADMIN');
INSERT INTO rasu_role (id, name) VALUES (2, 'READ_REF');
INSERT INTO rasu_role (id, name) VALUES (3, 'REF');
INSERT INTO rasu_role (id, name) VALUES (4, 'READ_ARCH');
INSERT INTO rasu_role (id, name) VALUES (5, 'ARCH');
INSERT INTO rasu_role (id, name) VALUES (6, 'READ_PAZ');
INSERT INTO rasu_role (id, name) VALUES (7, 'PAZ');

INSERT INTO rasu_user (id, username, password, first_name, last_name, active, created_at)
VALUES ('7f000001-8a56-11d1-818a-56e25ae30000', 'admin', '{noop}secret', 'RASU', 'ADMIN', true, NOW());

INSERT INTO rasu_user (id, username, password, first_name, last_name, active, created_at)
VALUES ('7f000001-8a56-11d1-818a-56e25ae30001', 'enrico.zecchinato@comune.verona.it', NULL, 'ENRICO', 'ZECCHINATO', true, NOW());

INSERT INTO rasu_user (id, username, password, first_name, last_name, active, created_at)
VALUES ('7f000001-8a56-11d1-818a-56e25ae30002', 'francesco.girardi@comune.verona.it', NULL, 'FRANCESCO', 'GIRARDI', true, NOW());

INSERT INTO user_role (user_id, role_id) VALUES ('7f000001-8a56-11d1-818a-56e25ae30000', 1);
INSERT INTO user_role (user_id, role_id) VALUES ('7f000001-8a56-11d1-818a-56e25ae30001', 1);
INSERT INTO user_role (user_id, role_id) VALUES ('7f000001-8a56-11d1-818a-56e25ae30002', 1);

INSERT INTO rasu_authority (id, name) VALUES (1, 'ADMIN_READ');
INSERT INTO rasu_authority (id, name) VALUES (2, 'ADMIN_WRITE');
INSERT INTO rasu_authority (id, name) VALUES (3, 'REF_WRITE');
INSERT INTO rasu_authority (id, name) VALUES (4, 'REF_READ');
INSERT INTO rasu_authority (id, name) VALUES (5, 'ARCH_WRITE');
INSERT INTO rasu_authority (id, name) VALUES (6, 'ARCH_READ');
INSERT INTO rasu_authority (id, name) VALUES (7, 'PAZ_WRITE');
INSERT INTO rasu_authority (id, name) VALUES (8, 'PAZ_READ');

-- ADMIN can read and write
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 1);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 2);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 3);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 4);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 5);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 6);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 7);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (1, 8);

-- READ_REF può leggere i referti
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (2, 4);

-- REF può modificare i referti
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (3, 3);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (3, 4);

-- READ_ARCH può leggere i referti
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (4, 6);

-- ARCH può modificare l'archivio
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (5, 5);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (5, 6);

-- READ_PAZ può leggere i pazienti
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (6, 8);

-- ARCH può modificare l'archivio
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (7, 7);
INSERT INTO rasu_role_authority (role_id, authority_id) VALUES (7, 8);

CREATE TABLE IF NOT EXISTS CVRRASU (
    PRESIDIO             varchar(8),
    SCHEDAPS             varchar(14),
    PS_DESC              varchar(80),
    DATA_EVENTO          varchar(16),
    COGNOME              varchar(30),
    NOME                 varchar(20),
    DATA_NASCITA         DATE,
    CODICE_FISCALE       varchar(16),
    CAUSA_INCIDENTE      varchar(30),
    LUOGO_INCIDENTE      varchar(240),
    COMUNE_INCIDENTE     varchar(30),
    MODALITA_ARRIVO      varchar(45),
    MODALITA_ACCESSO     varchar(45),
    RASU_PROTOCOLLO      numeric(10) NOT NULL,
    RASU_DATAINS         DATE,
    RASU_DATAINVIO       DATE,
    RASU_NOTE            varchar(4000) NOT NULL,
    PM_IP                varchar(15),
    PM_USER              varchar(16) NOT NULL,
    PM_COGNOME           varchar(30),
    PM_NOME              varchar(20),
    PMVA_COGNOME         varchar(30),
    PMVA_NOME            varchar(20),
    PMVA_DATANASCITA     DATE,
    PMVA_RESIDENZA       varchar(50),
    PMVA_LUOGOINCIDENTE  varchar(240)
);

CREATE TABLE IF NOT EXISTS CVRRASUCONF (
    CHIAVE  varchar(16) NOT NULL,
    VALORE  varchar(256)
);

CREATE TABLE IF NOT EXISTS CVRRASULOG (
    DATALOG   DATE NOT NULL,
    PM_IP     varchar(15),
    PM_USER   varchar(16),
    PRESIDIO  varchar(8),
    SCHEDAPS  varchar(14),
    TESTOLOG  varchar(128)
);

CREATE TABLE CVRRASUREFERTI (
    RASU_PROTOCOLLO     numeric(10) NOT NULL,
    INCIDENTE_PROT      varchar(9),
    REFERTO_DATA        DATE,
    REFERTO_ESITO       varchar(32),
    POSITIV_ALCOL       numeric(3,2),
    POSITIV_DROGHE      varchar(128),
    VEICOLO_PROVV       varchar(32),
    VEICOLO_PROVV_DATA  DATE,
    RILEV_REPARTO       varchar(32),
    RILEV_AGENTI        varchar(32),
    RILEV_AVVISO        varchar(32),
    RECORD_LOG          varchar(2000),
    PRESIDIO            varchar(8) NOT NULL,
    SCHEDAPS            varchar(14) NOT NULL,
    REFERTO_NOTE        varchar(128),
    DEF_RAPPORTO        varchar(32)
);

CREATE TABLE TAB_DROGHE (
    ID    numeric(3),
    NOME  varchar(32) NOT NULL
);

CREATE TABLE TAB_OSPRICOVERO (
    ID    numeric(3),
    NOME  varchar(80) NOT NULL
);

CREATE TABLE TAB_PROVVEICOLO (
    ID    numeric(3),
    NOME  varchar(32) NOT NULL
);

CREATE TABLE TAB_RASURICHIESTE (
    ID           numeric(3),
    NOME         varchar(32) NOT NULL,
    DESCRIZIONE  varchar(384) NOT NULL
);

CREATE TABLE TAB_REFESITO (
    ID    numeric(3),
    NOME  varchar(32) NOT NULL
);


