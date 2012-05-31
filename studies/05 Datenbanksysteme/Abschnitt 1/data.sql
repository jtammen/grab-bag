INSERT INTO land VALUES ( 'de', 'Deutschland' );
INSERT INTO land VALUES ( 'fr', 'Frankreich' );
INSERT INTO land VALUES ( 'ch', 'Schweiz' );
INSERT INTO land VALUES ( 'it', 'Italien' );

INSERT INTO attraktion VALUES ( 1, 'Freizeitpark', 'Europapark', 'Beschrieb', 'de'  );
INSERT INTO attraktion VALUES ( 2, 'Freizeitpark', 'Disneyland Paris', 'Beschrieb', 'fr'  );
INSERT INTO attraktion VALUES ( 3, 'Freizeitpark', 'Connyland', 'Beschrieb', 'ch'  );
INSERT INTO attraktion VALUES ( 4, 'Freizeitpark', 'Acquapark', 'Beschrieb', 'it'  );

INSERT INTO ferienwohnung VALUES ( 1, 1, 10.5, 20.5, 'de', 0, 0  );
INSERT INTO ferienwohnung VALUES ( 2, 2, 22.5, 67.5, 'de', 1, 0  );
INSERT INTO ferienwohnung VALUES ( 3, 3, 40.5, 80.5, 'fr', 0, 1  );
INSERT INTO ferienwohnung VALUES ( 4, 2, 72.5, 127.5, 'fr', 1, 1  );
INSERT INTO ferienwohnung VALUES ( 5, 2, 10.5, 20.5, 'ch', 0, 0  );
INSERT INTO ferienwohnung VALUES ( 6, 4, 92.5, 97.5, 'ch', 1, 0  );
INSERT INTO ferienwohnung VALUES ( 7, 3, 50.5, 70.5, 'it', 0, 1  );
INSERT INTO ferienwohnung VALUES ( 8, 1, 12.5, 27.5, 'it', 0, 0  );

INSERT INTO entfernung VALUES ( 100.98, 1, 1 );
INSERT INTO entfernung VALUES ( 220.38, 2, 1 );
INSERT INTO entfernung VALUES ( 60.4, 3, 2 );
INSERT INTO entfernung VALUES ( 45.5, 4, 2 );
INSERT INTO entfernung VALUES ( 600.98, 5, 3 );
INSERT INTO entfernung VALUES ( 9.18, 6, 3 );
INSERT INTO entfernung VALUES ( 122.94, 7, 4 );
INSERT INTO entfernung VALUES ( 56.38, 8, 4 );

INSERT INTO fluglinie VALUES ( 1, 'Lufthansa' );
INSERT INTO fluglinie VALUES ( 2, 'Air France' );
INSERT INTO fluglinie VALUES ( 3, 'Swiss Air' );
INSERT INTO fluglinie VALUES ( 3, 'British Airways' );

INSERT INTO fluglinie_land VALUES ( 1, 'de' );
INSERT INTO fluglinie_land VALUES ( 1, 'ch' );
INSERT INTO fluglinie_land VALUES ( 2, 'fr' );
INSERT INTO fluglinie_land VALUES ( 2, 'ch' );
INSERT INTO fluglinie_land VALUES ( 3, 'ch' );
INSERT INTO fluglinie_land VALUES ( 3, 'de' );
INSERT INTO fluglinie_land VALUES ( 4, 'fr' );

INSERT INTO kunde VALUES ( 1, 'Hans', 'Müller', 12345, 'Hamburg', 'Sonnenweg', '1a', 123456, 7890123, 'de' );
INSERT INTO kunde VALUES ( 2, 'Erwin', 'Schulze', 12345, 'Berlin', 'Blumenweg', '10', 123456, 6547645, 'de' );
INSERT INTO kunde VALUES ( 3, 'Francois', 'LeGrand', 54566, 'Paris', 'La Rue', '90', 476566, 565665, 'fr' );
INSERT INTO kunde VALUES ( 4, 'Michele', 'LaPetite', 6774, 'Grenoble', 'La Rue', '10', 645645, 3435685, 'fr' );
INSERT INTO kunde VALUES ( 5, 'Urs', 'Huber', 54356, 'Bern', 'Bergstrasse', '34', 5435435, 77657, 'ch' );
INSERT INTO kunde VALUES ( 6, 'Chris', 'Gruetli', 65465, 'Zürich', 'Feldweg', '54', NULL, NULL, 'ch' );
INSERT INTO kunde VALUES ( 7, 'Michel', 'Grimaldi', 5435, 'Rom', 'Testweg', '46c', NULL, NULL, 'it' );
INSERT INTO kunde VALUES ( 8, 'Ernesto', 'Venecia', 5436, 'Rom', 'Teststrasse', '54', 67567, 54544, 'it' );

INSERT INTO buchung VALUES ( 1, '2004-01-02', '2003-12-01', '2003-12-25', 1, 4 );
INSERT INTO buchung VALUES ( 2, '2005-01-02', '2004-11-02', '2004-12-01', 4, 7 );
INSERT INTO buchung VALUES ( 3, '2004-08-01', '2004-02-22', '2004-03-05', 8, 6 );
INSERT INTO buchung VALUES ( 4, '2004-07-12', '2004-06-12', '2004-06-25', 2, 1 );
INSERT INTO buchung VALUES ( 5, '2005-02-24', '2005-01-22', '2005-02-13', 3, 2 );
