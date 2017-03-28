package com.example.android.drjardin.modelo;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Santiago Celada González on 16/03/2017.
 */

public class Clave {

    public Clave() {

    }

    public static String dameClave(String password) {

        String clave = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); // Se puede cambiar a "UTF-16"
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            clave = bigInt.toString(16);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Clave.class.getName()).log(Level.SEVERE, null, ex);

        }

        return clave;
    }
}
