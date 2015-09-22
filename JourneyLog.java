import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.io.*;

/**
 * Write a description of class Text2file here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JourneyLog extends JFrame
{
    

    /**
     * Constructor for objects of class Text2file
     */
    public JourneyLog()
    {
        super("LostPyramid");
        createGUI();
    }

    /**
     * main method that creates the Text2File object
     * 
     */
    public static void main(String args[])
    {
        JourneyLog jl = new JourneyLog();
        
    }
    
    /**
     * create new object for the GUI
     */
    private void createGUI() {
        JMenuBar javaMenu = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem writeItem = new JMenuItem("Write");
        JMenuItem readItem = new JMenuItem("Read");
        JMenuItem quitItem = new JMenuItem("Quit");
        
        setJMenuBar(javaMenu);
        setPreferredSize(new Dimension(300,300));
        
        javaMenu.add(menu);
        menu.add(writeItem);
        menu.add(readItem);
        menu.add(quitItem);
        
        writeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s = userInput();
                writeFile(s);
            }
        
        });
        
        readItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{readFile();}
                catch(Exception f){
                }
           }
        
        });
        
        quitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            System.exit(0);
            }
        
        });
        
        pack();
        setVisible(true);
    }
    
    /**
     * 
     */
    public void readFile()throws Exception{  
      FileReader fr=new FileReader("abc.txt");  
      int i;  
      while((i=fr.read())!=-1)  
      System.out.print((char)i);  
      
      fr.close();  
     }  
    
    /**
     * 
     */
    public void writeFile(String s){
          try{  
           FileWriter fw=new FileWriter("abc.txt");  
           fw.write(s);  
           fw.close();  
          }catch(Exception e){System.out.println(e);}  
          System.out.println("success");  
        }  
    
    /**
     * 
     */
    
    public String userInput(){
        return (String) JOptionPane.showInputDialog("Enter text to save");
    }
    
}
