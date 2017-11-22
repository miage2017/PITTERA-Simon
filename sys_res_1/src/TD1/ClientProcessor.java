package TD1;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientProcessor implements Runnable{
    private Socket sock;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    public ClientProcessor(Socket client) {
        sock = client;
    }

    @Override
    public void run(){
        System.err.println("Lancement du traitement de la connexion cliente");

        boolean closeConnexion = false;
        //tant que la connexion est active, on traite les demandes
        while(!sock.isClosed()){

            try {

                writer = new PrintWriter(sock.getOutputStream());
                reader = new BufferedInputStream(sock.getInputStream());
                String line = read();
                System.out.println(line);
                if(line!= null) {
                    if (line.equals("FINISH"))
                        sock.close();
                    else if (line.matches("^NAME:(?!\\s*$).+")) {
                        System.out.println("sending");
                        String name = line.substring(line.indexOf(":") + 1);
                        writer.write("Hello " + name);
                        writer.flush();
                    } else {
                        writer.write(line);
                        writer.flush();
                    }
                }
                else break;



                /*//On attend la demande du client
                String response = read();
                InetSocketAddress remote = (InetSocketAddress)sock.getRemoteSocketAddress();

                //On affiche quelques infos, pour le débuggage
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Demande de l'adresse : " + remote.getAddress().getHostAddress() +".";
                debug += " Sur le port : " + remote.getPort() + ".\n";
                debug += "\t -> Commande reçue : " + response + "\n";
                System.err.println("\n" + debug);*/




            }catch(SocketException e){
                System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String read() throws IOException{
        String response = "";
        int stream;
        byte[] b = new byte[4096];
        stream = reader.read(b);
        if(stream==-1)
            return null;
        response = new String(b, 0, stream);
        return response;
    }
}
