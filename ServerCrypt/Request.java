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
			/*crypt.crypter c1 = new aes();
			crypt.crypter c2 = new des();
			String msg = c1.encrypt("nfidovfd","cds");*/
      String msgIn = socket.readMsg();
      System.out.println(msgIn);
      String msgOut = "Hi I'm a host";
			socket.sendMsg(msgOut);
			socket.close( );
		}catch(Exception ex){
			ex.printStackTrace( );
		}
	}
}
