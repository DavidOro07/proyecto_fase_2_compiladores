import javax.swing.*;

public class VennFrame extends JFrame {

    public VennFrame(String operacion) {

        setTitle("SetMaster X - Venn");

        setSize(650, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setContentPane(
                new VennPanel(operacion)
        );

        setVisible(true);
    }
}