package TD1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur_Jouet {
    private ServerSocket server = null;
    int port = 12000;
    Boolean isRunning = true;

    public Serveur_Jouet() {
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
        Serveur_Jouet serveur_jouet = new Serveur_Jouet();
        serveur_jouet.open();
    }
}
