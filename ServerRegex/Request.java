import java.net.*;
import java.io.*;

/**
* @author Zush18
**/

public class Request extends Thread {
	private SocketIO socket;
	public Request(Socket socket) throws Exception{
		this.socket = new SocketIO (socket);
	}
	public String getBack(String url) {
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
	@Override
	public void run(){
		try{
			Check check = new Check();
			String msgIn = socket.readMsg();
      System.out.println("Solicitud - "+msgIn);
			String msgOut = getBack(msgIn);
			System.out.println("Respuesta - "+msgOut);
			socket.sendMsg(msgOut);
			socket.close( );
		}catch(Exception ex){
			ex.printStackTrace( );
		}
	}
}
