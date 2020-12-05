import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.Object;



public class CmdClient extends Thread
{
  private String hostName;
  private int port;

  //DataInputStream input_message = null;
  //DataInputStream server_message = null;
  //DataOutputStream output_mesage = null;
  Socket sock = null;
  BufferedReader in;
  BufferedReader stdIn;
  PrintWriter out;



  // Validation function to enforce Tcp ports from 5000-5500.
  boolean isPortValid(int Port){
    if(Port >= 5000 || Port <= 5500){
      return true;
    }
    else{
      return false;
    }
  }

  // Function to Set up Client server port.
  private void setUpClientServer(){
    try{
       /* make connection to server socket */
       sock = new Socket(hostName,port);
       SysLib.cout("Client Connected on : " + hostName +"\n");
       SysLib.cout(" (Client) Enter Message: ");
       stdIn = new BufferedReader(new InputStreamReader(System.in));
       out = new PrintWriter(sock.getOutputStream(),true);
       in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
       
       String fromClient;
       do{
         fromClient = stdIn.readLine();
         out.println(fromClient);

       }
       while(!fromClient.equals("bye"));

    }catch(IOException e){
      SysLib.cout("Error in Port Set up: "+ e);
    }
  }
  /**
    Constructor to set user inputed port 
    and hostname.If hostname isnt provided 
    the default to localhost.
   */
  public CmdClient(String args[]){
    
    // Default to local host
    if(args.length <2){
      SysLib.cout("No Hostname was provided. Default to local host.");
      hostName = "127.0.0.1";

        int temp_port = Integer.parseInt(args[0]);
        // Port validator
        if(isPortValid(temp_port)){
          port = Integer.parseInt(args[0]);
        }
        else{
          SysLib.cout("Port out of bounds... \n\n");
        }
    }
    // Connect to remote host.
    else{
    hostName = args[0];
    port = Integer.parseInt(args[1]);
    }
  }
  
  public void run() {
    try{
     // Set up Client Server with port and hostname.
      setUpClientServer();
     
     
       

      

      // Check for stdin or message from server. 
      String fromServer;
    
      
        try{
          if((fromServer = in.readLine()) != null ){
           SysLib.cout("Client: (from server) "+fromServer + "\n");
           }
            // User input option.
            
        }
        catch(IOException e){
          System.out.println("message execption: " + e);
        }

     sock.close();
     SysLib.exit( );
      
    } catch(IOException e){
        System.out.println("message execption: " + e);
    }
      
     
  }

  // Start CmdClient here 
  public static void main(String args[]) {
    new CmdClient(args).start();
  }
}
