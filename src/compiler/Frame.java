package compiler;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Frame implements ActionListener
{
	
	static JTextArea textarea;
	JButton sb, rb, pb, cb;
	static DefaultListModel<String> model = new DefaultListModel<>();
	
	public void frame()
	{
		
		//area for writing the code
		textarea = new JTextArea();
		textarea.setBounds(0,0,600,600);
		JScrollPane tsp = new JScrollPane(textarea);
		tsp.setBounds(0,0,600,600);
		
		//list of previous codes
		final JList<String> list = new JList<>(model);
        MouseListener mouseListener = new MouseAdapter()
        {
        	
        	@Override
            public void mouseClicked(MouseEvent mouseEvent)
            {
        		
        		JList<?> theList = (JList<?>) mouseEvent.getSource();
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0)
                {
                	
                	File f = new File("C:/Sina#/codes/" + theList.getModel().
                			getElementAt(index).toString());
                	
                	try
                	{
                		
                		BufferedReader input = new BufferedReader
                				(new InputStreamReader(new FileInputStream(f)));
    	            	textarea.read(input, "READING FILE :-)");
                		
                	}
                	catch (Exception e){}
                	
                }
                
            }
            
        };
        list.addMouseListener(mouseListener);
        JScrollPane sp = new JScrollPane(list);
        sp.setBounds(0,20,275,400);
        final File folder = new File("C://Sina#//codes");
        for (final File fileEntry : folder.listFiles())
	    	model.addElement(fileEntry.getName());
        JPanel panel = new JPanel(new BorderLayout());
		panel.setBounds(600,0,170,600);
        panel.add(sp);
		
        //scan button
        sb = new JButton("Scan");
		sb.setBounds(0,600,100,50);
		sb.addActionListener(this);
		
        //run button
		rb = new JButton("Run");
		rb.setBounds(100,600,400,50);
		rb.addActionListener(this);
		
		//parse button
		pb = new JButton("Parse");
		pb.setBounds(500,600,100,50);
		pb.addActionListener(this);
		
		//choose button
		cb = new JButton("Choose Other File");
		cb.setBounds(600,600,170,50);
		cb.addActionListener(this);
		
		//frame
    	JFrame frame = new JFrame();
    	frame.add(tsp); frame.add(rb);
    	frame.add(sb); frame.add(pb);
    	frame.add(panel); frame.add(cb);
    	frame.setSize(785,685);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
	}
	
	//buttons action listener
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource() == sb)
			Scan.scan(textarea.getText());
		
		if(ae.getSource() == rb)
			Run.run(textarea.getText());
		
		if(ae.getSource() == cb)
			Choose.choose();
		
	}
	
}