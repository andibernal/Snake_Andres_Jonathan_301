/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.Snake;

/**
 *
 * @author aboyb
 */
import java.util.ArrayList;
import java.awt.Color;

public class Cuadro {
	//ArrayList que contendrá los colores
	ArrayList<Color> C =new ArrayList<Color>();
	int color; //2: snake , 1: comida, 0:vacio 
	Panel cuadrado;
        
	public Cuadro(int col){
		
		//Permite agregar el color a la lista de arreglos
		C.add(Color.darkGray);//0
		C.add(Color.BLUE);    //1
		C.add(Color.white);   //2
		color=col;
		cuadrado = new Panel(C.get(color));
	}
	public void Encender(int c){
		cuadrado.ChangeColor(C.get(c));
	}
}