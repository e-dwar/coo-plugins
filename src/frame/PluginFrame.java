package frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import plugins.Plugin;

public class PluginFrame extends JFrame{
	
	/*
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel container = new JPanel();
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu menuFile = new JMenu(), menuTools = new JMenu(), menuHelp = new JMenu();
	private JMenuItem menuExit = new JMenuItem(), menuAbout = new JMenuItem(), menuSave = new JMenuItem(), menuOpen = new JMenuItem(), menuHowTo = new JMenuItem();
	private JTextArea textArea = new JTextArea(10,30);
	
	protected HashMap<Plugin, JMenuItem> plugins = new HashMap<Plugin, JMenuItem>();
	
	private String filePath;
	
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Extendable Editor");
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
		initMenuFile();
		initMenuTools();
		initMenuHelp();
		
		this.setJMenuBar(mainMenu);
	}
	
	private void initMenuFile(){
		//Main button
		menuFile.setText("File");
		
		//Submenu
		menuSave.setText("Save");
		menuOpen.setText("Open text file");
		menuExit.setText("Exit");
		
		//Actions
		menuSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				save();
			}
		});
		
		menuOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				open();
			}
		});
				
		menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                close();
            }
           });
		
		menuFile.add(menuOpen);
		menuFile.add(menuSave);
		menuFile.add(new JPopupMenu.Separator());
		menuFile.add(menuExit);
		
		mainMenu.add(menuFile);
	}
	
	private void initMenuTools(){
		//Main button
		menuTools.setText("Tools - 0");
		
		//Submenu & Actions
		for(JMenuItem itemTemp : plugins.values()){			
			menuTools.add(itemTemp);
		}
		
		mainMenu.add(menuTools);
	}
	
	private void initMenuHelp(){
		//Main button
		menuHelp.setText("Help");
		
		//Submenu
		menuHowTo.setText("How to use it?");
		menuAbout.setText("About the editor");
		
		//Actions
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				showDialog("Extendable Editor - FELV - 2015 ");
			}
		});
		
		menuHowTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt){
				showDialog("Visit our website : extendableeditor.org to get more informations.");
			}
		});
				
		menuHelp.add(menuHowTo);
		menuHelp.add(new JPopupMenu.Separator());
		menuHelp.add(menuAbout);
		
		mainMenu.add(menuHelp);
	}
	
	private void close(){
		this.dispose();
	}
	
	private void showDialog(String text){
		JOptionPane.showMessageDialog(this, text);
	}
	
	private void save(){
		String text = textArea.getText();
		System.out.println(text);
		if(filePath == null || filePath.equals("")){
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save");
			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				write(chooser.getSelectedFile().getAbsolutePath(), text);
				filePath = chooser.getSelectedFile().getAbsolutePath();
			}  
		} else {
			write(filePath, text);
		}
	}
	
	private void open(){
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Open text file");
		chooser.setFileFilter(new FileNameExtensionFilter("text files (*.txt)", "txt"));
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			String path = chooser.getSelectedFile().getAbsolutePath();
			if(path.substring(path.lastIndexOf(".")+1).equals("txt")){
				filePath = chooser.getSelectedFile().getAbsolutePath();
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
					textArea.setText(bufferedReader.readLine());
					bufferedReader.close();
					showDialog("File is now opened !");
				} catch (IOException e) {
					showDialog("Something went wrong during the opening process.");
				}
			}
			else {
				showDialog("This file isn't a text file");
			}
		} 
	}

	private void write(String absolutePath, String text) {
		BufferedWriter bufferedWriter;
		showDialog("The file has been saved on : " + absolutePath);
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(absolutePath));
			bufferedWriter.write(text,0,text.length());
			bufferedWriter.newLine();
			bufferedWriter.close();
			showDialog("The saving process succeed.");
		}
		catch (IOException e){
			showDialog("Something went wrong during the saving process.");
		}
	}

	public void addPlugin(final Plugin plugin){
		JMenuItem menuItem = new JMenuItem();
		menuItem.setText(plugin.getLabel());
		
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				textArea.setText(plugin.transform(textArea.getText()));
			}
		});
		
		plugins.put(plugin, menuItem);
		
		menuTools.add(menuItem);
		updateMenuTools();
	}
	
	public void update(Plugin plugin) {
		this.delete(plugin);
		this.addPlugin(plugin);
	}
	
	public void delete(Plugin plugin){
		Plugin p = searchPlugin(plugin);
		menuTools.remove(plugins.get(p));
		plugins.remove(p);
		updateMenuTools();
	}
		
	public HashMap<Plugin, JMenuItem> getPlugins(){
		return this.plugins;
	}
	
	public Plugin searchPlugin(Plugin plugin){
		for(Plugin pluginTemp : plugins.keySet()){
			if(pluginTemp.getLabel().equals(plugin.getLabel())){
				return pluginTemp;
			}
		}
		return null;
	}
	
	public void updateMenuTools(){
		menuTools.setText("Tools [" + plugins.size() + "]");
	}


}
