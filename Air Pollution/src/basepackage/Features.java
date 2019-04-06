package basepackage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class provides the functionality of the program
 */
public class Features {
    private static Parser parser = new Parser();

    public Features(){
        parser = new Parser();
    }


    /**
     * Printout of the current air quality index for the given (name) measurement station
     * @param StationName Name of the station we are interested in
     * @return Index of the station
     * @throws IOException If an input or output exception occurred
     */
    public String func1(String StationName) throws IOException{
        HashMap<String, Station> stations = parser.stationParser();
        try {
            Index index = parser.indexParser(stations.get(StationName).getId());
        }
        catch (NullPointerException e){
            throw new NullPointerException("Nie znaleziono podanej stacji");
        }
        Index index = parser.indexParser(stations.get(StationName).getId());
        //System.out.println(index.getStIndexLevel().getIndexLevelName());
        return index.getStIndexLevel().getIndexLevelName();

    }

    /**
     * Printout of the current value of a given parameter for the given day, time and measurement station
     * @param StationName Name of the station we are interested in
     * @param date Date of the measurement being sought
     * @param key Parameter
     * @return Values of a given parameter at given time
     * @throws Exception If an input or output exception occurred
     */
    public float func2(String StationName, Date date, String key) throws Exception {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensorHashMap;
        try {
            sensorHashMap = parser.sensorParser(stations.get(StationName).getId());
        }
        catch (NullPointerException e){
            throw new NullPointerException("Nie znaleziono podanej stacji");
        }
        int stationId = -1;
        for (Integer a : sensorHashMap.keySet()) {
            if (sensorHashMap.get(a).getParam().getParamFormula().equals(key)) stationId=sensorHashMap.get(a).getId();
        }
        boolean x = false;
        if (stationId!=-1){
            Data data = parser.dataParser(stationId);
            for (int i = 0; i < data.getValues().length; i++){
                if (data.getValues()[i].getDate().equals(date)){
                    return data.getValues()[i].getValue();
                }
            }
        }
        else {
            throw new Exception("Podana stacja nie bada takiego parametru");
        }
        throw new Exception("Brak pomiarów stacji dla podanej daty i godziny");
    }

    /**
     * Calculation of the average pollutant / parameter value for a given period for a given station
     * @param StationName Name of the station we are interested in
     * @param startDate Date of start the measurement being sought
     * @param endDate Date of end the measurement being sought
     * @param key Parameter
     * @return Average parameter value for a given period
     * @throws Exception If an input or output exception occurred
     */
    public float func3(String StationName, Date startDate, Date endDate, String key) throws Exception {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensorHashMap;
        try {
            sensorHashMap = parser.sensorParser(stations.get(StationName).getId());
        }
        catch (NullPointerException e){
            throw new NullPointerException("Nie znaleziono podanej stacji");
        }
        int stationId = -1;
        for (Integer a : sensorHashMap.keySet()) {
            if (sensorHashMap.get(a).getParam().getParamFormula().equals(key)) stationId=sensorHashMap.get(a).getId();
        }
        if (stationId!=-1){
            Data data = parser.dataParser(stationId);
            int k = 0;
            float sum = 0;
            for (int i = 0; i < data.getValues().length; i++){
                if ((data.getValues()[i].getDate().after(startDate) || data.getValues()[i].getDate().equals(startDate)) && (data.getValues()[i].getDate().before(endDate) || (data.getValues()[i].getDate().equals(endDate)))){
                    k++;
                    sum += data.getValues()[i].getValue();
                }
            }
            return sum/(k);
        }
        else {
            throw new Exception("Podana stacja nie bada takiego parametru");
        }
    }

    /**
     * Finding, for the mentioned stations, the parameter whose value, starting from the given hour (on a given day), fluctuated the most
     * @param stationName Name of the stations that  we are interested in
     * @param startDate Date of start the measurement being sought
     * @return Parameter whose value fluctuated the most
     * @throws Exception If an input or output exception occurred
     */
    public String func4(ArrayList<String> stationName, Date startDate) throws Exception {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensor;
        HashMap<Integer, Sensor> sensors = new HashMap<>();

        for (String station : stations.keySet()){
            if (stationName.contains(station)){
                try {
                    sensor = parser.sensorParser(stations.get(station).getId());
                }
                catch (NullPointerException e){
                    throw new NullPointerException("Nie znaleziono podanej stacji");
                }
                for (Integer i : sensor.keySet()){
                    sensors.put(i, sensor.get(i));
                }
            }
        }



        String[] params = new String[sensors.keySet().size()];
        float[] values = new float[sensors.keySet().size()];
        int i = 0;
        for (int x : sensors.keySet()){
            params[i] = sensors.get(x).getParam().getParamFormula();
            Data data = parser.dataParser(x);
            for (int j = 1; j < data.getValues().length; j++){

                float prev=0;
                boolean flag = false;
                if (data.getValues()[j].getDate().after(startDate)){
                    if (flag=false){
                        prev = data.getValues()[j].getValue();
                        flag = true;
                    }
                    else {
                        float difference = Math.abs(data.getValues()[j].getValue()-prev);
                        values[i] += difference;
                        prev = data.getValues()[j].getValue();
                    }
                }
            }
            i++;
        }

        //for (int x = 0; x < sensors.keySet().size(); x++){
        //    System.out.println(params[x] +" " +values[x]);
        //}


        int maxId=0;
        float max = values[0];
        for (int k = 1; k < values.length; k++){
            if (max < values[k]){
                maxId = k;
                max = values[k];
            }
        }
        if (maxId==-1) throw new Exception("Nie można wyznaczyć największych wachań na podstawie jednego pomiaru");;
        return params[maxId];
    }

