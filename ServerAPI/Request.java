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
		//Ian ;v
	 	Socket socket = new Socket ("localhost", 1001 );
 		InputStream flujoEntrada = socket.getInputStream();
 		BufferedReader entrada =  new BufferedReader(new InputStreamReader(flujoEntrada));
 		OutputStream flujoSalida = socket.getOutputStream();
 		PrintWriter salida =  new PrintWriter(new OutputStreamWriter(flujoSalida));
		salida.write(url);
		salida.flush();
		String res = entrada.readLine();
		socket.close();
		return res;
	}


	public String cryptServe(String url){
		//Mike

		//Ip de ServerCrypt en este caso es localhost :3
		InetAddress server = InetAddress.getByName("localhost");

		//Variable int para la longitud
		int longitud = 25;

		//Mi puerto ya está establecido, no se recibe de ninguna insercion
		int puertoServer = 1002;

		//Pues aquí hice mi instancia del socket de datagrama :v
		DatagramSocket clientSocket = new DatagramSocket();

		//Se crea un primer mensaje para preparar el servidor de 25 bytes
		byte[] msg1 = url.getBytes().lenght;

		//Se usa para enviar el mensaje para prepararse
		DatagramPacket dataMsg1 = new DatagramPacket(msg1, msg1.lenght, server, 1002);
		clientSocket.send(dataMsg1);//Primer mensaje de preparacion

		//Como es un socket de datagrama, se debe convertir el String en un arreglo de bytes :')
		//En este caso se utilizó un if para poder mandar datagramas con la correcta longitud
		byte[] msg = url.getBytes();

		//Se usar para poder enviar el datagrama real
		DatagramPacket dataMsg = new DatagramPacket(msg, msg.lenght, server, 1002);

		//Se usa el mètodo send() para enviar el datagrama, el parámetro que se recibe es el datagrama
		clientSocket.send(dataMsg);//Datagrama real

		//Ya cierra la cola socket!!!
		clientSocket.close();
		/*-----------------------------A partir de este punto el client será el receptor del datagrama-----------------------------------*/

		int puertoClient = 1000;



	}
	public String getBack(String url) throws Exception{
		String msgOut = "";
		String data = url.substring(url.indexOf(":")+1);
		System.out.println("DATA: "+data);
		switch(url.substring(0,url.indexOf(":"))){
			case "regex":
				msgOut = regexServe(data);
				System.out.println("Respuesta - "+msgOut);
			break;
			case "crypt":
				msgOut = cryptServe(data);
				System.out.println("Respuesta - "+msgOut);
			break;
		}
		return msgOut;
	}
	@Override
	public void run(){
		try{
			try {
				String msgIn = socket.readMsg();
				System.out.println("Solicitud - "+msgIn);
				socket.sendMsg(getBack(msgIn));
			}catch(Exception ex){
				ex.printStackTrace();
			}
			socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
