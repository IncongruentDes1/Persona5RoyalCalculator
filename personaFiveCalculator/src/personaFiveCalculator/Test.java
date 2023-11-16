package personaFiveCalculator;

import javax.swing.JFrame;
import javax.swing.JList;
import java.util.ArrayList;

public class Test {
	

    public void Test(ArrayList<String> values) {
        JFrame f = new JFrame("Test");
        String[] data = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        f.add(new JList(data));
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }


}
