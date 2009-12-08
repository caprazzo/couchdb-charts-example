/*

  SVG Dynamic Bar chart Grapher
  
  Written by B.Venn for Developer Works article
*/ 

import java.sql.*;
import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class SVG_barchart 
{

public static void main(String args[]){

     SVG_barchart.renderSVGGraph(args);

}

public static void renderSVGGraph(String[] barChartValues)
{
       		
        int largestNumber=0;
        int barChartValue=0;
        int XValue = -1;
        double YValue = -1;
        int i=0;
        int LargestXPos=0;
        double LargestYPos=0;
        int LineCount=0;
        int averageNumber=0;
        double YAxisScalingFactor=0;
        Random random = new Random();

        // This is where we find out what the largest value contained in each of the array is,
        // its a simple loop that goes through each value passed, and determines the highest
        do{ 		
            barChartValue = Integer.parseInt(barChartValues[i]);

            if (barChartValue > largestNumber){ 
                largestNumber= barChartValue; 
            }
            i++;
        }while (i<barChartValues.length);


        // Work out the Y Axis scaling number from the highest value       
        YAxisScalingFactor = 1000/(double)largestNumber; 
        System.out.println("YAxisScalingFactor is " + YAxisScalingFactor);
    
        // The highest value passed in is stored in the parameter largestNumber
        try{
                 
            // Get the SVG file ready for the drawing of the perfomance graph 
            File SVGOutputFile = new File("SVGBarChart.svg");
            FileWriter SVGout = new FileWriter(SVGOutputFile);
        
            // Get the SVG graph ready
            SVGout.write("<?xml version=\"1.0\"?>");
            SVGout.write("\n<svg width=\"1300\" height=\"1300\">");
            SVGout.write("\n<desc>Developer Works Dynamic Bar chart Scaling Example</desc>");
            
            // Move the graph down and right a little so its not stuck in the top corner. Also
            // Scale it to half size so it fits on the screen better
            SVGout.write("\n<g transform=\"translate(50,50) scale(0.5)\">");
            
            // Draw a basic X and Y axis
            SVGout.write("\n<!-- Now Draw the main X and Y axis -->");
            SVGout.write("\n<g style=\"stroke-width:5; stroke:black\">");
            SVGout.write("\n<!-- X Axis -->");
                    
            SVGout.write("\n<path d=\"M 0 1000 L 1000 1000 Z\"/>");
            SVGout.write("\n<!-- Y Axis -->");
            SVGout.write("\n<path d=\"M 0 0 L 0 1000 Z\"/>");
            SVGout.write("\n</g>");
    
            // Draw a dotted line a the top of the Y-Axis and one half way down
            SVGout.write("\n<g style=\"fill:none; stroke:#B0B0B0; stroke-width:2; stroke-dasharray:2 4;text-anchor:end; font-size:30\">");
            SVGout.write("\n<path d=\"M 0 0 L 1000 0 Z\"/>");
            SVGout.write("\n<path d=\"M 0 500 L 1000 500 Z\"/>");
            
            // Add a text label of the values of the highest,and half of the highest.
            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"-10\" y=\"0\" >" + largestNumber + "</text>");
            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"-10\" y=\"500\" >" + (largestNumber/2) + "</text>");
            SVGout.write("\n</g>");
    
            // The Graph is ready to be rendered with the values.
            for(i=0;i<barChartValues.length;i++){
    
        	// Calculate the Y position. First work out how high the bar will be by multiplying the Value by the Scaling Factor
                // calculated earlier
                double barHeight = 
                        Integer.parseInt(barChartValues[i]) * YAxisScalingFactor;
                System.out.println("Bar Height is =" + barHeight);
    
                // We now have the height that the bar will be. 
                // Need to work out now where to place the bar. 
                // With Y values running positively down,               
                // and the Y-Axis being 1000 pixels tall, simply 
                // subtract the bar height from 1000 to get the
                // position of where to place
                // the bar
                double YStart = 1000 - barHeight;
                
                // Each of our bars is 100 pixels wide. So to space 
                // them out (with a 10 pixel gap between them), 
                // multiply the readings postion
                // in the array by 110.
                double XPosition = (i*110);
    
                // Generate some random numbers for our bar colours
                int randomRed = random.nextInt(255);
                int randomGreen = random.nextInt(255);
                int randomBlue = random.nextInt(255);
    
                // We now have all our values ready. Draw the rectangle 
                SVGout.write("\n<rect x=\""+XPosition+"\" y=\""+
                             YStart+"\" width =\""+ 100 +"\" height=\""+barHeight+"\" style=\"fill:rgb("
                             +randomRed+","+randomGreen+","+randomBlue+");\" /> ");
    	                             
            }
                
        // Close off our tags and the file.
        SVGout.write("\n</g>");
     	SVGout.write("\n</svg>");
    	SVGout.close();
   }
   catch (java.io.IOException e){
       System.out.println("IO Exception error caught");
       e.printStackTrace();
   }
   catch (Exception e) 
   {
       System.out.println("Unknown error caught");
       e.printStackTrace();
   }
}


}          

