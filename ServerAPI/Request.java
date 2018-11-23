import java.net.*;
import java.io.*;

/**
* @author Zush18
**/

public class hilo extends Thread {
	private SocketIO socket;
	public hilo(Socket socket) throws Exception{
		this.socket = new SocketIO (socket);
	}
	@Override
	public void run(){
		try{
      String msgIn = socketDatos.readMsg();
      System.out.println(msgIn);
      String msgOut = "Hi I'm a host";
			socketDatos.sendMsg(msgOut);
			socketDatos.close( );
		}catch(Exception ex){
			ex.printStackTrace( );
		}
	}
}
