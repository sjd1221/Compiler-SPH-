package compiler;

import java.io.File;

public class Main
{
	
	public static void main(String[] args)
	{
		
		//folder for storing codes
		new File("C://Sina#//codes").mkdirs();
		
		//compiler frame
		Frame f = new Frame();
		f.frame();
		
	}
	
}