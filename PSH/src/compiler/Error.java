package compiler;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Error
{
	
	public static void error(String e)
	{
		
		if(Frame.mode == 0)
		{
			
			UIManager.put("OptionPane.background", Color.white);
			UIManager.put("Panel.background", Color.white);
			
		}
		
		if(Frame.mode == 1)
		{
			
			UIManager.put("OptionPane.background", Color.black);
			UIManager.put("Panel.background", Color.black);
			e = "<html><font color=#FFFFFF>" + e + "</font>";
			
		}
		
		JOptionPane.showMessageDialog(null, e, "", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
}