package book;

import javax.swing.*;

public class Main  {
    public static void main(String[] args){
        GUI gui = new GUI("Book Store");
        //gui.setContentPane(new BookStore().panelMain);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //gui.setSize(600,500);
        //gui.pack();
        gui.setVisible(true);

    }
}
