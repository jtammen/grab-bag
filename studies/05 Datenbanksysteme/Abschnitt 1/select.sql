SELECT f.fwnr, b.von, b.bis
FROM ferienwohnung f
LEFT OUTER JOIN buchung b
ON ( b.fwnr = f.fwnr )
WHERE
(
  ( b.von < TO_DATE('2004-11-01', 'YYYY-MM-DD') AND b.bis < TO_DATE('2004-11-21', 'YYYY-MM-DD') )
  OR 
  ( b.von > TO_DATE('2004-11-01', 'YYYY-MM-DD') AND b.bis > TO_DATE('2004-11-21', 'YYYY-MM-DD') )
  OR 
  ( b.bunr IS NULL )
)
AND f.landcode = 'ch'
AND f.hat_sauna = 1;

# Alternative?
SELECT f.*
FROM ferienwohnung f
WHERE EXISTS
(
  SELECT * 
  FROM buchung b
  WHERE 
  ( 
   ( b.von < '2004-11-01' AND b.bis > '2004-11-21' )
    OR 
   ( b.von > '2004-11-01' AND b.bis < '2004-11-21' )
  )
)
AND f.hat_sauna = 1
AND f.landcode = 'ch';

SELECT f.fwnr,f.land,e.km,e.atnr
FROM ferienwohnung f
JOIN entfernung e
ON (f.fwnr = e.fwnr)
    WHERE e.atnr = 2
    AND f.landcode = 'fr'
    AND e.km <= 100;

SELECT f.landcode,COUNT(*) FROM 
ferienwohnung f
JOIN buchung b
ON (f.fwnr = b.fwnr)
    JOIN land l
    ON (l.landcode = f.landcode)
    GROUP BY f.landcode;
