package compiler;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Scan
{
	
	public static void scan(String code)
	{
		
	    ArrayList<String> tokens = Tokenize.tokenize(code);
		int i , s = tokens.size()/2;
		
		//tokens table and frame
		JTable jt = new JTable(new DefaultTableModel(new Object[]{"Token" , "Type"} , tokens.size()/2));
		jt.setDefaultEditor(Object.class , null);
		jt.setBounds(0,0,485,265);
		
		if(Frame.mode == 1)
		{
			
			jt.setBackground(Color.black);
			jt.setForeground(Color.white);
			
		}
		
		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0,0,485,265);
		JFrame frame = new JFrame();
		frame.add(sp);
		frame.setSize(500,300);
		frame.setLayout(null);
		frame.setVisible(true);
		
        //setting values of the tokens table
        for (i = 0 ; i < s ; i++)
        	jt.setValueAt(tokens.get(i) , i , 0);
        
        for (i = s ; i < tokens.size() ; i++)
        	jt.setValueAt(tokens.get(i) , i - s , 1);
        
	}
	
}