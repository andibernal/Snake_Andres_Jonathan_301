/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.Snake;
import java.awt.Color;
import javax.swing.JPanel;
/**
 *
 * @author aboyb
 */
public class Panel extends JPanel{
    
    	private static final long serialVersionUID = 1L;

	public Panel(Color d){
		this.setBackground(d);
	}
	
	public void ChangeColor(Color d){
		this.setBackground(d);
		this.repaint();
	}
        
        /*public void Score(int d){
         d.setcolor(color.white);
         d.setfont new font("arial", font,PLAIN, 14);
         d.drawString("Puntajes: "+score, 780, 30);
        }*/
    
}
