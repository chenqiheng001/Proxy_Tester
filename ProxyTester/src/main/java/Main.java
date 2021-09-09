import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.HttpsURLConnection;

public class Main {


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileReader.readFromFile();
        for(int i = 0; i < 1; i++){
            CheckerThread thread = new CheckerThread();
            thread.start();
        }
        long end = System.currentTimeMillis();

    }

}
