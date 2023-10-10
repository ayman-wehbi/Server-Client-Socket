import java.io.*;
import java.util.*;
import java.net.*;



public class ClientHandler implements Runnable {

	public static ArrayList<ClientHandler> clientCarrier = new ArrayList<>();
	 
	 Socket s;
	 BufferedReader input;
	 BufferedWriter bufferedWriter;
	 String username;

	//Method that handles clients
	public ClientHandler  (Socket s, String username, BufferedReader input, BufferedWriter bufferedWriter)throws IOException{

			this.s = s;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter (s.getOutputStream()));
			this.input = new BufferedReader( new InputStreamReader(s.getInputStream()));
			this.username = username;
			
			clientCarrier.add(this);	
	}

	@Override
	 public void run()  {
	 	
	 	String clientMsg;

	 	while (s.isConnected()) {
	 		try{
	 			clientMsg = input.readLine();
	 			broadcastMessage(clientMsg);
	 		} catch (IOException e){
	 
	 		}
	 	}
	} // end method
	 
	public void broadcastMessage(String messagetoSend){
	 	for (ClientHandler clientHandler: clientCarrier) {
	 		try{
	 			if (!clientHandler.username.equals(username)){ //this ensures that the user does not recieve their own message
	 				clientHandler.bufferedWriter.write(messagetoSend);
	 				clientHandler.bufferedWriter.newLine();
	 				clientHandler.bufferedWriter.flush();
	 			}
	 		} catch(Exception e){
	 			
	 		}
	 	}
	}

} // end class