/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crypt;

import javax.crypto.SecretKey;

/**
 * 
 * @author Zush18
 */
public abstract class crypter {
    public abstract String encrypt(String mes, String ks, int size)throws Exception;
    public abstract String decrypt(String mes, String ks, int size)throws Exception;
}
