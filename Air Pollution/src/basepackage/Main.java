package basepackage;


import java.io.FileReader;
import java.io.IOException;
import java.net.CookieHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;


/**
 *  A program that provides information on the quality of air
 *  It also allows you to compare different statistics and draw diagrams
 *  @author Rafał Gaweł
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Features features= new Features();

        if (args.length==0){
            printHelp();
            return;
        }

        String s = new String();
        String d = new String();
        String d1 = new String();
        String d2 = new String();
        String p = new String();
        String f =new String();

        for(String x: args){
            x.replace("\"", "");
            if (x.startsWith("-f=")){ f = x.replace("-f=", "").trim(); }
            else if (x.startsWith("-d=") || x.startsWith("-d1=")){
                d = x.replace("-d=", "").replace("-d1=", "").trim();
                d1 = x.replace("-d=", "").replace("-d1=", "").trim(); }
            else if (x.startsWith("-d2=")){ d2 = x.replace("-d2=", "").trim(); }
            else if (x.startsWith("-p=")) p = x.replace("-p=", "").trim().toUpperCase();
            else if (x.startsWith("-s=")){s = x.replace("-s=", "").trim();}
        }

        try{
            switch (f){
                case "1":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    else System.out.println(features.func1(s));
                    break;
                }
                case "2":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    if (d.length()==0) throw new IllegalArgumentException("Nie podano daty");
                    if (p.length()==0) throw new IllegalArgumentException("Nie podano parametru");
                    System.out.println(features.func2(s, stringToDate(d), p));
                    break;
                }
                case "3":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    if (d1.length()==0) throw new IllegalArgumentException("Nie podano daty początkowej");
                    if (d2.length()==0) throw new IllegalArgumentException("Nie podano daty końcowej");
                    if (p.length()==0) throw new IllegalArgumentException("Nie podano parametru");
                    System.out.println(features.func3(s, stringToDate(d1), stringToDate(d2), p));
                    break;
                }
                case "4":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    if (d.length()==0) throw new IllegalArgumentException("Nie podano daty");
                    String[] parts = s.split("\\|");
                    ArrayList<String> station = new ArrayList<>();
                    for (int i = 0; i < parts.length; i++){
                        station.add(parts[i].trim());
                    }
                    System.out.println(features.func4(station, stringToDate(d)));
                    break;
                }
                case "5":{
                    if (d.length()==0) throw new IllegalArgumentException("Nie podano daty");
                    System.out.println(features.func5(stringToDate(d)));
                    break;
                }
                case "6":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    if (d.length()==0) throw new IllegalArgumentException("Nie podano daty");
                    LinkedHashMap<String, Float> wyniki = features.func6(s, stringToDate(d));
                    if (wyniki.size()==0) System.out.println("O podanej godzinie nie przekroczono żadnej normy");
                    else{
                        for (String k: wyniki.keySet()){

                            System.out.println("Normę " + k + " przekroczono o " + wyniki.get(k));
                        }
                    }
                    break;
                }
                case "7":{
                    if (p.length()==0) throw new IllegalArgumentException("Nie podano parametru");
                    features.func7(p);
                    break;
                }
                case "8":{
                    if (s.length()==0) throw new IllegalArgumentException("Nie podano nazwy stacji");
                    if (d1.length()==0) throw new IllegalArgumentException("Nie podano daty początkowej");
                    if (d2.length()==0) throw new IllegalArgumentException("Nie podano daty końcowej");
                    if (p.length()==0) throw new IllegalArgumentException("Nie podano parametru");
                    String[] parts = s.split("\\|");
                    ArrayList<String> station = new ArrayList<>();
                    for (int i = 0; i < parts.length; i++){
                        station.add(parts[i].trim());
                    }
                    features.func8(station, p, stringToDate(d1), stringToDate(d2));
                    break;
                }
                case "9":{
                    Parser parser = new Parser();
                    parser.updateCache();
                    break;
                }
                default:
                    //throw new Exception("Wybierz funkcje z przedziału 1-8");
            }
        }catch (IndexOutOfBoundsException e){

        }

        catch (NullPointerException e){
            //System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        //catch (Exception e){
       //     System.out.println(e);
        //}
    }

    /**
     * @param date Date in string format
     * @return Date as an object
     * @throws Exception When it is called in the wrong format
     */
    public static Date stringToDate(String date) throws Exception{
        Date newDate;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            newDate = formatter.parse(date);
            return newDate;
        }catch (ParseException e){
            throw new Exception("Wrong date format");
        }
    }


    /**
     * Method prints help when project is called without arguments
     */
    public static void printHelp(){
        System.out.println("1. Wypisanie aktualnego indeksu jakości powietrza dla podanej (nazwy) stacji pomiarowej\n" +
                "2. Wypisanie dla podanego dnia, godziny oraz stacji pomiarowej (czytelna nazwa stacji) aktualnej wartości danego parametru (np. PM10)\n" +
                "3. Obliczenie średniej wartości zanieczyszczenia / parametru (np. SO2) za podany okres dla danej stacji\n" +
                "4. Odszukanie, dla wymienionych stacji, parametru którego wartość, począwszy od podanej godziny (danego dnia), uległa największym wahaniom\n" +
                "5. Odszukanie parametru, którego wartość była najmniejsza o podanej godzinie podanego dnia\n" +
                "6. Dla podanej stacji, wypisanie N stanowisk pomiarowych (parametrów), posortowanych rosnąco (względem wartości przekroczenia normy), które o podanej godzinie określonego dnia, zanotowały przekroczenie normy zanieczyszczenia\n" +
                "7. Dla podanego parametru wypisanie informacji: kiedy (dzień, godzina) i gdzie (stacja), miał on największą wartość, a kiedy i gdzie najmniejszą\n" +
                "8. Rysowanie (w trybie tekstowym) wspólnego (dla wszystkich podanych godzin) wykresu zmian wartości (np. wykres słupkowy, za pomocą różnorodnych znaków ASCII) podanego parametru w układzie godzinowym, tzn. jaka było zanieczyszczenie (np. SO2):\n" +
                "Aby wywołać program należy wywołać odpowiednią funkcję i podać wymagane dane wejściowe\n" +
                "   np. -f=\"1\" -s=\"Kraków, Aleja Krasińskiego\"");
    }

}

