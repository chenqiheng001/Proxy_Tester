import sun.net.www.protocol.http.AuthCacheImpl;
import sun.net.www.protocol.http.AuthCacheValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class CheckerThread extends Thread{

    static class ProxyAuthenticator extends Authenticator {

        private String user, password;

        public ProxyAuthenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password.toCharArray());
        }
    }

    public void run(){

        while(FileReader.gathered_proxies.size()!=0){
            String host;
            int port;
            String user_name;
            String password;
            long start_time;
            long end_time;

            synchronized(FileReader.gathered_proxies){
                AuthCacheValue.setAuthCache(new AuthCacheImpl());
                ProxyBuilder CurProxy = FileReader.gathered_proxies.get(0);
                host = CurProxy.ip;
                port = Integer.parseInt(CurProxy.port);
                user_name = CurProxy.user_name;
                password = CurProxy.password;
                FileReader.gathered_proxies.remove(0);
            }

            try{
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host,port));
                URL url = new URL("https://www.google.com/");

                if(!user_name.equals("null")){
                    try {
                        Authenticator.setDefault(new ProxyAuthenticator(user_name,password));
                    }
                    catch(Exception e) {
                        System.out.println(host + ":" + port + ":" + user_name + ":" + password + " Error validation");
                    }
                }


                try{
                    HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
                    start_time = System.currentTimeMillis();
                    httpsConn.setConnectTimeout(2000);
                    httpsConn.connect();
                    end_time = System.currentTimeMillis();
                    if(!user_name.equals("null")){
                        System.out.println(host + ":" + port + ":" + user_name + ":" + password +  " Response time:" + (end_time - start_time) + "ms");
                    }

                    else{
                        System.out.println(host + ":" + port + " Response time:" + (end_time - start_time) + "ms");
                    }
                }

                catch (Exception e){
                    HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
                    int status = httpsConn.getResponseCode();

                    if(status == 407){
                        System.out.println(host + ":" + port + ":" + user_name + ":" + password +  " Error validation");
                    }

                    else{
                        System.out.println(host + ":" + port + "Error validation");
                    }
                }




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Thread.currentThread().interrupt();



    }

    public static void main(String[] args) throws InterruptedException {
        FileReader.readFromFile();

        CheckerThread p1 = new CheckerThread();
        p1.run();
        p1.join();
    }
}
