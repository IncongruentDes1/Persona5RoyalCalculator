package personaFiveCalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
// - import section
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.FileWriter;
//rwar

public class Main {
	
	// - Persona list
	public static ArrayList<persona> personas;
	public Boolean saveData;
	
	  private static ArrayList<String> selectedAbilities = new ArrayList<>();
	
	
	public static void main(String[] args) {
		createImage("test");
		saveCheck();
		ArrayList<ability> abilities =  abilityHandling();
		
		personas = personaReader(abilities);
		
		
		addCombos(personas);
		Interface(personas);
	}
	
	
	
	// COMMANDS/OPTION HANDLING
	
	
	//TODO
	public static Boolean saveCheck() {
		String check = "";
		
		try {
			File newFile = new File("personalFiles.txt");
			Scanner scanner = new Scanner(newFile);
			check = scanner.nextLine();
			scanner.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (check.equals("NoData")) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	
	
	public static void levelCheck(ArrayList<persona> personas, Scanner myObj) {
		// rewrite input
		
		String curInput = JOptionPane.showInputDialog("Please enter your current Level in Persona 5/Persona 5 Royal" , "1");
	
		Integer lvlInt = Integer.parseInt(curInput);
		String text = "Achievable Personas by level: \n \n";
		
		for (int x = 0; x < personas.size(); x++) {
			
			if (personas.get(x).Level <= lvlInt) {
				text += (x+1+":"+ personas.get(x).Name + " \n");
			}
		}
		 displayTextInFrame(text);
		
	}
	
	
	
	public static void PrintOut(ArrayList<persona> personas, Scanner myObj, String Type) {
		String[] values = new String[242];
		for (int x = 0; x < personas.size(); x++) {
			values[x] = (x+1+":"+ personas.get(x).Name);
		}
		
		callList(values, personas, Type);
		
	}
	
	public static void checkboxPicks(persona curPersona){
		
		//- JFrame creation
		
		JFrame frame = new JFrame("Checkbox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        // - more formatting
     // Create a JPanel to hold checkboxes
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0, 6));
        
        // Create checkboxes for each ability in curPersona.allowedAbilities
        for (ability ab : curPersona.allowedAbilities) {
        	String abString = ab.toString();
        	
            JCheckBox checkBox = new JCheckBox(abString);
            checkBox.addActionListener((ActionListener) new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
			        JCheckBox source = (JCheckBox) e.getSource();
			        if (source.isSelected()) {
			            selectedAbilities.add(source.getText());
			        } else {
			            selectedAbilities.remove(source.getText());
			        }
				}

            });
            checkboxPanel.add(checkBox);
        }

