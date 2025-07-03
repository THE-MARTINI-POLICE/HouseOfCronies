
package gui;
import javax.swing.*;
import java.util.*;
import java.awt.*;
public class Gui {
    private JFrame frame;
    private JPanel panel;

    public Gui() {
        frame = new JFrame("House of Cronies");
        
        

       
        
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void toetsgui() {
        String path ="/Users/diehanvanderwesthuizen/houseofcronies/resources/cards/";
        System.out.println("This is a test method.");
        
     
        
        Map<String, String> cardImages = new HashMap<>();
        cardImages.put("corruption 4", path + "corruption4.png");
        cardImages.put("corruption 7", path + "corruption7.png");
        cardImages.put("foreign ally 1", path + "foreignally1.png");
        cardImages.put("foreign ally 2", path + "foreignally2.png");
        cardImages.put("foreign ally 3", path + "foreignally3.png");

        Set<ImageIcon> iconSet = new HashSet<>();
        for (String imagePath : cardImages.values()) {
            ImageIcon icon3 = new ImageIcon(imagePath);
            iconSet.add(icon3);
        }
        Set<JLabel> labels = createLabels(iconSet);
        scaleIcons(labels);
        setDisplayCoordinates(labels);
    }

    public Set<JLabel> createLabels(Set<ImageIcon> icons){
        Set<JLabel> labels = new HashSet<>();
        for (ImageIcon icon : icons) {
            JLabel label = new JLabel(icon);
            labels.add(label);
        }
        return labels;

    }
    public void setDisplayCoordinates(Set<JLabel> labels) {
        int x = 0;
        int y = 0;
        for (JLabel label : labels) {
            label.setBounds(x, y, 200, 300); // Set the size of each label
            x += 100; // Move to the right for the next label
            if (x > frame.getWidth() - 250) { // If we reach the end of the frame width
                x += 100; // Reset x to start from the left again
                y += 1100; // Move down for the next row
            }
            frame.add(label);
        }
    }
    public void scaleIcons(Set<JLabel> labels) {
        for (JLabel label : labels) {
            ImageIcon icon = (ImageIcon) label.getIcon();
            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
        }
    }

    public static void main(String[] args) {


        Gui gui = new Gui();
        gui.toetsgui();
        gui.frame.setVisible(true);
    }
}
