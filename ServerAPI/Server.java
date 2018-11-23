import java.net.*;
import java.io.*;

/**
* @author Zush18
*/
public class Server {

 	public static void main(String[] args) {
			try {
        ServerSocket socketConexion = new ServerSocket(5000);
        while(true)	{
          new Request(socketConexion.accept()).start();
        }
			}catch (Exception ex) {
				ex.printStackTrace( );
			}
	}
}
