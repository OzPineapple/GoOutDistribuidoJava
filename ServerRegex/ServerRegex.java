import java.net.*;
import java.io.*;

public class ServerRegex {

  public static void main(String[] args) throws Exception{
    int puerto = Integer.parseInt(args[0]);
    ServerSocket socketConexion = new ServerSocket(puerto);
    while (true) {
      Socket socket = socketConexion.accept();
      new Thread(new Runnable(){
        @Override
        public void run(){
          try{
            procesarSocket(socket);
          }catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }).start();
    }
    socketConexion.close();
  }
  public static void procesarSocket(Socket socket) throws Exception{
    InputStream flujoEntrada = socket.getInputStream();
    BufferedReader entrada =  new BufferedReader(new InputStreamReader(flujoEntrada));
    OutputStream flujoSalida = socket.getOutputStream();
    PrintWriter salida =  new PrintWriter(new OutputStreamWriter(flujoSalida));
    String rec = entrada.readLine();
    String res1 = getBack(rec);
    salida.write(res1);
    salida.flush();
    socket.close();
  }
  //No modificar
  public static String getBack(String url) {
		String value = url.substring(url.indexOf("|")+1);
		boolean rigth = false;
		Check check = new Check();
		switch (url.substring(0,url.indexOf("|"))) {
			case "integer":
				rigth = check.noEsUnNumeroEntero(value);
			break;
			case "usuario":
				rigth = check.usuario(value);
			break;
			case "pass":
				rigth = check.contraseña(value);
			break;
		}
		return ((!rigth)? "true":check.getMensaje());
	}
}
