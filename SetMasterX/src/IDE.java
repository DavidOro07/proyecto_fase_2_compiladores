import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDE extends JFrame {

    private JTextPane editor;
    private JTextArea consola;
    private JTextArea panelInfo;
    private JScrollPane infoScroll;

    public IDE() {

        // =====================================
        // CONFIGURACIÓN VENTANA
        // =====================================

        setTitle("SetMaster X Compiler");
        setSize(1100, 750);
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // =====================================
        // EDITOR
        // =====================================

        editor = new JTextPane();

        editor.setFont(new Font("Consolas", Font.PLAIN, 16));

        editor.setBackground(new Color(30, 30, 30));
        editor.setForeground(Color.WHITE);
        editor.setCaretColor(Color.WHITE);

        editor.putClientProperty(
                JTextPane.HONOR_DISPLAY_PROPERTIES,
                true
        );

        JScrollPane editorScroll =
                new JScrollPane(editor);

        editorScroll.setRowHeaderView(
                new LineNumber(editor)
        );

        editorScroll.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        "Editor"
                )
        );

        // =====================================
        // CONSOLA
        // =====================================

        consola = new JTextArea();

        consola.setEditable(false);

        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.GREEN);

        consola.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane consolaScroll =
                new JScrollPane(consola);

        consolaScroll.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        "Consola"
                )
        );

        panelInfo = new JTextArea();

        panelInfo.setEditable(false);

        panelInfo.setBackground(new Color(25,25,25));

        panelInfo.setForeground(Color.WHITE);

        panelInfo.setFont(
                new Font("Consolas", Font.PLAIN, 14)
        );

        infoScroll =
                new JScrollPane(panelInfo);

        infoScroll.setPreferredSize(
                new Dimension(250,0)
        );

        // =====================================
        // BOTONES
        // =====================================

        JButton btnCompilar =
                new JButton("▶ Compilar");

        btnCompilar.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JButton btnLimpiar =
                new JButton("🧹 Limpiar");

        btnLimpiar.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        JButton btnEjemplo =
                new JButton("📄 Cargar Ejemplo");

        btnEjemplo.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        // =====================================
        // EVENTOS BOTONES
        // =====================================

        btnCompilar.addActionListener(e -> compilar());

        btnLimpiar.addActionListener(e -> {

            editor.setText("");
            panelInfo.setText("");
            consola.setText("");
            SymbolTable.tabla.clear();
        });

        btnEjemplo.addActionListener(e -> cargarEjemplo());

        // =====================================
        // PANEL SUPERIOR
        // =====================================

        JPanel topPanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(btnCompilar);
        topPanel.add(btnLimpiar);
        topPanel.add(btnEjemplo);

        // =====================================
        // SPLIT PANEL
        // =====================================

        JSplitPane split =
                new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT,
                        editorScroll,
                        consolaScroll
                );

        split.setDividerLocation(450);

        // =====================================
        // AGREGAR COMPONENTES
        // =====================================

        add(topPanel, BorderLayout.NORTH);
        JSplitPane horizontal =
                new JSplitPane(
                        JSplitPane.HORIZONTAL_SPLIT,
                        split,
                        infoScroll
                );

        horizontal.setDividerLocation(800);

        add(horizontal, BorderLayout.CENTER);

        // =====================================
        // SYNTAX HIGHLIGHTING
        // =====================================

        Timer timer = new Timer(300, e -> colorear());

        timer.setRepeats(false);

        editor.getDocument().addDocumentListener(
                new DocumentListener() {

                    public void insertUpdate(DocumentEvent e) {
                        timer.restart();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        timer.restart();
                    }

                    public void changedUpdate(DocumentEvent e) {
                        timer.restart();
                    }
                }
        );

        setVisible(true);
    }

    // ====================================================
    // MÉTODO COMPILAR
    // ====================================================

    private void compilar() {

        try {

            consola.setText("");

            SymbolTable.tabla.clear();

            // =====================================
            // ARCHIVO TEMPORAL
            // =====================================

            File archivo =
                    new File("input_temp.txt");

            PrintWriter writer =
                    new PrintWriter(archivo);

            writer.print(editor.getText());

            writer.close();

            // =====================================
            // LEXER + PARSER
            // =====================================

            Lexer lexer =
                    new Lexer(new FileReader(archivo));

            parser p =
                    new parser(lexer);

            p.parse();

            consola.append("✅ Compilación exitosa\n\n");

            panelInfo.setText("");

            panelInfo.append("TOKENS / SÍMBOLOS\n\n");

            for(String key : SymbolTable.tabla.keySet()){

                panelInfo.append(
                        key +
                                " = " +
                                SymbolTable.tabla.get(key)
                                + "\n"
                );
            }

            // =====================================
            // TABLA DE SÍMBOLOS
            // =====================================

            consola.append(
                    "===== TABLA DE SÍMBOLOS =====\n"
            );

            for (String key : SymbolTable.tabla.keySet()) {

                consola.append(
                        key + " = " +
                                SymbolTable.tabla.get(key)
                                + "\n"
                );
            }

            consola.append("\n");

            // =====================================
            // URL VENN
            // =====================================


        } catch (Exception ex) {

            consola.append(
                    "❌ ERROR:\n\n" +
                            ex.getMessage()
            );
        }
    }

    // ====================================================
    // CARGAR EJEMPLO
    // ====================================================

    private void cargarEjemplo() {

        editor.setText("""
SET_START
INICIO

A = {1,2,3};
B = {2,3,4};

C = A ∪ B;
D = A ∩ B;
E = A - B;
F = A Δ B;

VENN(A ∪ B);

FIN
SET_END
""");
    }

    // ====================================================
    // SYNTAX HIGHLIGHTING
    // ====================================================

    private void colorear() {

        String text = editor.getText();

        StyledDocument doc =
                editor.getStyledDocument();

        // =====================================
        // ESTILO NORMAL
        // =====================================

        StyleContext sc =
                StyleContext.getDefaultStyleContext();

        AttributeSet blanco =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.WHITE
                );

        AttributeSet cyan =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.CYAN
                );

        AttributeSet naranja =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.ORANGE
                );

        AttributeSet rosa =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.PINK
                );

        AttributeSet verde =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.GREEN
                );

        AttributeSet gris =
                sc.addAttribute(
                        SimpleAttributeSet.EMPTY,
                        StyleConstants.Foreground,
                        Color.LIGHT_GRAY
                );

        // =====================================
        // LIMPIAR
        // =====================================

        doc.setCharacterAttributes(
                0,
                text.length(),
                blanco,
                true
        );

        // =====================================
        // IDs
        // =====================================

        Matcher ids =
                Pattern.compile(
                                "\\b[a-zA-Z][a-zA-Z0-9]*\\b"
                        )
                        .matcher(text);

        while (ids.find()) {

            doc.setCharacterAttributes(
                    ids.start(),
                    ids.end() - ids.start(),
                    gris,
                    true
            );
        }

        // =====================================
        // KEYWORDS
        // =====================================

        String[] keywords = {
                "SET_START",
                "SET_END",
                "INICIO",
                "FIN",
                "SI",
                "ENTONCES",
                "PARA_CADA",
                "EN",
                "VENN"
        };

        for (String keyword : keywords) {

            Matcher m =
                    Pattern.compile(
                                    "\\b" + keyword + "\\b"
                            )
                            .matcher(text);

            while (m.find()) {

                doc.setCharacterAttributes(
                        m.start(),
                        keyword.length(),
                        cyan,
                        true
                );
            }
        }

        // =====================================
        // NÚMEROS
        // =====================================

        Matcher numbers =
                Pattern.compile("\\b\\d+\\b")
                        .matcher(text);

        while (numbers.find()) {

            doc.setCharacterAttributes(
                    numbers.start(),
                    numbers.end() - numbers.start(),
                    naranja,
                    true
            );
        }

        // =====================================
        // OPERADORES
        // =====================================

        String[] ops = {
                "∪",
                "∩",
                "Δ",
                "-",
                "=",
                "⊂",
                "∈"
        };

        for (String op : ops) {

            Matcher matcher =
                    Pattern.compile(
                                    Pattern.quote(op)
                            )
                            .matcher(text);

            while (matcher.find()) {

                doc.setCharacterAttributes(
                        matcher.start(),
                        op.length(),
                        rosa,
                        true
                );
            }
        }

        // =====================================
        // COMENTARIOS
        // =====================================

        Matcher comments =
                Pattern.compile("//.*")
                        .matcher(text);

        while (comments.find()) {

            doc.setCharacterAttributes(
                    comments.start(),
                    comments.end() - comments.start(),
                    verde,
                    true
            );
        }
    }
}