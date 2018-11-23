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
      String msgIn = socket.readMsg();
      System.out.println(msgIn);
			String url[] = msgIn.split(":");
			SocketIO socketClient;
			String hostClient;
			int portCLient;
			switch(url[0]){
				case "regex":
					hostClient = "localhost";
					portCLient = 1000;
				break;
				case "crypt":
				hostClient = "localhost";
				portCLient = 2000;
				break;
				default:
					url[1] = "406"
			}
			socket.sendMsg(url[1]);
			socket.close();
		}catch(Exception ex){
			socket.sendMsg("500");
			socket.close();
			ex.printStackTrace();
		}
	}
}
