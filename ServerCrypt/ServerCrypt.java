import java.net.*;
import java.io.*;
import crypt.*;

public class ServerCrypt {

  public static void main(String[] args) throws Exception{
    //Se crea un ServerSocket con argumentos del arreglo en la prosicion 0
    ServerSocket socketConexion = new ServerSocket(new Integer(args[0]));

    //Ciclo infinito
    while(true)	{
      //Crea un hilo y pasa como parámetro el socket de conexion
      new threadSocket(socketConexion.accept()).start();
    }
  }
}

class threadSocket extends Thread  {
  Socket server;
  threadSocket(Socket socket){
    this.server = socket;
  }
  @Override
  public void run(){
    try{
      //Se crean las conexiones de entrada y salida con el cliente
      InputStream in = server.getInputStream();
      OutputStream out = server.getOutputStream();

      //Se crea un objeto de BufferedReader para leer lo que llega del socket
      BufferedReader leer = new BufferedReader(new InputStreamReader(in));

      //Se crea un portl para escribir
      PrintWriter msg = new PrintWriter(new OutputStreamWriter(out));

      //Se le asigana a la variable url lo que el BufferedReader leyó de lo que llegó
      String url = leer.readLine();

      //A la variable criptado se le asigna un proceso de encriptacion del url
      String criptado = getBack(url);

      //Se imprime el criptado
      msg.println(criptado);

      //Se envía de regreso el criptado
      msg.flush();

      //Se cierra la conexion
      server.close();
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }
  //No modificar
  public String getBack(String url) throws Exception{
		String msgOut = "";
		crypter crypt = new aes();
		String value = url.substring(url.indexOf("|")+1);
		switch (url.substring(0,url.indexOf("|"))) {
			case "encrypt":
				msgOut = crypt.encrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
			case "decrypt":
				msgOut = crypt.decrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
		}
		return msgOut;
	}
}
