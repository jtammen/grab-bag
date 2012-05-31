declare
fun {Append Xs Ys}
   case Xs of nil then Ys
   [] X|Xr then X|{Append Xr Ys} end
end

declare X Y in
{Browse {Append [1 23 abc] X}}