package compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Run
{
	
	public static void run(String code)
	{
		
		ArrayList<String> tokens = Tokenize.tokenize(code);
		String token , type , ccode = " ";
		int i , index = tokens.size()/2;
		
		//converting to C
		for(i = 0 ; i < index ; i++)
		{
			
			token = tokens.get(i);
			type = tokens.get(index + i);
			
			if(type.equals("Keyword"))
			{
				
				if(token.equals("Benevis"))
					token = " printf ";
				
				else if(token.equals("Begir"))
					token = " scanf ";
				
				else if(token.equals("agar"))
					token = " if ";
				
				else if(token.equals("ta"))
					token = " while ";
				
				else if(token.equals("Sahih"))
					token = " int ";
				
				else if(token.equals("Ashari"))
					token = " float ";
				
				else if(token.equals("Harf"))
					token = " char ";
				
			}
			
			if(type.equals("Operator"))
			{
				
				if(token.equals("Jam"))
					token = "+";
				
				else if(token.equals("Kam"))
					token = "-";
				
				else if(token.equals("YekiBala"))
					token = "++";
				
				else if(token.equals("YekiPain"))
					token = "--";
				
				else if(token.equals("Zarb"))
					token = "*";
				
				else if(token.equals("Tagsim"))
					token = "/";
				
				else if(token.equals("Bagimonde"))
					token = "%";
				
				else if(token.equals("&B"))
					token = ">";
				
				else if(token.equals("&K"))
					token = "<";
				
				else if(token.equals("&BM"))
					token = ">=";
				
				else if(token.equals("&KM"))
					token = "<=";
				
				else if(token.equals("&MM"))
					token = "==";
				
				else if(token.equals("&NM"))
					token = "!=";
				
			}
			
			if(type.equals("Special"))
			{
				
				if(token.equals("^"))
					token = ";\n";
				
				else if(token.equals("{"))
					token = "(";
				
				else if(token.equals("}"))
					token = ")";
				
				else if(token.equals("["))
					token = "{";
				
				else if(token.equals("]"))
					token = "}";
				
			}
			
			ccode = ccode + token;
			
		}
		
		//adding &
		index = ccode.indexOf("scanf");
		while(index != -1)
		{
			
			index = ccode.indexOf("," , index);
			ccode = ccode.substring(0 , index + 1) + '&' + ccode.substring(index + 1);
			index = ccode.indexOf("scanf" , index + 1);
			
		}
		
		ccode = "#include <stdio.h>\nvoid main() {\n" + ccode + "\n}";
		
		try
		{
			
			//writing C file
			FileWriter myWriter = new FileWriter("C:\\Sina#\\C.c");
			myWriter.write(ccode);
			myWriter.close();
			
			//creating executable
			Process p = Runtime.getRuntime().exec
			("C:\\cygwin\\bin\\gcc.exe C:/Sina#/C.c -o C:/Sina#/C", null, new File("C:\\cygwin\\bin\\"));
			
			//if successful
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			if((stdError.readLine()) == null)
			{
				
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
			
			//if not successful
			else
				Error.error("ERROR OCCURRED!");
			
		}
		catch(Exception e){}
		
	}
	
}