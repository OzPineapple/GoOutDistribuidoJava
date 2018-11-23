import java.net.*;
import java.io.*;

/**
* @author Zush18
 */
public class SocketIO extends Socket {
 	private Socket socket;
 	private BufferedReader dataIn;
	private PrintWriter dataOut;

 	SocketIO (String host,int port ) throws SocketException, IOException{
  		socket = new Socket (host, port );
      setWays();
 	}

 	SocketIO(Socket socket) throws IOException {
 		this.socket = socket;
 		setWays();
 	}

 	private void setWays() throws IOException{
 		InputStream infoIn = socket.getInputStream();
 		this.dataIn =  new BufferedReader(new InputStreamReader(infoIn));
 		OutputStream infoOut = socket.getOutputStream();
 		this.dataOut =  new PrintWriter(new OutputStreamWriter(infoOut));
 	}

 	public void sendMsg(String mensaje) throws IOException {
		dataOut.println(mensaje);
		dataOut.flush();
 	}

	public String readMsg() throws IOException {
 		String mensaje = dataIn.readLine( );
 		return mensaje;
 	}

	public void close () throws IOException {
 		socket.close();
	}
}
