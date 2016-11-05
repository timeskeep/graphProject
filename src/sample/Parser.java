package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Parser {
    @FXML
    JFXButton button;
    @FXML
    JFXTextField textUSD;
    @FXML
    JFXTextField textEUR;
    @FXML
    JFXTextField textGold;

    @FXML
    public void initialize(){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    textUSD.setText(readUrl(uniteUrl(currencyUSDEUR,jsonUrl)).substring(11));
                    textEUR.setText(readUrl(uniteUrl(currencyEURUSD,jsonUrl)).substring(11));
                    textGold.setText(readUrl(uniteUrl(currencyGOLD,jsonUrl)).substring(8));
                } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });
    }

    static  String jsonUrl = "http://finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1&s=";

    static String currencyUSDEUR = "USDEUR";
    static String currencyEURUSD = "EURUSD";
    static String currencyGOLD = "XAU";
    static double usdEUR;
    static double eurUSD;
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

            String jsonUSD = readUrl(uniteUrl(currencyUSDEUR,jsonUrl));
            System.out.println();
            System.out.println(jsonUSD);
            usdEUR = usdGetter(jsonUSD);
            System.out.println(usdEUR);

            String jsonEUR = readUrl(uniteUrl(currencyEURUSD,jsonUrl));
            System.out.println();
            System.out.println(jsonEUR);
            eurUSD = eurGetter(jsonEUR);
            System.out.println(eurUSD);

            String jsonGold = readUrl(uniteUrl(currencyGOLD,jsonUrl));
            System.out.println();
            System.out.println(jsonGold);
            gold = goldGetter(jsonGold);
            System.out.println(gold);


    }

    public static double usdGetter(String jsonUSD){
        String usdText = jsonUSD.substring(11,17);
        usdEUR = Double.parseDouble(usdText);
        return usdEUR;
    }

    public static double eurGetter(String jsonEUR){
        String eurText = jsonEUR.substring(11,17);
        eurUSD = Double.parseDouble(eurText);
        return eurUSD;
    }

    public static double goldGetter(String jsonGold){
        String goldText = jsonGold.substring(8,14);
        gold = Double.parseDouble(goldText);
        return gold;
    }

    public static String uniteUrl(String currency,String jsonUrl){

        return jsonUrl+currency+"=X";

    }
}
