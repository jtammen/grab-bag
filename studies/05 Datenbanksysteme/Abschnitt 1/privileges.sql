GRANT SELECT ON attraktion TO dbsys01;
GRANT SELECT ON bild TO dbsys01;
GRANT SELECT, INSERT ON buchung TO dbsys01;
GRANT SELECT ON entfernung TO dbsys01;
GRANT SELECT ON ferienwohnung TO dbsys01;
GRANT SELECT ON fluglinie TO dbsys01;
GRANT SELECT ON fluglinie_land TO dbsys01;
GRANT SELECT, ALTER, INSERT ON kunde TO dbsys01;
GRANT SELECT ON land TO dbsys01;

GRANT SELECT, ALTER, INSERT, DELETE ON attraktion TO dbsys02;
GRANT SELECT, ALTER, INSERT, DELETE ON bild TO dbsys02;
GRANT SELECT ON buchung TO dbsys02;
GRANT SELECT, ALTER, INSERT, DELETE ON entfernung TO dbsys02;
GRANT SELECT, ALTER, INSERT, DELETE ON ferienwohnung TO dbsys02;
GRANT SELECT ON fluglinie TO dbsys02;
GRANT SELECT ON fluglinie_land TO dbsys02;
GRANT SELECT ON kunde TO dbsys02;
GRANT SELECT ON land TO dbsys02;

GRANT SELECT ON ferienwohnung TO dbsys33;
GRANT SELECT ON land TO dbsys33;
GRANT SELECT ON fluglinie TO dbsys33;
GRANT SELECT ON entfernung TO dbsys33;
GRANT SELECT ON attraktion TO dbsys33;
