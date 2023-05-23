package artist;

import java.util.Random;

public class app {
	public static void main(String[] args) {
		Random rnd = new Random();
		int n_clients = rnd.ints(5,10).findFirst().getAsInt();
		for(int i = 0; i < n_clients; i++) {
			new user().start();
		}
		new artist().start();
	}
}
