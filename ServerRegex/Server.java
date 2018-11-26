import java.net.*;
import java.io.*;

/**
* @author Zush18
*/
public class Server {

 	public static void main(String[] args) {
			try {
        ServerSocket socketConexion = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("Servidor API escuchando en "+args[0]);
        while(true)	{
          new Request(socketConexion.accept()).start();
          System.out.println("Conexion aceptada");
        }
			}catch (Exception ex) {
				ex.printStackTrace( );
			}
	}
}
