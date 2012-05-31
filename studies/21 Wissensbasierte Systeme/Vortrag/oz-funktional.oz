declare Len
fun {Len Xs}
   case Xs of nil then 0
   [] X|Xr then success({Len Xr})
   end
end

{Browse {Len [hallo 2 3 xyz]}}
