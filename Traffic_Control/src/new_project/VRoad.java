//package new_project;
//import java.awt.*;
//
//public class VRoad {
//
//    // Variables declaration
//    //Set size of road
//    private int xStart,yStart, trafficlight, RGB;
//    private final int road_width = 50;
//    private final int road_length = 100;
//
//    VRoad(int x_Start, int y_Start, int trafficlight, int RGB){
//        this.xStart = x_Start;
//        this.yStart = y_Start;
//        this.trafficlight = trafficlight;
//        System.out.print(" Reached here A:" + trafficlight +" .\n");
//        this.RGB = RGB;
//    }    
//    
//    protected void doDrawing(Graphics g)
//    {
//        Graphics2D road=(Graphics2D) g;
//        Graphics2D lane_divider=(Graphics2D) g;
//        Graphics2D road_border=(Graphics2D) g;
//       
//        //draw vertical road
//        road.setColor(Color.gray);
//        road.fillRect(xStart, yStart, road_width , road_length);
//        
//        //draw road divider
//        float[] dash = {4f, 0f, 2f};
//       
//        //draw road border
//        road_border.setColor(Color.white);
//        road_border.drawLine(xStart,yStart,xStart,yStart+road_length-1);
//        road_border.drawLine(xStart+road_width,yStart,xStart+road_width,yStart+road_length-1);
//        
//        //draw road lines
//        BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_ROUND, 1.0f, dash, 2f);
//        lane_divider.setStroke(bs3);
//        lane_divider.setColor(Color.white);
//        lane_divider.drawLine(xStart+road_width/2,yStart,xStart+road_width/2,yStart+road_length-1);
//        
//        if (trafficlight ==10){//traffic lights on the top
//            TrafficLight.trafficlight(xStart, yStart,  RGB);
//            TrafficLight.doDrawing(g);
//            TrafficLight.trafficlight(xStart, road_width,  RGB);
//            TrafficLight.doDrawing(g);
//            System.out.print(" Reached here D:" + RGB+ " .\n");
//        }else if (trafficlight ==01){//traffic lights on the bottom
//            TrafficLight.trafficlight(xStart+road_length-5, road_width/2,  RGB);
//            TrafficLight.doDrawing(g); 
//            TrafficLight.trafficlight(xStart+road_length-5, road_width,  RGB);
//            TrafficLight.doDrawing(g);  
//            System.out.print(" Reached here E:" + RGB+ " .\n");
//        }
//    }
//       
//}