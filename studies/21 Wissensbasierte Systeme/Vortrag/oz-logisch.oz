declare
proc {Append Xs Ys Zs}
   case Xs of nil then Zs = Ys % 'ask': case, 'tell': Zs=Ys 
   [] X|Xr then Zr in
      Zs = X|Zr {Append Xr Ys Zr}
   end
end

declare X Y in
{Browse {Append [1 23 abc] [X Y]}}