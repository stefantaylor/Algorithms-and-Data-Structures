package imult;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import java.lang.Math;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
/*
 * Class GraphingData: Draws a graph and saves it to JPEG file
 */

public class GraphingData extends JPanel {
  private double[][] data = null; 
  private double c_ = 0;
  private double a_ = 0;
  private final int PAD = 60;
  private final int SPAD = 8;
  private final int n_ = 0;
  private final int school_ = 1;
  private final int ko_ = 2;
  public GraphingData(double c, double a, double[][] runTimes) {
    super();
    data = runTimes;
    c_ = c;
	a_ = a;  
  }
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    int w = getWidth();
    int h = getHeight();
    // Draw ordinate.
    g2.draw(new Line2D.Double(PAD, PAD/2, PAD, h-PAD));
    // Draw abcissa.
    g2.draw(new Line2D.Double(PAD, h-PAD, w-(PAD/2), h-PAD));
    // Draw labels.
    Font font = g2.getFont();
    FontRenderContext frc = g2.getFontRenderContext();
    LineMetrics lm = font.getLineMetrics("0", frc);
    float sh = lm.getAscent() + lm.getDescent();
    
    // Ordinate label.
    String s = "seconds";
    float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
    for(int i = 0; i < s.length(); i++) {
        String letter = String.valueOf(s.charAt(i));
        float sw = (float)font.getStringBounds(letter, frc).getWidth();
        float sx = ((PAD - sw)/2) - (SPAD * 2);
        g2.drawString(letter, sx, sy);
        sy += sh;
    }
    
    // Abcissa label.
    s = "digits";
    sy = h - PAD + (PAD - sh)/2 + lm.getAscent() + SPAD;
    float sw = (float)font.getStringBounds(s, frc).getWidth();
    float sx = (w - sw)/2;
    g2.drawString(s, sx, sy);
        
