package basepackage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents BibTeX items as an object-oriented item
 * Can be printed in many ways
 */
public class BibTeX {
    final private HashMap<String, Item> items;
    final private String filePath;
    final private Parser parser;


    public BibTeX(String filePath){
        this.filePath = filePath;
        this.parser = new Parser(filePath);
        this.items = parser.parse();
    }

    /**
     * Prints all loaded objects of a type
     * @param type is a type user wants to print
     */
    public void printCategory(String type) {
        for (String s : items.keySet()){
            if (items.get(s).getType().toString().equals(type.toUpperCase())){
                System.out.println(items.get(s).toString());
            }

        }
    }

    /**
     * Prints all loaded objects
     */
    public void printAll() {
        for (String s : items.keySet()) System.out.println(items.get(s).toString());
    }

    /**
     * Prints all loaded objects of a type
     * @param author is an author user wants to print
     */
    public void printAuthors(String author){
        Author a = new Author(author);
        ArrayList<String> keys = new ArrayList<>();
        for (String s : items.keySet()){
            for (Author aut : items.get(s).getAuthors()){
                if ((aut.equals(a)) && keys.contains(s)==false) keys.add(s);
            }
            for (Author ed : items.get(s).getEditors()){
                if ((ed.equals(a)) && keys.contains(s)==false) keys.add(s);
            }
        }
        for (String k : keys){
            System.out.println(items.get(k).toString());
        }
    }
}
