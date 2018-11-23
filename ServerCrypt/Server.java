import java.net.*;
import java.io.*;

/**
* @author Zush18
*/
public class Server {

 	public static void main(String[] args) {
			try {
        listen(5000);
			}catch (Exception ex) {
				ex.printStackTrace( );
			}
	}
  private void listen(int port) throws Exception{
    ServerSocket socketConexion = new ServerSocket(port);
    while(true)	{
      new hilo(socketConexion.accept()).start();
    }
  }
}
