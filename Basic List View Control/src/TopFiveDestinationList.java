import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Bora Bora - Bora Bora is a small South Pacific island northwest of Tahiti in Fench Polynesia. Photo Credit: The TerraMar Project", new ImageIcon(getClass().getResource("/resources/Bora Bora.jpg")));
        addDestinationNameAndPicture("2. Disney World - An entertainment resort complex in Bay Lake and Lake Buena Vista, Florida, United States, located near Orlando. Photo Credit: Katie Rommel-Esham", new ImageIcon(getClass().getResource("/resources/Disney World.jpg")));
        addDestinationNameAndPicture("3. Bahamas - An island country within the Lucayan Archipelago of the West Indies in the North Atlantic. Photo Credit: Gregor Julien Straube", new ImageIcon(getClass().getResource("/resources/bahamas.jpg")));
        addDestinationNameAndPicture("4. Greece - A country in southeastern Europe with thousands of isnalds throughout the Aegean and Ionian seas. Photo Credit: George E. Koronaios", new ImageIcon(getClass().getResource("/resources/greece.jpg")));
        addDestinationNameAndPicture("5. New Zealand - An island country in the southwestern Pacific Ocean. Photo Credit: Michal Klajban", new ImageIcon(getClass().getResource("/resources/New Zealand.jpg")));
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);

        // Set the background color
        list.setBackground(Color.lightGray);
        // Sets the selected background color
        list.setSelectionBackground(Color.CYAN);
        list.setCellRenderer(renderer);

        JLabel nameLabel = new JLabel("Developer: Brad Follett");
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(nameLabel, BorderLayout.NORTH);
    }

    private void addDestinationNameAndPicture(String text, ImageIcon imageIcon) {
    	// Scale the image to a desire width and height
    	int desiredWidth = 250; // Specofy the desired width
    	int desireHeight = 200; // Specify the desire height
    	Image originalImage = imageIcon.getImage();
    	Image scaledImage = originalImage.getScaledInstance(desiredWidth, desireHeight, Image.SCALE_SMOOTH);
    	
    	// Create a new ImageIcon with the scaled image
    	ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
    	
        TextAndIcon tai = new TextAndIcon(text, scaledImageIcon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
    	// The object from the combo box model MUST be a TextAndIcon. 	
    	TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}