package TD3;

public class GenClient {

    public void createClient(String hote, int port, String name){
        System.out.println(hote);
        System.out.println(port);
        System.out.println(name);
        Mini_Client mini_client = new Mini_Client(hote,port,name);
        mini_client.start();
    }

    public static void main(String[] args) {

    }
}
