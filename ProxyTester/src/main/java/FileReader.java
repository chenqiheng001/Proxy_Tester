import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class FileReader {
    public static ArrayList<ProxyBuilder> gathered_proxies = new ArrayList<ProxyBuilder>();

    public static void readFromFile(){
        try {
            File file = new File("/Users/chenqiheng/Desktop/proxy.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(":");

                if(line.length > 1 && line.length <4){
                    ProxyBuilder temp = new ProxyBuilder(line[0],line[1],"null","null");
                    gathered_proxies.add(temp);
                }
                else if (line.length  == 4){
                    ProxyBuilder temp = new ProxyBuilder(line[0],line[1],line[2],line[3]);
                    gathered_proxies.add(temp);
                }

            }
            scanner.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        readFromFile();
        for(ProxyBuilder p:gathered_proxies){
            System.out.println(p.ip);
            System.out.println(p.port);
            System.out.println(p.user_name);
            System.out.println(p.password);
        }
    }
}
