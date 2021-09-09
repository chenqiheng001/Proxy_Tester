public class ProxyBuilder {
    public String ip;
    public String port;
    public String user_name;
    public String password;

    public ProxyBuilder(String ip, String port, String user_name, String password){
        this.ip = ip;
        this.port = port;
        this.user_name = user_name;
        this.password = password;
    }

}
