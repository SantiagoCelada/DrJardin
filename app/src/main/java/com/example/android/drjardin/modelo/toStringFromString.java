package com.example.android.drjardin.modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Santiago Celada González on 18/03/2017.
 */

public class toStringFromString {

    public toStringFromString() {
    }

    public static String toString(Serializable o) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();


        return org.kobjects.base64.Base64.encode(baos.toByteArray());
    }

    public static Object fromString(String s) {
        ObjectInputStream ois = null;
        Object object = null;
        try {
            byte[] data = org.kobjects.base64.Base64.decode(s);
            ois = new ObjectInputStream(
                    new ByteArrayInputStream(data));
            object = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(toStringFromString.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (Exception ex) {
                Logger.getLogger(toStringFromString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return object;
    }
}
