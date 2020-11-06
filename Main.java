import java.util.concurrent.Semaphore;

public class Main {
    static Semaphore[] fork = new Semaphore[5];

    static Semaphore leftfork = new Semaphore(1);
    static Semaphore rightfork = new Semaphore(1);

    static public void Sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

        static public class Philosopher extends Thread {
        int i;
        public Philosopher(int identifier){i = identifier;}

        public void eat( Semaphore [] utinsils){
            //CRITICAL SECTION-BEGIN
            do {
                try {
                    leftfork = utinsils[i];
                    leftfork.acquire();
                    rightfork = utinsils[(i + 1) % 5];
                    rightfork.acquire();
                    System.out.println("Philosopher " + i +  " is currently eating...");
                    Sleep(1000);
                    System.out.println("Philosopher "+ i + " has finished eating...");
                    leftfork.release();
                    rightfork.release();
                    System.out.println("Philosopher " + i +" is currently thinking...");
                    Sleep(2000);

                } catch (InterruptedException e) {
                } finally {
                    leftfork.release();
                    rightfork.release();
                }
                Sleep(500);
            } while (true);
        }
    }
    public static void main(String[] args) {
        fork[0] = new Semaphore(1);
        fork[1] = new Semaphore(1);
        fork[2] = new Semaphore(1);
        fork[3] = new Semaphore(1);
        fork[4] = new Semaphore(1);

        Philosopher p1 = new Philosopher(0);
        Philosopher p2 = new Philosopher(1);
        Philosopher p3 = new Philosopher(2);
        Philosopher p4 = new Philosopher(3);
        Philosopher p5 = new Philosopher(4);

        p1.eat(fork);
        p2.eat(fork);
        p3.eat(fork);
        p4.eat(fork);
        p5.eat(fork);

    }
}