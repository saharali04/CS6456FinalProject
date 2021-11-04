// Fig. 11.34: PaintPanel.java
 // Using class MouseMotionAdapter.
 import java.awt.Point;
 import java.awt.Graphics;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseMotionAdapter;
 import javax.swing.JPanel;
 import java.util.Collections;
 import java.util.Arrays;
  import java.awt.event.MouseAdapter;
  import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class PaintPanel extends JPanel {

    private int pointCount = 0 ; // count number of points
     // array of 10000 java.awt.Point references
     private Point points[] = new Point[ 1000000 ];

     private List<Integer> xpoints = new ArrayList<Integer>();
     private List<Integer> ypoints = new ArrayList<Integer>();
     // set up GUI and register mouse event handler
     public PaintPanel() {
     // handle frame mouse motion event
     addMouseMotionListener( new MouseMotionAdapter() // anonymous inner class
        {
        // store drag coordinates and repaint
        public void mouseDragged( MouseEvent event ) {
             if ( pointCount < points.length )
             {
             points[ pointCount ] = event.getPoint(); // find point
             xpoints.add(event.getPoint().x);
             ypoints.add(event.getPoint().y);

             pointCount++; // increment number of points in array
             repaint(); // repaint JFrame
             } // end if
        } // end method mouseDragged
        } // end anonymous inner class
      ); // end call to addMouseMotionListener

     addMouseListener(new MouseAdapter() {
        @Override public void mouseReleased(MouseEvent e) {

            int minX = Collections.min(xpoints);
            int maxX = Collections.max(xpoints);

            System.out.println("minX " + minX);
            System.out.println("maxX " + maxX);


            int centerX = (maxX + minX)/2;


            int minY = Collections.min(ypoints);
            int maxY = Collections.max(ypoints);

            System.out.println("minY " + minY);
            System.out.println("maxY " + maxY);

            int centerY = (maxY + minY)/2;

            int radius = ((maxX - minX) + (maxY - minY)) / 4;

            System.out.println(radius);

            System.out.println(centerX + " , " + centerY);

            // (x-h)^2 + (y - k)^2 = r^2
            int difference = 0;
            for ( int i = 0 ; i < pointCount; i++ ) {
                double radNew = Math.sqrt(Math.pow(points[i].x  - centerX, 2) + Math.pow(points[i].y - centerY, 2));
                double diff = Math.pow(radius - radNew, 2);
                difference+=diff;
            }
            System.out.println(difference/ypoints.size());
            System.out.println(ypoints.size());
            if (difference/ypoints.size() < 10 && ypoints.size() > 100) {
                System.out.println("Circle/Rectangle");
            } else {
                System.out.println("Stray Mark");
            }


        }

        public void mouseClicked(MouseEvent e) {
            if ( pointCount < points.length )
            {
             points[ pointCount ] = e.getPoint(); // find point
             xpoints.add(e.getPoint().x);
             ypoints.add(e.getPoint().y);

             pointCount++; // increment number of points in array
             repaint(); // repaint JFrame
            }

        }
     });
     } // end PaintPanel constructor

     // draw oval in a 4-by-4 bounding box at specified location on window
     public void paintComponent( Graphics g ) {
        super.paintComponent( g ); // clears drawing area
        // draw all points in array
        for ( int i = 0 ; i < pointCount; i++ ) {
            System.out.println(points[i]);
            g.fillOval( points[ i ].x, points[ i ].y, 8, 8 );
        }

    } // end method paintComponent
} // end class PaintPanel