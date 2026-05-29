import javax.swing.*;
import java.util.Set;

public class VennFrame extends JFrame {

    public VennFrame(
            String operacion,
            Set<Integer> resultado,
            Set<Integer> A,
            Set<Integer> B
    ){

        setTitle("SetMaster X - Venn");

        setSize(760, 560);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setContentPane(
                new VennPanel(
                        operacion,
                        resultado,
                        A,
                        B
                )
        );

        setVisible(true);
    }
}