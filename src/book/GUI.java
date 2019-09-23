/*
  Name: Ross Wagner
  Course: CNT 4714 – Fall 2019
  Assignment title: Program 1 – Event-driven Programming
  Date: Sunday September 22, 2019

  This class handles the front end appearance of the app.
  */

package book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    public JLabel[] labels;
    public String[] labelStrings;

    public JTextField[] textFields;
    private JPanel[] panels;
    public JButton[] buttons;
    public String[] buttonStings;
    private JPanel jContentPane=null;
    public final int ITEM_INDEX = 0;
    public final int BOOK_ID_INDEX = 1;
    public final int QUANTITY_INDEX = 2;
    public final int INFO_INDEX = 3;
    public final int SUBTOTAL_INDEX = 4;
    public final int NUM_FIELDS = 5;

    public final int NUM_BUTTONS = 6;
    public final int PROCESS_BUTTON_INDEX = 0;
    public final int CONFIRM_BUTTON_INDEX = 1;
    public final int VIEW_BUTTON_INDEX = 2;
    public final int FINISH_BUTTON_INDEX = 3;
    public final int NEW_BUTTON_INDEX = 4;
    public final int EXIT_BUTTON_INDEX = 5;






    public GUI(String title){
        super(title);
        initialize();
    }

    private void initialize(){

        // to do register listeners

        jContentPane = new JPanel(null);

        JPanel panel = new JPanel();
        panel.setBounds(61, 11, 700, 140);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBounds(61, 160, 700, 140);


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
        ButtonHandler handler = new ButtonHandler();
        buttonStings[PROCESS_BUTTON_INDEX] = "Process Item #%d";
        buttonStings[CONFIRM_BUTTON_INDEX] = "Confirm Item #%d";
        buttonStings[VIEW_BUTTON_INDEX] = "View Order";
        buttonStings[FINISH_BUTTON_INDEX] = "Finish Order";
        buttonStings[NEW_BUTTON_INDEX] = "New Order";
        buttonStings[EXIT_BUTTON_INDEX] = "Exit";

        for(int i=0; i < NUM_FIELDS; i++){
            labels[i] = new JLabel(labelStrings[i]);
            panels[i] = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            panels[i].add(labels[i]);
            textFields[i] = new JTextField(35);
            //textFields[i].setSize(20,140);
            panels[i].add(textFields[i]);
            panel.add(panels[i]);
        }

        // add the 6 buttons

        for(int i=0; i < NUM_BUTTONS; i++){
            buttons[i] = new JButton(String.format("%d",i));
            buttons[i].setText(buttonStings[i]);
            buttons[i].addActionListener(handler);
            buttonsPanel.add(buttons[i]);
        }

        // disable initially disabled buttons
        buttons[CONFIRM_BUTTON_INDEX].setEnabled(false);
        buttons[VIEW_BUTTON_INDEX].setEnabled(false);
        buttons[FINISH_BUTTON_INDEX].setEnabled(false);



        //jContentPane.add(buttonsPanel);
        this.setSize(800,300);
        this.setContentPane(jContentPane);
    }

    public void display(String message){
        JOptionPane.showMessageDialog( GUI.this, String.format(
                "%s", message ) );

    }

    // inner class for button event handling
    private class ButtonHandler implements ActionListener
    {
        // handle button event
        public void actionPerformed( ActionEvent event )
        {
           // JOptionPane.showMessageDialog( GUI.this, String.format(
           //         "You pressed: %s", event.getActionCommand() ) );


            if (event.getActionCommand().contains("Process")){
                Main.process();
            }else if(event.getActionCommand().contains("Exit")){
                Main.exit();
            } else if(event.getActionCommand().contains("Confirm")){
                Main.confirm();
            }else if(event.getActionCommand().contains("View")){
                Main.view();
            }else if(event.getActionCommand().contains("Finish")){
                Main.finish();
            } else if(event.getActionCommand().contains("New")){
                Main.newOrder();
            }

        } // end method actionPerformed
    } // end private inner class ButtonHandler


}
