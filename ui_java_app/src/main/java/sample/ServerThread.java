package sample;

import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public class ServerThread extends Thread{
	protected Socket socket;
	InputStream inputStream;
    String fileName = "test_sent";

	public ServerThread(Socket socket) {
		super();
		this.socket = socket;

		 try {
			 inputStream = socket.getInputStream();
		 	} 
		 catch (IOException e) { e.printStackTrace(); }
	}

	public void run() {
		try {
			byte [] sizeArr = new byte [64];
			inputStream.read(sizeArr);
			int size = ByteBuffer.wrap(sizeArr).asIntBuffer().get();

			byte[] imageAr = new byte[size];
			inputStream.read(imageAr);

			BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
			ImageIO.write(image, "jpg", new File("Server_IMG/"+fileName+".jpg"));

		}
		catch (Exception e) { e.printStackTrace(); }
	}
}