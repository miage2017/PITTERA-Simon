package TD2;

import java.util.ArrayList;

public class Parking {
    private ArrayList<Voiture> voitures;
    private int nbPlace = 2;
    private boolean isLocked = true;

    public Parking() {
        this.voitures = new ArrayList<>();
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public int getNbVoiture() {
        return voitures.size();
    }

    @Override
    public String toString() {
        return "Parking{" +
                "voitures=" + voitures +
                ", nbPlace=" + nbPlace +
                '}';
    }

    public boolean add(Voiture voiture){
        if(!isLocked){
            isLocked = true;
            voitures.add(voiture);
            System.out.println(this);
            return true;
        }else {
            System.out.println(voiture.toString() + ": Parking locked");
            return false;
        }
    }

    public void remove(Voiture voiture){
        voitures.remove(voiture);
        System.out.println(this);
    }

    public void unlock() {
        isLocked = false;
    }

    public void lock() {
        isLocked = true;
    }
}
