package pro1.swingComponents;

import pro1.drawingModel.*;
import pro1.drawingModel.Rectangle;
import pro1.utils.ColorUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    DisplayPanel displayPanel;
    JComboBox<String> colorCombo;
    JCheckBox sizeCheck;

    public MainFrame() {
        this.setTitle("PRO1 Drawing");
        this.setVisible(true);
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.displayPanel = new DisplayPanel();
        this.add(this.displayPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(
                new Dimension(321, 0));
        this.add(leftPanel, BorderLayout.WEST);

        //levý panel
        String[] colors = {"#000000", "#123456", "#654321", "#12FF12"};
        colorCombo = new JComboBox<>(colors);

        leftPanel.add(new JLabel("Barva křížku"));
        leftPanel.add(colorCombo);

        sizeCheck = new JCheckBox("Větší křížek");
        sizeCheck.addActionListener(e -> updateScaleForExisting());
        leftPanel.add(sizeCheck);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> displayPanel.clearDrawables());
        leftPanel.add(btnReset);

        this.displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                createCross(e.getX(), e.getY());
            }
        });
    }

   /* private Drawable example(int x, int y) {
        var color = ColorUtils.randomColor();
        var d1 = new Ellipse(0, 0, 150, 250, color);
        var d2 = new Text(0, 0, color);
        var d3 = new Line(0, 50,170,170,3, color);
        return new Group(new Drawable[]{d1, d2, d3}, x, y, 40, 1, 1);
    }*/
   private void createCross(int x, int y) {
       // Data pro novou instanci získána aktuálním stavem prvki
       String color = (String) colorCombo.getSelectedItem();
       double scale = sizeCheck.isSelected() ? 2.0 : 1.0;
       // Vytvoření čár křížku kolem bodu 0,0 (20x20px)
       Line l1 = new Line(-10, -10, 10, 10, 3, color);
       Line l2 = new Line(-10, 10, 10, -10, 3, color);
       Group crossGroup = new Group(new Drawable[]{l1, l2}, x, y, 0, scale, scale);
       displayPanel.addDrawable(crossGroup);
   }
   private void updateScaleForExisting() {
        double newScale = sizeCheck.isSelected() ? 2.0 : 1.0;
        // Aktualizace všech stávajících bodů v seznamu
        for (Drawable d : displayPanel.getDrawables()) {
            if (d instanceof Group) {
                ((Group) d).setScale(newScale);
            }
        }
        displayPanel.repaint();
        }
}