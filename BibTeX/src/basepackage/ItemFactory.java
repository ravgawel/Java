package basepackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Class is responsible from creating new Items
 */
public class ItemFactory {
    private final String type;
    private final String k;
    private final LinkedHashMap<String, String> values;
    private Item item;

    public ItemFactory(String type, String k, LinkedHashMap<String, String> values){
        this.type = type;
        this.k = k;
        this.values = values;
    }

    /**
     * Create Item that we will be able to add into our object-oriented form
     * @return formatted item without ignored fields
     */
    public Item getItem(){
        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Author> editors = new ArrayList<>();

        if (values.containsKey("author") == true){
            authors = authorsToArray(values.get("author"));
            values.remove("author");
        }
        if (values.containsKey("editor") == true){
            editors = authorsToArray(values.get("editor"));
            values.remove("editor");
        }

        switch(type) {
            case "ARTICLE":
                item = new Item(Type.ARTICLE, k, authors, editors, values);
                break;
            case "BOOK":
                item = new Item(Type.BOOK, k, authors, editors, values);
                break;
            case "INPROCESINGS":
                item = new Item(Type.INPROCESINGS, k, authors, editors, values);
                break;
            case "BOOKLET":
                item = new Item(Type.BOOKLET, k, authors, editors, values);
                break;
            case "INBOOK":
                item = new Item(Type.INBOOK, k, authors, editors, values);
                break;
            case "INCOLLECTION":
                item = new Item(Type.INCOLLECTION, k, authors, editors, values);
                break;
            case "MANUAL":
                item = new Item(Type.MANUAL, k, authors, editors, values);
                break;
            case "MASTERSTHESIS":
                item = new Item(Type.MASTERSTHESIS, k, authors, editors, values);
                break;
            case "PHDTHESIS":
                item = new Item(Type.PHDTHESIS, k, authors, editors, values);
                break;
            case "TECHREPORT":
                item = new Item(Type.TECHREPORT, k, authors, editors, values);
                break;
            case "MISC":
                item = new Item(Type.MISC, k, authors, editors, values);
                break;
            case "UNPUBLISHED":
                item = new Item(Type.UNPUBLISHED, k, authors, editors, values);
                break;
            default:
                throw new IllegalArgumentException("Incorrect entry type");
        }

        if(item.checkMandatoryFields()){
            item.deleteIgnored();
            return item;
        }
        else{
            throw new IllegalArgumentException("Missing obligatory field");
        }
    }

    /**
     * This methos is used to creating table of authors or editors
     * It takes object and converts it into ArrayList
     * @param author we will operatate on i.e. author or editor
     * @return ArrayList of authors
     */
    public ArrayList<Author> authorsToArray(String author){
        ArrayList<Author> authors = new ArrayList<>();

        String[] parts = author.split("and");
        for (int i = 0; i < parts.length; i++){
            authors.add(new Author(parts[i].trim()));
        }
        return authors;
    }
}
