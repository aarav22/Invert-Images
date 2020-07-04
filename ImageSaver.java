/**
 * Make copies of all images selected within a directory (or folder).
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.File;

public class ImageSaver {  
        public ImageResource convertToNegative(ImageResource inImage) {
            ImageResource outImage = new ImageResource(inImage.getWidth(), 
            inImage.getHeight());
            for(Pixel pixel : outImage.pixels()) {
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
                pixel.setRed(255 - inPixel.getRed());
                pixel.setGreen(255 - inPixel.getGreen());
                pixel.setBlue(255 - inPixel.getBlue());
            }
            return outImage;
        }   
           
        public ImageResource convertToGray(ImageResource inImage) {
            ImageResource outImage = new ImageResource(inImage.getWidth(), 
            inImage.getHeight());
            for(Pixel pixel : outImage.pixels()) {
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
                int average = (inPixel.getRed() + inPixel.getGreen() 
                + inPixel.getBlue())/3;
                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);
            }
            return outImage;
        }
        //Doesn't Save.
        public void selectAndConvert() {
            DirectoryResource dir = new DirectoryResource();
            for(File currFile : dir.selectedFiles()) {
                ImageResource inImage = new ImageResource(currFile);
                ImageResource outImage = convertToGray(inImage);
                outImage.draw();
            }
        }
        //Saves
        public void doSave() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			String fname = image.getFileName();
			String newName = "copy-" + fname;
                        image = convertToGray(image);
			image.setFileName(newName);
			image.draw();
			image.save();
		}
	}
	
	public void testConvertToNegativeImage() {
            ImageResource output = new ImageResource();
            output = convertToNegative(output);
            output.draw();
        }
        
        public void testConvertToGrayImage() {
            ImageResource output = new ImageResource();
            output = convertToGray(output);
            output.draw();
        }
}
