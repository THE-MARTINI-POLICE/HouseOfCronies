package gui;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Gui {
    private JFrame frame;
    private JPanel panel;

    public Gui() {
        frame = new JFrame("House of Cronies");
        panel = new JPanel();
        panel.setLayout(null); // Use absolute positioning
        panel.setBackground(Color.WHITE); // Optional: to see the panel bounds
        
        frame.add(panel);
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
        panel.repaint();
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
        int cardWidth = 150;
        int cardHeight = 225;
        int spacing = 20; // Space between cards
        
        // Calculate total width needed for 5 cards
        int totalWidth = (5 * cardWidth) + (4 * spacing);
        
        // Center the cards horizontally and vertically
        int startX = (frame.getWidth() - totalWidth) / 2;
        int startY = (frame.getHeight() - cardHeight) / 2;
        
        int currentX = startX;
        int cardCount = 0;
        
        for (JLabel label : labels) {
            if (cardCount >= 5) break; // Only display 5 cards
            
            label.setBounds(currentX, startY, cardWidth, cardHeight);
            currentX += cardWidth + spacing;
            panel.add(label);
            cardCount++;
        }
    }
    
    public void scaleIcons(Set<JLabel> labels) {
        int cardWidth = 150;
        int cardHeight = 225;
        
        for (JLabel label : labels) {
            ImageIcon icon = (ImageIcon) label.getIcon();
            if (icon != null) {
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImage));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
            gui.toetsgui();
        });
    }
}
