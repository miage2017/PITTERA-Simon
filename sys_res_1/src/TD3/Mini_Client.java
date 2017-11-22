package TD3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Mini_Client extends Thread {
    String hote;
    int port;
    String name;
    Socket serveur;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    public Mini_Client(String hote, int port, String name) {
        this.hote = hote;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        connect();
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter something: ");
        String n = reader.next(); // Scans the next token of the input as an int.
        send(n);
        while (true){

        }
    }

    private void send(String n) {
        writer.write(n);
        writer.flush();
        //System.out.println(name);
        String response = null;
        try {
            response = read();
            System.out.println("\t * " + name + " : Reponse recue " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        try {
            serveur = new Socket(hote,port);
            writer = new PrintWriter(serveur.getOutputStream());
            reader = new BufferedInputStream(serveur.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendInt(int i) {
        writer.write(Integer.toString(i));
        writer.flush();
        //System.out.println(name);
        String response = null;
        try {
            response = read();

            System.out.println("\t * " + name + " : R�ponse re�ue " + response);
            if(i == 99)
                serveur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //M�thode pour lire les r�ponses du serveur
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        response = new String(b, 0, stream);
        return response;
    }

    public void sendName(){
       writer.write("NAME:" + name);
       writer.flush();
        //System.out.println(name);
        String response = null;
        try {
            response = read();

        System.out.println("\t * " + name + " : R�ponse re�ue " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String hote = args[0];
        int port = Integer.parseInt(args[1]);
        String name = args[2];
        System.out.println(hote);
        System.out.println(port);
        System.out.println(name);
        Mini_Client mini_client = new Mini_Client(hote,port,name);
        mini_client.start();

    }
}
