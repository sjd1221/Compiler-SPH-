package compiler;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Run
{
	
	public static void run(String code)
	{
		
		int sindex , eindex , i = 0 , j;
		String text = code;
		ArrayList<String> quotes = new ArrayList<String>();
		
		//storing string constants
		sindex = text.indexOf('\"');
		while(sindex != -1)
		{
			
			eindex = eind(sindex , text);
			quotes.add(text.substring(sindex , eindex + 1));
			text = text.substring(0 , sindex) + '#' + text.substring(eindex + 1);
			sindex = text.indexOf('\"');
			
		}
		
		StringBuffer sb = new StringBuffer();
		String[] words = Scan.split(text);
		
		//converting tokens
		for (j = 0; j < words.length; j++)
		{
			
			if(words[j].equals("Benevis"))
				words[j] = "printf";
			
			if(words[j].equals("Begir"))
				words[j] = "scanf";
			
			if(words[j].equals("agar"))
				words[j] = "if";
			
			if(words[j].equals("ta"))
				words[j] = "while";
			
			if(words[j].equals("Sahih"))
				words[j] = "int";
			
			if(words[j].equals("Ashari"))
				words[j] = "float";
			
			if(words[j].equals("Harf"))
				words[j] = "char";
			
			if(words[j].equals("Jam"))
				words[j] = "+";
			
			if(words[j].equals("Kam"))
				words[j] = "-";
			
			if(words[j].equals("YekiBala"))
				words[j] = "++";
			
			if(words[j].equals("YekiPain"))
				words[j] = "--";
			
			if(words[j].equals("Zarb"))
				words[j] = "*";
			
			if(words[j].equals("Tagsim"))
				words[j] = "/";
			
			if(words[j].equals("Bagimonde"))
				words[j] = "%";
			
			if(words[j].equals("'") == false && words[j].equals("\"") == false)
			{
				
				sb.append(words[j]);
				sb.append(" ");
				
			}
			
			if(words[j].equals("'"))
			{
				
				sb.append(words[j]);
				j++;
				sb.append(words[j]);
				j++;
				sb.append(words[j]);
				
			}
			
		}
		
		text = sb.toString();
		
		text = text.replace("^" , ";\n");
		text = text.replace("{" , "(");
		text = text.replace("}" , ")");
		text = text.replace("[" , "{");
		text = text.replace("]" , "}");
		text = text.replace("&BM" , ">=");
		text = text.replace("&B" , ">");
		text = text.replace("&KM" , "<=");
		text = text.replace("&K" , "<");
		text = text.replace("&MM" , "==");
		text = text.replace("&NM" , "!=");
		
		//adding &
		sindex = text.indexOf("scanf");
		while(sindex != -1)
		{
			
			sindex = text.indexOf("," , sindex);
			text = text.substring(0 , sindex + 1) + '&' + text.substring(sindex + 1);
			sindex = text.indexOf("scanf" , sindex + 1);
			
		}
		
		//extracting string constants
		sindex = text.indexOf('#');
		while(sindex != -1)
		{
			
			text = text.substring(0 , sindex) + quotes.get(i) + 
					text.substring(sindex + 1);
			sindex = text.indexOf('#' , sindex + quotes.get(i).length());
			i++;
			
		}
		
		text = "#include <stdio.h>\nvoid main() {\n" + text + "\n}";
		
		try
		{
			
			//writing C file
			FileWriter myWriter = new FileWriter("C:\\Sina#\\C.c");
			myWriter.write(text);
			myWriter.close();
			
			//creating executable
			Runtime.getRuntime().exec
			("C:\\cygwin\\bin\\gcc.exe C:/Sina#/C.c -o C:/Sina#/C", null, 
					new File("C:\\cygwin\\bin\\"));
			
			Thread.sleep(3000);
			
			//running the executable in console
			Runtime.getRuntime().exec("cmd /c Start cmd.exe /K \"C:\\Sina#\\C.exe\"");
			
			//storing the run code
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HHmmss");
			LocalDateTime now = LocalDateTime.now();
			FileWriter w = new FileWriter("C:\\Sina#\\codes\\" + dtf.format(now) + ".S#");
			w.write(code);
			w.close();
			Frame.model.addElement(dtf.format(now) + ".S#");
			
		}
		catch(Exception e){}
		
	}
	
	//not mistaking "s inside string value for the one at the end
	static int eind(int sindex, String text)
	{
		
		int eindex = text.indexOf('\"' , sindex + 1);
		
		if(text.charAt(eindex - 1) != '\\')
			return eindex;
		
		else
			return eind(eindex , text);
		
	}
	
}