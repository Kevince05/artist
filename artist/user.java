package artist;

import java.util.concurrent.TimeUnit;

public class user extends Thread{
	public static int t_wait = 5;
	public void run() {
		try {
			if(artist.chairs.tryAcquire(t_wait, TimeUnit.SECONDS)) {
				System.out.println("A queued client took a chair.");
			}else {
				System.out.println("A queued client left.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
