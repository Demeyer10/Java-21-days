package java21days.lesson13;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class TextFrame extends JFrame{
    public TextFrame(String text, String fontName){
        // Calls the superclass JFrame with the title 
        super("Show Font");
        setSize(425, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TextFramePanel sf = new TextFramePanel(text, fontName);
        add(sf);
        setVisible(true);
    }

    public static void main(String[] args){
        if (args.length < 2){
            System.out.println("Usage: java TextFrame msg font");
            System.exit(-1);
        }
        TextFrame frame = new TextFrame(args[0], args[1]);
    }
}

class TextFramePanel extends JPanel{
    String text;
    String fontName;

    public TextFramePanel(String text, String fontName){
        super();
        this.text = text;
        this.fontName = fontName;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font(fontName, Font.BOLD, 18);
        FontMetrics metrics = getFontMetrics(font);
        g2.setFont(font);
        int x = (getSize().width - metrics.stringWidth(text)) / 2;
        int y = getSize().height / 2;
        g2.drawString(text, x, y);
    }
}
