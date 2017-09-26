package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;


public class ScanAreaCalc {
        final int RESOLUTION = 300;
        final float INCH = 2.54f;
        private float area = 0f;
        private int a4Count = 0;
        
        public float getArea(){
            return area;
        }
        
        public int getA4(){
            return a4Count;
        }

        public void calculateArea(String path) {
            try {
            File[] files = new File(path).listFiles();
            for (File file:files){
                if (!(((new MimetypesFileTypeMap().getContentType(file)).split("/")[0]).equals("image")))
                    continue;
                
                BufferedImage bimg = ImageIO.read(file);
                float width = (bimg.getWidth())/(RESOLUTION/INCH);
                float height = (bimg.getHeight())/(RESOLUTION/INCH);
                if (width > height){
                    float temp = width;
                    width = height;
                    height = temp;
                }
                System.out.println("w " + width + " h " + height);
                if ((width < 21.1)&&(height < 29.8)){
                    a4Count +=1;
                } else if ((width < 29.8)&&(height < 42.1)) {
                    a4Count += 2;
                } else {
                    area += (width*height/10000);
                }
            }
        } catch (IOException ex) {
        }
        
        }
        
}
