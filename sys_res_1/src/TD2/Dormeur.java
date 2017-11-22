package TD2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dormeur extends Thread {
    private String nom;
    private List<Thread> threadList;

    public Dormeur(String nom, List<Thread> threadList) {
        this.nom = nom;
        this.threadList = threadList;
    }



    @Override
    public synchronized void run() {
        Random random = new Random();
        try {
            wait(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(nom + " stop wait");

        for (Thread t:threadList
                ) {
            try {
                //System.out.println(nom + " join " + ((Dormeur)t).nom);
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Je suis " + nom + " et j'ai fini");
    }

    public static void main(String[] args) {
        List<Thread> listA = new ArrayList<>();
        List<Thread> listB = new ArrayList<>();
        List<Thread> listC = new ArrayList<>();
        Dormeur C = new Dormeur("C", listC);
        listB.add(C);
        Dormeur B = new Dormeur("B",listB);
        listA.add(B);
        Dormeur A = new Dormeur("A",listA);

        A.start();
        B.start();
        C.start();
    }
}
