import java.net.*;
import java.io.*;
import java.util.*;
import java.time.*;

public class Server{

	public static ArrayList<ClientHandler> clientCarrier = new ArrayList<>(); //Arraylist to store users. 																													   
	private BufferedReader input;											   //Arraylist are very convenient to iterate through 
	private ServerSocket ss;												   //so it is the ideal way to store users.
	private BufferedWriter bufferedWriter;
	private String username;
	static int i = 1;
	java.util.Date date= new java.util.Date();
	
	public Server(ServerSocket ss){
		this.ss = ss;
	}

	public void startServer() throws IOException {
			
		while(!ss.isClosed()){ // While the Socket is NOT closed
		
			try{
					

				Socket s = ss.accept(); // Accept Client
				System.out.println("Client #" + i + " accepted"); 

				i++;

				bufferedWriter = new BufferedWriter(new OutputStreamWriter (s.getOutputStream()));
				input = new BufferedReader( new InputStreamReader(s.getInputStream()));
					
				this.username = input.readLine(); //get the username provided by user
										
				broadcastMessage("\n" + username + " joined the chat         \n");
					
				ClientHandler hand = new ClientHandler(s, username, input, bufferedWriter); //run a new Arraylist

				Thread thread = new Thread(hand); //Thread for multiple being on the same server
					
				clientCarrier.add(hand); //add user to the initial ArrayList

				thread.start(); 		

				} catch (IOException e){
					input.close(); 
					bufferedWriter.close();
					ss.close();
			}

		}
	}// end method

	// Method to Broadcast Message whenever new person joins
	public void broadcastMessage(String messagetoSend){ 
	 	for (ClientHandler clientHandler: clientCarrier) { //iterate through the Arraylist

	 		try{
	 			if (!clientHandler.username.equals(username)){ 
	 				clientHandler.bufferedWriter.write(messagetoSend + date); 
	 				clientHandler.bufferedWriter.newLine(); 
	 				clientHandler.bufferedWriter.flush();
	 			}
	 		} catch(Exception e){
	 		
	 		}
	 	}
	 }

	public static void main(String[] args) throws IOException {
	
		ServerSocket ss = new ServerSocket(9610); 
		System.out.println("Server Running");
		System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
		System.out.println("Port: " + ss.getLocalPort());
		Server server = new Server(ss);
		server.startServer();
	}
}// end class