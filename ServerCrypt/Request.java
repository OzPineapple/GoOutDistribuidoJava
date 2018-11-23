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
	@Override
	public void run(){
		try{
      String msgIn = socket.readMsg();
      System.out.println(msgIn);
			String[] url = msgIn.split("/");
			String[] params = url[1].split("&");
			String msgOut = "res/";
			crypter crypt = new aes();
			for (int i=0;i < params.length ; i++) {
				String[] values = params[i].split("=");
				switch (url[0]) {
					case "encrypt":
						msgOut += values[0]+"="+ crypt.encrypt(values[1],"d6F3Efeqd6F3Efeq",256);
					break;
					case "decrypt":
						msgOut += values[0]+"="+ crypt.decrypt(values[1],"d6F3Efeqd6F3Efeq",256);
					break;
				}
			}
			socket.sendMsg(msgOut);
			socket.close( );
		}catch(Exception ex){
			socket.sendMsg("500");
			socket.close( );
			ex.printStackTrace( );
		}
	}
}
