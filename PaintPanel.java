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
import java.util.HashMap;



public class PaintPanel extends JPanel {

    private int pointCount = 0 ; // count number of points
    List<List<Point>> masterPointList = new ArrayList<List<Point>>();

    private int shapeCount = 0;

    private List<Point> points = new ArrayList<Point>();

    private List<Integer> xpoints = new ArrayList<Integer>();
    private List<Integer> ypoints = new ArrayList<Integer>();

    private List<Shapes> shapeList = new ArrayList<Shapes>();

    private List<ShapeInfo> shapeInfoList = new ArrayList<ShapeInfo>();

    private Shapes currentShape = null;

    Component compClicked = null;
    List<Component> listOfComponenets = new ArrayList<Component>();

    public PaintPanel() {


        addMouseMotionListener(new MouseMotionAdapter() {

                public void mouseDragged( MouseEvent event ) {
                    if (compClicked != null) {
                        int x = event.getY();
                        int y = event.getY();
                        compClicked.setBounds(x,y, compClicked.getWidth(), compClicked.getHeight());
                        repaint();
                    } else {
                        if (pointCount < 10000000) {
                            points.add(event.getPoint());
                            xpoints.add(event.getPoint().x);
                            ypoints.add(event.getPoint().y);
                            pointCount++;

                            try {
                                masterPointList.get(shapeCount);
                                masterPointList.set(shapeCount,points);
                            } catch (IndexOutOfBoundsException e) {
                                masterPointList.add(points);
                            }
                            repaint();
                        }
                    }
                }
            }
        );

        addMouseListener(new MouseAdapter() {
            @Override public void mouseReleased(MouseEvent e) {
                System.out.println(xpoints);
                if (points.size() > 0) {
                    int minX = Collections.min(xpoints);
                    int maxX = Collections.max(xpoints);
                    int sizex,sizey;
                    int flag=0;

                    int centerX = (maxX + minX)/2;


                    int minY = Collections.min(ypoints);
                    int maxY = Collections.max(ypoints);


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

                    // (x-h)^2 + (y - k)^2 = r^2
                    int difference = 0;
                    for ( int i = 0 ; i < pointCount; i++ ) {
                        double radNew = Math.sqrt(Math.pow(points.get(i).x  - centerX, 2) + Math.pow(points.get(i).y - centerY, 2));
                        double diff = Math.pow(radius - radNew, 2);
                        difference+=diff;
                    }
                    //System.out.println(difference/ypoints.size());
                    //System.out.println(ypoints.size());
                    if (difference/ypoints.size() < 10 && ypoints.size()>50) {
                        flag=1;
                        shapeList.add(Shapes.CIRCLE);
                        currentShape = Shapes.CIRCLE;

                    } else {

                        if (endptdiffX<10 && endptdiffY<10 && flag==0)
                        {
                            flag=1;
                            shapeList.add(Shapes.RECTANGLE);
                            currentShape = Shapes.RECTANGLE;
                        }
                        else if (endptdiffY<10 && ypoints.size()<20 && flag==0) {
                            flag=1;
                            shapeList.add(Shapes.DASH);
                            currentShape = Shapes.DASH;
                        } else if(endptdiffY<10 && Math.abs(ypoints.get(midy)-midptY)<10 && flag==0) {
                            flag=1;
                            shapeList.add(Shapes.HORIZONTAL_LINE);
                            currentShape = Shapes.HORIZONTAL_LINE;
                        } else if(endptdiffX<10 && Math.abs(xpoints.get(midx)-midptX)<10 && flag==0) {
                            flag=1;
                            shapeList.add(Shapes.VERTICAL_LINE);
                            currentShape = Shapes.VERTICAL_LINE;
                        } else if(endptdiffY<10 && (ypoints.get(midy)-midptY)>10 && flag==0){
                            flag=1;
                            shapeList.add(Shapes.BOTTOM_ARROW);
                            currentShape = Shapes.BOTTOM_ARROW;
                        } else if(endptdiffY<10 && (ypoints.get(midy)-midptY)<10 && flag==0){
                            flag=1;
                            shapeList.add(Shapes.TOP_ARROW);
                            currentShape = Shapes.TOP_ARROW;
                        } else if(endptdiffX<50 && (xpoints.get(midx)-midptX)>10 && flag==0){
                            flag=1;
                            shapeList.add(Shapes.RIGHT_ARROW);
                            currentShape = Shapes.RIGHT_ARROW;
                        } else if(endptdiffX<50 && (xpoints.get(midx)-midptX)<10 && flag==0){
                            flag=1;
                            shapeList.add(Shapes.LEFT_ARROW);
                            currentShape = Shapes.LEFT_ARROW;
                        } else {
                            masterPointList.get(shapeCount).clear();
                            repaint();
                            currentShape = null;
                            shapeCount-=1;
                        }
                    }

                    if (currentShape != null) {
                        ShapeInfo thisShapesInfo = new ShapeInfo(maxX - minX, maxY - minY, points.get(0).x, points.get(0).y, points.get(points.size() - 1).x, points.get(points.size() -1).y);
                        shapeInfoList.add(thisShapesInfo);

                    }
                    xpoints = new ArrayList<Integer>();
                    ypoints = new ArrayList<Integer>();
                    points = new ArrayList<Point>();
                    pointCount = 0;
                    shapeCount+=1;
                }
                compClicked = null;
            }

        });





    } // end PaintPanel constructor

    boolean grid_yes=true;
    int step=20;
    // draw oval in a 4-by-4 bounding box at specified location on window
    public void paintComponent( Graphics g ) {
        super.paintComponent( g ); // clears drawing area
        // draw all points in array
        for (int j = 0; j < masterPointList.size(); j++) {
            for (int i = 0 ; i < masterPointList.get(j).size(); i++) {
                g.fillOval( masterPointList.get(j).get(i).x, masterPointList.get(j).get(i).y, 8, 8 );
            }
        }

       if(grid_yes==true){
       //     int this.size
           for(int j = 0; j < 10000; j=j+step){
           g.drawLine(0, j, 10000, j);
           g.drawLine(j, 0, j, 10000);
       }

       }
    } // end method paintComponent

    public List<Shapes> getShapeList() {
        return shapeList;
    }

    public List<ShapeInfo> getShapeInfoList() {
        return shapeInfoList;
    }

    public List<List<Point>> getMasterPointList() {
        return masterPointList;
    }

    public void clearComponents(){
        masterPointList.clear();
        xpoints.clear();
        ypoints.clear();
        points.clear();
        pointCount=0;
        shapeCount=0;
        shapeList.clear();
        shapeInfoList.clear();
        repaint();


    }

    public void repaintScreen() {
        repaint();
    }

    public Component getCompClicked() {
        return compClicked;
    }

    public void setCompClicked(Component c) {
        compClicked = c;
    }

    public void addToListOfComp(Component c) {
        listOfComponenets.add(c);
        System.out.println(listOfComponenets);
    }
} // end class PaintPanel