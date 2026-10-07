package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil
{
    private HashUtil() {}

    public static String sha256(String texto)
    {
        if (texto == null)
        {
            throw new IllegalArgumentException("El texto no puede ser null.");
        }
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] digest = md.digest(texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder(digest.length * 2);

            for (byte b : digest) {sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException ex)
        {
            throw new IllegalStateException("SHA-256 no disponible.", ex);
        }
    }
}
