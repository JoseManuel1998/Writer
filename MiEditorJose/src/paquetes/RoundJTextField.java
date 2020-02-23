package paquetes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

public class RoundJTextField extends JTextField implements FocusListener{
	
	private final Font fontLost =new Font("Helvetica",Font.PLAIN,18);
	private final Font fontGained = new Font("Helvetica",Font.PLAIN,18);
	private final Color colorLost = Color.LIGHT_GRAY;
	private final Color colorGained = Color.BLACK;
	private String hint;
    private Shape shape;
    
    
    //Para busca en la barra inferior quería tener un hint, para que usuario supiese para que era esa barra, como java no tiene opciones ni para redondear los bordes
   // ni para el hint, creamos una clase que extiende de JTextField.
    
    public RoundJTextField(int size) {
    	
        super(size);
        addFocusListener(this);
        setOpaque(false); 
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
    
 
    public void setHint(String hint) {
        setForeground(colorLost);
        setFont(fontLost);
        setText(hint);
        this.hint = hint;
    }
 
    public String getHint() {
        return hint;
    }
 
    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(getHint())) {
            setText("");
            setFont(fontGained);
            setForeground(colorGained);
        } else {
            setForeground(colorGained);
            setFont(fontGained);
            setText(getText());
        }
    }
 
    @Override
    public void focusLost(FocusEvent e) {
        if (getText().length() <= 0) {
            setHint(getHint());
        } else {
            setForeground(colorGained);
            setFont(fontGained);
            setText(getText());
        }
    }

}