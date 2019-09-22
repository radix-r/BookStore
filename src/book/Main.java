/*
Name: Ross Wagner
Course: CNT 4714 – Fall 2019
Assignment title: Program 1 – Event-driven Programming
Date: Sunday September 22, 2019
*/

package book;

import javax.swing.*;

public class Main  {

    private static GUI gui;

    public static void main(String[] args){
        gui=new GUI("Book Store");
        init();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }

    private int numTransactions;

    /**
     * sets initial state of gui and store
     * */
    private static void init(){
        updateItemNumber(0);
    }

    public static void updateItemNumber(int itemNum){
        //int len =
        for(int i = gui.NUM_FIELDS-1; i >= 0; i--){
            gui.labels[i].setText(String.format(gui.labelStrings[i],itemNum));
        }

        for(int i = gui.NUM_BUTTONS-1; i >= 0; i--){
            gui.buttons[i].setText(String.format(gui.buttonStings[i],itemNum));
        }
    }
}
