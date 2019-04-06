package basepackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ParserTest {
    String filePath = "C:\\Users\\Rafał\\IdeaProjects\\Projekt1\\tests\\basepackage\\test1.bib";
    Parser parser = new Parser(filePath);
    HashMap<String, Item> items = parser.parse();
    String key1 = "whole-collection";
    String key2 = "inbook-full";
    ArrayList<Author> authors;
    ArrayList<Author> editors;
    @Test
    public void parse() {
        assertEquals(items.get(key1).getType(), Type.BOOK);
        assertEquals(items.get(key2).getType(), Type.INBOOK);

        authors = new ArrayList<>();
        editors = new ArrayList<>();
        Author a1 = new Author("David J. Lipcoll");
        Author a2 = new Author("D. H. Lawrie");
        Author a3 = new Author("A. H. Sameh");
        editors.add(a1);
        editors.add(a2);
        editors.add(a3);

        assertEquals(items.get(key1).getAuthors(), authors);
        assertEquals(items.get(key1).getEditors(), editors);

    }


    @Test
    public void check1() {
        assertEquals(parser.check("sep"), "\"september\"");
        assertEquals(parser.check("\"miesiąc: \" # sep"), "\"miesiąc: september\"");
        assertEquals(parser.check("sep # \" miesiąc\""), "\"september miesiąc\"");
        assertEquals(parser.check("sep # sep"), "\"septemberseptember\"");
        assertEquals(parser.check("sep # \" to \" # sep"), "\"september to september\"");
    }
}