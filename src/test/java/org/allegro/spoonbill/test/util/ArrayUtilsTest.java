package org.allegro.spoonbill.test.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void testSizeForRemoveElements() {
        
        String[] originalArray = new String[5];
        originalArray[0] = "A";
        originalArray[1] = "B";
        originalArray[2] = "C";
        originalArray[3] = "D";
        originalArray[4] = "E";
        
        String[] result = ArrayUtils.removeElements(originalArray, "A", "C");
        
        Assert.assertEquals(3, result.length);
    }
    
    @Test
    public void testContentForRemoveElements() {
        
        String[] originalArray = new String[5];
        originalArray[0] = "A";
        originalArray[1] = "B";
        originalArray[2] = "C";
        originalArray[3] = "D";
        originalArray[4] = "E";
        
        String[] result = ArrayUtils.removeElements(originalArray, "A", "C");
        
        for(int index = 0; index < result.length; index++) {
            
            if(result[index].equalsIgnoreCase("A") || result[index].equalsIgnoreCase("C")) {
                Assert.fail("Should be removed");
            }
        }
    }
    
    @Test
    public void testSizeForAddElements() {
        
        String[] originalArray = new String[5];
        originalArray[0] = "A";
        originalArray[1] = "B";
        originalArray[2] = "C";
        originalArray[3] = "D";
        originalArray[4] = "E";
        
        String[] result = ArrayUtils.addElements(originalArray, "E", "C", "E", "G", "H");
        
        Assert.assertEquals(7, result.length);
    }
    
    @Test
    public void testContentForAddElements() {
        
        String[] originalArray = new String[5];
        originalArray[0] = "A";
        originalArray[1] = "B";
        originalArray[2] = "C";
        originalArray[3] = "D";
        originalArray[4] = "E";
        
        String[] result = ArrayUtils.addElements(originalArray, "E", "C", "E", "G", "H");
        
        Assert.assertEquals("A", result[0]);
        Assert.assertEquals("B", result[1]);
        Assert.assertEquals("C", result[2]);
        Assert.assertEquals("D", result[3]);
        Assert.assertEquals("E", result[4]);
        Assert.assertEquals("G", result[5]);
        Assert.assertEquals("H", result[6]);
    }
}
