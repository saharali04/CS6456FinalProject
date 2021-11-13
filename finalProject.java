import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


public class finalProject {
    // unlike the past example, we’ll put the initial code in the constructor and then
    // create an instance of SimpleApp in the main function.
    List<ShapeInfo> shapeInfoList = new ArrayList<ShapeInfo>();
    List<Shapes> shapeList = new ArrayList<Shapes>();
    PaintPanel central_bar = new PaintPanel(); // create paint panel
    List<JSlider> sliderList = new ArrayList<JSlider>();
    int num_comp;

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
        f.setSize(800, 600);
        f.setVisible(true);

        JPanel left_bar=new JPanel();
        JPanel right_bar=new JPanel();
        //JPanel paint_bar=new JPanel();

        //shapeInfoList=central_bar.returnShapeInfoList();
        //shapeList=central_bar.returnShapeList();


        f.getContentPane().add(left_bar, BorderLayout.WEST);
        f.getContentPane().add(right_bar, BorderLayout.EAST);
        f.getContentPane().add(central_bar, BorderLayout.CENTER);
        //paint_bar.add(central_bar);
        central_bar.setBackground(Color.white);

        //f.getContentPane().add(text, BorderLayout.CENTER);
        JLabel label_component_hierarchy = new JLabel("Component Hierarchy");
        left_bar.add(label_component_hierarchy);

        JLabel label_component_picker = new JLabel("Component Picker");
        right_bar.add(label_component_picker);

        JTextArea text = new JTextArea("This is a text area.\nYou can type into it!", 20, 40);
        text.setEditable(true);
        // note that when we add component, we have to indicate which “slot” they go into

        central_bar.setLayout(null);
        //JSlider slider = new JSlider(0, 50, 50);
        //slider.setSize(50, 50);
        //central_bar.add(slider);
        JButton undo_button = new JButton("Undo");
        undo_button.setBounds(130, 515, 75, 20);
        undo_button.setSize(new Dimension(75, 20));
        central_bar.add(undo_button);

        JButton redo_button = new JButton("Redo");
        redo_button.setBounds(230, 515, 75, 20);
        redo_button.setSize(new Dimension(75, 20));
        central_bar.add(redo_button);

        JButton reset_button = new JButton("Reset");
        reset_button.setBounds(330, 515, 75, 20);
        reset_button.setSize(new Dimension(75, 20));
        central_bar.add(reset_button);

        //JButton redo_button = new JButton("Redo");

