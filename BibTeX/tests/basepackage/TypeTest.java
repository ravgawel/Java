package basepackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypeTest {
    Type type;
    @Test
    public void contains() {
        assertTrue(type.contains("BOOK"));
        assertTrue(type.contains("ARTICLE"));
        assertTrue(type.contains("INPROCESINGS"));
        assertTrue(type.contains("BOOKLET"));
        assertTrue(type.contains("INBOOK"));
        assertTrue(type.contains("INCOLLECTION"));
        assertTrue(type.contains("MANUAL"));
        assertTrue(type.contains("MASTERSTHESIS"));
        assertTrue(type.contains("PHDTHESIS"));
        assertTrue(type.contains("TECHREPORT"));
        assertTrue(type.contains("MISC"));
        assertTrue(type.contains("UNPUBLISHED"));
        assertTrue(type.contains("book"));
        assertTrue(type.contains("bOOk"));
        assertTrue(type.contains("BooK"));
        assertFalse(type.contains("STRING"));
        assertFalse(Type.contains("string"));
        assertFalse(Type.contains("abcd"));
        assertFalse(Type.contains(""));
    }

}