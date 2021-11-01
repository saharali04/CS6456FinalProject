import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;


public class finalProject {
    // unlike the past example, we’ll put the initial code in the constructor and then
    // create an instance of SimpleApp in the main function.
    public finalProject() {
        // create a frame
        JFrame f = new JFrame("Menu demo");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // tell the content pane to use BorderLayout to manage children
        f.getContentPane().setLayout(new BorderLayout());

        // create a menubar
        JMenuBar mb = new JMenuBar();

        // create a menu
        JMenu File_menu = new JMenu("File");
        JMenu Code_menu = new JMenu("Code-View");
        JMenu Visual_menu = new JMenu("Visual Editor Mode");

        // create menuitems
        //JMenuItem m1 = new JMenuItem("MenuItem1");
        //JMenuItem m2 = new JMenuItem("MenuItem2");
        //JMenuItem m3 = new JMenuItem("MenuItem3");

        // add menu items to menu
        //x1.add(m1);
        //x1.add(m2);
        //x1.add(m3);

        // add menu to menu bar
        mb.add(File_menu);
        mb.add(Code_menu);
        mb.add(Visual_menu);

        // add menubar to frame
        f.setJMenuBar(mb);

        // set the size of the frame
        f.setSize(700, 500);
        f.setVisible(true);

        JPanel left_bar=new JPanel();
        JPanel right_bar=new JPanel();
        //JPanel central_bar=new JPanel();
        PaintPanel central_bar = new PaintPanel(); // create paint panel


        f.getContentPane().add(left_bar, BorderLayout.WEST);
        f.getContentPane().add(right_bar, BorderLayout.EAST);
        f.getContentPane().add(central_bar, BorderLayout.CENTER);
        central_bar.setBackground(Color.white);

        //f.getContentPane().add(text, BorderLayout.CENTER);
        JLabel label_component_hierarchy = new JLabel("Component Hierarchy");
        left_bar.add(label_component_hierarchy);

        JLabel label_component_picker = new JLabel("Component Picker");
        right_bar.add(label_component_picker);

        JTextArea text = new JTextArea("This is a text area.\nYou can type into it!", 20, 40);
        text.setEditable(true);
        // note that when we add component, we have to indicate which “slot” they go into

        central_bar.setLayout(new BorderLayout());
        JButton add_button = new JButton("Add");
        JButton reset_button = new JButton("Reset");
        //add_button.setSize(new Dimension(20, 20));
        //reset_button.setSize(new Dimension(20, 20));
        //f.getContentPane().add(add_button);
        //central_bar.add(reset_button);

        // use of Lambda expressions for event listeners.
        //button.addActionListener(e -> System.exit(0));
        //central_bar.add(add_button, BorderLayout.SOUTH);
        //central_bar.add(reset_button, BorderLayout.SOUTH);

        //f.pack();

        f.show();
                }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                finalProject app = new finalProject();
            }
        });

    }}



