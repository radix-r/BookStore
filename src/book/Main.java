/*
Name: Ross Wagner
Course: CNT 4714 – Fall 2019
Assignment title: Program 1 – Event-driven Programming
Date: Sunday September 22, 2019
*/

package book;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main  {

    private static GUI gui;
    // the item that we are on
    private static int itemIndex;
    // total number of items planed
    private static int itemCount = 0;
    private static BufferedReader br;
    private static  int discount = 0;

    public static void main(String[] args){
        gui=new GUI("Book Store");
        init();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    public static void confirm(){
        // info box pop ups

        // increment itemIndex

    }

    public static void exit(){
        gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
    }

    public static void finish(){

    }

    /**
     * sets initial state of gui and store
     * */
    private static void init(){
        updateItemNumber(0);
        itemIndex=0;
        discount = 0;
        itemCount = 0;
        try{
            br = new BufferedReader(new FileReader("../inventory.txt"));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void newOrder() {
    }

    public static void process() {
        // look at text fields at gui.BOOK_ID_INDEX and gui.QUANTITY_INDEX
        String id = gui.textFields[gui.BOOK_ID_INDEX].getText();
        int quantity = Integer.parseInt(gui.textFields[gui.QUANTITY_INDEX].getText());

        String info = null;
        // look up info from ../inventory.txt and put in gui.INFO_INDEX
        // iterate through inventory.txt, print line that contains id
        String line = null;

        double itemTotal = 0.0;
        try {
            while ((line = br.readLine()) != null) {
                String[] segments= line.split(",");
                if (segments[0].equals(id)) {
                    info = line;
                    itemCount+=quantity;
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
        }
        else{
            // display error message if id not found
            gui.display("Book ID " + id + " not in file");
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
    }


}
