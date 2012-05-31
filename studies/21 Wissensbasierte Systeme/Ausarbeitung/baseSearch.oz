declare 
proc {Father F C}
   choice F=terach C=abraham
   [] F=terach C=nachor
   [] F=terach C=haran
   [] F=abraham C=isaac
   [] F=haran C=lot
   [] F=haran C=milcah
   [] F=haran C=yiscah
   end
end

proc {ChildrenFun X Kids}
   {Search.base.all proc {$ K} {Father X K} end Kids}
end

{Browse {ChildrenFun terach}}