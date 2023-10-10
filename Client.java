import java.net.*;
import java.io.*;
import java.util.*;

public class Client{

	private Socket s;
	private BufferedReader input;
	private BufferedWriter bufferedWriter;
	private String username;
	java.util.Date date = new java.util.Date();

	public Client(String username, Socket s){
		try{
			this.s = s;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter (s.getOutputStream()));
			this.input = new BufferedReader(new InputStreamReader (s.getInputStream()));
			this.username = username;
		}catch (IOException e){

		}
	}// end method

	public void sendMessage(){ 
		try{
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();

			Scanner scanner = new Scanner (System.in); //scanner for messatges user types
			
			while (s.isConnected()){ //while socket is connected
				
				String messageToSend = scanner.nextLine();
					
				if(messageToSend.equals("bye")){ //If user types bye, they leave the chat
					bufferedWriter.write("SERVER: Goodbye " + username + "\n        "+ username +" left the chat \n");
					bufferedWriter.newLine();
					bufferedWriter.flush();
					break;
				}

				else if(messageToSend.equals("All")){ //If user types all, he sees all users (unfinished)
					bufferedWriter.write( "" );
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
				
				else{ //basically send message and add date		
					bufferedWriter.write(username + ": " + messageToSend + "\n" + date + "\n________________________________" + "\n" );
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
			}
			
			}catch (IOException e){		
		
		}
	}// end method
	 
	public void getMessage(){

		new Thread(new Runnable() {
			@Override
			public void run(){

				String chatMsg;

				while (s.isConnected()) {

					try{ 
						
						chatMsg = input.readLine();
						System.out.println(chatMsg);

					}catch (IOException e){
					}	
				}
			}
		}).start();
	}// end method


	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter nickname");
		String username = scanner.nextLine();
		
		Socket s = new Socket("localhost", 9610);
		Client client = new Client(username, s);
		
		
		client.getMessage();
		client.sendMessage();
	}// end method
}// end class