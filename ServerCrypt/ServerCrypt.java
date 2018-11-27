import java.net.*;
import java.io.*;

public class ServerCrypt {

public static void main(String[] args) {
  //Puerto del server
  int puertoServer = 1002;

  //Es la instancia para crear el socket del servidor para recibir los datagramas
  DatagramSocket serverSocket = new DatagramSocket(puertoServer);

  //Arreglo para poder alojar el datagrama.
  byte[] bandeja = new byte[5];


  DatagramPacket datagrama = new DatagramPacket(bandeja, 5);

  //Se recibe el datagrama
  serverSocket.receive(datagrama);
  int tamañoEsperado = (int) bandeja;
  bandeja = new byte[tamañoEsperado]
  datagrama = new DatagramPacket(bandeja, tamañoEsperado);
  serverSocket.receive(datagrama);
  //variable para obetener el datagrama, pasarlo a String, encriptarlo y volverlo a convertir en un arreglo de Bytes
  String mensaje = new String(bandeja);
  String mensajeEncriptado = mensaje.getBack(url);


  /*-----------------------------A partir de este punto el server será el emisor del datagrama-----------------------------------*/

  InetAddress receptor = InetAddress.getByName('localhost');

  int cliente = 1000;
  DatagramSocket clienteSocket = new DatagramSocket();

  mensajeEncriptado = (String) url.lenght();
  byte[] encriptado = mensajeEncriptado.getBytes();

  DatagramPacket datagramaSC = new DatagramPacket(encriptado, encriptado.lenght, cliente, 1000);
  clientSocket.send(dataMsg1);

  puertoServer.close();

}
  //No modificar
  public String getBack(String url) throws Exception{
		String msgOut = "";
		crypter crypt = new aes();
		String value = url.substring(url.indexOf("|")+1);
		switch (url.substring(0,url.indexOf("|"))) {
			case "encrypt":
				msgOut = crypt.encrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
			case "decrypt":
				msgOut = crypt.decrypt(value,"d6F3Efeqd6F3Efeq",256);
			break;
		}
		return msgOut;
	}
}
