/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.Snake;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author aboyb
 */
public class Teclado extends KeyAdapter{
    
 		public void keyPressed(KeyEvent e){
 		    switch(e.getKeyCode()){
		    	case 39:// ->Derecha
                               // si no es la dirección opuesta
		    		if(Hilos.directionSnake!=2) 
		    			Hilos.directionSnake=1;
		    		break;
		    	case 38:// ->parte superior
				if(Hilos.directionSnake!=4) 
					Hilos.directionSnake=3;
		    		break;
		    				
		    	case 37:// -> Izquierda 
				if(Hilos.directionSnake!=1)
					Hilos.directionSnake=2;
		    		break;
		    				
		    	case 40:// -> boton
				if(Hilos.directionSnake!=3)
					Hilos.directionSnake=4;
		    		break;
		    	
		    	default: 	break;
 		    }
 		}
 	
 }

