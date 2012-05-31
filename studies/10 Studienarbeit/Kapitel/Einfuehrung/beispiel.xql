for $a in document("beispiel.xml")/Studienarbeit/Autoren/Autor
let $namensTeile := tokenize($a/@Name, "\s+")
where starts-with($namensTeile[2], "B")
order by $a
return
<Student>
  <Name>{data($a/@Name)}</Name>
  {$a/Email}
</Student>
