CREATE TABLE buchung_storniert (
    bunr        NUMBER(10),
    rdatum      DATE            DEFAULT SYSDATE,
    stornodatum DATE            DEFAULT SYSDATE,
    von         DATE,
    bis         DATE,
    kdnr        NUMBER(10),
    fwnr        NUMBER(10) );

CREATE TRIGGER buchung_stornieren
    BEFORE DELETE ON buchung
    FOR EACH ROW
    BEGIN
        INSERT INTO buchung_storniert VALUES (:old.bunr, :old.rdatum, SYSDATE(), :old.von, :old.bis, :old.kdnr, :old.fwnr);
    END buchung_stornieren;
