import javax.swing.*;
import java.awt.*;

public class VennPanel extends JPanel {

    private String operacion;

    private float alpha = 0.0f;

    public VennPanel(String operacion) {

        this.operacion = operacion;

        setBackground(new Color(35,35,35));

        // ============================
        // ANIMACIÓN
        // ============================

        Timer timer = new Timer(30, e -> {

            alpha += 0.03f;

            if(alpha >= 1.0f){

                alpha = 1.0f;

                ((Timer)e.getSource()).stop();
            }

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // =====================================
        // FADE
        // =====================================

        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        alpha
                )
        );

        // =====================================
        // TÍTULO
        // =====================================

        g2.setColor(Color.WHITE);

        g2.setFont(
                new Font("Consolas", Font.BOLD, 28)
        );

        g2.drawString(
                "Diagrama de Venn",
                160,
                50
        );

        // =====================================
        // OPERACIÓN
        // =====================================

        g2.setFont(
                new Font("Consolas", Font.PLAIN, 22)
        );

        g2.setColor(Color.CYAN);

        g2.drawString(
                operacion,
                220,
                90
        );

        // =====================================
        // BASE
        // =====================================

        int x1 = 140;
        int x2 = 260;
        int y = 140;
        int size = 220;

        // =====================================
        // DIFERENTES OPERACIONES
        // =====================================

        if(operacion.contains("∪")){

            // UNIÓN

            g2.setColor(
                    new Color(255,80,80,180)
            );

            g2.fillOval(x1,y,size,size);

            g2.setColor(
                    new Color(80,80,255,180)
            );

            g2.fillOval(x2,y,size,size);
        }

        else if(operacion.contains("∩")){

            // INTERSECCIÓN

            g2.setColor(
                    new Color(80,80,255,80)
            );

            g2.fillOval(x1,y,size,size);

            g2.fillOval(x2,y,size,size);

            g2.setColor(
                    new Color(180,0,255,220)
            );

            g2.fillOval(230,140,140,220);
        }

        else if(operacion.contains("-")){

            // DIFERENCIA

            g2.setColor(
                    new Color(255,80,80,220)
            );

            g2.fillOval(x1,y,size,size);

            g2.setColor(
                    new Color(80,80,255,70)
            );

            g2.fillOval(x2,y,size,size);
        }

        else if(operacion.contains("Δ")){

            // DIF SIMÉTRICA

            g2.setColor(
                    new Color(255,80,80,220)
            );

            g2.fillOval(x1,y,size,size);

            g2.setColor(
                    new Color(80,80,255,220)
            );

            g2.fillOval(x2,y,size,size);

            g2.setColor(
                    getBackground()
            );

            g2.fillOval(230,140,140,220);
        }

        // =====================================
        // BORDES
        // =====================================

        g2.setStroke(new BasicStroke(3));

        g2.setColor(Color.WHITE);

        g2.drawOval(x1,y,size,size);

        g2.drawOval(x2,y,size,size);

        // =====================================
        // LABELS
        // =====================================

        g2.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        g2.drawString("A", 230, 130);

        g2.drawString("B", 370, 130);
    }
}