    int max = (int)getMaxRT();
    // The space between values along the abcissa.
    double xInc = (double)(w - 2*PAD)/(data.length);
    // Scale factor for ordinate/data values.
    double scale = (double)(h - 2*PAD)/max;
    // Ordinate data points
    for(int i = 0; i <= max; i+=10) {
      float val = (float)i / 1000f;
      s = String.valueOf(val);
      sw = (float)font.getStringBounds(s, frc).getWidth();
      sx = PAD - sw - SPAD;
      sy = h - PAD - (float)(scale*i) + lm.getAscent()/2;
      g2.drawString(s, sx, sy);
      if(i + 10 > max) {        
        val = (float)(i + 10) / 1000f;
        s = String.valueOf(val);
        sw = (float)font.getStringBounds(s, frc).getWidth();
        sy = h - PAD - (float)(scale*(i + 10)) + lm.getAscent()/2;
        g2.drawString(s, sx, sy);
      }
    }
    // Draw school lines.
    g2.setPaint(Color.green.darker());
    for(int i = 0; i < data.length-1; i++) {
      double x1 = PAD + i*xInc;
      double y1 = h - PAD - scale*data[i][school_];
      double x2 = PAD + (i+1)*xInc;
      double y2 = h - PAD - scale*data[i+1][school_];
      g2.draw(new Line2D.Double(x1, y1, x2, y2));
    }
    // Mark data points.
    int modVal = (int)(data.length / 10f);
    //sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
    sy = h - PAD + (PAD - sh) / 2;
    for(int i = 0; i < data.length; i++) {
      double x = PAD + i*xInc;
      double y = h - PAD - scale*data[i][school_];
      //g2.setPaint(Color.red);
      //g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
      // add data point labels
      //g2.drawString("abc", (float)x, (float)y); 
      // add x-axis labels
      g2.setPaint(Color.black);
      if(i%modVal==0)
        g2.drawString(String.valueOf((int)data[i][0]), (float)(x), sy);
      if(i == data.length - 2)
        g2.drawString(String.valueOf((int)data[i+1][0]), (float)(PAD + (i+1)*xInc), sy);
    }
    // Draw ko lines.
    g2.setPaint(Color.blue.darker());
    for(int i = 0; i < data.length-1; i++) {
      double x1 = PAD + i*xInc;
      double y1 = h - PAD - scale*data[i][ko_];
      double x2 = PAD + (i+1)*xInc;
      double y2 = h - PAD - scale*data[i+1][ko_];
      g2.draw(new Line2D.Double(x1, y1, x2, y2));
    }
    // Mark data points.
    /*g2.setPaint(Color.red);
    //sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
    sy = h - PAD + (PAD - sh) / 2;
    for(int i = 0; i < data.length; i++) {
      double x = PAD + i*xInc;
      double y = h - PAD - scale*data[i][ko_];
      g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
    }*/
    // Draw theoretical bounds lines.
    g2.setPaint(Color.orange);
    for(int i = 0; i < data.length-1; i++) {
      double x1 = PAD + i*xInc;
      double y1 = h - PAD - scale*(c_ * Math.pow(data[i][n_], BigIntMul.lgPwr_));
      double x2 = PAD + (i+1)*xInc;
      double y2 = h - PAD - scale*(c_ * Math.pow(data[i+1][n_], BigIntMul.lgPwr_));
      g2.draw(new Line2D.Double(x1, y1, x2, y2));
    }
	  // Draw average bounds lines.
	g2.setPaint(Color.magenta);
	for(int i = 0; i < data.length-1; i++) {
	  double x1 = PAD + i*xInc;
	  double y1 = h - PAD - scale*(a_ * Math.pow(data[i][n_], BigIntMul.lgPwr_));
	  double x2 = PAD + (i+1)*xInc;
	  double y2 = h - PAD - scale*(a_ * Math.pow(data[i+1][n_], BigIntMul.lgPwr_));
	  g2.draw(new Line2D.Double(x1, y1, x2, y2));
	}	  
	// Draw legend
    g2.setStroke(new BasicStroke(2));
    s = "school runtimes";
    sw = (float)font.getStringBounds(s, frc).getWidth();
    sh = (float)font.getStringBounds(s, frc).getHeight();
    float xplace = w-sw-75;
    g2.setPaint(Color.black.darker());
    g2.drawString(s, xplace, sh);
    g2.setPaint(Color.green.darker());
    g2.draw(new Line2D.Double(xplace+sw+5, (sh/2)+5, w-5, (sh/2)+5));
    s = "karatsuba runtimes";
    sw = (float)font.getStringBounds(s, frc).getWidth();
    sh = (float)font.getStringBounds(s, frc).getHeight();
    xplace = w-sw-75;
    g2.setPaint(Color.black.darker());
    g2.drawString(s, xplace, sh*2);
    g2.setPaint(Color.blue.darker());
    g2.draw(new Line2D.Double(xplace+sw+5, 2*((sh/2)+6), w-5, 2*((sh/2)+6)));
    s = "theor. bound";
    sw = (float)font.getStringBounds(s, frc).getWidth();
    sh = (float)font.getStringBounds(s, frc).getHeight();
    xplace = w-sw-75;
    g2.setPaint(Color.black.darker());
    g2.drawString(s, xplace, sh*3);
    g2.setPaint(Color.orange.darker());
    g2.draw(new Line2D.Double(xplace+sw+5, 3*((sh/2)+6), w-5, 3*((sh/2)+6)));
	s = "avg. bound";
	sw = (float)font.getStringBounds(s, frc).getWidth();
	sh = (float)font.getStringBounds(s, frc).getHeight();
	xplace = w-sw-75;
	g2.setPaint(Color.black.darker());
	g2.drawString(s, xplace, sh*4);
	g2.setPaint(Color.magenta.darker());
	g2.draw(new Line2D.Double(xplace+sw+5, 4*((sh/2)+6), w-5, 4*((sh/2)+6)));	  
  }
  private double getMaxRT() {
    double max = -Integer.MAX_VALUE;
    for(int i = 0; i < data.length; i++) {
      for(int j = 1; j < data[i].length; j++)
        if(data[i][j] > max) max = data[i][j];
    }
    return max;
  }
  protected void save() {
    try {
      File fFile = new File("test.jpeg");
      this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
      JFileChooser fc = new JFileChooser();
      // Start in current directory
      fc.setCurrentDirectory(new File("."));
      // Set to a default name for save.
      fc.setSelectedFile(fFile);
      // Open chooser dialog
      int result = fc.showSaveDialog(this);
      if (result == JFileChooser.CANCEL_OPTION) {
      } else if (result == JFileChooser.APPROVE_OPTION) {
          fFile = fc.getSelectedFile();
          if (fFile.exists()) {
              int response = JOptionPane.showConfirmDialog(null,
                      "Overwrite existing file?", "Confirm Overwrite",
                      JOptionPane.OK_CANCEL_OPTION,
                      JOptionPane.QUESTION_MESSAGE);
              if (response == JOptionPane.CANCEL_OPTION) {
              }
          }
          // save buffered image
          Image img = createImage(getWidth(), getHeight());
          Graphics g = img.getGraphics();
          paint(g);
          ImageIO.write(toBufferedImage(img), "jpeg", fFile);
          JOptionPane.showMessageDialog(null, "Image saved to " + fFile.toString());
          g.dispose();
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    } 
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  private BufferedImage toBufferedImage(Image image) {
  // This method returns a buffered image with the contents of an image
    if (image instanceof BufferedImage) {
        return (BufferedImage) image;
    }
    // This code ensures that all the pixels in the image are loaded
    image = new ImageIcon(image).getImage();
    // Determine if the image has transparent pixels; for this method's
    // implementation, see e661 Determining If an Image Has Transparent Pixels
    boolean hasAlpha = hasAlpha(image);
    // Create a buffered image with a format that's compatible with the screen
    BufferedImage bimage = null;
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
        // Determine the type of transparency of the new buffered image
        int transparency = Transparency.OPAQUE;
        if (hasAlpha) {
            transparency = Transparency.BITMASK;
        }
        // Create the buffered image
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gs.getDefaultConfiguration();
        bimage = gc.createCompatibleImage(
                image.getWidth(null), image.getHeight(null), transparency);
    } catch (HeadlessException e) {
    // The system does not have a screen
    }
    if (bimage == null) {
        // Create a buffered image using the default color model
        int type = BufferedImage.TYPE_INT_RGB;
        if (hasAlpha) {
            type = BufferedImage.TYPE_INT_ARGB;
        }
        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
    }
    // Copy image to buffered image
    Graphics g = bimage.createGraphics();
    // Paint the image onto the buffered image
    g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
    g.dispose();
    return bimage;
  }
  // This method returns true if the specified image has transparent pixels
  public static boolean hasAlpha(Image image) {
    // If buffered image, the color model is readily available
    if (image instanceof BufferedImage) {
        BufferedImage bimage = (BufferedImage) image;
        return bimage.getColorModel().hasAlpha();
    }
    // Use a pixel grabber to retrieve the image's color model;
    // grabbing a single pixel is usually sufficient
    PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
    try {
        pg.grabPixels();
    } catch (InterruptedException e) {}

    // Get the image's color model
    ColorModel cm = pg.getColorModel();
    return cm.hasAlpha();
  }
  public static void main(String[] args) {

  }
}


