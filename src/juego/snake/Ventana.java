/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.Snake;

import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author aboyb
 */
public class Ventana extends JFrame{
    
    private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<Cuadro>> Grid;
	public static int width = 20;
	public static int height = 20;
	public Ventana(){
		
		
		// Crea el arraylist que contendrá los hilos
		Grid = new ArrayList<ArrayList<Cuadro>>();
		ArrayList<Cuadro> data;
		
		// Crea hilos y sus datos y los agrega a la lista de arreglos
		for(int i=0;i<width;i++){
			data= new ArrayList<Cuadro>();
			for(int j=0;j<height;j++){
				Cuadro c;
                            c = new Cuadro(2);
				data.add(c);
			}
			Grid.add(data);
		}
		
                
		//Configurar el diseño del panel
		getContentPane().setLayout(new GridLayout(20,20,0,0));
		
		// Comience y pausa todos los hilos, luego agrega cada cuadrado de cada hilo al panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
			 getContentPane().add(Grid.get(i).get(j).cuadrado);
                         
			}
		}
		
		//posición inicial de la serpiente
		Metodos position = new Metodos(10,10);
		//pasando este valor al controlador
		Hilos c = new Hilos(position);
		//Comensamos el juego 
		c.start();

		// Vincula la ventana al presentador del teclado.
		this.addKeyListener((KeyListener) new Teclado());

		//Para hacer: manejar multijugadores. Lo anterior funciona, probarlo y ver qué pasa
		
		//Metodos position2 = new Metodos(13,13);
		//ControlleurThreads c2 = new ControlleurThreads(position2);
		//c2.start();
		
	}

    void Titulo(String snake) {
        throw new UnsupportedOperationException("Aún no es compatible."); // Para cambiar el cuerpo de los métodos generados, elija Herramientas | Plantillas.
    }

}
