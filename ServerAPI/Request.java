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
		salida.println(url);
		salida.flush();
		String res = entrada.readLine();
		socket.close();
		return res;
	}


	public String cryptServe(String url) throws Exception{
		//Mike

		//Instancia para crear el socket
		Socket socketCliente = new Socket("localhost", 1002);

		//Se crean los flujos de entrada
		InputStream in = socketCliente.getInputStream();

		//Se hace la el objeto de BufferedReader para poder leer lo que recibe
		BufferedReader msg = new BufferedReader(new InputStreamReader(in));

		//Se crean los flujos de salida
		OutputStream out = socketCliente.getOutputStream();

		//Se crea un portal para poder leerlo
		PrintWriter mensaje = new PrintWriter(new OutputStreamWriter(out));

		//Aquí se imprime el url y con el metodo flush se manda al servidor
		mensaje.println(url);
		mensaje.flush();

		//Esta variable llamada input lee lo que se recibe del servidor
		String criptado = msg.readLine();

		//Se cierra el socket
		socketCliente.close();

		//Se retorna el mensaje criptado
		return criptado;

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
