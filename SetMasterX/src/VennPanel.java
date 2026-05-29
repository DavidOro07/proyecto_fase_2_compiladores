import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.awt.geom.Area;
import java.awt.Shape;

public class VennPanel extends JPanel {

    private String operacion;

    private Set<Integer> resultado;

    private Set<Integer> A;

    private Set<Integer> B;

    private float alpha = 0.0f;

    private Set<Integer> soloASet;
    private Set<Integer> soloBSet;
    private Set<Integer> interseccionSet;

    public VennPanel(
            String operacion,
            Set<Integer> A,
            Set<Integer> B,
            Set<Integer> resultado
    ) {

        this.operacion = operacion;

        this.resultado = resultado;

        // =========================
        // CALCULAR REGIONES
        // =========================

        soloASet = new HashSet<>(A);

        soloASet.removeAll(B);

        soloBSet = new HashSet<>(B);

        soloBSet.removeAll(A);

        interseccionSet = new HashSet<>(A);

        interseccionSet.retainAll(B);

        setBackground(new Color(30,30,30));

        // =========================
        // ANIMACIÓN
        // =========================

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

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER,
                        alpha
                )
        );

        // =========================
        // FONDO
        // =========================

        setBackground(new Color(30,30,30));

        // =========================
        // TÍTULO
        // =========================

        g2.setColor(Color.WHITE);

        g2.setFont(
                new Font("Consolas", Font.BOLD, 30)
        );

        g2.drawString(
                "Diagrama de Venn",
                170,
                60
        );

        // =========================
        // OPERACIÓN
        // =========================

        g2.setFont(
                new Font("Consolas", Font.BOLD, 24)
        );

        g2.setColor(Color.CYAN);

        g2.drawString(
                operacion,
                260,
                110
        );

        // =========================
        // RESULTADO
        // =========================

        g2.setColor(Color.GREEN);

        g2.drawString(
                resultado.toString(),
                180,
                150
        );

        // =========================
        // POSICIONES
        // =========================

        int x1 = 140;
        int x2 = 330;
        int y = 200;
        int size = 230;

        Shape A =
                new java.awt.geom.Ellipse2D.Double(
                        x1, y, size, size
                );

        Shape B =
                new java.awt.geom.Ellipse2D.Double(
                        x2, y, size, size
                );

        Area areaA = new Area(A);
        Area areaB = new Area(B);

        Area inter = new Area(A);
        inter.intersect(new Area(B));

        Area soloA = new Area(A);
        soloA.subtract(new Area(B));

        Area soloB = new Area(B);
        soloB.subtract(new Area(A));

        // ==================================
        // UNIÓN
        // ==================================

        if(operacion.contains("∪")){

            g2.setColor(new Color(220,0,0));
            g2.fill(soloA);

            g2.setColor(new Color(120,0,255));
            g2.fill(inter);

            g2.setColor(new Color(0,0,220));
            g2.fill(soloB);
        }

        // ==================================
        // INTERSECCIÓN
        // ==================================

        else if(operacion.contains("∩")){

            g2.setColor(new Color(90,90,90));
            g2.fill(A);

            g2.fill(B);

            g2.setColor(new Color(180,0,255));
            g2.fill(inter);
        }

        // ==================================
        // DIFERENCIA
        // ==================================

        else if(operacion.contains("-")){

            g2.setColor(new Color(220,0,0));
            g2.fill(soloA);

            g2.setColor(new Color(50,50,120));
            g2.fill(soloB);

            g2.setColor(new Color(30,30,30));
            g2.fill(inter);
        }

        // ==================================
        // DIFERENCIA SIMÉTRICA
        // ==================================

        else if(operacion.contains("Δ")){

            g2.setColor(new Color(220,0,0));
            g2.fill(soloA);

            g2.setColor(new Color(0,0,220));
            g2.fill(soloB);

            g2.setColor(new Color(30,30,30));
            g2.fill(inter);
        }

        // =========================
        // BORDES
        // =========================

        g2.setStroke(new BasicStroke(4));

        g2.setColor(Color.WHITE);

        g2.draw(A);

        g2.draw(B);

        // =========================
        // LABELS
        // =========================

        g2.setFont(
                new Font("Arial", Font.BOLD, 34)
        );

        g2.drawString("A", 250, 190);

        g2.drawString("B", 430, 190);

        // =========================
        // TEXTOS INTERNOS
        // =========================

        g2.setFont(
                new Font("Consolas", Font.BOLD, 22)
        );

        g2.setColor(Color.WHITE);

        g2.drawString(
                soloASet.toString(),
                190,
                330
        );

        g2.setColor(Color.YELLOW);

        g2.drawString(
                interseccionSet.toString(),
                320,
                330
        );

        g2.setColor(Color.WHITE);

        g2.drawString(
                soloBSet.toString(),
                450,
                330
        );
    }
}