let $l := doc("/db/studienarbeit/ivbf/laender.xml")//Land
for $w in doc("/db/studienarbeit/ivbf/wohnungen.xml")//Wohnung[Land/@Nr = $l/@Nr] 
let $land := $l[@Nr &= $w/Land/@Nr]
where match-all($w/Name, "Max") 
and $w/Anzahl-Zimmer = 3
and $w/Preis-Pro-Tag <= 450
and $l/@Nr = 23
and exists($w/Ausstattung/Telefon)
return
(: s. obiges Listing :)
