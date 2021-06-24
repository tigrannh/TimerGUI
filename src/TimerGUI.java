import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerGUI extends JFrame implements ActionListener {
    JButton jb1;
    JTextField jt1, jt2;
    JLabel lbl, lbl1;

    TimerGUI() {

        jt1 = new JTextField("0");
        jt1.setBounds(90, 50, 40, 40);
        add(jt1);

        jt2 = new JTextField("0");
        jt2.setBounds(150, 50, 40, 40);
        add(jt2);

        lbl = new JLabel("Time:  ");
        lbl.setBounds(90, 95, 150, 30);
        add(lbl);

        lbl1 = new JLabel(":");
        lbl1.setBounds(138, 50, 40, 40);
        add(lbl1);

        jb1 = new JButton("Start");
        jb1.setBounds(90, 140, 100, 30);
        add(jb1);
        jb1.addActionListener(this);

        setLayout(null);
        setSize(300, 300);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        int m = Integer.parseInt(jt1.getText()) + (Integer.parseInt(jt2.getText())) / 60;
        int s = Integer.parseInt(jt2.getText()) % 60;

        if (e.getSource().equals(jb1)) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    jb1.setEnabled(false);
                    int min = m;
                    int sec = s;
                    while ((min != 0 || sec != 0)) {
                        StringBuilder str = new StringBuilder("Time:  ")
                                .append(min < 10 ? "0" + min : min)
                                .append("m:")
                                .append(sec < 10 ? "0" + sec : sec)
                                .append("s");
                        lbl.setText(str.toString());

                        if (sec != 0) --sec;
                        else {
                            --min;
                            sec = 59;
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    lbl.setText("Time:  00m:00s");
                    jb1.setEnabled(true);
                }
            });

            t.start();

        }
    }

    public static void main(String args[]) {
        TimerGUI t = new TimerGUI();
    }

}
