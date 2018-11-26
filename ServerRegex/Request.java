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
	@Override
	public void run(){
		try{
			Check check = new Check();
			String msgIn = socket.readMsg();
      System.out.println("Solicitud - "+msgIn);
			String[] url = msgIn.split("/");
			String[] params = url[1].split("&");
			String msgOut = "res/";
			for (int i=0;i < params.length ; i++) {
				String[] values = params[i].split("=");
				boolean rigth = false;
				if (values[0].equals("")) {
					rigth = false;
				}else{
					switch (url[0]) {
						case "integer":
							rigth = check.noEsUnNumeroEntero(values[1]);
						break;
						case "noEsAlfabetico":
							rigth = check.noEsAlfabetico(values[1]);
						break;
						case "noEsAlfanumerico":
							rigth = check.noEsAlfanumerico(values[1]);
						break;
						case "usuario":
							rigth = check.usuario(values[1]);
						break;
						case "pass":
							rigth = check.contraseña(values[1]);
						break;
					}
				}
				msgOut += values[0]+"="+ ((!rigth)? "true":check.getMensaje());
			}
			System.out.println("Respuesta - "+msgOut);
			socket.sendMsg(msgOut);
			socket.close( );
		}catch(Exception ex){
			ex.printStackTrace( );
		}
	}
}
