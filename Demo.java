import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Demo {
    public static void main(String[] args) {
		// Create all of the components
        JFrame frame = new JFrame("A Window");
        frame.setSize(800, 600);
        JTextArea text = new JTextArea(20, 80);
        JScrollPane scroll = new JScrollPane(text);
        JButton ok = new JButton("Add");
        JButton cancel = new JButton("Reset");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancel.setAlignmentX(Component.CENTER_ALIGNMENT);

        PaintPanel paintPanel = new PaintPanel(); // create paint panel
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           text, paintPanel);

        JPanel jLeft = new JPanel();
        JPanel jRight = new JPanel();
        jRight.setLayout(new FlowLayout(FlowLayout.CENTER));
        jRight.add(ok);
        jRight.add(cancel);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           jLeft, jRight);


        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(450);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);
        paintPanel.setMinimumSize(minimumSize);
        text.setMinimumSize(minimumSize);

        splitPane2.setOneTouchExpandable(true);
        splitPane2.setDividerLocation(450);

        //Provide minimum sizes for the two components in the split pane
        jLeft.setMinimumSize(minimumSize);
        jRight.setMinimumSize(minimumSize);

		// This tells the frame to quit the app when the window’s Close box is checked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// This manages the positioning of the child components; more on this later
        frame.setLayout(new BorderLayout());

		// Here’s where we actually create the tree
        frame.add(splitPane, BorderLayout.CENTER);
        frame.add(splitPane2, BorderLayout.SOUTH);

		// Last two steps are necessary to put the UI on the screen:  pack() sizes the window
		// “shrink wrapping” it to the size needed to contain it’s children.  show() displays it
        frame.pack();
        frame.show();
    }
}