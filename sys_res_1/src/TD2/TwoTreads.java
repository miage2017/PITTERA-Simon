package TD2;

public class TwoTreads {
    public static void main(String[] args) {
        Thread threadInc = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=1000; i++){
                    System.out.println("INC: " + i);
                }
            }
        });
        Thread threadDec = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1000; i>=0; i--){
                    System.out.println("DEC: " + i);
                }
            }
        });
        threadInc.start();
        threadDec.start();
    }
}
