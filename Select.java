package stegnoTexttoImage;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Select extends JFrame{
    JButton button ;
    JLabel label;
    static String ToencodeFile;
    String msg = "Digital Forensics Course";
    static final String StegFile = "C:/Users/user/workspace/Indus/Stegnograpy/src/steg1.png";
    public Select(){
    super("Lets Encode the Image");
    button = new JButton("Browse");
    button.setBounds(300,300,100,40);
    label = new JLabel();
    label.setBounds(10,10,670,250);
    add(button);
    add(label);
    
    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
          JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              String path = selectedFile.getAbsolutePath();
              ToencodeFile = path;
              label.setIcon(ResizeImage(path));
              encode en = new encode(msg, ToencodeFile, StegFile);
              
          }
           //if the user click on save in Jfilechooser


          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
        }
    });
    
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(700,400);
    setVisible(true);
    }
     
     // Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
		System.out.println("Please give your choice, type 1 or 2");
		System.out.println("1. Encode");
		System.out.println("2. Decode");
		String ch = in.nextLine();
		int c = Integer.valueOf(ch);
		
		switch(c){
		case 1:
			new Select();
           break;
		case 2:
			decode de = new decode(StegFile);
            
    }
   }}
