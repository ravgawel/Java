package basepackage;


import java.io.FileNotFoundException;

/**
 * @author Rafał Gaweł
 * The program loads the BibTeX file
 * It has functionalities such as printing all entries
 * priting entries of a certain type
 * and printing entries of a certain author
 */
public class Main {

    public static void main(String[] args){
        try{
            if (args.length == 0){
                printHelp();
                return;
            }

            String filePath = args[0];
            BibTeX bibtex = new BibTeX(filePath);
            int last = filePath.length();
            if (((filePath.substring(last-4, last)).equals(".bib")) == false) throw new FileNotFoundException("The program supports BibTeX(*.bib) files");
            if (args.length ==1){
                bibtex.printAll();
            }
            else{
                for (int i = 1; i<args.length; i++){
                    if (args[i].startsWith("@")){
                        System.out.println("List of publications of  type " + args[i]);
                        bibtex.printCategory(args[i].replace("@", ""));
                    }
                    else {
                        System.out.println("List of publications of " + args[i]);
                        bibtex.printAuthors(args[i]);
                    }
                }
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.toString());
        }
        catch (FileNotFoundException e){
            System.out.println(e.toString());
        }
    }

    /**
     * Method that print Help
     * Should be used when the user will not provide arguments
     */
    public static void printHelp(){
        System.out.println("If u want to print all publications enter as an argument only filepath of BibTeX.");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib");
        System.out.println("If u want to print all publications enter as an argument only filepath of BibTeX.");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib");
        System.out.println("If you want to list publications of specific types after file path enter type with @ at the beginning");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib @BOOK");
        System.out.println("or for many types");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib @BOOK @ARTICLE @MANUAL");
        System.out.println("If you want to list publications of specific types after file path enter name of aurhor with @ at the beginning");
        System.out.println("Remember using \" For Example \"David J. Lipcoll \"");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib \"David J. Lipcoll\"");
        System.out.println("or for many types");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib \"David J. Lipcoll\" \"von Last, Jr, First\"");
        System.out.println("You can enter the author and type at one time");
        System.out.println("For Example: C:\\Users\\Rafał\\Desktop\\test1.bib \"David J. Lipcoll\" @BOOK");
    }
}
