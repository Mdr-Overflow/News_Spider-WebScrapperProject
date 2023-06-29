package Proj;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
public class Encryptor {

    //dce506b8-9b53-41fc-a97f-d77c9f17e1fd

    public class Main {

        public static void main(String[] args) throws Exception {

            String secretKey = "ssshhhhhhhhhhh!!!!"; // this must be 16 characters long
            String apiKey = "dce506b8-9b53-41fc-a97f-d77c9f17e1fd";

            SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);

            byte[] encryptedApiKey = cipher.doFinal(apiKey.getBytes());
            String encryptedApiKeyStr = Base64.getEncoder().encodeToString(encryptedApiKey);

            System.out.println("Encrypted API Key: " + encryptedApiKeyStr);
        }
    }
}
