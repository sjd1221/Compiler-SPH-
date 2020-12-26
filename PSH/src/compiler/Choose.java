package compiler;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

public class Choose
{
	
	public static void choose()
	{
		
		
		if(Frame.mode == 0)
		{
			
			UIManager.put("Panel.background", Color.white);
			UIManager.put("List.background", Color.white);
			UIManager.put("List.foreground", Color.black);
			
		}
		
		if(Frame.mode == 1)
		{
			
			UIManager.put("Panel.background", Color.black);
			UIManager.put("List.background", Color.black);
			UIManager.put("List.foreground", Color.white);
			
		}
		
		//choosing the file
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = fc.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION)
        {
        	
            File file = fc.getSelectedFile();
            
            //showing the content of the chosen file
            try
            {
            	
            	BufferedReader input = new BufferedReader(new InputStreamReader (new FileInputStream(file)));
            	Frame.textarea.read(input, "READING FILE :-)");
            	
            }
            catch(Exception e){}
            
        }
		
	}
	
}