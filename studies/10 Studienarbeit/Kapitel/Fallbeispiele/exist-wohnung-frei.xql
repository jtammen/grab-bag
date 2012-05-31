let $von := xs:date(request:request-parameter("Zeitraum-Von", current-date())),
    $bis := xs:date(request:request-parameter("Zeitraum-Bis", current-date())),
    $wnr := xs:integer(request:request-parameter("Wohnung-Nr", 0))
for $buchung in //Buchung[Wohnung/@Nr = $wnr]
where
(
  ( 	
    xs:date($buchung/Zeitraum/@von) < $bis
    and xs:date($buchung/Zeitraum/@bis) > $von
  )
  or
  (     
    xs:date($buchung/Zeitraum/@von) > $bis
    and xs:date($buchung/Zeitraum/@bis) < $von
  )
)
return count($buchung)
