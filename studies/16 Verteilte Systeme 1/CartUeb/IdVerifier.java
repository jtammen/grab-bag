/*
 *
 * Copyright 2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

public class IdVerifier {

    public IdVerifier() {
    }

    public boolean validate(String id) {

       boolean result = true;
       for (int i = 0; i < id.length(); i++) {
         if (Character.isDigit(id.charAt(i)) == false)
            result = false;
       }
       return result;
    }
}
