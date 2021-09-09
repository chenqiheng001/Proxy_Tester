import sun.net.www.protocol.http.AuthCacheImpl;
import sun.net.www.protocol.http.AuthCacheValue;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.*;

public class Junk {
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

    public static void main(String[] args) throws IOException {
        //        System.setProperty("http.proxyHost", "usa.resi.pookyyproxies.com");
//        System.setProperty("http.proxyPort", "16733");
//        System.setProperty("http.proxyUser", "yM495yUP");
//        System.setProperty("http.proxyPassword", "F0qvFONhAi4DnTpB0ewEMDqMzSvTtk2d0j2MJgfwLT6XUVjQHUq2lznE8kMkclPb6vfGp-PzxBdDkgj6");


//        Authenticator.setDefault(new ProxyAuthenticator("yM495yUP", "F0qvFONhAi4DnTpB0ewEMDqMzSvTtk2d0j2MJgfwLT6XUVjQHUq2lznE8kMkclPb6vfGp-qWVXO4qseN"));
//        System.setProperty("https.proxyHost", "usa.resi.pookyyproxies.com");
//        System.setProperty("https.proxyPort", "5400");
//
//        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");

        /*162.254.4.130:6964:A97UM:X96GP9QN*/
        String h = "162.254.4.130";
        int port = 6964;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(h,port));

        try {
            AuthCacheValue.setAuthCache(new AuthCacheImpl());
            Authenticator.setDefault(new ProxyAuthenticator("A97UM", "X96GP9QN"));
        }
        catch(Exception e) {
            System.out.println("haha");
        }
        URL url = new URL("https://www.google.com/");
        try{
            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
            long start = System.currentTimeMillis();
            httpsConn.connect();
            long end = System.currentTimeMillis();
            System.out.println("Response time:"+(end-start)+"ms");

        }
        catch (Exception e){
            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
            int status = httpsConn.getResponseCode();
            System.out.println(status);
        }


//        h = "199.116.44.171";
//        port = 8989;
//        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(h,port));
//
//        try {
//            AuthCacheValue.setAuthCache(new AuthCacheImpl());
//            Authenticator.setDefault(new ProxyAuthenticator("1p8c9plc", "elj6ol1+"));
//        }
//        catch(Exception e) {
//            System.out.println("haha");
//        }
//        url = new URL("https://www.google.com/");
//        try{
//            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
//            long start = System.currentTimeMillis();
//            httpsConn.connect();
//            long end = System.currentTimeMillis();
//            System.out.println("Response time:"+(end-start)+"ms");
//
//        }
//        catch (Exception e){
//            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection(proxy);
//            int status = httpsConn.getResponseCode();
//            System.out.println(status);
//        }
    }

}
