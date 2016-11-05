package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Parser {

    static  String jsonUrl = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=EURUSD=X";

    static String currencyUSD = "USD";
    static String currencyEUR = "EUR";
    static String currencyGOLD = "XAU";
    static double usd;
    static double eur;
    static double gold;


    public static int getDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // the format of your date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2")); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        int todayDate = Integer.parseInt(formattedDate);
        return todayDate;

    }


    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];

            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);


            return buffer.toString();
        } finally {

            if (reader != null)
                reader.close();
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println(readUrl(jsonUrl));
           /* String jsonUSD = readUrl(uniteUrl(getDate(), currencyUSD,jsonUrl));
            usd = usdGetter(jsonUSD);
            System.out.println(usd);

            String jsonEUR = readUrl(uniteUrl(getDate(),currencyEUR,jsonUrl));
            eur = eurGetter(jsonEUR);
            System.out.println(eur);

            String jsonGold = readUrl(uniteUrl(getDate(),currencyGOLD,jsonUrl));
            gold = goldGetter(jsonGold);
            System.out.println(gold);

            System.out.println(getDate());*/

    }

  /*  public static double usdGetter(String jsonUSD){
        String usdText = jsonUSD.substring(38,47);
        usd = Double.parseDouble(usdText);
        return usd;
    }

    public static double eurGetter(String jsonEUR){
        String eurText = jsonEUR.substring(33,41);
        eur = Double.parseDouble(eurText);
        return eur;
    }

    public static double goldGetter(String jsonGold){
        String goldText = jsonGold.substring(35,44);
        //System.out.println(goldText);
        gold = Double.parseDouble(goldText);
        return gold;
    }*/

    public static String uniteUrl(int date,String currency,String jsonUrl){
        String sDate = Integer.toString(date);

        return jsonUrl+"valcode="+currency+"&date="+sDate;

    }
}
