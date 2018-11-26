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
	public String regexServe(String url) throws Exception{
		System.out.println("Enviando solicitud regex "+url);
		SocketIO s = new SocketIO("localhost",1001);
		s.sendMsg(url);
		String msgIn = s.readMsg();
		System.out.println("Reciviendo respuesta "+msgIn);
		return msgIn;
	}
	public String cryptServe(String url) throws Exception{
		System.out.println("Enviando solicitud crypt "+url);
		SocketIO s = new SocketIO("localhost",1002);
		s.sendMsg(url);
		String msgIn = s.readMsg();
		System.out.println("Reciviendo respuesta "+msgIn);
		return msgIn;
	}
	@Override
	public void run(){
		try{
      String msgIn = socket.readMsg();
      System.out.println("Solicitud - "+msgIn);
			String url[] = msgIn.split(":");
			String msgOut;
			switch(url[0]){
				case "regex":
					msgOut = regexServe(url[1]);
					socket.sendMsg(msgOut);
					System.out.println("Respuesta - "+msgOut);
				break;
				case "crypt":
					msgOut = cryptServe(url[1]);
					socket.sendMsg(msgOut);
					System.out.println("Respuesta - "+msgOut);
				break;
			}
			socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