        // Create a button to display selected abilities
        JButton displayButton = new JButton("Display Selected Abilities");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Selected Abilities: " + selectedAbilities);
            }
        });
        
        // Create a main panel and add components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(checkboxPanel, BorderLayout.CENTER);
        mainPanel.add(displayButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

	}
	



	
	
	public static void personaSearch(Scanner myObj, ArrayList<persona> personas ) {
		
		String personaName = JOptionPane.showInputDialog("Please enter the name of the persona you would like to look up" , "Enter Persona Name here");
		
		getPersona(personas, personaName, false);
		
	}
	public static void getPersonaCombos(ArrayList<persona> personas, String name, Boolean isFound) {
		for (int x = 0; x < personas.size(); x++) {
			String fullString = "";
			if (personas.get(x).Name.toLowerCase().equals(name.toLowerCase())) {
				
				isFound = true;
				fullString += (personas.get(x).Name);
				fullString += "\n\n";
				String tempString = "";
				for (int y = 0; y < personas.get(x).combos.size(); y++) {
					System.out.println(personas.get(x).combos.get(y));
					tempString += personas.get(x).combos.get(y);
					tempString += "\n";
				}
				fullString += tempString ;
				
				displayTextInFrame(fullString);
			}
	}
	}
		
	
	public static void getPersona(ArrayList<persona> personas, String name, Boolean isFound) {
		
		for (int x = 0; x < personas.size(); x++) {
			String fullString = "";
			if (personas.get(x).Name.toLowerCase().equals(name.toLowerCase())) {
				
				isFound = true;
				fullString += (personas.get(x).StatReader());
				displayTextInFrame(fullString);
			}
			
		}


		}		
	
	
	
	
	
	// - INTERFACE HANDLING
	
	// - Image Testing
	public static void createImage(String URL) {
        JFrame frame = new JFrame("Image Test");
        frame.setSize(600, 600);
        JLabel label = new JLabel();
        BufferedImage ThisIsTheImageYouWant = null;

        try {
			ThisIsTheImageYouWant = ImageIO.read(new File("images/WALUIGI.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        label.setIcon(new ImageIcon(new ImageIcon(ThisIsTheImageYouWant).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
        
        
        
        
        frame.add(label);
        frame.pack();
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

	}
	
	
	
	// - Calls main menu 
	public static void Interface(ArrayList<persona> personas) {
		String[] options = {"See List of Personas and Stats", "See List of Personas and Combos", "See What personas you can use by level", "Search Persona", "Personal Persona cache"};
		callList(options, personas, "input");		
	}
	

	
	
	// - creates the clickable list and reads in personas
	public static void callList(String[] values, ArrayList<persona> personas, String type) {
	    JFrame f = new JFrame("Persona 5 Royal Calculator");
	    JList<String> list = new JList<>(values);
	    JScrollPane scrollPane = new JScrollPane(list);
	    
	    
	    

	    Color PersonaRed = new Color(217,35,35);
	    Color PersonaBlack = new Color(13,13,13);
	    Color PersonaDarkRed = new Color(115,36,36);
	    Color PersonaTan =  new Color(140,103,35);
	    Color PersonaYellow = new Color(242,232,82);
	    
	    scrollPane.setBorder(BorderFactory.createMatteBorder(10,10,10,10, PersonaRed));
	    scrollPane.getViewport().getView().setBackground(PersonaBlack);
	    scrollPane.getViewport().getView().setForeground(PersonaYellow);
	    scrollPane.getViewport().setOpaque(true);

	    
	    
	    f.add(scrollPane,  BorderLayout.CENTER);
	    f.setSize(300, 300);
	//    f.pack();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);

	    // Add a ListSelectionListener to handle selection events
	    list.addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent e) {
	            if (!e.getValueIsAdjusting()) {
	                int selectedIndex = list.getSelectedIndex();
	                if (selectedIndex != -1) {
	                    // Get the selected value
	                    try {
							onSelection(selectedIndex, personas, type);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            }
	        }
	    });
	}
	
	
	
	public static void displayTextInFrame(String text) {
	    JFrame frame = new JFrame("Text Display"); // Create a JFrame with a title
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
	    
	    frame.setSize(400, 200);
	    
	    // Set the size of the frame

	    // Modify the text to split it into two lines vertically
	    String[] lines = text.split("\n");
	    StringBuilder newText = new StringBuilder();
	    for (String line : lines) {
	        newText.append(line).append("<br>"); // Use <br> for HTML-style line breaks
	    }

	    JLabel label = new JLabel("<html>" + newText.toString() + "</html>"); // Create a JLabel with HTML formatting
	    label.setHorizontalAlignment(JLabel.CENTER); // Center-align the text in the label
	    
	    
	   

	    
	    Color PersonaRed = new Color(217,35,35);
	    Color PersonaBlack = new Color(13,13,13);
	    Color PersonaDarkRed = new Color(115,36,36);
	    Color PersonaTan =  new Color(140,103,35);
	    Color PersonaYellow = new Color(242,232,82);
	    
	    label.setBorder(BorderFactory.createMatteBorder(10,10,10,10, PersonaRed));
	    frame.getContentPane().setBackground(PersonaBlack);
	    label.setForeground(PersonaYellow);


	    frame.add(label); // Add the label to the frame
	    frame.pack();
	    
	    

	    frame.setVisible(true); // Make the frame visible
	}
	
	
	public static void onSelection(int val,  ArrayList<persona> personas, String type) throws IOException {
	    // Handle the selected value here
	   System.out.println(val);
	   if (type == "base") {
		   String getName = (personas.get(val).Name);
		   getPersona(personas, getName, true);
	   }
	   else if (type == "combos") {
		   String getName = (personas.get(val).Name);
		   getPersonaCombos(personas, getName, true);
	   }
	   else if (type == "account") {
		   if (val == 0) {
			   //TODO
			   charLevelSet(personas);
		   }
		   else if (val ==1) {
			   //TODO

		   }
		   else if (val ==2) {
			   //TODO
		   }
		   else if (val == 3) {
			   Scanner myObj = new Scanner(System.in);
			   PrintOut(personas, myObj, "create");
			   
		   }
	   }
	   else if (type == "create") {
		   System.out.println("here");
		   String getName = (personas.get(val).Name);
		   System.out.println(getName);
		   checkboxPicks(personas.get(val));
	   }
	   
	   else if (type == "input") {
		   Scanner myObj = new Scanner(System.in);
		   
		   if (val == 0) {
			   PrintOut(personas, myObj, "base");
		   }
		   else if ( val == 1) {
			   PrintOut(personas, myObj, "combos");
		   }
		   else if (val == 2) {
			   levelCheck(personas, myObj);
		   }
		   else if (val == 3) {
			   personaSearch(myObj, personas);
		   }
		   else if (val == 4) {
			   
			   // - Data Check
			   Boolean hasData = saveCheck();
			   System.out.println(hasData);
			   
			   // - Options based on Data
			   String[] options = {};
			   if (hasData) {
				   options = new String[]{"Create New Data (Warning: this will remove your old data", "Edit current Personas", "Check possible fusions", "Add a new persona"};
				   callList(options, personas, "account");
				   }
			   else {
				   options = new String[]{"Create New Data"};
				   callList(options, personas, "account");
			   }

			   
			   //TODO
		   }
	   }
	}
	
	
	// - this function works to set profile data and maybe name?
	public static void charLevelSet(ArrayList<persona> personas) throws IOException {
		String curInput = JOptionPane.showInputDialog("Please enter your current Level in Persona 5/Persona 5 Royal" , "1");
		Integer lvlInt = Integer.parseInt(curInput);
		String nameInput = JOptionPane.showInputDialog("Please Enter A Name for your Profile" , "Enter Name Here");
		
		//- -overwriting File
		try {
			File newFile = new File("personalFiles.txt");
			Scanner scanner = new Scanner(newFile);
			
			FileWriter fw = new FileWriter("personalFiles.txt");
			fw.write(lvlInt.toString());
			fw.write(System.getProperty( "line.separator" ));
			fw.write(nameInput);
			fw.write(System.getProperty( "line.separator" ));
			fw.write("Fool:1:Arsene:Curse:2:2:2:3:1:no:no:no:wk:no:no:no:no:wk:rs:242");
			fw.close();
			
			
			scanner.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		  
	}
	
	

	
	

// - DATA HANDLING
	// - read in personal persona list
	public static ArrayList<ability> abilityHandling() {
		ArrayList<ability> abilities = new ArrayList<ability>();
		Integer num = 0;
		
		
		try {
			File newFile = new File("abilities.txt");
			Scanner scanner = new Scanner(newFile);
			
			scanner.nextLine();

			while (scanner.hasNextLine()) {
				num += 1;
				String curLine = scanner.nextLine();
				String[] split = curLine.split(":");
				
				
				ability newAbility = new ability(num, split[0], split[1], split[2], split[3]);
				abilities.add(newAbility);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		return abilities;
	}
	
	
	
	
	
	
	public static ArrayList<persona> personaReaderPersonal(){
		personas = new ArrayList<persona>();
		
		
		try {
			File newFile = new File("personalFiles.txt");
			Scanner scanner = new Scanner(newFile);

			while (scanner.hasNextLine()) {
				String curLine = scanner.nextLine();
				String[] split = curLine.split(":");
				persona curPersona = new persona(split[0], Integer.parseInt(split[1]), split[2], split[3],Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]),split[9], split[10], split[11], split[12], split[13], split[14], split[15],split[16], split[17], split[18], Integer.parseInt(split[19]));
				personas.add(curPersona);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return personas;
		
		
	}
	
	
	
	
	// Read in Personas
	public static ArrayList<persona> personaReader(ArrayList<ability> ab){
		//-  set up 
		personas = new ArrayList<persona>();
		
		try {
			File newFile = new File("personalStats.txt");
			Scanner scanner = new Scanner(newFile);

			while (scanner.hasNextLine()) {
				String curLine = scanner.nextLine();
				String[] split = curLine.split(":");
				persona curPersona = new persona(split[0], Integer.parseInt(split[1]), split[2], split[3],Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]),split[9], split[10], split[11], split[12], split[13], split[14], split[15],split[16], split[17], split[18], Integer.parseInt(split[19]));
				curPersona.abilityReading(ab);
				personas.add(curPersona);
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	
	// - Add all the potential combos to make in personas
	public static void addCombos(ArrayList<persona> curPersonas) {
		try {
			File newFile = new File("combos.txt");
			Scanner scanner = new Scanner(newFile);

			while (scanner.hasNextLine()) {
				// - Going through Lines
				String curLineOne = scanner.nextLine();
				String curLineTwo = scanner.nextLine();
				
				// - Cur Persona line processing
				Integer curLineOneInt = Integer.parseInt(curLineOne.split("\\s+")[0]);
				persona curPersona = curPersonas.get(curLineOneInt-1);
				
				curPersona.combos = new ArrayList<ArrayList<persona>>();
				//System.out.println(curPersona);
				
				
				// Second Line handling. For loop #1 makes splits into sections
				String[] Combos = curLineTwo.split("\\.");
				for (int x = 0; x < Combos.length; x++) {
					String[] splits = Combos[x].split("\\s+");
					ArrayList<persona> shortCombo = new ArrayList<persona>();
					
					// - start of individual combos
					for (int y = 0; y < splits.length; y++) {
						if (splits[y] != "") {
							Integer curInt = Integer.parseInt(splits[y]);
							shortCombo.add(personas.get(curInt-1));	
						}
					}
					curPersona.combos.add(shortCombo);
						
					}					
				}
			scanner.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
