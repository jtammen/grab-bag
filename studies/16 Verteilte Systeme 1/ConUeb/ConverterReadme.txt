Quellen 
converterEJB.java und 
ConverterClient.java

sind unvollständig !



Building EJB Application

compileEJB.bat
compileClient.bat
dptool.bat

dptool:    

  new application
     XyConverterApp.ear (in dir Converter)
  new enterprise bean (wizard) 
     XyConverterJAR
     Converter.class, ConverterEJB.class, ConverterHome,class
     XyConverterBean
  JNDINames
     XyConverter
  deploy application
    

ConverterClient.bat
   Name des ...Client.jar File ändern
   ConverterClient aufrufen  
 


   