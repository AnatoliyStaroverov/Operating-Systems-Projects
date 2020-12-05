import java.net.*;
import java.io.*;

public class CmdServer extends Thread{

//private ServerSocket sock = null;

// Function to see if port is avaliable.
public static boolean isTcpAvailable(int port){
	boolean portAvalability;
	try(var testSocket = new ServerSocket(port)){
		
		portAvalability = true;

	}catch(Exception ex){
		portAvalability = false;
	}
	return portAvalability;
}

  public void run() {
	try {
	ServerSocket sock  = null;
	for(int i = 0; i<=500;i++){
		if(isTcpAvailable(5000+i) == true){
			  sock = new ServerSocket(5000+i);
	 		 SysLib.cout(sock.getLocalSocketAddress()+" Listening on port: "+ sock.getLocalPort()+"\n");
			  break;
		}
	}

	  /* now listen for connections */
	  while (true) {
		  
		Socket client = sock.accept();

		// Get input from client 
		DataInputStream client_message_stream = new DataInputStream(
			new BufferedInputStream(client.getInputStream()));

		String client_message = "";
		while(!client_message.equals("die") || !client_message.equals("bye") ){
			try{
				client_message = client_message_stream.readUTF();
				System.out.println("Message from Client: "+client_message);
			}
			 catch(IOException i){
          		System.out.println(i);
        	}
		}

		//PrintWriter pout = new
		 // PrintWriter(client.getOutputStream(), true);

		/* write the Date to the socket */
		//pout.println(new java.util.Date().toString());
		/* close the socket and resume */
		/* listening for connections */
		client.close();
		client_message_stream.close();
		SysLib.exit();
	  }
	 
	}
	catch (IOException ioe) {
		System.err.println(ioe);
	}
  }
}
