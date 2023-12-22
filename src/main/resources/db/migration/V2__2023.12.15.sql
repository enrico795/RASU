CREATE TABLE rasu_episodi (
    idEpisodio integer not null,
    ulss varchar(15) not null,
    idPresidio varchar(20) not null,
    dataOraInizioEpisodio TIMESTAMP WITH TIME ZONE not null,
    codiceFiscale varchar(16) not null,
    dataOraIncidente TIMESTAMP WITH TIME ZONE not null,
    luogoIncidente varchar(256) not null,
    comuneIncidente varchar(32) not null,
    modalitaAccesso varchar(32) not null,
    causaIncidente varchar(256) not null,
    modalitaArrivo varchar(32) not null,
    sostanzeAlcoliche bool DEFAULT false,
    sostanzeStupefacenti bool DEFAULT false
);

CREATE TABLE IF NOT EXISTS rasu_episodi_note (
    idNota serial not null,
    utente uuid not null,
    nota varchar(512) not null,
    episodioId integer not null
);

CREATE TABLE IF NOT EXISTS rasu_pazienti (
    codiceFiscale varchar(16) not null,
    nome varchar(128),
    cognome varchar(128),
    dataDiNascita date
);