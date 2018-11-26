import java.net.*;
import java.io.*;
import crypt.crypter;
import crypt.aes;


/**
* @author Zush18
**/

public class Request extends Thread {
	private SocketIO socket;
	public Request(Socket socket) throws Exception{
		this.socket = new SocketIO (socket);
	}
	public String getBack(String url){
		String msgOut = "res|msg~";
		crypter crypt = new aes();
		String value = msgIn.substring(msgIn.indexOf("~")+1);
		switch (msgIn.substring(0,msgIn.indexOf("|"))) {
			case "encrypt":
				msgOut += crypt.encrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
			case "decrypt":
				msgOut += crypt.decrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
		}
		return msgOut;
	}
	@Override
	public void run(){
		try{
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
