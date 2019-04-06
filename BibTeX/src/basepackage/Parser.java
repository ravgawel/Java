package basepackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The class converts the text form of the BibTex into a data object-oriented form
 */
public class Parser {
    private String filePath;
    private BufferedReader fileReader = null;
    private HashMap<String, Item> items = new HashMap<>();
    private HashMap<String, String> strings = new HashMap<>();

    Parser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parser turns BibTeX file into object-oriented file
     * @return HashMap with object-oriented entries
     */
    public HashMap<String, Item> parse() {
        try {
            String line;
            StringBuilder record = new StringBuilder("");
            boolean isLookingForNew = true;
            try{
                fileReader = new BufferedReader(new FileReader(filePath));
            }
            catch (FileNotFoundException e){
                System.out.println("Nie znaleziono pliku");
            }
            Pattern pattern = Pattern.compile("}");

            while ((line = fileReader.readLine()) != null) {
                if (line.contains("}")) isLookingForNew = false;
                if (isLookingForNew == true) {
                    if (line.trim().startsWith("@")) {
                        record = new StringBuilder(line);
                        isLookingForNew = false;
                    }
                }
                else{
                    Matcher matcher = pattern.matcher(line);
                    record.append(line.trim());
                    if (matcher.find() == true){
                        isLookingForNew = true;
                        String recordString = record.toString().replace("@", "").replace("}", "");

                        String[] parts = recordString.split("\\{");



                        switch (parts[0].toLowerCase().trim()) {
                            case "comment":
                                break;
                            case "preamble":
                                break;
                            case "string":
                                //System.out.println("string");
                                handleString(parts[1].trim());
                                break;
                            default:
                                handleDefault(parts[0].trim().toUpperCase(), parts[1].trim());
                                break;
                        }

                    }
                }

            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * This methos is used to handle @String type
     * It puts word as a key into HashMap and its new form as a field
     * @param body
     */
    private void handleString(String body){
        String[] parts = body.split("=");
        strings.put(parts[0].trim(), parts[1].trim().replace("\"", ""));
    }

    /**
     * This methos is used to handle entry types
     * @param type is a type of the entry
     * @param body body contains fields of the entry
     */
    private void handleDefault(String type, String body){
        String[] parts = body.split(",");
        //key=parts[0]
        LinkedHashMap<String, String> values = new LinkedHashMap<>();


        for(int i = 1; i < parts.length; i++){
            String[] parts2 = parts[i].split("=");
            String field = check(parts2[1].trim());
            if (!field.replace("\"", "").trim().isEmpty()) values.put(parts2[0].trim(), field);

        }
        ItemFactory factory = new ItemFactory(type, parts[0], values);
        Item entryType;
        entryType = factory.getItem();
        items.put(parts[0], entryType);

    }

    /**
     * Methot check whether field contains string that should be replaced
     * @param field that we want to check
     * @return field that we were looking for
     */
    public String check(String field){
        if (field.contains("#") == false && strings.containsKey(field.trim()) == false) return field;

        StringBuilder newField = new StringBuilder("");
        String[] parts = field.split("#");
        Pattern pattern = Pattern.compile("\".+\"");
        boolean first = true;
        boolean flag = false; //remember "
        for (String s : parts) {
            Matcher matcher = pattern.matcher(s.trim());
            if (matcher.find() == true) {
                int position = newField.length();
                newField.append(s.trim());
                if (flag && s.trim().startsWith("\"")){
                    newField.replace(position, position+1, "");
                    flag = false;
                }
                first = false;
            }
            else{
                if (first == true) newField.append("\"");
                if (strings.containsKey(s.trim())){
                    if (newField.length() > 1) newField.replace(newField.length()-1, newField.length(), "");
                    newField.append(strings.get(s.trim()));
                    flag = true;
                }
            }
        }
        if (flag == true) newField.append("\"");
        return newField.toString();
    }
}
