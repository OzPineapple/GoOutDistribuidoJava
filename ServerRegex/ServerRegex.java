import java.net.*;
import java.io.*;

public class ServerRegex {

  public static void main(String[] args) throws Exception{
    int puerto = Integer.parseInt(args[0]);
    ServerSocket socketConexion = new ServerSocket(puerto);
    System.out.println("puerto establecido en el "+puerto);
    while (true) {
      Socket socket = socketConexion.accept();
      System.out.println("conexion aceptada");
      new Thread(new Runnable(){
        @Override
        public void run(){
          try{
            System.out.println("Procesando Solicitud");
            procesarSocket(socket);
          }catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }).start();
    }
    //socketConexion.close();
    //System.out.print("conexion cerrada");
  }
  public static void procesarSocket(Socket socket) throws Exception{
    System.out.println("Crendo flujos");
    InputStream flujoEntrada = socket.getInputStream();
    BufferedReader entrada =  new BufferedReader(new InputStreamReader(flujoEntrada));
    OutputStream flujoSalida = socket.getOutputStream();
    PrintWriter salida =  new PrintWriter(new OutputStreamWriter(flujoSalida));
    System.out.println("Leyendo entrada");
    String rec = entrada.readLine();
    System.out.println("Procesando url");
    String res1 = getBack(rec);
    System.out.println("Enviando respuesta");
    salida.println(res1);
    salida.flush();
    socket.close();
  }
  //No modificar
  public static String getBack(String url) {
		String value = url.substring(url.indexOf("|")+1);
		boolean rigth = false;
		Check check = new Check();
    System.out.println("Validando regex");
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
