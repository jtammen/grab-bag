for $w in doc("/db/studienarbeit/ivbf/wohnungen.xml")//Wohnung, 
    $l in doc("/db/studienarbeit/ivbf/laender.xml")//Land 
where $w/Land/@Nr = $l/@Nr 
and match-all($w/Name, "Max") 
and $w/Anzahl-Zimmer = 3 
and $w/Preis-Pro-Tag <= 450 
and $l/@Nr = 23
and exists($w/Ausstattung/Telefon)
return 
<Wohnung Nr="{data($w/@Nr)}">
  {$w/Name}
  {$w/Groesse}
  {$w/Anzahl-Zimmer}
  {$w/Preis-Pro-Tag}
  {$w/Ausstattung}
  {$l}
</Wohnung>
