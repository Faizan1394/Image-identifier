package sample;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class sampleClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        OutputStream outputStream = socket.getOutputStream();

        BufferedImage image = ImageIO.read(new File("Client_IMG/images3.jpg"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);

        byte[] size = ByteBuffer.allocate(64).putInt(byteArrayOutputStream.size()).array();
        outputStream.write(size);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.flush();

        socket.close();
    }
}