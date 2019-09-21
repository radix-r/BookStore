package book;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLabel[] labels;
    private JTextField[] textFields;
    private JPanel[] panels;
    private JPanel jContentPane=null;
    private final int ITEM_INDEX = 0;
    private final int BOOK_ID_INDEX = 1;
    private final int QUANTITY_INDEX = 2;
    private final int INFO_INDEX = 3;
    private final int SUBTOTAL_INDEX = 4;
    private final int NUM_FIELDS = 5;


    public GUI(String title){



        super(title);
        initialize();
    }

    private void initialize(){
        jContentPane = new JPanel(null);

        JPanel panel = new JPanel();

        panel.setBounds(61, 11, 600, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        jContentPane.add(panel);

        labels = new JLabel[NUM_FIELDS];
        textFields = new JTextField[NUM_FIELDS];
        panels = new JPanel[NUM_FIELDS];
        labels[ITEM_INDEX] = new JLabel("Enter number of items in this order: ");
        labels[BOOK_ID_INDEX] = new JLabel("Enter Book ID for Item #: ");
        labels[QUANTITY_INDEX] = new JLabel("Enter Quantity for Item #: ");
        labels[INFO_INDEX] = new JLabel("Item # info: ");
        labels[SUBTOTAL_INDEX] = new JLabel("Order subtotal for item(s): ");

        for(int i=0; i < NUM_FIELDS; i++){
            panels[i] = new JPanel(new GridLayout());
            panels[i].add(labels[i]);
            textFields[i] = new JTextField(20);
            panels[i].add(textFields[i]);
            panel.add(panels[i]);
        }

        this.setSize(700,500);
        this.setContentPane(jContentPane);
        //this.setTitle("Book Store");
        //add(jContentPane);
    }


}
