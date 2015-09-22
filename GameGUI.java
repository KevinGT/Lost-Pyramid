import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameGUI {
    public static void addComponentsToPane(Container contentPane) {
        // Use BorderLayout. 
        contentPane.setLayout(new BorderLayout(5,5));    

        JButton jbtn = new JButton("IMAGE WILL GO HERE");
        jbtn.setPreferredSize(new Dimension(1000, 500));
        contentPane.add(jbtn, BorderLayout.CENTER);

        jbtn = new JButton("GRIDLAYOUT - Controls will go here");
        jbtn.setPreferredSize(new Dimension(200, 600));
        contentPane.add(jbtn, BorderLayout.WEST);

        jbtn = new JButton("Adventure text goes here");
        jbtn.setPreferredSize(new Dimension(1000, 100));
        contentPane.add(jbtn, BorderLayout.SOUTH);

    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Lost Pyramid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane and add swing components to it
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
