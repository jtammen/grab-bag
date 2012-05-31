import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class CartClient {

   public static void main(String[] args) {
       try {
		   Context initial = new InitialContext();
		   Object objref = initial.lookup("wi-pc01Cart");
		   CartHome home = (CartHome)PortableRemoteObject.narrow(objref, CartHome.class);
           Cart shoppingCart = home.create("Jan Tammen");

           shoppingCart.addBook("Krieg und Frieden");
           shoppingCart.addBook("1984");
           shoppingCart.addBook("Der Mann mit der Sichel");
           
           Vector bookList = new Vector();
		   bookList = shoppingCart.getContents();
           Enumeration enumer = bookList.elements();
           while (enumer.hasMoreElements()) {
              String title = (String) enumer.nextElement();
              System.out.println(title);
           }

           shoppingCart.removeBook("1984");
		   //shoppingCart.removeBook("Die Bibel");
           //shoppingCart.remove();

		   System.out.println("--------------------------");

		   bookList = shoppingCart.getContents();
		   enumer = bookList.elements();
		   while (enumer.hasMoreElements())
		   {
			   String title = (String)enumer.nextElement();
			   System.out.println(title);
		   }

       } catch (BookException ex) {
           System.err.println("Caught a BookException: " + ex.getMessage());
       } catch (Exception ex) {
           System.err.println("Caught an unexpected exception!");
           ex.printStackTrace();
       }
   } 
} 
