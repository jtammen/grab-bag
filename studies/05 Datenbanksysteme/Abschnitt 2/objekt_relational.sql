CREATE OR REPLACE TYPE LandT AS OBJECT (
	landcode    VARCHAR2(4),
	fullname    VARCHAR2(100)
);
CREATE TABLE OR_Land OF LandT ( landcode PRIMARY KEY );
INSERT INTO OR_Land VALUES ( LandT( 'de', 'Deutschland' ) );
INSERT INTO OR_LAND VALUES ( LandT( 'fr', 'Frankreich' ) );

SELECT * FROM OR_Land;


CREATE OR REPLACE TYPE BildT AS OBJECT (
	binr        NUMBER(10),
	path        VARCHAR2(100)
);
CREATE TYPE Bilder_NT AS TABLE OF BildT;

CREATE OR REPLACE TYPE KundeT AS OBJECT (
    kdnr        NUMBER(10),
    vorname     VARCHAR2(50),
    nachname    VARCHAR2(50),
    zip         NUMBER(5),
    stadt       VARCHAR2(50),
    strasse     VARCHAR2(50),
    hsnr        VARCHAR2(4),
    blz         NUMBER(15),
    kontonr     NUMBER(15),
    land        REF LandT,
    bilder      Bilder_NT );

CREATE TABLE OR_Kunde OF KundeT (
	PRIMARY KEY (kdnr)
) NESTED TABLE bilder STORE AS Bilder_NT_TAB;

INSERT INTO OR_Kunde
  VALUES (
    2, 'vorname2', 'nachname2', 12345, 'stadt2', 'strasse2', '12a',
    12345, 12345,
    (SELECT REF(l) FROM OR_Land l WHERE l.landcode = 'fr'),
    Bilder_NT(
    	BildT(1, '/tmp/test1.jpg'),
    	BildT(2, '/tmp/test2.jpg')
    )
  );

SELECT vorname FROM OR_Kunde k;
SELECT DEREF(land).fullname FROM OR_Kunde k;

SELECT k.vorname,b.path 
FROM OR_Kunde k,
TABLE (k.bilder) b
WHERE k.land.landcode = 'de';




CREATE OR REPLACE TYPE AusListe AS VARRAY(2) OF ZusatzT;
CREATE OR REPLACE TYPE FerienwohnungT AS OBJECT (
	fwnr		NUMBER(10),
	anz_zimmer	NUMBER(1),
	groesse		NUMBER(5,2),
	preis		NUMBER(5,2),
	land		REF LandT,
	zusaetze	AusListe
);
CREATE TABLE OR_Ferienwohnung OF FerienwohnungT;

INSERT INTO OR_Ferienwohnung
  VALUES (
    1, 2, 14.5, 23.6,
    (SELECT REF(l) FROM OR_Land l WHERE l.landcode = 'de'),
    AusListe(
    	ZusatzT('Sauna')
    )
  );
  
INSERT INTO OR_Ferienwohnung
  VALUES (
    2, 3, 65.5, 93.6,
    (SELECT REF(l) FROM OR_Land l WHERE l.landcode = 'fr'),
    AusListe(
    	ZusatzT('Schwimmbad')
    )
  );



CREATE OR REPLACE TYPE BuchungT AS OBJECT (
    bunr    NUMBER(10),
    rdatum  DATE,
    von     DATE,
    bis     DATE,
    kunde   REF KundeT,
    wohnung REF FerienwohnungT
);
CREATE TABLE OR_Buchung OF BuchungT;

INSERT INTO OR_Buchung
  VALUES (
    1, '2004-01-02', '2003-12-01', '2003-12-25',
    (SELECT REF(k) FROM OR_Kunde k WHERE k.kdnr= 1),
    (SELECT REF(w) FROM OR_Ferienwohnung w WHERE w.fwnr = 1)
  );
INSERT INTO OR_Buchung
  VALUES (
    2, '2005-01-02', '2005-02-22', '2005-03-25',
    (SELECT REF(k) FROM OR_Kunde k WHERE k.kdnr= 2),
    (SELECT REF(w) FROM OR_Ferienwohnung w WHERE w.fwnr = 2)
  );
  
SELECT b.kunde.vorname FROM OR_BUCHUNG b;
SELECT b.wohnung.land.fullname FROM OR_Buchung b;


CREATE OR REPLACE TYPE ZusatzT AS OBJECT (
	bezeich     VARCHAR2(50)
);
CREATE OR REPLACE TYPE SaunaT UNDER ZusatzT (
	saunatyp    VARCHAR2(50)
);
CREATE OR REPLACE TYPE SchwimmbadT UNDER ZusatzT (
	laenge      NUMBER(5),
	breite      NUMBER(5)
);

