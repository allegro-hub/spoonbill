package org.allegro.spoonbill.test.util;

public class ArrayUtils {

    public static String[] removeElements(String[] array, String... toRemoveElements) {
        
        String[] returnValue = new String[array.length - toRemoveElements.length];
        
        int currentIndex = 0;
        for(int index = 0; index < array.length; index++) {
            
            if(isExisting(toRemoveElements, array[index])) {
                continue;               
            } else {
                
                returnValue[currentIndex] = array[index];
                currentIndex++;
            }
        }
        
        return returnValue;
    }
    
    public static boolean isExisting(String[] array, String element) {
        
        for(int index = 0; index < array.length; index++) {
            
            if(array[index].equalsIgnoreCase(element)) {
                return true;
            }
        }
        
        return false;
    }

    public static String[] addElements(String[] array, String... toAddElements) {
        
        // found difference length
        int diffLenght = 0;
        for(int index = 0; index < toAddElements.length; index++) {
            
            if(isExisting(array, toAddElements[index])) {
                continue;               
            } else {
                diffLenght++;
            }
        }
        
        String[] returnValue = new String[array.length + diffLenght];
        int currentIndex = 0;
        
        // Copy the existing ones to the new array
        for(int index = 0; index < array.length; index++) {
            
            returnValue[currentIndex] = array[index];
            currentIndex++;
        }
        
        // Insert different ones
        for(int index = 0; index < toAddElements.length; index++) {
            
            if(isExisting(array, toAddElements[index])) {
                continue;               
            } else {
                
                returnValue[currentIndex] = toAddElements[index];
                currentIndex++;
            }
        }
        
        return returnValue;
    }
    
}
