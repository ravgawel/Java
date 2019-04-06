package basepackage;

import java.util.Objects;

/**
 * This class String author into Object Author
 */
public class Author {
    private final String firstName;
    private final String von;
    private final String lastName;
    private final String jr;

    /**
     * Constructor of Author class
     * @param author Name of author in String form
     */
    public Author(String author){
        String[] parts = splitName(author);
        this.firstName = parts[0];
        this.von = parts[1];
        this.lastName = parts[2];
        this.jr = parts[3];
    }

    /**
     * @param n String that contains name
     * @return Table where tab[0]=firstName
     *                     tab[1]=von
     *                     tab[2]=lastName
     *                     tab[3]=jr
     */
    private String[] splitName(String n) {
        String name = n.replace("\"", "");
        String first = "";
        String last = "";
        String von = "";
        String jr = "";
        // Separate by |
        String[] parts = name.split("\\|");
        if (parts.length == 1) {
            // First von Last
            // Separate by space
            String[] spaceParts = name.split(" ");
            last = spaceParts[spaceParts.length-1];
            if (spaceParts.length!=1) {
                for (int i = 0; i < spaceParts.length; i++) {
                    String s = spaceParts[i];
                    if (!Character.isLowerCase(s.trim().charAt(0))) {
                        if (s == last) break;
                        first += " " + s;
                    } else {
                        von = s;
                        break;
                    }
                }
            }

        }
        else if (parts.length == 2) {
            // von Last, First
            String[] vonLast = splitVonLast(parts[0]);
            if (vonLast[1] != null) von = vonLast[1];
            last = vonLast[0];
            first = parts[1].trim();
            System.out.println();
        }
        else if (parts.length == 3) {
            // von Last, Jr, First
            String[] vonLast = splitVonLast(parts[0]);
            if (vonLast[1] != null) von = vonLast[1];
            last = vonLast[0];
            jr = parts[1].trim();
            first = parts[2].trim();
        }
        else {
            throw new IllegalArgumentException("Too many symbols \"|\" in name");
        }
        return new String[]{first.trim(), von.trim(), last.trim(), jr.trim()};
    }

    /**
     * @param s unsepparated part von with last name
     * @return Table where tab[1]=last and tab[0]=vonPart
     */
    private String[] splitVonLast(String s){
        String von = "", last = "";
        String vonLast[] = new String[2];
        int i = 0;
        char c = s.charAt(i);
        while ((Character.isLetter(c) == false || Character.isUpperCase(c) == false)){
            i++;
            c = s.charAt(i);
        }
        vonLast[0] = s.substring(i, s.length()).trim();
        if (i>0) vonLast[1] = s.substring(0, i-1).trim();

        return vonLast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(firstName, author.firstName) &&
                Objects.equals(von, author.von) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(jr, author.jr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, von, lastName, jr);
    }

    @Override
    public String toString(){
        if (von.isEmpty()) return firstName  + " " + lastName + " " + jr;
        else return firstName  + " " + von  + " " + lastName + " " + jr;
    }

}
