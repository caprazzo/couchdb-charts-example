/*

  SVG Dynamic Bar chart Grapher
  
  Written by B.Venn
*/ 

import java.sql.*;
import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class SVG_linegraph
{

public static void main(String args[]){

     SVG_linegraph.renderSVGLineGraph(args);

}

public static void renderSVGLineGraph(String valuesToPlot[]){
    
        int largestNumber=0;
        int number=0;
        int i=0;
        int LargestXPos=0;
        int LineCount=0;

        double YPos=0;
        double YAxisScalingFactor=0;
        double XValue = -1;
        double YValue = -1;

	// Need to work out the X-Axis scale, depending on how many readings we are taking.
	// X-Axis scale is 1000 pixels wide.
	// Hence divide 1000 by the number of readings to determine where each reading will be.  
			
	double XAxisScalingFactor = 1000/(double)valuesToPlot.length;
        System.out.println("X Scaling Factor is " +  XAxisScalingFactor);

        // Determine what the highest Value is
        do{ 		
            number = Integer.parseInt(valuesToPlot[i]);

            if (number > largestNumber){ 
                largestNumber= number; 
            }
            i++;
        }while (i<valuesToPlot.length);

        // Largest values now done.
        try{
    
            System.out.println("Setting up file");
            // Get the SVG file ready for the drawing of the perfomance graph 
            File SVGOutputFile = new File("SVGLineGraph.svg");
            FileWriter SVGout = new FileWriter(SVGOutputFile);
                
            // Get the SVG graph ready
            SVGout.write("<?xml version=\"1.0\"?>");
            SVGout.write("\n<svg width=\"1300\" height=\"1300\">");
            SVGout.write("\n<desc>Developer Works Dynamic LineGraph Scaling Example</desc>");

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
            
            // Draw a dotted line a the top of the Y-Axis
            SVGout.write("\n<g style=\"fill:none; stroke:#B0B0B0; stroke-width:5; stroke-dasharray:2 4;text-anchor:end; font-size:30\">");
            SVGout.write("\n<path d=\"M 0 0 L 1000 0 Z\"/>");
            // And one half way down
            SVGout.write("\n<path d=\"M 0 500 L 1000 500 Z\"/>");
        
            SVGout.write("\n<path d=\"M 1000 0 L 1000 1000 Z\"/>");
            // And one half way across
            SVGout.write("\n<path d=\"M 500 0 L 500 1000 Z\"/>");
            
            // Add a text label of the values of the highest,and half of the highest.
            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"-10\" y=\"0\" >" + largestNumber + "</text>");
            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"-10\" y=\"500\" >" + (largestNumber/2) + "</text>");

            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"510\" y=\"1030\" >" + (valuesToPlot.length/2) + "</text>");
            SVGout.write("\n <text style=\"fill:black; stroke:none\" x=\"1010\" y=\"1030\" >" + valuesToPlot.length + "</text>");

            SVGout.write("\n</g>");
                                                                                                                            
            // Work out a Y-Axis scaling factor for each event.
            // The Y-Axis is 1000 pixels high, hence divide 1000 by the highest reading to determine how many
            // pixels high each event is
        
            // Work out the Y Axis scaling number from the highest value       
            YAxisScalingFactor = 1000/(double)largestNumber; 
             
            System.out.println("YAxisScalingFactor is " + YAxisScalingFactor);
              
            // Render the line
    	    SVGout.write("\n<polyline points=\"0 1000,");
    	    
                for(i=0;i<valuesToPlot.length;i++){
    
        	// Calculate the X position by determining which value 
                //in the array we are dealing with
    	        XValue = ((i+1)*XAxisScalingFactor);
    
    	        YValue = Integer.parseInt(valuesToPlot[i]);
     	        YValue = YValue*YAxisScalingFactor;
    	        YValue = 1000-YValue;
    
    	        // Now have our polyline point
    	        SVGout.write(" " + XValue + " " + YValue +",\n");
                
            }           	           
                // Close off the polyline
    	    SVGout.write("\" style=\"stroke:red; stroke-width: 3; fill : none;\"/>");
    
    
    	// All done. Close off the groups and the SVG file		  
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

