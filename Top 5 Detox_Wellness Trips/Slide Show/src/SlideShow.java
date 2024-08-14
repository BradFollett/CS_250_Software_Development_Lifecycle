import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class SlideShow extends JFrame {

    private JPanel slidePane;
    private JPanel textPane;
    private JPanel buttonPane;
    private CardLayout card;
    private CardLayout cardText;
    private JButton btnPrev;
    private JButton btnNext;
    private JLabel lblSlide;
    private JLabel lblTextArea;
    private Timer timer;
    private Timer fadeTimer;
    private float opacity = 1.0f;
    private boolean fadingOut = false;
    private int currentIndex = 1;

    public SlideShow() throws HeadlessException {
        initComponent();
        startTimer();
    }

    private void initComponent() {
        card = new CardLayout();
        cardText = new CardLayout();
        slidePane = new JPanel();
        textPane = new JPanel();
        textPane.setBackground(Color.pink);
        textPane.setBounds(5, 470, 790, 50);
        textPane.setVisible(true);
        buttonPane = new JPanel();
        btnPrev = new JButton();
        btnNext = new JButton();
        lblSlide = new JLabel();
        lblTextArea = new JLabel();

        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Top 5 Detox/Wellness Vacations SlideShow");
        getContentPane().setLayout(new BorderLayout(10, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        slidePane.setLayout(card);
        textPane.setLayout(cardText);

        for (int i = 1; i <= 5; i++) {
            lblSlide = new JLabel();
            lblTextArea = new JLabel();
            lblSlide.setText(getResizeIcon(i));
            lblTextArea.setText(getTextDescription(i));
            slidePane.add(lblSlide, "card" + i);
            textPane.add(lblTextArea, "cardText" + i);
        }

        getContentPane().add(slidePane, BorderLayout.CENTER);
        getContentPane().add(textPane, BorderLayout.SOUTH);

        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnPrev.setText("Previous");
        btnPrev.addActionListener(e -> goPrevious());
        btnPrev.setBackground(Color.pink);
        btnPrev.setForeground(Color.WHITE);
        buttonPane.add(btnPrev);

        btnNext.setText("Next");
        btnNext.addActionListener(e -> goNext());
        btnNext.setBackground(Color.pink);
        btnNext.setForeground(Color.WHITE);
        buttonPane.add(btnNext);

        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    goPrevious();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    goNext();
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    private void goPrevious() {
        currentIndex = (currentIndex - 1) < 1 ? 5 : (currentIndex - 1);
        fadeTransition(true);
    }

    private void goNext() {
        currentIndex = (currentIndex + 1) > 5 ? 1 : (currentIndex + 1);
        fadeTransition(false);
    }

    private void fadeTransition(boolean isPrevious) {
        fadingOut = true;
        fadeTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fadingOut) {
                    opacity -= 0.05f;
                    if (opacity <= 0.0f) {
                        fadingOut = false;
                        opacity = 0.0f;
                        card.show(slidePane, "card" + currentIndex);
                        cardText.show(textPane, "cardText" + currentIndex);
                        ((Timer) e.getSource()).stop();
                        Timer fadeInTimer = new Timer(30, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                opacity += 0.05f;
                                if (opacity >= 1.0f) {
                                    opacity = 1.0f;
                                    ((Timer) e.getSource()).stop();
                                }
                                lblSlide.setOpaque(true);
                                lblSlide.setBackground(new Color(0, 0, 0, (int) (opacity * 255)));
                                lblTextArea.setOpaque(true);
                                lblTextArea.setBackground(new Color(0, 0, 0, (int) (opacity * 255)));
                            }
                        });
                        fadeInTimer.start();
                    }
                } else {
                    lblSlide.setOpaque(true);
                    lblSlide.setBackground(new Color(0, 0, 0, (int) (opacity * 255)));
                    lblTextArea.setOpaque(true);
                    lblTextArea.setBackground(new Color(0, 0, 0, (int) (opacity * 255)));
                }
            }
        });
        fadeTimer.start();
    }

    /**
     * Method to get the images
     */
    private String getResizeIcon(int i) {
        String image = "";
        switch (i) {
            case 1:
                // Changed testimage to photo of Cala Luna Boutique Hotel
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Cala Luna Boutique Hotel.jpg") + "'</body></html>";
                break;
            case 2:
                // Changed testimage to photo of Spa Eastman
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Spa Eastman.jpg") + "'</body></html>";
                break;
            case 3:
                // Changed testimage to photo of Isrotel Dead Sea Resort & Spa
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Dead Sea.jpg") + "'</body></html>";
                break;
            case 4:
                // Changed testimage to photo of Hilton Sedona Resort At Bell Rock
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Hilton Sedona.jpg") + "'</body></html>";
                break;
            case 5:
                // Changed testimage to photo of Nobu Hotel Los Cabos
                image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Nobu Hotel Los Cabos.jpg") + "'</body></html>";
                break;
        }
        return image;
    }

    /**
     * Method to get the text values
     */
    private String getTextDescription(int i) {
        String text = "";
        switch (i) {
            case 1:
                // Updated first location to Costa Rica
                text = "<html><body><font size='5'>#1 Cala Luna Boutique Hotel (Tamarindo, Costa Rica)</font> <br>Escape to the tropical paradise of Tamarindo, Costa Rica, for an invigorating wellness retreat amidst lush surroundings. <br><font size='1'>Image provided by Expedia</font></body></html>";
                break;
            case 2:
                // Updated below to include 2nd location, added font size for title of location and a newline for the description
                text = "<html><body><font size='5'>#2 Spa Eastman (Quebec, Canada)</font> <br>Nordic Spas are well-known for their ability to alleviate stress, enhance circulation, and rid the body of toxins. <br><font size='1'>Image provided by Expedia</font></body></html>";
                break;
            case 3:
                // Updated below to include 3rd location, added font size for title of location and a newline for the description
                text = "<html><body><font size='5'>#3 Isrotel Dead Sea Resort & Spa (Israel)</font> <br>Experience the benefits of sea mud at the Esprit Sea, where you can experience one-of-a-kind body and beauty therapy treatments. <br><font size='1'>Image provided by Expedia</font></body></html>";
                break;
            case 4:
                // Updated below to include 4th location, added font size for title of location and a newline for the description
                text = "<html><body><font size='5'>#4 Hilton Sedona Resort At Bell Rock (Sedona, Arizona)</font> <br>Free your mind in the day-hike capital of America. See the beautiful red rock formations in the Arizona desert.<br><font size='1'>Image provided by Expedia</font></body></html>";
                break;
            case 5:
                // Updated below to include 5th location, added font size for title of location and a newline for the description
                text = "<html><body><font size='5'>#5 Nobu Hotel Los Cabos (Cabo San Lucas, Mexico)</font> <br>Discover serenity at the tranquil spa nestled within Nobu Hotel. <br><font size='1'>Image provided by Expedia</font></body></html>";
                break;
        }
        return text;
    }

    /**
     * Start Timer for automatic transitions
     */
    private void startTimer() {
        timer = new Timer(5000, e -> goNext());
        timer.start();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SlideShow ss = new SlideShow();
            ss.setVisible(true);
        });
    }
}