        //JButton reset_button = new JButton("Reset");
        List<Shapes> finalShapeList = shapeList;
        central_bar.addMouseListener(new MouseAdapter() {
            @Override public void mouseReleased(MouseEvent e) {

                System.out.println(shapeList);
                //System.out.println(shapeInfoList);
                //System.out.println(shapeList.toArray().length);
                num_comp=shapeList.toArray().length;
                //System.out.println(num_comp);

                //Radio Button
                if(num_comp>=2 && num_comp%2==0){
                    int num_temp=num_comp;
                    ShapeInfo shapeInfo1=shapeInfoList.get(num_temp - 1);
                    ShapeInfo shapeInfo2=shapeInfoList.get(num_temp - 2);
                    while(num_temp>=0){
                        if(shapeList.get(num_temp - 1) == Shapes.HORIZONTAL_LINE && shapeList.get(num_temp - 2) == Shapes.CIRCLE){
                            System.out.println("Radio Button");
                            num_temp=num_temp-2;
                            num_comp=num_comp-2;
                        }
                    }

                }

                //Slider
                if(num_comp>=3){
                    ShapeInfo shapeInfo1=shapeInfoList.get(num_comp - 1);
                    ShapeInfo shapeInfo2=shapeInfoList.get(num_comp - 2);
                    ShapeInfo shapeInfo3=shapeInfoList.get(num_comp - 3);

                    if(shapeList.get(num_comp - 2) == Shapes.HORIZONTAL_LINE && shapeList.get(num_comp - 1) == Shapes.VERTICAL_LINE && shapeList.get(num_comp - 3)== Shapes.VERTICAL_LINE) {

                        if(shapeInfo1.getStartX()<shapeInfo3.getStartX())
                        {
                            if (Math.abs(shapeInfo1.getStartX()-shapeInfo2.getStartX())<10 && Math.abs(shapeInfo3.getStartX()-shapeInfo2.getEndX())<10)
                                System.out.println("Adding Slider");
                                JSlider slider = new JSlider(0, shapeInfo2.getWidth(), 100);
                                sliderList.add(slider);
                                slider.setBounds(shapeInfo2.getStartX(), shapeInfo2.getStartY(), shapeInfo2.getWidth(), shapeInfo2.getHeight());
                                central_bar.add(slider);
                                //central_bar.masterPointList.get(central_bar.shapeCount).clear();
                                central_bar.masterPointList.clear();
                                //central_bar.masterPointList.get(central_bar.shapeCount-1).clear();
                                //central_bar.masterPointList.get(central_bar.shapeCount-2).clear();
                                central_bar.repaint();
                                //central_bar.shapeCount-=3;
                            //JSlider slider = new JSlider(0, 50, 50);
                            //slider.setSize(50, 50);
                            //central_bar.add(slider);
                            //JButton undo_button = new JButton("Undo");
                            //undo_button.setBounds(130, 515, 75, 20);
                            //undo_button.setSize(new Dimension(75, 20));
                            //central_bar.add(undo_button);

                        }
                        else if(shapeInfo1.getStartX()>shapeInfo3.getStartX())
                        {
                            if (Math.abs(shapeInfo3.getStartX()-shapeInfo2.getStartX())<10 && Math.abs(shapeInfo1.getStartX()-shapeInfo2.getEndX())<10)
                                System.out.println("Adding Slider");
                                JSlider slider = new JSlider(0, shapeInfo2.getWidth(), 100);
                                sliderList.add(slider);
                                slider.setBounds(shapeInfo2.getStartX(), shapeInfo2.getStartY(), shapeInfo2.getWidth(), shapeInfo2.getHeight()+10);
                                central_bar.add(slider);
                        }

                    }

                    else if(shapeList.get(num_comp - 1) == Shapes.HORIZONTAL_LINE && shapeList.get(num_comp - 2) == Shapes.VERTICAL_LINE && shapeList.get(num_comp - 3)== Shapes.VERTICAL_LINE) {

                        if(shapeInfo2.getStartX()<shapeInfo3.getStartX())
                        {
                            if (Math.abs(shapeInfo2.getStartX()-shapeInfo1.getStartX())<10 && Math.abs(shapeInfo3.getStartX()-shapeInfo1.getEndX())<10)
                                System.out.println("Slider");

                        }
                        else if(shapeInfo2.getStartX()>shapeInfo3.getStartX())
                        {
                            if (Math.abs(shapeInfo3.getStartX()-shapeInfo1.getStartX())<10 && Math.abs(shapeInfo2.getStartX()-shapeInfo1.getEndX())<10)
                                System.out.println("Slider");
                        }
                    }
                    else if(shapeList.get(num_comp - 3) == Shapes.HORIZONTAL_LINE && shapeList.get(num_comp - 2) == Shapes.VERTICAL_LINE && shapeList.get(num_comp - 1)== Shapes.VERTICAL_LINE) {
                        if(shapeInfo2.getStartX()<shapeInfo1.getStartX())
                        {
                            if (Math.abs(shapeInfo2.getStartX()-shapeInfo3.getStartX())<10 && Math.abs(shapeInfo1.getStartX()-shapeInfo1.getEndX())<10)
                                System.out.println("Slider");

                        }
                        else if(shapeInfo2.getStartX()>shapeInfo1.getStartX())
                        {
                            if (Math.abs(shapeInfo1.getStartX()-shapeInfo3.getStartX())<10 && Math.abs(shapeInfo2.getStartX()-shapeInfo3.getEndX())<10)
                                System.out.println("Slider");
                        }
                    }



                }

                //System.out.println(shapeInfoList.toArray().length);

                //System.out.println()

            }
            });





        //reset_button.setSize(new Dimension(20, 20));
        //f.getContentPane().add(add_button);
        //central_bar.add(reset_button);
        //central_bar.add(add_button, BorderLayout.SOUTH);

        //central_bar.add(reset_button, BorderLayout.NORTH);
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
                app.shapeList=app.central_bar.returnShapeList();
                app.shapeInfoList=app.central_bar.returnShapeInfoList();
                //app.central_bar.masterPointList.clear();
                //app.central_bar.repaint();

                //System.out.println(app.shapeList);
                //System.out.println(Shapes.CIRCLE);
            }
        });

    }}



