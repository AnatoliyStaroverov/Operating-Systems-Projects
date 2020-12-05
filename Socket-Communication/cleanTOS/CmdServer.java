import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class CmdServer extends Thread{

ServerSocket sock;
Socket client;
PrintWriter out;
BufferedReader in;

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
String ReverseMessage(String message){
	StringBuilder input = new StringBuilder();
	input.append(message);
	input = input.reverse();
	String ReverseStringMessage = input.toString();
	return ReverseStringMessage;
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
		out = new PrintWriter(client.getOutputStream(),true);
        in = new BufferedReader(
			 new InputStreamReader(client.getInputStream()));

	} catch(IOException e){
		SysLib.cout("Error in Server setup: " + e);
	}
}
// Display message from Client
void DisplayClientMessage(){
	try{
	  //String message = in.readLine();
	  //out.println(message);
	  //SysLib.cout(message);
	  String line;
      while ( (line = in.readLine()) != null)
        SysLib.cout("(Server) " + line + "\n");
      

	}catch(IOException e){
		SysLib.cout("Error in Server setup: " + e);
	}

}


  public void run() {
	try {
	
		setUpServer();

		connectToClientServer();

		DisplayClientMessage();

		client.close();
		SysLib.exit();
	}
	catch (IOException ioe) {
		System.err.println(ioe);
	}
  }
}
