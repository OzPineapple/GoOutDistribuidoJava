import java.net.*;
import java.io.*;

/**
* @author Zush18
**/
public class ServerClient {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("ERROR: Need 2 data, <host>, <port>");
    }else{
      try{
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        SocketIO socket = new SocketIO(host, port);
        socket.sendMsg("crypt:encrypt/msg=Gatitos kawai&pass=tumama");
        socket.sendMsg("regex:validate/text=zzush18@gmail.com");
        System.out.println(socket.readMsg());
        socket.close();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    }
  }
}
