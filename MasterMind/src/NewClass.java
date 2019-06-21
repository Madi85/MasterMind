/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author madi
 */
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
 
 
 
public class NewClass {

public class Frame extends JFrame implements ActionListener, MouseListener {
    //private Object Borderlayout;
    
    private ArrayList icons      = null;
    private ArrayList names      = null;
    private ArrayList panels     = null;
    
    private Panel     mainPanel  = null;
    private Panel     infoPanel  = null;
    private Panel     naviPanel  = null;
    
    private Label     infoLabel  = null;
    
    
    private Button    nextButton = null;
    private Button    prevButton = null;
    private Button   nexnextButton =null;
    private Button   preprevButton =null;
    
    private JMenuItem menuItem_1x1 = null;
    private JMenuItem menuItem_2x1 = null;
    private JMenuItem menuItem_3x1 = null;
    private JMenuItem menuItem_4x1 = null;
    private JMenuItem menuItem_2x2 = null;
    private JMenuItem menuItem_3x2 = null;
    private JMenuItem menuItem_4x2 = null;
    private JMenuItem menuItem_3x3 = null;
    private JMenuItem menuItem_4x4 = null;
    
    
    private int       iconIdx  = 0;
    private int       iconRows = 3;
    private int       iconCols = 3;
    
    
    public Frame() {
        
       icons  = new ArrayList();
       names  = new ArrayList();
       panels = new ArrayList();
      
       mainPanel = new Panel();
       infoPanel = new Panel();
       naviPanel = new Panel();
        
       infoLabel = new Label();
       infoLabel.setBackground(Color.LIGHT_GRAY);
       infoLabel.setFont(new Font("Arial",Font.BOLD,20));
       
       
       nextButton = new Button("Next");
       prevButton = new Button("Prev");
       
       
       nexnextButton = new Button(">>");
       preprevButton = new Button("<<");
       nexnextButton.setFont(new Font("Arial",Font.BOLD,20));
       preprevButton.setFont(new Font("Arial",Font.BOLD,20));
       nexnextButton.addActionListener(this);
       preprevButton.addActionListener(this);
       
       
       nextButton.setFont(new Font("Arial", Font.PLAIN, 20));
       prevButton.setFont(new Font("Arial", Font.PLAIN, 20));
       nextButton.addActionListener(this);
       prevButton.addActionListener(this);
      
 
       //Erstellen einer Menüleiste
 JMenuBar menuBar = new JMenuBar();
 
 //Hinzufügen von Menüs
 JMenu  menuAnsicht = new JMenu("Ansicht");
 menuItem_1x1 = new JMenuItem("1x1");
        menuItem_2x1 = new JMenuItem("2x1");
        menuItem_3x1 = new JMenuItem("3x1");
        menuItem_4x1 = new JMenuItem("4x1");
        menuItem_2x2 = new JMenuItem("2x2");
        menuItem_3x2 = new JMenuItem("3x2");
        menuItem_4x2 = new JMenuItem("4x2");
        menuItem_3x3 = new JMenuItem("3x3");
        menuItem_4x4 = new JMenuItem("4x4");
        
        menuItem_1x1.addActionListener(this);
        menuItem_2x1.addActionListener(this);
        menuItem_3x1.addActionListener(this);
        menuItem_4x1.addActionListener(this);
        menuItem_2x2.addActionListener(this);
        menuItem_3x2.addActionListener(this);
        menuItem_4x2.addActionListener(this);
        menuItem_3x3.addActionListener(this);
        menuItem_4x4.addActionListener(this);
      
        menuAnsicht.add(menuItem_1x1);
        menuAnsicht.add(menuItem_2x1);
        menuAnsicht.add(menuItem_3x1);
        menuAnsicht.add(menuItem_4x1);
        menuAnsicht.add(menuItem_2x2);
        menuAnsicht.add(menuItem_3x2);
        menuAnsicht.add(menuItem_4x4);
        menuAnsicht.add(menuItem_3x3);
        menuAnsicht.add(menuItem_4x4);
  
 
 menuBar.add(menuAnsicht);
        this.setJMenuBar(menuBar);
 
    
  
       
       final File folder = new File("C:/Users/schul01/Desktop/@najjarm/256x256");
       addFilesForFolder(folder);
       
       
       this.setLayout(new BorderLayout(20, 20));
       this.add(mainPanel, BorderLayout.CENTER);
       this.add(infoPanel, BorderLayout.SOUTH);
       
       infoPanel.setLayout(new BorderLayout());
       infoPanel.add(infoLabel, BorderLayout.CENTER);
       infoPanel.add(naviPanel, BorderLayout.EAST);
       
      
       
       
       naviPanel.setLayout(new GridLayout(1, 2));
       naviPanel.add(preprevButton);
       naviPanel.add(prevButton);
       naviPanel.add(nextButton);
       naviPanel.add(nexnextButton);
       
       
       
        initUI();    
        setSize(new Dimension(800,600));
        setTitle("Image");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    
    }
    
