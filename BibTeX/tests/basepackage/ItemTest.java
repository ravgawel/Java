package basepackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class ItemTest {
    String filePath = "C:\\Users\\Rafał\\IdeaProjects\\Projekt1\\tests\\basepackage\\test1.bib";
    LinkedHashMap<String, String> values;
    Parser parser = new Parser(filePath);
    HashMap<String, Item> items = parser.parse();
    ItemFactory itemFactory ;
    String key1 = "whole-collection";
    String key2 = "inbook-full";
    ArrayList<Author> autors;
    ArrayList<Author> editors;
    Item item;
    @Test
    public void getType() {
        assertEquals(items.get(key1).getType(), Type.BOOK);
        assertEquals(items.get(key2).getType(), Type.INBOOK);
    }

    @Test
    public void getAuthors() {
        assertEquals(items.get(key1).getAuthors(), new ArrayList<>());

        autors = new ArrayList<>();
        Author a1 = new Author("Donald E. Knuth");
        autors.add(a1);
        assertEquals(items.get(key2).getAuthors(), autors);
    }

    @Test
    public void getEditors() {
        editors = new ArrayList<>();
        Author a1 = new Author("David J. Lipcoll");
        Author a2 = new Author("D. H. Lawrie");
        Author a3 = new Author("A. H. Sameh");
        editors.add(a1);
        editors.add(a2);
        editors.add(a3);
        assertEquals(items.get(key1).getEditors(), editors);

        assertEquals(items.get(key2).getEditors(), new ArrayList<>());
    }

    @Test
    public void checkMandatoryFields() {
        autors = new ArrayList<>();
        editors = new ArrayList<>();
        Author a1 = new Author("David J. Lipcoll");
        Author a2 = new Author("D. H. Lawrie");
        Author a3 = new Author("A. H. Sameh");
        autors.add(a1);
        autors.add(a2);
        autors.add(a3);
        values = new LinkedHashMap<>();
        values.put("title", "High Speed Computer and Algorithm Organization");
        values.put("publisher", "Academic Press");
        values.put("year", "1997");
        item = new Item(Type.BOOK, "whole-collection", autors, editors, values);
        assertTrue(item.checkMandatoryFields());

        autors = new ArrayList<>();
        editors = new ArrayList<>();
        values = new LinkedHashMap<>();
        values.put("title", "High Speed Computer and Algorithm Organization");
        values.put("publisher", "Academic Press");
        values.put("year", "1997");
        item = new Item(Type.BOOK, "whole-collection", autors, editors, values);
        assertFalse(item.checkMandatoryFields());

        autors = new ArrayList<>();
        editors = new ArrayList<>();
        a1 = new Author("David J. Lipcoll");
        a2 = new Author("D. H. Lawrie");
        a3 = new Author("A. H. Sameh");
        autors.add(a1);
        autors.add(a2);
        autors.add(a3);
        editors.add(a1);
        editors.add(a2);
        editors.add(a3);
        values = new LinkedHashMap<>();
        values.put("title", "High Speed Computer and Algorithm Organization");
        values.put("publisher", "Academic Press");
        values.put("year", "1997");
        item = new Item(Type.BOOK, "whole-collection", autors, editors, values);
        assertTrue(item.checkMandatoryFields());

        autors = new ArrayList<>();
        editors = new ArrayList<>();
        a1 = new Author("David J. Lipcoll");
        a2 = new Author("D. H. Lawrie");
        a3 = new Author("A. H. Sameh");
        autors.add(a1);
        autors.add(a2);
        autors.add(a3);
        editors.add(a1);
        editors.add(a2);
        editors.add(a3);
        values = new LinkedHashMap<>();
        values.put("publisher", "Academic Press");
        values.put("year", "1997");
        item = new Item(Type.BOOK, "whole-collection", autors, editors, values);
        assertFalse(item.checkMandatoryFields());

        autors = new ArrayList<>();
        a1 = new Author("Donald E. Knuth");
        autors.add(a1);
        values = new LinkedHashMap<>();
        values.put("title", "Fundamental Algorithms");
        values.put("publisher", "Addison-Wesley");
        values.put("pages", "10--119");
        values.put("chapter", "1.2");
        values.put("year", "1973");
        item = new Item(Type.INBOOK, "inbook-full", autors, editors, values);
        assertTrue(item.checkMandatoryFields());

        autors = new ArrayList<>();
        a1 = new Author("Donald E. Knuth");
        autors.add(a1);
        values = new LinkedHashMap<>();
        values.put("title", "Fundamental Algorithms");
        values.put("publisher", "Addison-Wesley");
        values.put("pages", "10--119");
        values.put("year", "1973");
        item = new Item(Type.INBOOK, "inbook-full", autors, editors, values);
        assertTrue(item.checkMandatoryFields());

        autors = new ArrayList<>();
        a1 = new Author("Donald E. Knuth");
        autors.add(a1);
        values = new LinkedHashMap<>();
        values.put("title", "Fundamental Algorithms");
        values.put("publisher", "Addison-Wesley");
        values.put("chapter", "1.2");
        values.put("year", "1973");
        item = new Item(Type.INBOOK, "inbook-full", autors, editors, values);
        assertTrue(item.checkMandatoryFields());

        autors = new ArrayList<>();
        a1 = new Author("Donald E. Knuth");
        autors.add(a1);
        values = new LinkedHashMap<>();
        values.put("title", "Fundamental Algorithms");
        values.put("publisher", "Addison-Wesley");
        values.put("year", "1973");
        item = new Item(Type.INBOOK, "inbook-full", autors, editors, values);
        assertFalse(item.checkMandatoryFields());
    }
}