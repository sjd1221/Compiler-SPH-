package compiler;

import java.util.ArrayList;

public class Parse
{
	
	public static void parse(String code)
	{
		
		ArrayList<String> tokens = Tokenize.tokenize(code);
		ArrayList<String> declared = new ArrayList<String>();
		int i = 0 , j = tokens.size()/2 , state = 0 , count = 0 , flag = 0 , ind;
		tokens.add(j, "$"); tokens.add(j, "$"); tokens.add(j, "$"); tokens.add(j, "$"); tokens.add(j, "$");
		tokens.add("$"); tokens.add("$"); tokens.add("$"); tokens.add("$"); tokens.add("$"); j = j + 5;
		
		//finite automata
		while(i < j && state != 100)
			switch(state)
			{
			
			case 0:
				if(tokens.get(i).equals("$") == false)
					state = 100;
				
				if(tokens.get(i + j).equals("Keyword"))
				{
					
					if(tokens.get(i).equals("Harf") || tokens.get(i).equals("Sahih") || tokens.get(i).equals("Ashari"))
						state = 1;
					
					if(tokens.get(i).equals("Benevis") || tokens.get(i).equals("Begir"))
						state = 5;
					
					if(tokens.get(i).equals("ta") || tokens.get(i).equals("agar"))
						state = 7;
					
				}
				
				if(tokens.get(i + j).equals("Identifier"))
				{
					
					if(declared.contains(tokens.get(i)))
						state = 10;
					
					else
						flag = 1;
					
				}
				
				if(tokens.get(i + j).equals("Special"))
				{
					
					if(tokens.get(i).equals("["))
						{state = 0; count++;}
					
					if(tokens.get(i).equals("]"))
						{state = 0; count--;}
					
				}
				
				i++;
				break;
				
			case 1:
				if(tokens.get(i + j).equals("Identifier"))
				{
					
					state = 2;
					declared.add(tokens.get(i));
					
				}
				
				else
					{state = 100; flag = 7;}
				
				i++;
				break;
					
			case 2:
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("^"))
					state = 0;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals(","))
					state = 1;
				
				if(tokens.get(i + j).equals("Operator") && tokens.get(i).equals("="))
					state = 3;
				
				if(state == 2)
					{state = 100; flag = 6;}
				
				i++;
				break;
			
			case 3:
				if(tokens.get(i + j).equals("Identifier"))
				{
					
					if(declared.contains(tokens.get(i)))
						state = 4;
					
					else
						flag = 1;
					
				}
				
