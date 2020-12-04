import java.net.*;
import java.io.*;
import java.util.*;


// use cp to copy file contents 
public class DateClient extends Thread
{
  
  public void run() {
    try {
      /* make connection to server socket */
      Socket sock = new Socket("127.0.0.1",6009);
      SysLib.cout("Client Connected..\n");
     

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
}
