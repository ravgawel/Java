package basepackage;

import java.util.*;

/**
 * Object oriented form of registry
 */
public class Item {
    /**
     * Required Map contains informations about required field of a certain type
     * Optional Map contains informations about optional field of a certain type
     * The rest of field of a cestain type are ignored
     */
    private static final Map<Type, String[]> required = createMap();
    private static Map<Type, String[]> createMap()
    {
        Map<Type, String[]> required = new HashMap<>();
        required.put(Type.ARTICLE, new String[]{"author", "title", "journal", "year"});
        required.put(Type.BOOK, new String[]{"author|editor", "publisher", "title", "year"});
        required.put(Type.INPROCESINGS, new String[]{"author", "title", " booktitle", "year"});

        required.put(Type.BOOKLET, new String[]{"title"});
        required.put(Type.INBOOK, new String[]{"author|editor", "title", "chapter|pages", "publisher", "year"});
        required.put(Type.INCOLLECTION, new String[]{"author", "title", "booktitle", "publisher", "year"});
        required.put(Type.MANUAL, new String[]{"title"});
        required.put(Type.MASTERSTHESIS, new String[]{"author", "title", "school", "year"});
        required.put(Type.PHDTHESIS, new String[]{"author", "title", "school", "year"});
        required.put(Type.TECHREPORT, new String[]{"author", "title", "institution", "year"});
        required.put(Type.MISC, new String[]{});
        required.put(Type.UNPUBLISHED, new String[]{"author", "title", "note"});
        return required;
    }

    private static final Map<Type, String[]> optional = createMap2();
    private static Map<Type, String[]> createMap2()
    {
        Map<Type, String[]> optional = new HashMap<>();
        optional.put(Type.ARTICLE, new String[]{"volume", "number", "pages", "month", "note", "key"});
        optional.put(Type.BOOK, new String[]{"volume", "series", "address", "edition", "month", "note", "key"});
        optional.put(Type.INPROCESINGS, new String[]{"veditor", "volume|number,", "series", "pages", "address", "month", "organization", "publisher", "note", "key"});

        optional.put(Type.BOOKLET, new String[]{"author", "howpublished", "address", "month", "year", "note", "key"});
        optional.put(Type.INBOOK, new String[]{"volume|number", "series", "type" ,"address", "edition", "month", "note", "key"});
        optional.put(Type.INCOLLECTION, new String[]{"editor", "volume|number", "series", "type", "chapter", "pages", "address", "edition", "month", "note", "key"});
        optional.put(Type.MANUAL, new String[]{"author", "organization,", "address", "edition", "month", "year", "note", "key"});
        optional.put(Type.MASTERSTHESIS, new String[]{"type", "address", "month", "note", "key"});
        optional.put(Type.PHDTHESIS, new String[]{"type", "address", " month", "note", "key"});
        optional.put(Type.TECHREPORT, new String[]{"editor", "volume|number", "series", "address", "month", "organization", "publisher", "note", "key"});
        optional.put(Type.MISC, new String[]{"author", "title", "howpublished", "month", "year", "note", "key"});
        optional.put(Type.UNPUBLISHED, new String[]{"month", "year", "key"});
        return optional;
    }

    private final Type type;
    private final LinkedHashMap<String, String> values;
    private final ArrayList<Author> authors;
    private final ArrayList<Author> editors;
    private final String k;

    /**
     * @return type of the registry
     */
    public Type getType() {
        return type;
    }

    /**
     * @return ArrayList of authors of the registry
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * @return ArrayList of editors of the registry
     */
    public ArrayList<Author> getEditors() {
        return editors;
    }


    public Item(Type type, String k , ArrayList<Author> authors, ArrayList<Author> editors, LinkedHashMap<String, String> values){
        this.type = type;
        this.k = k;
        this.authors = authors;
        this.editors = editors;
        this.values = values;
    }

    /**
     * Check whether registry got all required fields
     * @return True if registry got all required fields
     */
    public boolean checkMandatoryFields() {
        String[] obligatory = required.get(this.type);
        for (String s : obligatory){
            if (s.contains("|")){
                if (s.equals("author|editor")) {
                    if (authors.isEmpty() && editors.isEmpty()) return false;
                }
                else{
                    String parts[] = s.split("\\|");
                    if (!values.containsKey(parts[0]) && !values.containsKey(parts[1])) return false;
                }
            }
            else if (s.equals("author")){
                if (authors.isEmpty()==true) return false;
            }
            else if (s.equals("editor")){
                if (authors.isEmpty()==true) return false;
            }
            else if(values.containsKey(s)==false){
                //System.out.println(s);
                return false;
            }
        }

        return true;
    }

    /**
     * Method deletetes all fields that are ignored
     */
    public void deleteIgnored() {
        String[] obligatory = required.get(this.type);
        String[] optional1 = optional.get(this.type);
        List<String> ignored = new ArrayList<>();
        for (Object s : values.keySet()){
            if (Arrays.asList(obligatory).contains(s.toString())==false && Arrays.asList(optional1).contains(s.toString())==false){
                ignored.add(values.toString());
            }
        }
        for (String s : ignored) values.remove(s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String leftAlignFormat = "| %-15s | %-50s |%n";
        String leftAlignFormat2 = "| %-68s |%n";
        sb.append(String.format("+----------------------------------------------------------------------+%n"));
        sb.append(String.format(leftAlignFormat2, (type.toString()) + "(" + k + ")"));
        sb.append(String.format("+-----------------+----------------------------------------------------+%n"));
        boolean first = true;
        for (Author a : authors){
            if (first){
                sb.append(String.format(leftAlignFormat, "author", "*" + a.toString()));
                first = false;
            }
            else{
                sb.append(String.format(leftAlignFormat, "", "*" + a.toString()));
            }
        }
        if (!authors.isEmpty()) sb.append(String.format("+-----------------+----------------------------------------------------+%n"));
        first = true;
        for (Author a : editors){
            if (first){
                sb.append(String.format(leftAlignFormat, "editor", "*" + a.toString()));
                first = false;
            }
            else{
                sb.append(String.format(leftAlignFormat, "", "*" + a.toString()));
            }
        }
        if (!editors.isEmpty()) sb.append(String.format("+-----------------+----------------------------------------------------+%n"));
        for (Object s : values.keySet()){
            String value = values.get(s.toString()).replace("\"", "");
            if (value.length()<=50) sb.append(String.format(leftAlignFormat, s.toString(), value));
            else {
                int a = value.length()/50;
                int b = value.length()%50;
                String name = s.toString();
                int i;
                for (i = 0; i < a; i++){
                    sb.append(String.format(leftAlignFormat, name, value.substring(i*50, i*50+49)));
                    name = "";
                }
                sb.append(String.format(leftAlignFormat, "", value.substring(i*50, i*50+b)));
            }
            sb.append(String.format("+-----------------+----------------------------------------------------+%n"));
        }
        return sb.toString();
    }

}
