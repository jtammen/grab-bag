declare Demo
local
   proc {Demo Root}
      X Y Z
   in
      Root = loesung(x:X y:Y z:Z)
      X :: 1#3
      Y :: 2#3
      Z :: 1#4
      X <: Y
      X*X =: Z
      {FD.distribute ff Root}
   end
in
   {Browse {SearchOne Demo}}
   {ExploreAll Demo}
end


{Browse [X Y Z]}