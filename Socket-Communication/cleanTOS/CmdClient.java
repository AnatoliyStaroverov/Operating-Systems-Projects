import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.Object;



public class CmdClient extends Thread
{
  private String hostName;
  private int port;

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
      port = Integer.parseInt(args[0]);
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


  public static void main(String args[]) {

// args[0],Integer.parseInt(args[1])
    new CmdClient(args).start();
   
  }
}
