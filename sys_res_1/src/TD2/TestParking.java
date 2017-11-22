package TD2;

public class TestParking {
    public static void main(String[] args) {
        Parking parking = new Parking();
        Controleur controleur = new Controleur(parking);
        Voiture voiture1 = new Voiture("AAA");
        voiture1.start(controleur);
        Voiture voiture2 = new Voiture("AAB");
        voiture2.start(controleur);
        Voiture voiture3 = new Voiture("AAC");
        voiture3.start(controleur);
        Voiture voiture4 = new Voiture("AAD");
        voiture4.start(controleur);
        Voiture voiture5 = new Voiture("AAE");
        voiture5.start(controleur);
        Voiture voiture6 = new Voiture("AAF");
        voiture6.start(controleur);
    }
}
