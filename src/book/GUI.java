package book;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLabel[] labels;
    public String[] labelStrings;

    private JTextField[] textFields;
    private JPanel[] panels;
    private JButton[] buttons;
    public String[] buttonStings;
    private JPanel jContentPane=null;
    private final int ITEM_INDEX = 0;
    private final int BOOK_ID_INDEX = 1;
    private final int QUANTITY_INDEX = 2;
    private final int INFO_INDEX = 3;
    private final int SUBTOTAL_INDEX = 4;
    private final int NUM_FIELDS = 5;

    private final int NUM_BUTTONS = 6;
    private final int PROCESS_BUTTON_INDEX = 0;
    private final int CONFIRM_BUTTON_INDEX = 1;
    private final int VIEW_BUTTON_INDEX = 2;
    private final int FINISH_BUTTON_INDEX = 3;
    private final int NEW_BUTTON_INDEX = 4;
    private final int EXIT_BUTTON_INDEX = 5;






    public GUI(String title){
        super(title);
        initialize();
    }

    private void initialize(){
        jContentPane = new JPanel(null);

        JPanel panel = new JPanel();
        panel.setBounds(61, 11, 600, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBounds(61, 160, 600, 140);


        jContentPane.add(panel);
        jContentPane.add(buttonsPanel);
        labelStrings = new String[NUM_FIELDS];
        labels = new JLabel[NUM_FIELDS];
        textFields = new JTextField[NUM_FIELDS];
        panels = new JPanel[NUM_FIELDS];
        labelStrings[ITEM_INDEX] = "Enter number of items in this order: ";
        labelStrings[BOOK_ID_INDEX] = "Enter Book ID for Item #%d: ";
        labelStrings[QUANTITY_INDEX] = "Enter Quantity for Item #%d: ";
        labelStrings[INFO_INDEX] = "Item #%d info: ";
        labelStrings[SUBTOTAL_INDEX] = "Order subtotal for %d item(s): ";

        //JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttons = new JButton[NUM_BUTTONS];
        buttonStings = new String[NUM_BUTTONS];
        buttonStings[PROCESS_BUTTON_INDEX] = "Process Item #%d";
        buttonStings[CONFIRM_BUTTON_INDEX] = "Confirm Item #%d";
        buttonStings[VIEW_BUTTON_INDEX] = "View Order";
        buttonStings[FINISH_BUTTON_INDEX] = "Finish Order";
        buttonStings[NEW_BUTTON_INDEX] = "New Order";
        buttonStings[EXIT_BUTTON_INDEX] = "Exit";

        for(int i=0; i < NUM_FIELDS; i++){
            labels[i] = new JLabel(labelStrings[i]);
            panels[i] = new JPanel(new GridLayout());
            panels[i].add(labels[i]);
            textFields[i] = new JTextField(20);
            panels[i].add(textFields[i]);
            panel.add(panels[i]);
        }

        // add the 6 buttons

        for(int i=0; i < NUM_BUTTONS; i++){
            buttons[i] = new JButton(buttonStings[i]);
            buttonsPanel.add(buttons[i]);
        }



        //jContentPane.add(buttonsPanel);
        this.setSize(700,500);
        this.setContentPane(jContentPane);

    }

    public void update(){
        // refresh button and text labels

        // disable/enable things
    }
}
