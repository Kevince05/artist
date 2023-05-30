package artist;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class user extends Thread {
    private int customerId;
    private Semaphore chairsSemaphore;
    private Semaphore portraitSemaphore;
    private Random random = new Random();

    public user(int customerId, Semaphore chairsSemaphore, Semaphore portraitSemaphore) {
        this.customerId = customerId;
        this.chairsSemaphore = chairsSemaphore;
        this.portraitSemaphore = portraitSemaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente " + customerId + " è arrivato.");
            if (chairsSemaphore.tryAcquire(artist.MAX_WAIT_TIME, TimeUnit.MILLISECONDS)) {
                // Il cliente ha ottenuto una sedia e può procedere per l'attesa del ritratto
                System.out.println("Cliente " + customerId + " ha ottenuto una sedia.");
                while(true){
                    if(portraitSemaphore.tryAcquire()){
                        //il cliente procede a farsi fare il ritratto
                        getPortrait();
                        System.out.println("Cliente " + customerId + " ha terminato il ritratto.");
                        chairsSemaphore.release();
                        portraitSemaphore.release();
                        break;
                    }
                }
                    
                
            } else {
                // Il cliente ha aspettato troppo e rinuncia al ritratto
                System.out.println("Cliente " + customerId + " ha aspettato troppo e rinuncia al ritratto.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getPortrait() throws InterruptedException {
        
        int portraitTime = random.nextInt(5000) + 1000; // Tempo di esecuzione del ritratto (tra 1 e 5 secondi)
        Thread.sleep(portraitTime);
    }
}