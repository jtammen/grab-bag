CREATE TABLE land (
    landcode    VARCHAR2(4),
    fullname    VARCHAR2(100)   NOT NULL,

    CONSTRAINT  pk_land     PRIMARY KEY (landcode) );

CREATE TABLE ferienwohnung (
    fwnr        NUMBER(10),
    anz_zimmer  NUMBER(1)   NOT NULL,
    groesse     NUMBER(5,2) NOT NULL,
    preis       NUMBER(5,2) NOT NULL,
    landcode    VARCHAR2(4) NOT NULL,
    hat_sauna   NUMBER(1),
    hat_schwb   NUMBER(1),

    CONSTRAINT  pk_ferienwohnung    PRIMARY KEY (fwnr),
    CONSTRAINT  fk_ferienwohnung    FOREIGN KEY (landcode) 
                                    REFERENCES land (landcode) 
                                    ON DELETE SET NULL,
    CONSTRAINT  anz_zimmer_ok       CHECK   (anz_zimmer > 0),
    CONSTRAINT  groesse_ok          CHECK   (groesse > 0),
    CONSTRAINT  preis_ok            CHECK   (preis > 0),
    CONSTRAINT  hat_sauna_ok        CHECK   (hat_sauna  IN (0, 1)),
    CONSTRAINT  hat_schwb_ok        CHECK   (hat_schwb  IN (0, 1)) );

CREATE TABLE bild (
    binr        NUMBER(10),
    path        VARCHAR2(100)   NOT NULL,
    fwnr        NUMBER(10),

    CONSTRAINT  pk_bild     PRIMARY KEY (binr),
    CONSTRAINT  fk_bild     FOREIGN KEY (fwnr) 
                            REFERENCES ferienwohnung (fwnr)
                            ON DELETE CASCADE );

CREATE TABLE kunde (
    kdnr        NUMBER(10),
    vorname     VARCHAR2(50)    NOT NULL,
    nachname    VARCHAR2(50)    NOT NULL,
    zip         NUMBER(5)       NOT NULL,
    stadt       VARCHAR2(50)    NOT NULL,
    strasse     VARCHAR2(50)    NOT NULL,
    hsnr        VARCHAR2(4)     NOT NULL,
    blz         NUMBER(15),
    kontonr     NUMBER(15),
    landcode    VARCHAR2(4),

    CONSTRAINT  pk_kunde    PRIMARY KEY (kdnr),
    CONSTRAINT  fk_kunde    FOREIGN KEY (landcode) 
                            REFERENCES land (landcode)
                            ON DELETE SET NULL );

CREATE TABLE buchung (
    bunr        NUMBER(10),
    rdatum      DATE            DEFAULT SYSDATE,    
    von         DATE,                               
    bis         DATE,   
    kdnr        NUMBER(10),
    fwnr        NUMBER(10),

    CONSTRAINT  pk_buchung  PRIMARY KEY (bunr),
    CONSTRAINT  fk1_buchung FOREIGN KEY (kdnr) 
                            REFERENCES kunde (kdnr)
                            ON DELETE SET NULL,
    CONSTRAINT  fk2_buchung FOREIGN KEY (fwnr) 
                            REFERENCES ferienwohnung (fwnr)
                            ON DELETE SET NULL,
    CONSTRAINT  von_bis_ok  CHECK   (bis > von) );

CREATE TABLE attraktion (
    atnr        NUMBER(10),
    typ         VARCHAR2(100),
    fullname    VARCHAR2(100)   NOT NULL,
    beschrieb   VARCHAR2(255),
    landcode    VARCHAR2(4),

    CONSTRAINT  pk_attraktion   PRIMARY KEY (atnr),
    CONSTRAINT  fk_attraktion   FOREIGN KEY (landcode) 
                                REFERENCES land (landcode)
                                ON DELETE CASCADE );

CREATE TABLE entfernung (
    km          NUMBER(6,2) NOT NULL,
    fwnr        NUMBER(10),
    atnr        NUMBER(10),

    CONSTRAINT  fk_entfernung1  FOREIGN KEY (fwnr)  
                                REFERENCES ferienwohnung (fwnr)
                                ON DELETE CASCADE,
    CONSTRAINT  fk_entfernung2  FOREIGN KEY (atnr)  
                                REFERENCES attraktion (atnr)
                                ON DELETE CASCADE );

CREATE TABLE fluglinie (
    flnr        NUMBER(10),
    fullname    VARCHAR2(100)    NOT NULL,

    CONSTRAINT  pk_fluglinie    PRIMARY KEY (flnr) );

CREATE TABLE fluglinie_land (
    flnr        NUMBER(10),
    landcode    VARCHAR2(4),

    CONSTRAINT  fk_fluglinie_land1  FOREIGN KEY (flnr)      
                                    REFERENCES fluglinie (flnr)
                                    ON DELETE CASCADE,
    CONSTRAINT  fk_fluglinie_land2  FOREIGN KEY (landcode)  
                                    REFERENCES land (landcode)
                                    ON DELETE CASCADE );
