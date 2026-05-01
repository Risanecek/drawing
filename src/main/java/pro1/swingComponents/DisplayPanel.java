package pro1.swingComponents;

import pro1.drawingModel.Drawable;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DisplayPanel extends JPanel {

    private List<Drawable> drawables = new LinkedList<>();

    public DisplayPanel(){
        this.setBackground(Color.WHITE);
    }

    public void addDrawable(Drawable drawable) {
        this.drawables.add(drawable);
        if (this.drawables.size() > 10) {
                this.drawables.remove(0);
        }
        this.repaint();
        }

        public void clearDrawables() {
            this.drawables.clear();
            this.repaint();
        }

        public List<Drawable> getDrawables() {
            return this.drawables;
        }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable d : this.drawables) {
            d.draw((Graphics2D) g);
        }
    }
}