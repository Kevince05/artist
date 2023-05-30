package artist;

import java.util.concurrent.Semaphore;

public class Artist {
	public Semaphore chairs = new Semaphore(4);
	public Semaphore drawing = new Semaphore(1);
	public final int min_time_to_draw = 5;
	public final int max_time_to_draw = 10;
}