package compiler;

import java.awt.Color;

//change mode
public class Change
{
	
	public static void change()
	{
		
		if(Frame.mode == 0)
		{
			
			Frame.textarea.setBackground(Color.black);
			Frame.textarea.setForeground(Color.white);
			Frame.textarea.setCaretColor(Color.white);
			Frame.sb.setBackground(Color.black);
			Frame.rb.setForeground(Color.white);
			Frame.pb.setBackground(Color.black);
			Frame.hk.setForeground(Color.white);
			Frame.ho.setBackground(Color.black);
			Frame.hs.setForeground(Color.white);
			Frame.cm.setBackground(Color.black);
			Frame.cm.setForeground(Color.white);
			Frame.sb.setForeground(Color.white);
			Frame.rb.setBackground(Color.black);
			Frame.pb.setForeground(Color.white);
			Frame.hk.setBackground(Color.black);
			Frame.ho.setForeground(Color.white);
			Frame.hs.setBackground(Color.black);
			Frame.cf.setForeground(Color.white);
			Frame.cf.setBackground(Color.black);
			Frame.list.setBackground(Color.black);
			Frame.list.setForeground(Color.white);
			Frame.mode = 1;
			Highlight.clear();
			return;
			
		}
		
		if(Frame.mode == 1)
		{
			
			Frame.textarea.setBackground(Color.white);
			Frame.textarea.setForeground(Color.black);
			Frame.textarea.setCaretColor(Color.black);
			Frame.sb.setBackground(Color.white);
			Frame.rb.setForeground(Color.black);
			Frame.pb.setBackground(Color.white);
			Frame.hk.setForeground(Color.black);
			Frame.ho.setBackground(Color.white);
			Frame.hs.setForeground(Color.black);
			Frame.cm.setBackground(Color.white);
			Frame.cm.setForeground(Color.black);
			Frame.sb.setForeground(Color.black);
			Frame.rb.setBackground(Color.white);
			Frame.pb.setForeground(Color.black);
			Frame.hk.setBackground(Color.white);
			Frame.ho.setForeground(Color.black);
			Frame.hs.setBackground(Color.white);
			Frame.cf.setForeground(Color.black);
			Frame.cf.setBackground(Color.white);
			Frame.list.setBackground(Color.white);
			Frame.list.setForeground(Color.black);
			Frame.mode = 0;
			Highlight.clear();
			return;
			
		}
		
	}
	
}