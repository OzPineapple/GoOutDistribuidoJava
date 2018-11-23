package crypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 *
 * @author Zush18
 */
public class des extends crypter {
    Cipher c;
    public des() throws Exception{
        c = Cipher.getInstance("DES/ECB/PKCS5padding");
    }
    
    private SecretKey processKey(String ks) throws Exception{
        while(ks.length()<8) ks+= " ";
        while(ks.length()>8) ks = ks.substring(0, ks.length()-2);
        DESKeySpec kspec = new DESKeySpec(ks.getBytes());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey key = skf.generateSecret(kspec);
        return key;
    }
    @Override
    public String encrypt(String mes, String ks, int size) throws Exception{
        //Genreación de la llave
        SecretKey key = this.processKey(ks);
        //instancia del cifrador
        c.init(Cipher.ENCRYPT_MODE, key);
        //Encriptar
        byte[] mesUt8 = mes.getBytes("utf8");
        byte[] mesC = c.doFinal(mesUt8);
        String mesC64 = new BASE64Encoder().encode(mesC);
        return mesC64;
    }

    @Override
    public String decrypt(String mes, String ks, int size) throws Exception{
        //instancia del cifrador
        SecretKey key = this.processKey(ks);
        c.init(Cipher.DECRYPT_MODE, key);
        //Encriptar
        byte[] mes64 = new BASE64Decoder().decodeBuffer(mes);
        byte[] mesDutf8 = c.doFinal(mes64);
        String mesUtf8 = new String(mesDutf8,"utf8");
        return mesUtf8;
    }
}
