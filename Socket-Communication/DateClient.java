import java.net.*;
import java.io.*;

// use cp to copy file contents 
public class DateClient extends Thread
{
  public static void main(String[] args) {
    try {
      /* make connection to server socket */
      Socket sock = new Socket("127.0.0.1",6013);
     

      InputStream in = sock.getInputStream();
      BufferedReader bin = new
      BufferedReader(new InputStreamReader(in));

      /* read the date from the socket */
      String line;
      while ( (line = bin.readLine()) != null)
        SysLib.cout(line);

      /* close the socket connection*/
      sock.close();
    }
    catch (IOException ioe) {
      SysLib.cerr.println(ioe);
    }
  }
}
