import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;

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
                int sizex,sizey;

                System.out.println("minX " + minX);
                System.out.println("maxX " + maxX);


                int centerX = (maxX + minX)/2;


                int minY = Collections.min(ypoints);
                int maxY = Collections.max(ypoints);

                System.out.println("minY " + minY);
                System.out.println("maxY " + maxY);

                int centerY = (maxY + minY)/2;

                int radius = ((maxX - minX) + (maxY - minY)) / 4;

                sizex = xpoints.size();
                sizey = ypoints.size();
                int diffY=maxY-minY;
                int endptdiffY=Math.abs(ypoints.get(sizey-1)-ypoints.get(0));
                int endptdiffX=Math.abs(xpoints.get(sizex-1)-xpoints.get(0));

                int midptY=Math.abs(ypoints.get(sizey-1)+ypoints.get(0))/2;
                int midptX=Math.abs(xpoints.get(sizex-1)+xpoints.get(0))/2;

                int midy,midx;

                if(sizey%2==0)
                    midy=sizey/2;
                else
                    midy=(sizey+1)/2;

                if(sizex%2==0)
                    midx=sizex/2;
                else
                    midx=(sizex+1)/2;

                //System.out.println(xpoints.get(0));
                //System.out.println(sizex);
                //System.out.println(ypoints.get(0));
                //System.out.println(sizey);

                //System.out.println(radius);

                //System.out.println(centerX + " , " + centerY);

                // (x-h)^2 + (y - k)^2 = r^2
                int difference = 0;
                for ( int i = 0 ; i < pointCount; i++ ) {
                    double radNew = Math.sqrt(Math.pow(points[i].x  - centerX, 2) + Math.pow(points[i].y - centerY, 2));
                    double diff = Math.pow(radius - radNew, 2);
                    difference+=diff;
                }
                //System.out.println(difference/ypoints.size());
                //System.out.println(ypoints.size());
                if (difference/ypoints.size() < 10 && ypoints.size()>15) {
                    System.out.println("Circle");
                    System.out.println(ypoints.size());
                } else {
                    System.out.println("Not circle");



                    if(endptdiffY<10 && ypoints.size()<20) {
                        System.out.println("Dash");
                        //System.out.println(ypoints.size());
                    }



                    else if(endptdiffY<10 && Math.abs(ypoints.get(midy)-midptY)<10) {
                        System.out.println("Horizontal line");
                        //System.out.println(ypoints.size());
                    }

                    else if(endptdiffX<10 && Math.abs(xpoints.get(midx)-midptX)<10) {
                        System.out.println("Vertical line");
                    }


                    else if(endptdiffY<10 && (ypoints.get(midy)-midptY)>10){
                        System.out.println("Bottom arrow");
                    }

                    else if(endptdiffY<10 && (ypoints.get(midy)-midptY)<10){
                        System.out.println("Top arrow");
                    }


                    else if(endptdiffX<50 && (xpoints.get(midx)-midptX)>10){
                        System.out.println("Right arrow");
                    }

                    else if(endptdiffX<50 && (xpoints.get(midx)-midptX)<10){
                        System.out.println("Left arrow");
                    }


                    System.out.println(endptdiffX);
                    System.out.println(xpoints.get(midx)-midptX);
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



/*
public class PaintPanel extends JPanel {
    private int pointCount = 0; // count number of points
    // array of 10000 java.awt.Point references
    private Point points[] = new Point[10000];
    private List<Integer> xpoints = new ArrayList<Integer>();
    private List<Integer> ypoints = new ArrayList<Integer>();

    // set up GUI and register mouse event handler
    public PaintPanel() {
        // handle frame mouse motion event
        addMouseMotionListener(
                new MouseMotionAdapter() // anonymous inner class
                {
                    // store drag coordinates and repaint
                    public void mouseDragged(MouseEvent event) {
                        if (pointCount < points.length) {
                            points[pointCount] = event.getPoint(); // find point
                            xpoints.add(event.getPoint().x);
                            ypoints.add(event.getPoint().y);
                            pointCount++; // increment number of points in array
                            repaint(); // repaint JFrame
                        } // end if
                    } // end method mouseDragged
                } // end anonymous inner class
        ); // end call to addMouseMotionListener
    } // end PaintPanel constructor

    // draw oval in a 4-by-4 bounding box at specified location on window
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // clears drawing area

        // draw all points in array
        for (int i = 0; i < pointCount; i++)
            g.fillOval(points[i].x, points[i].y, 8, 8);
    } // end method paintComponent
} // end class PaintPanel
*/