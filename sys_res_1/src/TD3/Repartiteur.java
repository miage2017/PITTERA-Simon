package TD3;

import TD1.Serveur_multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Repartiteur {
    private int port;
    private ServerSocket serverSocket;
    private boolean isListening = false;

    public Repartiteur(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(){
        Thread t = new Thread(() -> {
            while(isListening){

                try {
                    //On attend une connexion d'un client
                    Socket client = serverSocket.accept();

                    System.out.println("Connexion cliente recue.");
                    Thread t1 = new Thread(new ServiceClient(client));
                    t1.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                serverSocket = null;
            }
        });

        t.start();
    }
    public static void main(String[] args) {

        int MIN_PORT_NUMBER = 0;
        int MAX_PORT_NUMBER = 65535;
        int port = Integer.parseInt(args[0]);
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }
        System.out.println(port);
        Repartiteur serveur_multi = new Repartiteur(port);
        serveur_multi.open();

    }
}
