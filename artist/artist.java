package artist;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class artist {
    private static final int NUM_CHAIRS = 4; // Numero di sedie disponibili
    static final int MAX_WAIT_TIME = 20000; // Tempo massimo di attesa per una sedia (in millisecondi)

    private Semaphore chairsSemaphore = new Semaphore(NUM_CHAIRS);
    private Semaphore portraitSemaphore = new Semaphore(1);

    public void startSimulation() {
        Random random = new Random();
        int numusers = random.nextInt(10) + 1; // Numero casuale di clienti (tra 1 e 10)

        System.out.println("Numero di clienti: " + numusers);

        for (int i = 1; i <= numusers; i++) {
            user user = new user(i, chairsSemaphore, portraitSemaphore);
            user.start();
        }
    }
    
}