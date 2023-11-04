/**
 * Convert any number of images to a gray scale version by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
	//I started with the image I wanted (inImage)
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		//outImage is your answer
		return outImage;
	}

	public void selectAndConvert () {
		DirectoryResource dr=new DirectoryResource();
		for(File f: dr.selectedFiles())
		{
		    
		    ImageResource inImage=new ImageResource(f);
		    ImageResource outImage=makeGray(inImage);
		    outImage.draw();
		}
	}

	public void testGray() {
		ImageResource ir = new ImageResource();
		ir.draw();
		ImageResource gray = makeGray(ir);
		gray.draw();
	}
	public void doSave()
	{
	    DirectoryResource dr=new DirectoryResource();
	    for(File f:dr.selectedFiles())
	    {
	        ImageResource image=new ImageResource(f);
	        String old = image.getFileName();
	        String newname = "copy-"+old;
	        ImageResource gray=makeGray(image);
	        gray.setFileName(newname);
	        gray.draw();
	        gray.save();
	        /*image=makeGray(image);
	        image.setFileName(newname);
	        image.draw();
	        image.save();*/
	    }
	   }
	   public ImageResource imageinvert(ImageResource image)
	   {
	       ImageResource invert=new ImageResource(image.getWidth(),image.getHeight());
	       for(Pixel p:invert.pixels())
	       {
	           Pixel inpixel=image.getPixel(p.getX(),p.getY());
	           //int avg=(inpixel.getRed()+inpixel.getGreen()+inpixel.getBlue());
	           /*p.setRed(avg);
	           p.setGreen(avg);
	           p.setBlue(avg);*/
	           int red=255-inpixel.getRed();
	           int green=255-inpixel.getGreen();
	           int blue=255-inpixel.getBlue();
	           p.setRed(red);
	           p.setGreen(green);
	           p.setBlue(blue);
	       }
	       return invert;
	   }
	   public void testinvert()
	   {
	       ImageResource ir=new ImageResource();
	       ir.draw();
	       ImageResource out=imageinvert(ir);
	       out.draw();
	       String newname="invert-"+ir.getFileName();
	       out.setFileName(newname);
	       out.save();
	   }
}
