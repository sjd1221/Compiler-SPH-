package compiler;

import java.awt.Color;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Highlight
{
	
	public static void keywords()
	{
		
		clear();
		
		highlight("Benevis" , 1);
		highlight("Begir" , 1);
		highlight("agar" , 1);
		highlight("ta" , 1);
		highlight("Sahih" , 1);
		highlight("Ashari" , 1);
		highlight("Harf" , 1);
		
	}
	
	public static void operators()
	{
		
		clear();
		
		highlight("&BM" , 0);
		highlight("&KM" , 0);
		highlight("&MM" , 0);
		highlight("&NM" , 0);
		highlight("&K" , 0);
		highlight("&B" , 0);
		highlight("Jam" , 1);
		highlight("Kam" , 1);
		highlight("YekiBala" , 1);
		highlight("YekiPain" , 1);
		highlight("Zarb" , 1);
		highlight("Tagsim" , 1);
		highlight("Bagimonde" , 1);
		highlight("=" , 0);
		
	}
	
	public static void specials()
	{
		
		clear();
		
		highlight("(" , 0);
		highlight(")" , 0);
		highlight("{" , 0);
		highlight("}" , 0);
		highlight("[" , 0);
		highlight("]" , 0);
		highlight("'" , 0);
		highlight("\"" , 0);
		highlight("^" , 0);
		highlight("," , 0);
		
	}
	
	private static void highlight(String word, int flag)
	{
		
		int sindex;
		String code = " " + Frame.textarea.getText();
		DefaultHighlighter.DefaultHighlightPainter hp;
		if(Frame.mode == 1)
			hp = new DefaultHighlighter.DefaultHighlightPainter(Color.gray);
		else
			hp = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
		
		//not highlighting strings and characters
		code = code.replace("'\"'", "'#'");
		code = remove(code , '"');
		code = remove(code , '\'');
		
		//highlighting words
		sindex = code.indexOf(word);
		while(sindex != -1)
		{
			
			if(flag == 0)
				try {Frame.textarea.getHighlighter().addHighlight(sindex - 1 , sindex + word.length() - 1 , hp);}
			catch(Exception e){}
			
			//not highlighting identifiers
			else if(flag == 1 && code.charAt(sindex + word.length()) != '_' && 
					Character.isLetterOrDigit(code.charAt(sindex - 1)) == false && code.charAt(sindex - 1) != '_' 
					&& Character.isLetterOrDigit(code.charAt(sindex + word.length())) == false)
				try {Frame.textarea.getHighlighter().addHighlight(sindex - 1 , sindex + word.length() - 1 , hp);}
			catch(Exception e){}
			
			sindex = code.indexOf(word , sindex + 1);
			
		}
		
	}
	
	//removing possible previous highlights
	static void clear()
	{
		
		Highlighter hilite = Frame.textarea.getHighlighter();
	    Highlighter.Highlight[] hilites = hilite.getHighlights();
	    for (int i = 0; i < hilites.length; i++)
	    	if (hilites[i].getPainter() instanceof HighlightPainter)
	    		hilite.removeHighlight(hilites[i]);
	    
	}
	
	static String remove(String code , char c)
	{
		
		int sindex = code.indexOf(c) , eindex;
		
		while(sindex != -1)
		{
			
			eindex = eind(code , c , sindex);
			StringBuffer buffer = new StringBuffer(eindex - sindex - 1);
			for(int i = 0 ; i < eindex - sindex - 1 ; i++)
				buffer.append("#");
			code = code.substring(0 , sindex + 1) + buffer + code.substring(eindex);
			sindex = code.indexOf(c , eindex + 1);
			
		}
		
		return code;
		
	}
	
	static int eind(String code , char c , int sindex)
	{
		
		int eindex = code.indexOf(c , sindex + 1);
		
		if(eindex == -1)
			return code.length();
		
		if(code.charAt(eindex - 1) == '\\' && code.charAt(eindex - 2) != '\\')
			return eind(code , c , eindex);
		
		return eindex;
		
	}
	
	static void error(int sind , int eind)
	{
		
		DefaultHighlighter.DefaultHighlightPainter hp = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
		try {Frame.textarea.getHighlighter().addHighlight(sind , eind , hp);}
		catch(Exception e){}
		
	}
	
}