package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import plugins.Plugin;

public class PluginFrame extends JFrame{
	
	/*
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel container = new JPanel();
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu menuFile = new JMenu(), menuTools = new JMenu(), menuHelp = new JMenu();
	private JMenuItem menuToUpperCase = new JMenuItem(), menuToLowerCase = new JMenuItem(), menuExit = new JMenuItem(), menuAbout = new JMenuItem(), menuSave = new JMenuItem(), menuHowTo = new JMenuItem();
	private JTextArea textArea = new JTextArea(10,30);
	
	protected ArrayList<Plugin> plugins = new ArrayList<Plugin>();
	
	/*
	 * Constructors
	 */
	public PluginFrame() {
		initComponents();
		initMenu();
        pack();
	}
	
	/*
	 * Methods
	 */
	
	private void initComponents() {
		System.out.println("Opening");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Extandable Editor");
        this.setVisible(true);
        this.setLocationRelativeTo (null);
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        container.setLayout(new GridLayout(0,1));
        container.setBackground(Color.white);
        container.add(areaScrollPane);
        this.setContentPane(container);
        
	}
	
	private void initMenu(){
		menuFile.setText("File");
		menuTools.setText("Tools");
		menuHelp.setText("Help");
				
		menuSave.setText("Save");
		menuToUpperCase.setText("To UpperCase");
		menuToLowerCase.setText("To LowerCase");
		menuExit.setText("Exit");
		menuHowTo.setText("How to use it?");
		menuAbout.setText("About the editor");
		
		menuSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				save();
			}
		});
		
		for(final Plugin pluginTemp : plugins){
			JMenuItem itemTemp = new JMenuItem();
			itemTemp.setText(pluginTemp.getLabel());
			
			itemTemp.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					pluginTemp.transform(textArea.getText());
				}
			});
		}
		
		menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                close();
            }
           });
		
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				showDialog("test");
			}
		});
		
		menuHowTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				showDialog("testtesttesttesttesttesttest\ntesttesttesttesttest\ntest");
			}
		});
		
		menuTools.add(menuToUpperCase);
		menuTools.add(menuToLowerCase);
		menuFile.add(menuSave);
		menuFile.add(new JPopupMenu.Separator());
		menuFile.add(menuExit);
		menuHelp.add(menuHowTo);
		menuHelp.add(new JPopupMenu.Separator());
		menuHelp.add(menuAbout);
		
		mainMenu.add(menuFile);
		mainMenu.add(menuTools);
		mainMenu.add(menuHelp);
		
		this.setJMenuBar(mainMenu);
	}
	
	public void close(){
		this.dispose();
	}
	
	public void showDialog(String text){
		JOptionPane.showMessageDialog(this, text);
	}
	
	public void save(){
		String text = textArea.getText();
		System.out.println(text);
	}

	public void update() {
		System.out.println("I do something !!");
	}


}
