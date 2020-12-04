import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.Object;



public class CmdClient extends Thread
{
  private String hostName;
  private int port;

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

        // Port validator
        if(isPortValid(args[0]){
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
      Socket sock = new Socket(hostName,port);
      SysLib.cout("Client Connected on : " + hostName +"\n");

      InputStream in = sock.getInputStream();
      BufferedReader bin = new
      BufferedReader(new InputStreamReader(in));

      /* read the date from the socket */
      String line;
      while ( (line = bin.readLine()) != null)
        SysLib.cout(line);

      /* close the socket connection*/
      sock.close();
      SysLib.exit( );
    }
    catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  // Start CmdClient here 
  public static void main(String args[]) {
    new CmdClient(args).start();
  }
}
