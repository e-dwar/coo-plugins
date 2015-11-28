package frame;

import java.awt.Color;

import javax.swing.*;

public class PluginFrame extends JFrame {
	
	/*
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel container = new JPanel();
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu menuFile = new JMenu(), menuTools = new JMenu(), menuHelp = new JMenu();
	private JMenuItem menuToUpperCase = new JMenuItem(), menuToLowerCase = new JMenuItem();
	private JTextArea textArea = new JTextArea(25,75);
	
	
	/*
	 * Constructors
	 */
	public PluginFrame() {
		initComponents();
	}
	
	/*
	 * Methods
	 */
	
	private void initComponents() {
		System.out.println("Opening");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Extandable Editor");
        this.setVisible(true);
        //this.setSize(300, 300);
        this.setLocationRelativeTo (null);
        
        /*textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);*/
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        container.setBackground(Color.white);
        //container.add(textArea);
        container.add(areaScrollPane);
        this.setContentPane(container);

        pack();
        initMenu();
	}
	
	private void initMenu(){
		menuFile.setText("File");
		menuTools.setText("Tools");
		menuHelp.setText("Help");
		
		menuToUpperCase.setText("To UpperCase");
		menuToLowerCase.setText("To LowerCase");
		menuTools.add(menuToUpperCase);
		menuTools.add(menuToLowerCase);
		
		mainMenu.add(menuFile);
		mainMenu.add(menuTools);
		mainMenu.add(menuHelp);
		
		this.setJMenuBar(mainMenu);
	}

}
