import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class CmdServer extends Thread{

ServerSocket sock;
Socket client;
PrintWriter pout;
BufferedReader in;

String clientMessage = "empty";
String revClientMessage = "rev";

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
// Function to return reverse message from client 
void ReverseMessage(){
	StringBuilder input = new StringBuilder();
	input.append(clientMessage);
	input = input.reverse();
	String ReverseStringMessage = input.toString();
	revClientMessage = ReverseStringMessage;
	SysLib.cout("(Server) Reversed string: "+ revClientMessage + "\n");
}
// Set up server port
void setUpServer(){
	try{
		for(int i = 0; i<=500;i++){
		if(isTcpAvailable(5000+i) == true){
			  sock = new ServerSocket(5000+i);
	 		 SysLib.cout(sock.getLocalSocketAddress()+" Listening on port: "+ sock.getLocalPort()+"\n");
			  break;
		}
	}

	}catch(IOException e){
		SysLib.cout("Error in Server setup: " + e);
	}
}
// Connect client server and init streams.
void connectToClientServer(){
	try{
		client = sock.accept();
		pout = new PrintWriter(client.getOutputStream(),true);
        in = new BufferedReader(
			 new InputStreamReader(client.getInputStream()));

	} catch(IOException e){
		SysLib.cout("Error in Server setup: " + e);
	}
}
// Display message from Client
void DisplayClientMessage(){
	try{
	  String line;
      while ( (line = in.readLine()) != null){
        SysLib.cout("(Server) " + line + "\n");
		clientMessage = line;
	  }
		
      
	}catch(IOException e){
		SysLib.cout("Error in Server setup: " + e);
	}

}

// Write Message back to client 
void SendMessage(){
	
		//out.println(revClientMessage);
		//pout.flush();
}

  public void run() {
	try {
	
		setUpServer();

		connectToClientServer();

		DisplayClientMessage();
		SysLib.cout("(Server) Reversed string: "+ revClientMessage + "\n");

		ReverseMessage();

		//SendMessage();
		pout.println(revClientMessage);
		pout.flush();
		
	
		pout.close();
        in.close();
		client.close();
		SysLib.exit();
	}
	catch (IOException ioe) {
		System.err.println(ioe);
	}
  }
  // Start CmdClient here 
  public static void main(String args[]) {
    new CmdServer().start();
  }
}
