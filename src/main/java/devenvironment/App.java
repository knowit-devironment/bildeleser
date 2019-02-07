package devenvironment;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Optional;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws URISyntaxException {

        final Socket socket = IO.socket("http://ec2-13-53-187-42.eu-north-1.compute.amazonaws.com:3000");
        socket.on(Socket.EVENT_CONNECT, arg -> {
        }).on("image", arg -> {
            String base64Image = (String) arg[0];
            byte[] decoded = Base64.getDecoder().decode(base64Image);
            Optional<String> hvaFantMan = QRCodeReader.decodeQRCode(decoded);
            if (hvaFantMan.isPresent()) {
                // TODO: Send dette til backend.
                System.out.println("Fant! " + hvaFantMan.get());
            }

        }).on(Socket.EVENT_DISCONNECT, arg -> {
        });

        socket.connect();

    }
}
