package compiler;

import java.awt.BorderLayout;
import java.awt.Color;
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
	
	static int mode = 0;
	static JTextArea textarea;
	static JButton hk, ho, hs, cm, sb, rb, pb, cf;
	static DefaultListModel<String> model = new DefaultListModel<>();
	static JList<String> list = new JList<>(model);
	
	public void frame()
	{
		
		//to highlight keywords
		hk = new JButton("Highlight Keywords");
		hk.setBounds(0,0,200,50);
		hk.setBackground(Color.white);
		hk.addActionListener(this);
		
		//to highlight operators
		ho = new JButton("Highlight Operators");
		ho.setBounds(200,0,200,50);
		ho.setBackground(Color.white);
		ho.addActionListener(this);
		
		//to highlight specials
		hs = new JButton("Highlight Specials");
		hs.setBounds(400,0,200,50);
		hs.setBackground(Color.white);
		hs.addActionListener(this);
		
		//to change mode
		cm = new JButton("Change Mode");
		cm.setBounds(600,0,170,30);
		cm.setBackground(Color.white);
		cm.addActionListener(this);
		
		//area for writing the code
		textarea = new JTextArea();
		textarea.setBounds(0,50,600,550);
		MouseListener ml = new MouseAdapter()
        {
			
			@Override
            public void mouseClicked(MouseEvent mouseEvent){Highlight.clear();}
            
        };
        textarea.addMouseListener(ml);
		JScrollPane tsp = new JScrollPane(textarea);
		tsp.setBounds(0,50,600,550);
		
		//list of previous codes
        MouseListener mouseListener = new MouseAdapter()
        {
        	
        	@Override
            public void mouseClicked(MouseEvent mouseEvent)
            {
        		
        		JList<?> theList = (JList<?>) mouseEvent.getSource();
                int index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0)
                {
                	
                	File f = new File("C:/Sina#/codes/" + theList.getModel().getElementAt(index).toString());
                	
                	try
                	{
                		
                		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
    	            	textarea.read(input, "READING FILE :-)");
    	            	Highlight.clear();
                		
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
		panel.setBounds(600,30,170,570);
        panel.add(sp);
		
        //scan button
        sb = new JButton("Scan");
		sb.setBounds(0,600,100,50);
		sb.setBackground(Color.white);
		sb.addActionListener(this);
		
        //run button
		rb = new JButton("Run");
		rb.setBounds(100,600,400,50);
		rb.setBackground(Color.white);
		rb.addActionListener(this);
		
		//parse button
		pb = new JButton("Parse");
		pb.setBounds(500,600,100,50);
		pb.setBackground(Color.white);
		pb.addActionListener(this);
		
		//choose file button
		cf = new JButton("Choose Other File");
		cf.setBounds(600,600,170,50);
		cf.setBackground(Color.white);
		cf.addActionListener(this);
		
    	//frame
    	JFrame frame = new JFrame();
    	frame.setBounds(0,0,785,685);
    	frame.add(hk); frame.add(ho); frame.add(hs);
    	frame.add(cm); frame.add(tsp);
    	frame.add(rb); frame.add(sb); frame.add(pb);
    	frame.add(panel); frame.add(cf);
    	frame.setSize(785,685);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
	}
	
	//buttons action listener
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource() == hk)
			Highlight.keywords();
		
		if(ae.getSource() == ho)
			Highlight.operators();
		
		if(ae.getSource() == hs)
			Highlight.specials();
		
		if(ae.getSource() == cm)
			Change.change();
		
		if(ae.getSource() == sb)
			Scan.scan(textarea.getText());
		
		if(ae.getSource() == rb)
			Run.run(textarea.getText());
		
		if(ae.getSource() == pb)
			Parse.parse(textarea.getText());
		
		if(ae.getSource() == cf)
			Choose.choose();
		
	}
	
}