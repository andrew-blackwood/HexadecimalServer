package httpserver;

import java.util.Random;

/**
 * Filename:    RandomHex.java
 * Purpose:     Generate a random hexadecimal number.
 * 
 * @author      Andrew Blackwood
 * @version     1.0, 22/09/2016
 */

public class RandomHex {
    
    private static final char[] HEX_CHARS = {'A','B','C','D','E','F'};
    private final Random randomIntGenerator = new Random();
    
    public RandomHex() {}
    
    public String getRandomHex(int length) {
        
        String result = "";
         
        try {
            for (int i = 1; i <= length; i++) {
                int randInt = this.randomIntGenerator.nextInt(16);

                if (randInt > 9) {
                    result += Character.toString(HEX_CHARS[randInt - 10]);
                }
                else {
                    result += randInt;
                }
            }
        }
        catch (Exception e) {
            ErrorHandler error = new ErrorHandler();
            error.print("Error: Could not generate a hexadecimal number of"
                    + " length " + length + ".");
        }
        
        return result;    
    }
}
