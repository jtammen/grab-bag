import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

//import Converter;
//import ConverterHome;

public class ConverterClient {

   public static void main(String[] args) {
       try {
		   Context initial = new InitialContext();
		   Object objref = initial.lookup("wi-pc01Converter");
		   ConverterHome home = (ConverterHome)PortableRemoteObject.narrow(objref, ConverterHome.class);

		   Converter demEuroConverter = home.create();
		   double betrag = demEuroConverter.DemToEuro(100.00);
		   System.out.println(String.valueOf(betrag));

		   betrag = demEuroConverter.EuroToDem(100.00);
		   System.out.println(String.valueOf(betrag));

       } catch (Exception ex) {
           System.err.println("Caught an unexpected exception!");
           ex.printStackTrace();
       }
   } 
} 