				if(tokens.get(i + j).equals("Number"))
					state = 4;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("'"))
					if(tokens.get(i + j + 1).equals("Character"))
					{
						
						if(tokens.get(i + j + 2).equals("Special") && tokens.get(i=i+2).equals("'"))
							state = 4;
						
						else
							flag = 2;
						
					}
				
				if(state == 3)
					state = 100;
				
				i++;
				break;
				
			case 4:
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("^"))
					state = 0;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals(","))
					state = 1;
				
				if(state == 4)
					{state = 100; flag = 6;}
				
				i++;
				break;
				
			case 5:
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("("))
					if(tokens.get(i + j + 1).equals("Special") && tokens.get(i=i+1).equals("\""))
						if(tokens.get(i + j + 1).equals("String"))
						{
							
							if(tokens.get(i + j + 2).equals("Special") && tokens.get(i=i+2).equals("\""))
								state = 6;
							
							else
								flag = 3;
							
						}
				
				if(state == 5)
					state = 100;
				
				i++;
				break;
				
			case 6:
				state = 100;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals(")"))
				{
					
					if(tokens.get(i + j + 1).equals("Special") && tokens.get(i=i+1).equals("^"))
						state = 0;
					
					else
						flag = 6;
					
				}
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals(","))
				{
					
					if(tokens.get(i + j + 1).equals("Identifier"))
					{
						
						if(declared.contains(tokens.get(i=i+1)))
							state = 6;
						
						else
							flag = 1;
						
					}
					
					else
						flag = 7;
					
				}
				
				else if(state == 100 && flag != 1 && flag != 6 && flag != 7)
					flag = 5;
				
				i++;
				break;
				
			case 7:
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("{"))
					state = 8;
				
				else
					state = 100;
				
				i++;
				break;
				
			case 8:
				if(tokens.get(i + j).equals("Identifier"))
				{
					
					if(declared.contains(tokens.get(i)))
						state = 9;
					
					else
						flag = 1;
					
				}
				
				if(tokens.get(i + j).equals("Number"))
					state = 9;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("'"))
					if(tokens.get(i + j + 1).equals("Character"))
					{
						
						if(tokens.get(i + j + 2).equals("Special") && tokens.get(i=i+2).equals("'"))
							state = 9;
						
						else
							flag = 2;
						
					}
				
				if(state == 8)
					state = 100;
				
				i++;
				break;
				
			case 9:
				state = 100;
				
				if(tokens.get(i + j).equals("Operator"))
				{
					
					if(tokens.get(i).equals("YekiBala") || tokens.get(i).equals("YekiPain"))
						i++;
					
					else
						state = 8;
					
				}
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("}"))
					state = 0;
				
				else if(tokens.get(i + j).equals("Operator") == false)
					flag = 4;
				
				i++;
				break;
				
			case 10:
				state = 100;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("^"))
					state = 0;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals(","))
				{
					
					if(tokens.get(i + j + 1).equals("Identifier"))
					{
						
						if(declared.contains(tokens.get(i=i+1)))
							state = 10;
						
						else
							flag = 1;
						
					}
					
					else
						flag = 7;
					
				}
				
				if(tokens.get(i + j).equals("Operator") && tokens.get(i).charAt(0) != '&')
				{
					
					if(tokens.get(i).equals("YekiBala") || tokens.get(i).equals("YekiPain"))
						state = 10;
					
					else
						state = 11;
					
				}
				
				else if(state == 100 && flag != 1 && flag != 7)
					flag = 6;
				
				i++;
				break;
				
			case 11:
				if(tokens.get(i + j).equals("Identifier"))
				{
					
					if(declared.contains(tokens.get(i)))
						state = 10;
					
					else
						flag = 1;
					
				}
				
				if(tokens.get(i + j).equals("Number"))
					state = 10;
				
				if(tokens.get(i + j).equals("Special") && tokens.get(i).equals("'"))
					if(tokens.get(i + j + 1).equals("Character"))
					{
						
						if(tokens.get(i + j + 2).equals("Special") && tokens.get(i=i+2).equals("'"))
							state = 10;
						
						else
							flag = 2;
						
					}
				
				if(state == 11)
					state = 100;
				
				i++;
				break;
				
			}
		
		if(state == 100)
		{
			
			//highlighting
			if(flag == 4)
				tokens.add(i - 1, "{");
			
			if(flag == 5)
				tokens.add(i - 1, "(");
			
			count = 1;
			ind = 0;
			
			if(flag == 4 || flag == 5)
				count = 0;
			
			for(j = 0 ; j < i - 1 ; j++)
				if(tokens.get(j).indexOf(tokens.get(i - 1)) != -1)
					count++;
			
			while(count > 0)
			{
				
				j = code.indexOf(tokens.get(i - 1) , ind);
				ind = j + 1;
				count--;
				
			}
			
			Highlight.error(j , j + tokens.get(i - 1).length());
			
			//displaying message
			if(flag == 0)
				Error.error("UNEXPECTED TOKEN!");
			
			if(flag == 1)
				Error.error("IDENTIFIER UNDECLARED!");
			
			if(flag == 2)
				Error.error("' EXPECTED!");
			
			if(flag == 3)
				Error.error("\" EXPECTED!");
			
			if(flag == 4)
				Error.error("} EXPECTED!");
			
			if(flag == 5)
				Error.error(") EXPECTED!");
			
			if(flag == 6)
				Error.error("^ EXPECTED!");
			
			if(flag == 7)
				Error.error("EXPRESSION EXPECTED!");
			
		}
		
		else
		{
			
			if(count < 0)
				Error.error("[ EXPECTED!");
			
			if(count == 0)
				Error.error("SYNTACTICALLY CORRECT!");
			
			if(count > 0)
				Error.error("] EXPECTED!");
			
		}
		
	}
	
}