package artist;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class artist extends Thread{
	public static Semaphore chairs = new Semaphore(4);
	public static int t_min = 5,t_max = 10;
	private int t = 0;
	
	public void run() {
		while(true) {
			if(chairs.availablePermits() < 4) {
				t = ThreadLocalRandom.current().nextInt(t_min, t_max);
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				chairs.release();
				System.out.println("A chair has been freed after " + t + "s.");
			}
		}
	}
}
