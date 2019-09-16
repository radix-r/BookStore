import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLabel label0;
    //private ;

    public GUI(){
        super("Ye old books");
        setLayout(new FlowLayout());
        label0 = new JLabel("Enter number of items in this order: ");
        add(label0);
    }


}
