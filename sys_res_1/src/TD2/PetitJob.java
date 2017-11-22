package TD2;

public class PetitJob implements Runnable {
    private ObjetEntier notre_entier ;
    private int increment;
    PetitJob(ObjetEntier notre_entier , int increment){
        this.increment=increment;
        this.notre_entier = notre_entier;
    }
    public void run() {
        for (int i = 0; i < 1e8; i++)
        {
            notre_entier.add(increment);
        }
        System.out.format("Thread faisant %d - termine, compteur= %s\n" , increment,notre_entier);

    }
    public static void main(String[] args) {
        ObjetEntier Compteur = new ObjetEntier();
        PetitJob objex1 = new PetitJob(Compteur, 1);
        PetitJob objex2 = new PetitJob(Compteur, -1);
        Thread tache1 = new Thread(objex1, "incrementeur");
        Thread tache2 = new Thread(objex2, "decrementeur");

        tache1.start();
        tache2.start();

        System.out.format("Le compteur vaut %s\n", Compteur);
        System.out.format("Le compteur vaut %s\n", Compteur);

        try {
            tache1.join();
            tache2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Compteur final " + Compteur);

        /*int j = 0;
        while (j<10e9)
            j++;
        System.out.format("Le compteur vaut %s\n" , Compteur);*/
    }
}
