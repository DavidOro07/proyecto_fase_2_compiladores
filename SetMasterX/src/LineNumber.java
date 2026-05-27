import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.*;

public class LineNumber extends JPanel
        implements CaretListener, DocumentListener {

    private final JTextPane textPane;

    public LineNumber(JTextPane textPane) {

        this.textPane = textPane;

        textPane.getDocument().addDocumentListener(this);

        textPane.addCaretListener(this);

        setPreferredSize(new Dimension(40, Integer.MAX_VALUE));

        setBackground(new Color(45,45,45));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.GRAY);

        int lineHeight =
                textPane.getFontMetrics(
                        textPane.getFont()
                ).getHeight();

        int lines =
                textPane.getDocument()
                        .getDefaultRootElement()
                        .getElementCount();

        for(int i=0;i<lines;i++){

            int y =
                    (i+1)*lineHeight;

            g.drawString(
                    String.valueOf(i+1),
                    10,
                    y
            );
        }
    }

    public void caretUpdate(CaretEvent e) {
        repaint();
    }

    public void insertUpdate(DocumentEvent e) {
        repaint();
    }

    public void removeUpdate(DocumentEvent e) {
        repaint();
    }

    public void changedUpdate(DocumentEvent e) {
        repaint();
    }
}
