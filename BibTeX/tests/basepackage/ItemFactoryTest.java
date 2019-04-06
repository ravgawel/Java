package basepackage;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemFactoryTest {
    ItemFactory itemFactory ;
    ArrayList<Author> autors = new ArrayList<>();


    @Test
    public void authorsToArray() {
        Author a1 = new Author("David J. Lipcoll");
        Author a2 = new Author("D. H. Lawrie");
        Author a3 = new Author("A. H. Sameh");
        autors.add(a1);
        autors.add(a2);
        autors.add(a3);
        assertEquals(itemFactory.authorsToArray("David J. Lipcoll and D. H. Lawrie and A. H. Sameh"), autors);
        autors = new ArrayList<>();
        a1 = new Author("First von Last");
        a2 = new Author("von Last| First");
        a3 = new Author("von Last| Jr |First");
        autors.add(a1);
        autors.add(a2);
        autors.add(a3);
        assertEquals(itemFactory.authorsToArray("First von Last and von Last| First and von Last| Jr |First"), autors);


    }
}