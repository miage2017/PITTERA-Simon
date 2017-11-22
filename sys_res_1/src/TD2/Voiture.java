package TD2;

import java.util.Random;

public class Voiture extends Thread {
    private String matricule;
    private Boolean isParked = false;
    private Controleur controleur;


    public Voiture(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public void run() {
        super.run();
        while (!isParked)
            askEnterPark();
        exitPark();
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "matricule='" + matricule + '\'' +
                '}';
    }

    private synchronized void exitPark() {
        Random random = new Random();
        int time = random.nextInt(10000);
        try {
            wait(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(toString() + " s'en va");
        isParked = false;
        controleur.getParking().remove(this);
    }

    private synchronized void askEnterPark() {
        /*try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       // System.out.println(toString() + ": Demande d'entre");
        if(controleur.givePermToEnter()){
            System.out.println(toString() + ": Demande accepte");
            if(controleur.getParking().add(this))
                isParked = true;
        }else{
            //System.out.println(toString() + ": Demande refuse");
        }
    }

    public void start(Controleur controleur) {
        this.controleur = controleur;
        this.start();
    }
}
