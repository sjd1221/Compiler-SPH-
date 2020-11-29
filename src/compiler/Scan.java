package compiler;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Scan
{
	
	public static void scan(String code)
	{
	    
		int sindex , eindex , i , j = 0;
		String w;
		ArrayList<String> al = new ArrayList<String>();
		
		//storing string constants
		sindex = code.indexOf('\"');
		while(sindex != -1)
		{
                        
			eindex = Run.eind(sindex , code);
			al.add(code.substring(sindex + 1 , eindex));
			code = code.substring(0 , sindex) + '#' + code.substring(eindex + 1);
			sindex = code.indexOf('\"');
			
		}
		
		String[] words = split(code);
		
		//tokens table and frame
		JTable jt = new JTable(new DefaultTableModel(new Object[]{"Token" , "Type"} , words.length));
		jt.setBounds(0,0,485,265);
	    JScrollPane sp = new JScrollPane(jt);
	    sp.setBounds(0,0,485,265);
	    JFrame frame = new JFrame();
	    frame.add(sp);
	    frame.setSize(500,300);
        frame.setLayout(null);
        frame.setVisible(true);
        
        //setting values of the tokens table
        for (i = 0; i < words.length; i++)
		{
        	
        	w = words[i];
        	
        	if(w.equals("#"))
        	{
        		
        		jt.setValueAt(al.get(j) , i , 0);
        		jt.setValueAt("Constant" , i , 1);
        		j++;
        		
        	}
        	
        	else
        	{
        		
        		jt.setValueAt(words[i] , i , 0);
        		
        		if(w.matches("^[+-]?\\d+(?:\\.\\d+(?:[eE][+-]?\\d+)?)?$"))
        			jt.setValueAt("Constant" , i , 1);
        		
        		if(w.equals("'") && words[i-2].equals("'"))
        			jt.setValueAt("Constant" , i - 1 , 1);
        		
        		if(w.equals("(")||w.equals(")")||w.equals("{")||w.equals("}")||w.equals("[")||w.equals("]")
        				||w.equals("'")||w.equals("\"")||w.equals("^")||w.equals(","))
        			jt.setValueAt("Special" , i , 1);
        		
        		else if(w.equals("Benevis")||w.equals("Begir")||w.equals("agar")||w.equals("ta")||
        				w.equals("Sahih")||w.equals("Ashari")||w.equals("Harf"))
        			jt.setValueAt("Keyword" , i , 1);
        		
        		else if(w.equals("&BM")||w.equals("&KM")||w.equals("&B")||w.equals("&K")||w.equals("&MM")||w.equals("&NM")
        				||w.equals("Jam")||w.equals("Kam")||w.equals("YekiBala")||w.equals("YekiPain")||
        				w.equals("Zarb")||w.equals("Tagsim")||w.equals("Bagimonde")||w.equals("="))
        			jt.setValueAt("Operator" , i , 1);
        		
        		else if(w.matches("^[_a-zA-Z][_a-zA-Z0-9]*$"))
        			jt.setValueAt("Identifier" , i , 1);
        		
        	}
        	
		}
		
	}
	
	static String[] split(String code)
	{
		
		//separating tokens that might not be separated from other tokens
		code = code.replace("(" , " ( ");
		code = code.replace(")" , " ) ");
		code = code.replace("{" , " { ");
		code = code.replace("}" , " } ");
		code = code.replace("[" , " [ ");
		code = code.replace("]" , " ] ");
		code = code.replace("^" , " ^ ");
		code = code.replace("," , " , ");
		code = code.replace("'" , " ' ");
		code = code.replace("#" , " \" # \" ");
		code = code.replace("=" , " = ");
		code = code.replace("&BM" , " &BM ");
		code = code.replace("&KM" , " &KM ");
		code = code.replace("&B" , " &B ");
		code = code.replace("&K" , " &K ");
		code = code.replace("&MM" , " &MM ");
		code = code.replace("&NM" , " &NM ");
		
		//tokens
		String[] words = code.split("\\s+");
		
		return words;
		
	}
	
}