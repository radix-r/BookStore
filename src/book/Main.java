/*
Name: Ross Wagner
Course: CNT 4714 – Fall 2019
Assignment title: Program 1 – Event-driven Programming
Date: Sunday September 22, 2019
*/

package book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main  {

    private static GUI gui;
    // the item that we are on
    private static int itemIndex;
    // total number of items planed
    private static int itemCount = 0;
    private static int quantity = 0;

    private static BufferedReader br;
    private static  int discount = 0;
    private static String log="";
    private static double subtotal;
    private static double itemTotal;
    private static final double taxRate = 6;

    public static void main(String[] args){
        gui=new GUI("Book Store");

        init();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    public static void confirm(){
        itemCount = Integer.parseInt(gui.textFields[gui.ITEM_COUNT_INDEX].getText());
        // append to log
        log+=String.format("%d. %s\n",itemIndex+1,gui.textFields[gui.INFO_INDEX].getText());

        // info box pop ups
        gui.display(String.format("Item %d accepted", itemIndex+1));
        // increment itemIndex
        itemIndex++;

        subtotal =+ itemTotal;
        gui.textFields[gui.SUBTOTAL_INDEX].setText(String.format("$%.2f",subtotal));

        gui.buttons[gui.VIEW_BUTTON_INDEX].setEnabled(true);
        gui.buttons[gui.CONFIRM_BUTTON_INDEX].setEnabled(false);

        gui.textFields[gui.ITEM_COUNT_INDEX].setEnabled(false);
        gui.textFields[gui.ITEM_COUNT_INDEX].setBackground(Color.gray);



        if(itemIndex >= itemCount){


            gui.buttons[gui.FINISH_BUTTON_INDEX].setEnabled(true);

            gui.buttons[gui.PROCESS_BUTTON_INDEX].setEnabled(false);

            // labels and fields
            //gui.labels[gui.BOOK_ID_INDEX].setText("");
            gui.textFields[gui.BOOK_ID_INDEX].setEnabled(false);
            gui.textFields[gui.BOOK_ID_INDEX].setBackground(Color.gray);

            //gui.labels[gui.QUANTITY_INDEX].setText("");
            gui.textFields[gui.QUANTITY_INDEX].setEnabled(false);
            gui.textFields[gui.QUANTITY_INDEX].setBackground(Color.gray);

            gui.labels[gui.SUBTOTAL_INDEX].setText(String.format(gui.labelStrings[gui.SUBTOTAL_INDEX],itemIndex+1));

        }else{
            updateItemNumber(itemIndex);
        }

        // clear text entry fields
        gui.textFields[gui.BOOK_ID_INDEX].setText("");
        gui.textFields[gui.QUANTITY_INDEX].setText("");
        gui.textFields[gui.QUANTITY_INDEX].setText("");

    }

    public static void exit(){
        gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
    }

    public static void finish(){

        DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String date1 = dateFormat1.format(new Date());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date2 = dateFormat2.format(new Date());

        String[] sp = log.split("\n");

        Path file = Paths.get("../transactions.txt");

        try{
            Files.writeString(file, "", StandardOpenOption.WRITE);
        }catch(java.nio.file.NoSuchFileException e){
            try{
                Files.writeString(file, "", StandardOpenOption.CREATE_NEW);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        try{
            for(int i=0; i<sp.length;i++){
                Files.writeString(file, String.format("%s, %s, %s\n",date1,sp[i],date2), StandardOpenOption.APPEND);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        String output= date2;
        output+="\n\n";
        output+=String.format("Number of items: %d\n\n",itemCount);
        output+=log+"\n\n";
        output+=String.format("Order subtotal: $%.2f\n\n",subtotal);
        output+=String.format("Tax rate: %.2f%%\n\n",taxRate);
        output+=String.format("Tax amount: $%.2f\n\n",subtotal*(taxRate/100.00));
        output+=String.format("Order total: $%.2f\n\n",subtotal*(1+taxRate/100.00));
        output+=String.format("Thank you for shopping with us!\n");
        gui.display(output);

        // reset everything
        init();

    }

    /**
     * sets initial state of gui and store
     * */
    private static void init(){
        quantity = 0;
        itemIndex=0;
        discount = 0;
        itemCount = 0;
        log = "";
        subtotal = 0.0;
        itemTotal = 0.0;


        // inital button state
        gui.buttons[gui.PROCESS_BUTTON_INDEX].setEnabled(true);
        gui.buttons[gui.CONFIRM_BUTTON_INDEX].setEnabled(false);
        gui.buttons[gui.VIEW_BUTTON_INDEX].setEnabled(false);
        gui.buttons[gui.FINISH_BUTTON_INDEX].setEnabled(false);

        // clear text fields
        for (int i = gui.NUM_FIELDS-1; i >=0; i--){
            gui.textFields[i].setText("");
        }

        gui.textFields[gui.ITEM_COUNT_INDEX].setBackground(Color.white);
        gui.textFields[gui.ITEM_COUNT_INDEX].setEnabled(true);

        gui.textFields[gui.BOOK_ID_INDEX].setBackground(Color.white);
        gui.textFields[gui.BOOK_ID_INDEX].setEnabled(true);

        gui.textFields[gui.QUANTITY_INDEX].setBackground(Color.white);
        gui.textFields[gui.QUANTITY_INDEX].setEnabled(true);

        //
        gui.textFields[gui.INFO_INDEX].setBackground(Color.gray);
        gui.textFields[gui.INFO_INDEX].setEnabled(false);
        gui.textFields[gui.SUBTOTAL_INDEX].setBackground(Color.gray);
        gui.textFields[gui.SUBTOTAL_INDEX].setEnabled(false);

        updateItemNumber(itemIndex);

    }

    public static void newOrder() {
        init();
    }

    public static void process() {

        // open file for reading
        try{
            br = new BufferedReader(new FileReader("../inventory.txt"));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        // look at text fields at gui.BOOK_ID_INDEX and gui.QUANTITY_INDEX
        String id = gui.textFields[gui.BOOK_ID_INDEX].getText();
        try{
            quantity = Integer.parseInt(gui.textFields[gui.QUANTITY_INDEX].getText());
        }catch(java.lang.NumberFormatException e){
            quantity=1;
        }

        String info = null;
        // look up info from ../inventory.txt and put in gui.INFO_INDEX
        // iterate through inventory.txt, get line that contains id
        String line = null;

        itemTotal = 0.0;
        try {
            while ((line = br.readLine()) != null) {
                String[] segments= line.split(",");
                if (segments[0].equals(id)) {
                    info = line;

                    // set discount
                    if(quantity <= 4){
                        discount=0;
                    } else if(quantity <= 9){
                        discount=10;
                    }else if(quantity <=14){
                        discount=15;
                    }else{
                        discount=20;
                    }

                    double unitCost = Double.parseDouble(segments[segments.length-1]);
                    itemTotal= (unitCost*quantity)*(1-(discount/100.0));
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        if(info!=null){

            gui.textFields[gui.INFO_INDEX].setText(String.format("%s %d %d%% $%.2f",info ,quantity , discount , itemTotal ));

            // if valid input unlock button at gui.CONFIRM_BUTTON_INDEX
            gui.buttons[gui.CONFIRM_BUTTON_INDEX].setEnabled(true);
            // gui.buttons[gui.PROCESS_BUTTON_INDEX].setEnabled(false);
        }
        else{
            // display error message if id not found
            gui.display("Book ID " + id + " not in file");
        }


        try{
            br.close();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void updateItemNumber(int itemNum){

        for(int i = gui.NUM_FIELDS-1; i >= 0; i--){
            if(i==gui.SUBTOTAL_INDEX){
                gui.labels[i].setText(String.format(gui.labelStrings[i],itemNum));
            }
            else{
                gui.labels[i].setText(String.format(gui.labelStrings[i],itemNum+1));
            }

        }

        for(int i = gui.NUM_BUTTONS-1; i >= 0; i--){

            gui.buttons[i].setText(String.format(gui.buttonStings[i],itemNum+1));

        }
    }

    public static void view() {
        gui.display(log);
    }


}
