declare
fun {Map List Function}
   case List of nil then nil
   [] Head|Tail then {Function Head}|{Map Tail Function}
   end
end

{Browse {Map [1 2 3 4] fun {$ X} (X+X)*2 end}}