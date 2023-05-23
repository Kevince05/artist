package artist;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class artist extends Thread{
	public static Semaphore chairs = new Semaphore(4);
	public static int t_min = 5,t_max = 10;
	private int t = 0;
	
	public void run() {
		while(true) {
			t = ThreadLocalRandom.current().nextInt(t_min, t_max);
			try {
				sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(chairs.availablePermits() == 0) {
				chairs.release();
				System.out.println("A chair has been freed after " + t + "s.");
			}
		}
	}
}
