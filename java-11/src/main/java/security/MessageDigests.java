package security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigests {

    public static void main(String[] args) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");

            BigInteger hash = new BigInteger(1, digest.digest(new byte[]{
                    0b101
            }));

            System.out.println((int) 0b101);
            System.out.println(hash);
            System.out.println(hash.toString(8));
            System.out.println(hash.toString(16));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
