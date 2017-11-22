package TD1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_multi {
    int port;
    private ServerSocket server = null;
    Boolean isRunning = true;

    public Serveur_multi(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //On lance notre serveur
    public void open(){

        //Toujours dans un thread � part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning){

                    try {
                        //On attend une connexion d'un client
                        Socket client = server.accept();

                        //Une fois re�ue, on la traite dans un thread s�par�
                        System.out.println("Connexion cliente re�ue.");
                        Thread t = new Thread(new ClientProcessor(client));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    public void close(){
        isRunning = false;
    }


    public static void main(String[] args) {
        int MIN_PORT_NUMBER = 0;
        int MAX_PORT_NUMBER = 65535;
        int port = Integer.parseInt(args[0]);
        if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }
        System.out.println(port);
        Serveur_multi serveur_multi = new Serveur_multi(port);
        serveur_multi.open();

    }
}
