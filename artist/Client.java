package artist;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {
	private Artist artist;
	private int id;
	static int next_id = 0;
	
	
	private final int min_wait_time = 10;
	private final int max_wait_time = 20;
	private int wait_time;
	private Random rnd;
	
	public Client(Artist artist) {
		this.artist = artist; 
		id = next_id;
		next_id++;
		rnd = new Random();
	}
	
	public void run() {
		System.out.println("Client_" + id + " is queueing for a chair...");
		try {
			wait_time = rnd.nextInt(min_wait_time,max_wait_time+1);
			if(artist.chairs.tryAcquire(wait_time, TimeUnit.SECONDS)) {
				System.out.println("Client_" + id + " got a chair!");
				while(true) {
					if(artist.drawing.tryAcquire()) {
						sleep(rnd.nextInt(artist.min_time_to_draw,artist.max_time_to_draw+1) * 1000);
						System.out.println("Client_" + id + " is done and leaves.");
						artist.drawing.release();
						artist.chairs.release();
						join();
					}
				}
			}else {
				System.out.println("Client_" + id + " got tired and left.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}