package elak.readinghood.backend.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author Spiros
 */
class ServerConnection {
    private static PrintStream originalStream = System.out;
    private static PrintStream noOutputStream = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) {
        }
    });

    /**
     * This function sends and authenticated request to the server.
     *
     * @param link          of the request
     * @param email         of the user
     * @param password      of the user
     * @param requestMethod server based method
     * @return the request that was asked to deliver
     * @throws IOException Can not Connect to server
     */
    protected static String sendAuthenticatedRequest(String link, String email, String password, String requestMethod) throws IOException, MalformedURLException {
        try {
            System.setOut(noOutputStream); // Silence all outputs

            String authString = email + ":" + password;
            String authStringEncrypted = new String(Base64.getEncoder().encode(authString.getBytes()));

            // Send request
            URL obj = new URL(link);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("User-Agent", "RH-Client");
            con.setRequestProperty("Authorization", "Basic " + authStringEncrypted);

            // Get request response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.setOut(originalStream); // Desilence all outputs

            return response.toString();
        } catch (MalformedURLException e) {
            System.setOut(originalStream); // Desilence all outputs
            throw new MalformedURLException();
        } catch (IOException e) {
            System.setOut(originalStream); // Desilence all outputs
            throw new IOException();
        }
    }

    /**
     * This function sends and a simple request to the server.
     *
     * @param link          of the request
     * @param requestMethod server based method
     * @return the request that was asked to deliver
     * @throws IOException Can not Connect to server
     */
    protected static String sendSimpleRequest(String link, String requestMethod) throws IOException {
        try {
            System.setOut(noOutputStream); // Silence all outputs

            // Send request
            URL obj = new URL(link);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("User-Agent", "RH-Client");

            // Get request response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.setOut(originalStream); // Desilence all outputs

            return response.toString();
        } catch (IOException e) {
            System.setOut(originalStream);
            throw new IOException();
        }
    }
}