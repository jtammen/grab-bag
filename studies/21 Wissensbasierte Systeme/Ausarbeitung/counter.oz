declare Counter
class Counter 
   attr val
   meth browse 
      {Browse @val}
   end 
   meth inc(Value)
      val := @val + Value
   end 
   meth init(Value)
      val := Value
   end 
end

declare C in
C = {New Counter init(23)}
{C inc(1)}
{C browse}
