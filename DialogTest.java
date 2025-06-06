import src.com.marcusdevcode.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//public class MainGame extends JFrame {
public class DialogTest extends JPanel {
//    public static void main(String[] args) {
//        new Main();
//    }
    private BufferedImage image;
    private BufferedImage Windowblack;
    private int textX = 0, textY = 0;
    private Timer timer;
//    private AlphaComposite mask;
    private BufferedImage TextImage;
    private int textwidth;
    private int textheight;
    private Font font;
    private String text;
    public DialogTest() {
        text = "Hello, Image!";
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        font = new Font("Arial", Font.PLAIN, 19);
        textwidth = (int)(font.getStringBounds(text, frc).getWidth());
        textheight = (int)(font.getStringBounds(text, frc).getHeight());
        System.out.println(textwidth);
        System.out.println(textheight);


        try {
           image = ImageIO.read(new File("resources/images/Window04.png"));
//            Windowblack = ImageIO.read(new File("resources/images/Windowblack.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a simple animation to move the text
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textY += 2.55f;
                if (textY > getHeight()) {
                    textY = 0; // Reset to start
                }
                repaint();
            }
        });

    timer.start();
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image

        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
//        // Apply a mask using AlphaComposite
//        Graphics2D g2d = (Graphics2D) g;
////        g2d.fill(new Ellipse2D.Double(0, 20, 20, 20));
//        g2d.setComposite(mask);
        // Draw the moving text
        TextImage = new BufferedImage(textwidth,textheight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D TextI = TextImage.createGraphics();
        TextI.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        TextI.setColor(Color.RED);
        TextI.setFont(font);
        TextI.drawString(text, 0, textY);
        TextI.dispose();
        g.drawImage(TextImage, 0, 0, textwidth, textheight, null);
//        g2d.setComposite(AlphaComposite.SrcOver);
        // Reset to default composite
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Text with Mask");
        DialogTest panel = new DialogTest();
        // Set up the mask (this could be more complex)
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
