package crypt;

/**
 *
 * @author Zush18
 */
/*
Examen
*/

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class aes extends crypter{
    private static final String Algo ="AES";
    @Override
    public String encrypt(String mes, String ks, int size)throws Exception{
        //Generar llave
        Key key = generateKey(ks, size);
        //Instanciar al chiper
        Cipher c = Cipher.getInstance(Algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        //Encriptar
        System.out.println("$$"+mes);
        byte[] mesBy = mes.getBytes();

        System.out.println("$$" + mesBy);


        byte[] mesC = c.doFinal(mesBy);

        System.out.println("$$" + mesC);


        String mesC64 = new BASE64Encoder().encode(mesC);

        System.out.println("$$" + mesC64);
        return mesC64;


    }
    private SecretKey generateKey(byte[] ksby)throws Exception {
        System.out.println("Llave a usar: "+ksby);
        SecretKey key = new SecretKeySpec(ksby, Algo);
        return key;
    }
    @Override
    public String decrypt(String mes, String ks, int size)throws Exception{
        //Instanciar cipher
        Cipher c = Cipher.getInstance(Algo);
        SecretKey key = generateKey(ks, size);
        c.init(Cipher.DECRYPT_MODE, key);
        //Decriptar
        System.out.println("##"+mes);
        byte[] mes64 = new BASE64Decoder().decodeBuffer(mes);
        System.out.println("##"+mes64);
        byte[] mesDby = c.doFinal(mes64);
        System.out.println("##"+mesDby);
        String mesD = new String(mesDby);
        System.out.println("##"+mesD);
        return mesD;
    }

    private SecretKey generateKey(String ks, int size)throws Exception {
        switch(size){
            case 128:
                while(ks.length()>16) ks = ks.substring(0, ks.length()-2);
                while(ks.length()<16) ks += " ";
            break;
            case 192:
                while(ks.length()>24) ks = ks.substring(0, ks.length()-2);
                while(ks.length()<24) ks += " ";
            break;
            case 256:
                while(ks.length()>32) ks = ks.substring(0, ks.length()-2);
                while(ks.length()<32) ks += " ";
            break;
            default:
                throw new RuntimeException("Class aes: Wrong key size");
        }
        SecretKey key = new SecretKeySpec(ks.getBytes(), Algo);
        return key;
    }
}