    /**
     * Finding the parameter whose value was the smallest at the given hour of the given day
     * @param date Date of the measurement being sought
     * @return Parameter whose value was the smallest
     * @throws IOException If an input or output exception occurred
     */
    public String func5(Date date) throws Exception {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensors;
        HashMap<Integer, Sensor> allSensors = new HashMap<>();
        for (String stationName: stations.keySet()){
            sensors = parser.sensorParser(stations.get(stationName).getId());
            for (Integer i : sensors.keySet()){
                allSensors.put(i, sensors.get(i));
                //System.out.println(i);
            }
        }

        String param = "";
        float value = 10000;


        for (Integer i: allSensors.keySet()) {

            Data data = new Data();
            //try{
                data = parser.dataParser(i);
            //}catch (IOException e){
             //   System.out.println(i);
            //}


            for (int j = 0; data!=null && j < data.getValues().length; j++){

               if (data.getValues()[j].getDate().equals(date) && data.getValues()[j].getValue()<value && data.getValues()[j].getValue()>0){
                   value = data.getValues()[j].getValue();
                   param = data.getKey();
                   break;
                }


            }


        }
        if (value == 10000) throw new Exception("Brak pomiarów dla podanej daty i godziny");
        //System.out.println(param + " " + value);
        return param;

    }

    /**
     * For the given station, printing out of N measuring stations (parameters),
     * sorted in ascending order (in relation to the value of the exceeded standard),
     * which at the given time specified on the day, noted the exceedance of the pollution standard
     * @param StationName Name of the stations that  we are interested in
     * @param date Date of the measurement being sought
     * @throws Exception If an input or output exception occurred
     */
    public LinkedHashMap<String, Float> func6(String StationName, Date date) throws Exception {
        HashMap <String, Float> limits = new HashMap<>();
        limits.put("C6H6", (float) 5.0);
        limits.put("NO2", (float) 200.0);
        limits.put("SO2", (float) 350.0);
        limits.put("CO", (float) 10000.0);
        limits.put("PM10", (float) 50.0);
        limits.put("PM2.5", (float) 25.0);
        limits.put("Pb", (float) 0.5);

        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensors;
        try {
            sensors = parser.sensorParser(stations.get(StationName).getId());
        } catch (NullPointerException e) {
            throw new NullPointerException("Nie znaleziono podanej stacji");
        }

        HashMap <Float, String> tooHigh = new HashMap<>();
        boolean s = false;
        for (Integer x : sensors.keySet()){
            String param = sensors.get(x).getParam().getParamCode();
            Float a = (float) 0;
            Data data = parser.dataParser(x);
            for (int i = 0; i < data.getValues().length; i++){
                if (data.getValues()[i].getDate().equals(date)){
                    a = data.getValues()[i].getValue();
                    s = true;
                }
            }
            //System.out.println(sensors.get(x).getParam().getParamCode() + " Pomiar: " +a + " Norma: " + limits.get(sensors.get(x).getParam().getParamCode()));

             if (limits.containsKey(param) && a > limits.get(param)) {
                 tooHigh.put((a - limits.get(param)), param);
             }
        }

        if (!s) throw new Exception("Brak pomiarów dla podanej daty i godziny");

        LinkedHashMap<String, Float> wyniki = new LinkedHashMap<>();

        if (tooHigh.isEmpty()){
            return wyniki;
        }
        else{
            List keys = new ArrayList(tooHigh.keySet());
            Collections.sort(keys);
            for (Object k: keys){
                wyniki.put(tooHigh.get(k), (float) k);
            }
            return wyniki;
        }
    }

