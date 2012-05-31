declare Money
local
   proc {Money Root}
      S E N D M O R Y   % Variablen deklarieren
   in 
      Root = sol(s:S e:E n:N d:D m:M o:O r:R y:Y)   % Datenstruktur
      Root ::: 0#9                                  % Domainconstraint
      {FD.distinct Root}                            % paarweise versch.
      S \=: 0                                       % S und M != 0
      M \=: 0
      1000*S + 100*E + 10*N + D +                   % Lsg. der Gleichung
      1000*M + 100*O + 10*R + E =:
      10000*M + 1000*O + 100*N + 10*E + Y
      {FD.distribute ff Root}                       % Distribuierung
   end
in
   {Browse {SearchAll Money}}                       % Loesung berechnen
   {ExploreOne Money}                               % Suchbaum anzeigen
end
