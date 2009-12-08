import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;


public class SVG_ScatterGraph
{

public static void main(String args[]){

     SVG_ScatterGraph.renderSVGScatterPlot(args);

}

public static void renderSVGScatterPlot(String[] dataToPlot)
{

      int i=0;
      int largestXValue=0;
      int largestYValue=0;
      int index=0;

      double XValue = 0;
      double YValue = 0;
      double YAxisScalingFactor=0;
      double XAxisScalingFactor=0;

   		
      // Determine what the highest Value of X and Y are
      do{ 
            // Get the value out of the array
            String value = dataToPlot[i];

            // The X and Y values are separated by a comma,seperate the values out by
            // finding the comma and getting the values either side of it.

            index = value.indexOf (',');
            String X_Val = value.substring(0,index);
            String Y_Val = value.substring(index+1);

            // Turn these into Numbers
            int X_Value = Integer.parseInt(X_Val);
            int Y_Value = Integer.parseInt(Y_Val);
            
            // See if either of these is the largest X or Y value.
            if (X_Value > largestXValue){ 
                largestXValue= X_Value; 
            }
    
            if (Y_Value > largestYValue){ 
                largestYValue= Y_Value; 
            }
 
            i++;

        }while (i<dataToPlot.length);

        // Work out a X axis scaling factor 
        XAxisScalingFactor = 1000/(double)largestXValue;
        YAxisScalingFactor = 1000/(double)largestYValue;
        System.out.println("XAxisScalingFactor is " + XAxisScalingFactor);
        System.out.println("YAxisScalingFactor is " + YAxisScalingFactor);

        // Largest values now done.
   try{

        // Get the SVG file ready for the drawing of the perfomance graph 
	File SVGOutputFile = new File("SVGScatterPlot.svg");
	FileWriter SVGout = new FileWriter(SVGOutputFile);

        // Get the SVG graph ready
        SVGout.write("<?xml version=\"1.0\"?>");
        SVGout.write("\n<svg width=\"1300\" height=\"1300\">");
        SVGout.write("\n<desc>Developer Works Dynamic Scatter Graph Scaling Example</desc>");

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

        // Draw 4 dotted lines as guides for the Y-Axis
        SVGout.write("\n<g style=\"fill:none; stroke:#B0B0B0; stroke-width:5; stroke-dasharray:2 4;text-anchor:end; font-size:30\">");
        
        SVGout.write("\n<path d=\"M 0 0 L 1000 0 Z\"/>");
        SVGout.write("\n<path d=\"M 0 500 L 1000 500 Z\"/>");
        SVGout.write("\n<path d=\"M 0 250 L 1000 250 Z\"/>");
        SVGout.write("\n<path d=\"M 0 750 L 1000 750 Z\"/>");

        // And the same for the X Axis
        SVGout.write("\n<path d=\"M 1000 0 L 1000 1000 Z\"/>");
        SVGout.write("\n<path d=\"M 500 0 L 500 1000 Z\"/>");
        SVGout.write("\n<path d=\"M 250 0 L 250 1000 Z\"/>");
        SVGout.write("\n<path d=\"M 750 0 L 750 1000 Z\"/>");
	SVGout.write("\n</g>");

        // Add text labels for the X and Y axis as guides. The go at 4 interval across each axis
        // Y-Axis 
        SVGout.write("\n<g style=\"fill:black; font-size:15; stroke:none\">");
        SVGout.write("\n <text x=\"-35\" y=\"10\" >" + largestYValue + "</text>");
        SVGout.write("\n <text x=\"-45\" y=\"500\" >" + (largestYValue*(0.5)) + "</text>");
        SVGout.write("\n <text x=\"-45\" y=\"750\" >" + (largestYValue*(0.25)) + "</text>");
        SVGout.write("\n <text x=\"-45\" y=\"250\" >" + (largestYValue*(0.75)) + "</text>");

        // X-Axis
        SVGout.write("\n <text x=\"990\" y=\"1030\" >" + largestXValue + "</text>");
        SVGout.write("\n <text x=\"730\" y=\"1030\" >" + (largestXValue*(0.75)) + "</text>");
        SVGout.write("\n <text x=\"490\" y=\"1030\" >" + (largestXValue*(0.5)) + "</text>");
        SVGout.write("\n <text x=\"230\" y=\"1030\" >" + (largestXValue*(0.25)) + "</text>");
        SVGout.write("\n</g>");

        // The Axis and the guide lines are ready, 
        // now draw the data.
        SVGout.write("\n    <g style=\"fill:none; stroke:red; stroke-width:3\">");

        // Loop through the data
        for(i=0;i<dataToPlot.length;i++){
                 
                // Get the value out of the array.
                String value = dataToPlot[i];
                
                // The data is in the form <XValue),(Y-Value), 
                // so find the comma and get the values either side of it
                index = value.indexOf (',');
                String X_Pos = value.substring(0,index);
                String Y_Pos = value.substring(index+1);

                // Change them to numbers
                XValue = Integer.parseInt(X_Pos);
                YValue = Integer.parseInt(Y_Pos);

                // Calculate the points position by using the 
                // scaling factor calculated earlier
  	        XValue = XValue*XAxisScalingFactor;     
 	        YValue = YValue*YAxisScalingFactor;
	        YValue = 1000-YValue;

	        // Now have our point. As it's a scatter plot it 
                // would look nice with an X, so use the
                // point to draw a line from the top left to the 
                // bottom right, and from the top right to the bottom left
                SVGout.write("\n  <line x1=\""+ (int)(XValue-5) + 
                             "\" y1=\""+(int)(YValue+5)+
                             "\" x2=\""+(int)(XValue+5)+"\" y2=\""+(int)(YValue-5)+"\" />"); 
                SVGout.write("\n  <line x1=\""+ (int)(XValue+5) +
                              "\" y1=\""+(int)(YValue+5)+"\" x2=\""+(int)(XValue-5)+
                             "\" y2=\""+(int)(YValue-5)+"\" />"); 
               
          }

        SVGout.write("\n    </g>");
        	
        SVGout.write("\n<!-- End of Data -->");

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


