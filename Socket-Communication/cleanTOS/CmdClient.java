import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.Object;



public class CmdClient extends Thread
{
  private String hostName;
  private int port;

  DataInputStream input_message = null;
  DataOutputStream output_mesage = null;
  Socket sock = null;


  // Validation function to enforce Tcp ports from 5000-5500.
  boolean isPortValid(int Port){
    if(Port >= 5000 || Port <= 5500){
      return true;
    }
    else{
      return false;
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
    try {
      
      /* make connection to server socket */
     sock = new Socket(hostName,port);
      SysLib.cout("Client Connected on : " + hostName +"\n");

       input_message = new DataInputStream(System.in);
       output_mesage = new DataOutputStream(sock.getOutputStream());

     }
     // Execption for unknow host. 
       catch(UnknownHostException u){
      System.err.println(u);
      }
       catch(IOException e){
       System.err.println(e);
       }


      String user_input = "";
      while(!user_input.equals("bye") || !user_input.equals("die") ){
        try{
          user_input = input_message.readLine();
          output_mesage.writeUTF(user_input);
         }
        catch(IOException i){
          System.out.println(i);
         }
       }

      try{
       /* close the socket connection*/
       input_message.close();
       output_mesage.close();
       sock.close();
       SysLib.exit( );
       }
      catch(IOException e){
       //System.err.println(e);
       }
    
  }

  // Start CmdClient here 
  public static void main(String args[]) {
    new CmdClient(args).start();
  }
}
