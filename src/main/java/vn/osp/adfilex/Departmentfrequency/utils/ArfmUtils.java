package vn.osp.adfilex.Departmentfrequency.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ArfmUtils {

    public static List<String> freqs = new LinkedList<>();
    public static List<String> telcos = new LinkedList<>();
    public static List<String> techs = new LinkedList<>();
    public static List<String> status = new LinkedList<>();

    static {
        telcos.add("1");
        telcos.add("VIETTEL");
        telcos.add("MOBIFONE");
        telcos.add("VNPT");
        telcos.add("VIETNAMMOBILE");
        telcos.add("ARFM");

        techs.add("1");
        techs.add("2G");
        techs.add("3G");
        techs.add("4G");
        techs.add("5G");

        status.add("1");
        status.add("ALL");
        status.add("NOISE");
        status.add("NOISE_AUTO");

        freqs.add("Khac");
        freqs.add("900");
        freqs.add("1800");
        freqs.add("2100");


    }

    public static int getIdxFreq(String freq) {

        return freqs.indexOf(freq);
    }

    public static int getIdxTech(String tech) {

        return techs.indexOf(tech);
    }

//    public static int getIdxStatus(String status) {
//        return techs.indexOf(tech);
//    }

    public static int getIdxTelco(String telco) {
        return telcos.indexOf(StringUtils.upperCase(telco));
    }


    public static String getTelco(int idx) {
        return telcos.get(idx);

    }

    public static String getTech(int idx) {
        return techs.get(idx);

    }

    public static Map<Double, Double> sortByComparator(Map<Double, Double> unsortMap, final boolean order)
    {

        List<Map.Entry<Double, Double>> list = new LinkedList<Map.Entry<Double, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Double, Double>>()
        {
            public int compare(Map.Entry<Double, Double> o1,
                               Map.Entry<Double, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Double, Double> sortedMap = new LinkedHashMap<Double, Double>();
        for (Map.Entry<Double, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static Map<String, Double> sortByComparatorValue(Map<String, Double> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
    }
}
