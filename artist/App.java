package artist;

public class App{
    public static void main(String[] args) {
    	Artist street_artist = new Artist();
    	
    	int n_clients = 10;
    	System.out.println("There are " + n_clients + " clients");   	
    	for(int i = 0; i < n_clients; i++) {
    		new Client(street_artist).start();
    	}
    }   
}