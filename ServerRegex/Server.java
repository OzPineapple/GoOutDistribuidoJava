import java.net.*;
import java.io.*;

/**
* @author Zush18
*/
public class Server {

 	public static void main(String[] args) {
			try {
        Server socketConexion = new Server(5000);
        while(true)	{
          new Request(socketConexion.accept()).start();
        }
			}catch (Exception ex) {
				ex.printStackTrace( );
			}
	}
}
