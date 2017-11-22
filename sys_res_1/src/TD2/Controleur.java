package TD2;

public class Controleur {

    private Parking parking;

    public Controleur(Parking parking) {
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public boolean givePermToEnter(){
        if(parking.getNbPlace() > parking.getNbVoiture()){
            parking.unlock();
            return true;
        }else {
            parking.lock();
            return false;
        }
    }
}
