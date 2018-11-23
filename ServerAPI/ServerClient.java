import java.net.*;
import java.io.*;

/**
* @author Zush18
**/
public class ServerClient {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("ERROR: Need 2 data, <host>, <port>, <url>");
    }else{
      try{
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String url = args[2];
        SocketIO socket = new SocketIO(host, port);
        socket.sendMsg(url);
        System.out.println(socket.readMsg());
        socket.close();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    }
  }
}