    public void func7(String key) throws IOException {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensors;
        HashMap<Integer, Sensor> allSensors = new HashMap<>();
        for (String stationName: stations.keySet()){
            sensors = parser.sensorParser(stations.get(stationName).getId());
            for (Integer i : sensors.keySet()){
                if (sensors.get(i).getParam().getParamCode().equals(key)) allSensors.put(i, sensors.get(i));
                //System.out.println(i);
            }
        }
        if (allSensors.size()==0) throw new IllegalArgumentException("Nie znaleziono podanego parametru");

        Date min_Date = new Date();
        Date max_Date = new Date();
        float min_value = Float.MAX_VALUE;
        float max_value = (float) 0;
        int min_sensorID = -1;
        int max_sensorID = -1;

        for (Integer i: allSensors.keySet()) {
            Data data = new Data();
            data = parser.dataParser(i);

            for (int j = 0; j < data.getValues().length; j++){
                Float value = data.getValues()[j].getValue();
                if (value > max_value){
                    max_value = value;
                    max_Date = data.getValues()[j].getDate();
                    max_sensorID = i;
                }
                if (value < min_value && value > 0){
                    min_value = value;
                    min_Date = data.getValues()[j].getDate();
                    min_sensorID = i;
                }
            }

        }
        int min_stationID = allSensors.get(min_sensorID).getStationId();
        int max_stationID = allSensors.get(max_sensorID).getStationId();

        String min_station = "";
        String max_station = "";

        for (String x : stations.keySet()){
            if (stations.get(x).getId()==min_stationID) min_station = stations.get(x).getStationName();
            if (stations.get(x).getId()==max_stationID) max_station = stations.get(x).getStationName();
        }

        System.out.println(min_station + " " + min_Date + " " + min_value);
        System.out.println(max_station + " " + max_Date + " " + max_value);
    }

    /**
     * Drawing (in text mode) of a common (for all given hours) graph of changes
     * in values (eg bar graph, using various ASCII characters) given parameter
     * in hourly system
     * @param stationName Name of the stations that  we are interested in
     * @param key Parameter
     * @param stationName Name of the stations that  we are interested in
     * @param startDate Date of start the measurement being sought
     * @throws IOException If an input or output exception occurred
     */
    public void func8(ArrayList<String> stationName, String key, Date startDate, Date endDate) throws IOException {
        HashMap<String, Station> stations = parser.stationParser();
        HashMap<Integer, Sensor> sensors = new HashMap<>();
        HashMap<Integer, Sensor> allSensors = new HashMap<>();

        int sensorsids[] = new int[stationName.size()];
        String[] stationNames = new String[stationName.size()];
        int z = 0;


        for (String stationN: stations.keySet()){
            try {
                sensors = parser.sensorParser(stations.get(stationN).getId());
            }
            catch (NullPointerException e){
                throw new NullPointerException("Nie znaleziono podanej stacji");
            }
            if (stationName.contains(stationN)){

                for (Integer i : sensors.keySet()){
                    if (sensors.get(i).getParam().getParamCode().equals(key)){
                        allSensors.put(i, sensors.get(i));
                        stationNames[z] = stationN;
                        sensorsids[z]=i;
                        z++;
                    }
                }
            }
        }

        if (allSensors.size()==0) throw new IllegalArgumentException("Nie znaleziono podanego parametru");

        Float max_value = (float) 0;

        for (Integer i: allSensors.keySet()) {
            Data data = new Data();
            data = parser.dataParser(i);

            for (int j = 0; j < data.getValues().length; j++){
                Float value = data.getValues()[j].getValue();
                Date d = data.getValues()[j].getDate();
                if (value > max_value && ((d.after(startDate) || d.equals(startDate)) && (d.before(endDate) || d.equals(endDate)))){
                    max_value = value;
                }
            }

        }

        Data[] datas = new Data[stationName.size()];
        for (int i = 0; i < stationName.size(); i++){
            datas[i] = parser.dataParser(sensorsids[i]);
        }


        String leftAlignFormat = "%-50s %-40s %n";
        Date date = startDate;
        while(date.before(endDate)){
            for (int i = 0; i < stationName.size(); i++){
                for (int j = 0; j < datas[i].getValues().length; j++){
                    if (datas[i].getValues()[j].getDate().equals(date)) {
                        Float value = datas[i].getValues()[j].getValue();
                        StringBuilder asci = new StringBuilder();
                        int x = Math.round(30*value/max_value);
                        for (int k = 0; k<x; k++){
                            asci.append("∎");
                        }
                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        System.out.format(leftAlignFormat, dateFormat.format(date) + " (" + stationNames[i] + ")", asci + "(" + value + ")");

                    }
                }

            }

            Calendar cal =Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR_OF_DAY,1); // this will add two hours
            date = cal.getTime();
        }

    }

}
