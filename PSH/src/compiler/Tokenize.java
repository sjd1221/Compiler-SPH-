package compiler;

import java.util.ArrayList;

public class Tokenize
{
	
	public static ArrayList<String> tokenize(String code)
	{
		
		String temp;
		code = code + "   ";
		int ind = 0 , i = 0 , state = 0 , flag = 0;
		ArrayList<String> tokens = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
		
		//finite automata
		while(i < code.length())
			switch(state)
			{
				
				case 0:
					if(code.charAt(i) != ' ' && code.charAt(i) != '\n' && code.charAt(i) != '\t')
						state = 100;
					
					if(code.charAt(i) == ',' || code.charAt(i) == '^' || code.charAt(i) == '=' || 
					code.charAt(i) == '[' || code.charAt(i) == ']' || code.charAt(i) == '{' || 
					code.charAt(i) == '}' || code.charAt(i) == '(' || code.charAt(i) == ')')
						state = 1;
					
					if(code.charAt(i) == '&')
						state = 2;
					
					if(Character.isDigit(code.charAt(i)) || code.charAt(i) == '-' || code.charAt(i) == '+')
						{state = 7; ind = i;}
					
					if(code.charAt(i) == '"')
						{state = 11; ind = i;}
					
					if(code.charAt(i) == '\'')
						{state = 13; ind = i;}
					
					if(Character.isLetter(code.charAt(i)) || code.charAt(i) == '_')
						{state = 15; ind = i;}
					
					i++;
					break;
					
				case 1:
					tokens.add(code.substring(i - 1 , i));
					
					if(code.substring(i - 1 , i).equals("="))
						types.add("Operator");
					
					else
						types.add("Special");
					
					state = 0;
					break;
					
				case 2:
					if(code.charAt(i) == 'K' || code.charAt(i) == 'B')
						state = 3;
					
					if(code.charAt(i) == 'M' || code.charAt(i) == 'N')
						state = 4;
					
					else if(code.charAt(i) != 'K' && code.charAt(i) != 'B')
						state = 100;
					
					i++;
					break;
					
				case 3:
					if(code.charAt(i) == 'M')
						state = 5;
					
					else
						state = 6;
					
					i++;
					break;
					
				case 4:
					if(code.charAt(i) == 'M')
						state = 5;
					
					else
						state = 100;
					
					i++;
					break;
					
				case 5:
					tokens.add(code.substring(i - 3 , i));
					types.add("Operator");
					state = 0;
					break;
					
				case 6:
					i--;
					tokens.add(code.substring(i - 2 , i));
					types.add("Operator");
					state = 0;
					break;
					
				case 7:
					state = 10;
					
					if(Character.isDigit(code.charAt(i)))
						state = 7;
					
					if(code.charAt(i) == '.')
						if(code.charAt(i - 1) != '+' && code.charAt(i - 1) != '-')
							state = 8;
					
					if(code.charAt(i) == 'e' || code.charAt(i) == 'E')
						if(code.charAt(i - 1) != '+' && code.charAt(i - 1) != '-')
							state = 9;
					
					i++;
					break;
					
				case 8:
					state = 10;
					
					if(Character.isDigit(code.charAt(i)))
						state = 8;
					
					else if(code.charAt(i) == 'e' || code.charAt(i) == 'E')
						if(code.charAt(i - 1) != '.')
							state = 9;
					
					i++;
					break;
					
				case 9:
					state = 10;
					
					if(Character.isDigit(code.charAt(i)))
						state = 9;
					
					else if(code.charAt(i) == '-' || code.charAt(i) == '+')
						if(code.charAt(i - 1) == 'e' || code.charAt(i - 1) == 'E')
							state = 9;
					
					i++;
					break;
					
				case 10:
					i--;
					
					if(code.charAt(i - 1) == '+' || code.charAt(i - 1) == '-'  || code.charAt(i - 1) == '.' || 
							code.charAt(i - 1) == 'e' || code.charAt(i - 1) == 'E')
						state = 100;
					
					else
					{
						
						tokens.add(code.substring(ind , i));
						types.add("Number");
						state = 0;
						
					}
					
					break;
					
				case 11:
					if(code.charAt(i) == '"')
					{
						
						if(code.charAt(i - 1) != '\\')
							state = 12;
						
						else if(code.charAt(i - 1) == '\\')
							if(code.charAt(i - 2) == '\\')
								state = 12;
						
					}
					
					else if(i == code.length() - 1)
						{code = code + "\" "; flag = 1;}
					
					i++;
					break;
				
				case 12:
					tokens.add("\"");
					types.add("Special");
					tokens.add(code.substring(ind + 1 , i - 1));
					types.add("String");
					if(flag == 0)
						{types.add("Special"); tokens.add("\"");}
					
					state = 0;
					break;
					
				case 13:
					if(code.charAt(i) == '\'')
					{
						
						if(code.charAt(i - 1) != '\\')
							state = 14;
						
						else if(code.charAt(i - 1) == '\\')
							if(code.charAt(i - 2) == '\\')
								state = 14;
						
					}
					
					else if(i == code.length() - 1)
						{code = code + "' "; flag = 1;}
					
					i++;
					break;
				
				case 14:
					tokens.add("'");
					types.add("Special");
					tokens.add(code.substring(ind + 1 , i - 1));
					types.add("Character");
					if(flag == 0)
						{tokens.add("'"); types.add("Special");}
					
					state = 0;
					break;
					
				case 15:
					if(Character.isLetterOrDigit(code.charAt(i)) == false && code.charAt(i) != '_')
					{
						
						temp = code.substring(ind , i);
						tokens.add(temp);
						
						if(temp.equals("Benevis") || temp.equals("Begir") || temp.equals("agar") || temp.equals("ta") 
								|| temp.equals("Sahih") || temp.equals("Ashari") || temp.equals("Harf"))
							types.add("Keyword");
						
						else if(temp.equals("Jam") || temp.equals("Kam") || temp.equals("Zarb") || temp.equals("Tagsim") 
								|| temp.equals("YekiBala") || temp.equals("YekiPain") ||  temp.equals("Bagimonde"))
							types.add("Operator");
						
						if(tokens.size() != types.size())
							types.add("Identifier");
						
						state = 0;
						i--;
						
					}
					
					i++;
					break;
					
				case 100:
					Highlight.error(i - 1 , i);
					Error.error("ERROR AT CHARACTER: " + code.charAt(i - 1));
					i = code.length();
					break;
				
			}
		
		tokens.addAll(types);
		
		return tokens;
		
	}
	
}