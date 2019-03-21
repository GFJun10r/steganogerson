package steganogerson;

import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Encoder {
	
	private BufferedImage img;
	private BufferedImage encodedImg;
	private String msg;
	private Queue<Integer> msgBits;
	private int Y = 0;
	private int X = 0;

	public Encoder(BufferedImage img, String msg) {
		this.img = img;
		this.msg = msg;
		this.encodedImg = img;
		msgBits = new ArrayBlockingQueue<Integer>(msg.length());
	}

	public static Encoder build(BufferedImage img, String msg) {
		return new Encoder(img, msg);
	}

	public BufferedImage encode() {
		transformMsgToMsgBits();
		encodeHeader();
		encodeMessage();
		return encodedImg;
	}

	private void transformMsgToMsgBits() {
		for (char c : msg.toCharArray()) {
			msgBits.add((int) c);
		}
	}

	private void encodeHeader() {
		boolean stillHaveHeader = true;
		String header = Integer.toBinaryString(msg.length()&0xffffffff);
		
		while (stillHaveHeader || Y < encodedImg.getHeight()) {
			while (stillHaveHeader || X < encodedImg.getWidth()) {
				int p = encodedImg.getRGB(X, Y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;
				
				String aBin = Integer.toBinaryString(a&0xff);
				aBin = aBin.substring(0, 6) + header.substring(0, 2);
				a = Integer.parseInt(aBin,2);
				header = header.substring(2, header.length());
				
				if(header.length()==0) {
					stillHaveHeader = false;
				}

				p = (a << 24) | (r << 16) | (g << 8) | b;
				img.setRGB(X, Y, p);
				
				X++;
			}
			if (stillHaveHeader) {
				Y++;
			}
		}
	}
	
	private void encodeMessage() {
		
	}

}