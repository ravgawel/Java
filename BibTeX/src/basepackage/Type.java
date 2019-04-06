package basepackage;

/**
 * Types of publications that can be used
 */
public enum Type {
    ARTICLE,
    BOOK,
    INPROCESINGS,
    BOOKLET,
    INBOOK,
    INCOLLECTION,
    MANUAL,
    MASTERSTHESIS,
    PHDTHESIS,
    TECHREPORT,
    MISC,
    UNPUBLISHED;

    /**
     * Test whether enum Type constains given type
     * @param test tha name of
     * @return True if given type exists
     */
    public static boolean contains(String test) {
        for (Type c : Type.values()) {
            if (c.toString().equals(test.toUpperCase())){

                return true;
            }
        }
        return false;
    }

}