    private void initUI(){
       
        this.mainPanel.removeAll();
      
        this.mainPanel.setLayout(new GridLayout(iconRows, iconCols));
    
        for (int i = 0; i < iconRows; i++)
            for (int j = 0; j < iconCols; j++) { 
                
                if (iconCols*i+j < icons.size()) {
                    int k = iconCols*i+j+iconIdx ;
                    IconPanel panel = new IconPanel((String)this.names.get(k), (ImageIcon)this.icons.get(k));
                    panel.addMouseListener(this);
                    panels.add(panel);
                    this.mainPanel.add(panel);
                    
                }
            }
        
    
        prevButton.setEnabled((getNextIconIdx(iconIdx, -1) != iconIdx));
        preprevButton.setEnabled((getNextIconIdx(iconIdx, -1 * (iconRows*iconCols)) != iconIdx));
        nextButton.setEnabled((getNextIconIdx(iconIdx, +1) != iconIdx));  
        nexnextButton.setEnabled((getNextIconIdx(iconIdx, +1 * (iconRows*iconCols)) != iconIdx));
        
          
        this.mainPanel.repaint();
          
          this.menuItem_1x1.addActionListener(this);
    }  
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
                JFrame ex = new Frame();
                ex.setVisible(true);                
            }
        });
    }
    
    public void actionPerformed(ActionEvent ae) {
        
      
            if (ae.getSource() == prevButton)
                iconIdx = getNextIconIdx(iconIdx, -1);
            if (ae.getSource() == preprevButton)
                iconIdx = getNextIconIdx(iconIdx, -1 * (iconRows*iconCols));
            if (ae.getSource() == nextButton)
                iconIdx = getNextIconIdx(iconIdx, +1);
            if (ae.getSource() == nexnextButton)
                iconIdx = getNextIconIdx(iconIdx, +1 * (iconRows*iconCols));
      
                if  (ae.getSource() == this.menuItem_1x1)
                { 
                   this.iconRows = 1; 
                   this.iconCols = 1;
                }
          
                if (ae.getSource() == this.menuItem_2x1)
                { 
                    this.iconRows = 1; 
                    this.iconCols = 2;
                }
            
                if (ae.getSource() == this.menuItem_3x1)
                { 
                    this.iconRows = 1; 
                    this.iconCols = 3;
                }
                if (ae.getSource() == this.menuItem_4x1)
                { 
                    this.iconRows = 1; 
                    this.iconCols = 4;
                }   
             
                 if(ae.getSource() == this.menuItem_2x2)
                { 
                    this.iconRows = 2; 
                    this.iconCols = 2;
                }
                if (ae.getSource() == this.menuItem_3x2)
                { 
                    this.iconRows = 2; 
                    this.iconCols = 3;
                }
                if (ae.getSource() == this.menuItem_4x2)
                { 
                   this.iconRows = 2; 
                   this.iconCols = 4;
                } 
                if (ae.getSource() == this.menuItem_3x3)
                { 
                   this.iconRows = 3; 
                   this.iconCols = 3;
                }
                if (ae.getSource() == this.menuItem_4x4)
                { 
                   this.iconRows = 4; 
                   this.iconCols = 4;
                }     
       initUI();
       this.revalidate();
       this.repaint();
       System.out.println("Hello0o");
       
    }
    
    public void addFilesForFolder(final File folder) {
       
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                addFilesForFolder(fileEntry);
            }   
            else {
            //if (icons.size() <10)
                
                icons.add(new ImageIcon(folder.getAbsolutePath()+"/"+fileEntry.getName())) ;
                
                names.add(fileEntry.getName());
            } 
        }
    }     
    
    public boolean isIconIdxOk(int idx){
       
        int len = icons.size();
   
        if (idx < 0) {   
            System.out.println("Zu klein");
            return false;
        } 
        
        if (idx > len - (iconRows * iconCols))  { 
            System.out.println("Zu gross");
            return false;
        }
   
        return true;
    }
    int getNextIconIdx(int idx, int add) {

    int min = 0;
    int max = icons.size() - (iconCols * iconRows);
    if (idx + add < min)
      add = min - idx;
    
    if (idx + add > max)
      add = max - idx;
    System.out.println("Add: "+add);
    return idx + add;
}
    private void isIconIndex(int iconIdx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void mouseClicked(MouseEvent me) {
         System.out.println(me.getSource());
         infoLabel.setText(me.getSource().toString());
    }
    @Override
    public void mousePressed(MouseEvent me) {
       
    }
    @Override
    public void mouseReleased(MouseEvent me) {
    }
    @Override
    public void mouseEntered(MouseEvent me) {
        System.out.println(me.getSource().toString());
        infoLabel.setText(me.getSource().toString());
        
    }
    @Override
    public void mouseExited(MouseEvent me) {
        infoLabel.setText(null);
    
}
}
    
}
