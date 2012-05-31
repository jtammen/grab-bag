declare
proc {Append Xs Ys Zs}
   choice
      Xs = nil
      Zs = Ys
   [] X Xr Zr in
      Xs = X|Xr
      Zs = X|Zr
      {Append Xr Ys Zr}
   end
end

declare Anfrage Suche in
proc {Anfrage Loesung} X Y
in
   {Append X Y [1 2 3 4 5]}
   Loesung = sol(X Y)
end

Suche = {New Search.object script(Anfrage)}
{Browse {Suche next($)}}