package stegnoTexttoImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class decode {

	
	public static String b_msg="";
	public static int len = 0;
	public decode(String stegfile) {
		String File1 = stegfile;
		
		BufferedImage theImage = null;
		File p = new File (File1);
		try{
		theImage = ImageIO.read(p);
		}catch (IOException e){
		e.printStackTrace();
		System.exit(1);
		}
		

		BufferedImage yImage=theImage;
	

	MsgDecode(yImage);
	String msg="";
	//System.out.println("len is "+len*8);
	for(int i=0;i<len*8;i=i+8){
		
		String sub=b_msg.substring(i,i+8);
		
		int m=Integer.parseInt(sub,2);
		char ch=(char) m;
		
		msg+=ch;
		
	}
	System.out.println(msg);
}

	private void MsgDecode(BufferedImage yImage) {
		int j=0;
		int currentBitEntry=0;
		String bx_msg="";
		for (int x = 0; x < yImage.getWidth(); x++){
		for ( int y = 0; y < yImage.getHeight(); y++){
		if(x==0&&y<8){

			int currentPixel = yImage.getRGB(x, y);	
			int red = currentPixel>>16;
			red = red & 255;
			int green = currentPixel>>8;
			green = green & 255;
			int blue = currentPixel;
			blue = blue & 255;
			String x_s=Integer.toBinaryString(blue);
			bx_msg+=x_s.charAt(x_s.length()-1);
			len=Integer.parseInt(bx_msg,2);
			
		}
		else if(currentBitEntry<len*8){
		
			int currentPixel = yImage.getRGB(x, y);	
			int red = currentPixel>>16;
			red = red & 255;
			int green = currentPixel>>8;
			green = green & 255;
			int blue = currentPixel;
			blue = blue & 255;
			String x_s=Integer.toBinaryString(blue);
			b_msg+=x_s.charAt(x_s.length()-1);

			
			currentBitEntry++;	
		
		}
		}
		}

		}
		
	}

