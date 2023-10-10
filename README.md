# Server-Client-Socket

This is a multithreaded socket chat program created given a simple MyServer/MyClient file as an example to learn the structure of s server and a client. My task was to figure out how to implement a multithreaded server that takes multiple clients through a client handler file. This was all accomplished by using a socket, a server socket, a buffered reader and writer, and strings to intake usernames. I used an ArrayList to store the clients since its linear and very easy to iterate through.

## Server.java

The server file creates a serverSocket for us to run the program locally through the machine’s IP and custom port. It begins with a while loop [while(!ss.isClosed())] so ensure that this method only runs while the server socket is connected. It proceeds to try to accept the client through the socket, then prints out that the client is accepted, preceded by the number of the client who just entered the chat. Then a buffered reader and writer are created as they will be used in our client handler method, and of course to read the username that the user gives. It then takes the client, creates a new thread, and adds the client to the ArrayList right before starting the thread. At last, it broadcasts that a new user has joined the group chat. This is pretty much the while loop of the server. Then we run a main method, throwing an IO Exception due to the try/catch in the previous method, this main method runs a new Server Socket with port number 9610, confirms that the Server is running, prints the IP address, and port number, then starts the Server.

## ClientHandler.java 

This is the extra file that we have to write due to this being a multithreaded program. It is short but obviously essential. This program defines the Client Handler method with a username, socket, and buffered reader and writer. It has to implement a runnable interface so that each instance is run by a different thread. This causes the method run to be overridden before we can start a while loop to broadcast whatever message the user types in when the socket is connected. Then, there is a broadcast method that sends the message to everybody by iterating through the ArrayList and sending each client except the sender the message that was typed by the sender.

## Client.java

The client starts similarly to the clienthandler, but this method takes the username and the socket only, without adding anything to an ArrayList. We have the send message method that runs an if-else scenario within a while loop. These scenarios are in case a user says “bye” or “All Users”. Typing “bye” kicks the user out of the chat using a break. Then we have a method that gets the message, using an override due to the runnable interface. This method uses a .start() method so I did not implement the whole file to be a runnable interface, but only this method. Finally, we have a main method that asks for a nickname with a scanner, connects to the socket to confirm the localhost number, runs a new client, and connects that client to the getmessage and sendmessage methods. I also added a line to make it look a little nicer along with the date of each message. Nothing fancy.

#### Total Work Time: about 5-7 hours.

I ran into so much difficulty making this program accept multiple clients. At first, it would accept a client, but wouldn’t accept a second one until the first one exits. After hours of working, it turns out it was a simple misplacement of the readLine() method. I also had issues as to how to make the server kick a user out after the user types “bye”, but I worked around that. The getmessage method was the hardest to implement because of it having a runnable interface, and I could not figure out the All Users feature.

<img src="https://i.imgur.com/waR8K8t.png" alt="Alt Text" width="1200"/>
<img src="https://imgur.com/yG1YUJO" alt="Alt Text" width="1200"/>







