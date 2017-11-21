/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.Snake;
import java.util.ArrayList;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author aboyb
 */
public class Hilos extends Thread{
	 ArrayList<ArrayList<Cuadro>> Cuadrado= new ArrayList<ArrayList<Cuadro>>();
         ArrayList<ArrayList<Cuadro>> Cuadrado2= new ArrayList<ArrayList<Cuadro>>();
         
	 Metodos CabezaSnake;
         
         public static int direcionSnake ;
	 int tamañoSnake=3;
         private int score=0;
	 long velocidad = 100;
         

	 Metodos posicionComida;
         ArrayList<Metodos> posiciones = new ArrayList<Metodos>();
         Metodos  obstaculo;
         
	 
         
         
	 //Constructor de Hilos 
         Hilos(Metodos positionDepart){
		//Obtener todos los hilos
		Cuadrado=Ventana.Grid;
		
		CabezaSnake=new Metodos(positionDepart.x,positionDepart.y);
		direcionSnake = 1;

		//!!! Puntero !!!!
		Metodos headPos = new Metodos(CabezaSnake.getX(),CabezaSnake.getY());
		posiciones.add(headPos);
		
		posicionComida= new Metodos(Ventana.height-1,Ventana.width-1);
		spawnFood(posicionComida);
             Metodos foodPosition1 = new Metodos(Ventana.height-1,Ventana.width-1);
		spawnFood(foodPosition1);
                
                

	 }
	 
	 //parte importante :
	 public void run() {
		 while(true){
			 moveInterne(direcionSnake);
			 checkCollision();
			 moveExterne();
			 deleteTail();
			 Pausa();
		 }
	 }
	 
	 //retraso entre cada movimiento de la serpiente
	 private void Pausa(){
		 try {
				sleep(velocidad);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
	 }
	 
	 //Comprobando si la serpiente se muerde o está comiendo
	 private void checkCollision() {
		 Metodos posCritique = posiciones.get(posiciones.size()-1);
		 for(int i = 0;i<=posiciones.size()-2;i++){
			 boolean mordida = posCritique.getX()==posiciones.get(i).getX() && posCritique.getY()==posiciones.get(i).getY();
			 
                         if(mordida){
                             JOptionPane.showMessageDialog(null, "Has muerto");
				stopTheGame();
			 }
                         
                         
		 }
		 
                 
		 boolean eatingFood = posCritique.getX()==posicionComida.y && posCritique.getY()==posicionComida.x;
		 if(eatingFood){
			 System.out.println("comi");  // controla puntos
                         score++;
                         if(score==3){
                             JOptionPane.showMessageDialog(null, "segundo nivel");
                             velocidad =50;
                         }
                         if(score==5){
                             JOptionPane.showMessageDialog(null, "tercer nivel");
                             velocidad=20;
                         }
                         
                         JOptionPane.showMessageDialog(null, score);
                    	 tamañoSnake=tamañoSnake+1;
			 	posicionComida = getValAleaNotInSnake();
                                
			 spawnFood(posicionComida);	
                         generarobstaculo(posicionComida);
                         
		 }
	 }
	 
	 //Detiene el juego
	 private void stopTheGame(){
		 System.out.println("choque! \n");
                 //this.dispose();
		 while(true){
			 Pausa();
		 }
	 }
         
         //muestra score
         
         
	 
	 //Coloca la comida en una posición y la muestra
	 private void spawnFood(Metodos foodPositionIn){
		 	Cuadrado.get(foodPositionIn.x).get(foodPositionIn.y).Encender(1);
	 }
	 private void generarobstaculo(Metodos foodPositionIn){
		 	Cuadrado.get(foodPositionIn.x-4).get(foodPositionIn.y-4).Encender(1);
	 }
	 //devolver una posición no ocupada por la serpiente
	 private Metodos getValAleaNotInSnake(){
		 Metodos p ;
		 int ranX= 0 + (int)(Math.random()*19); 
		 int ranY= 0 + (int)(Math.random()*19); 
		 p=new Metodos(ranX,ranY);
		 for(int i = 0;i<=posiciones.size()-1;i++){
			 if(p.getY()==posiciones.get(i).getX() && p.getX()==posiciones.get(i).getY()){
				 ranX= 0 + (int)(Math.random()*19); 
				 ranY= 0 + (int)(Math.random()*19); 
				 p=new Metodos(ranX,ranY);
				 i=0;
			 }
		 }
		 return p;
	 }
         
         
	 
	 //Mueve la cabeza de la serpiente y actualiza las posiciones en la lista de arrays
	 //1: derecha 2: izquierda 3: arriba 4: abajo 0: nada
	 private void moveInterne(int dir){
		 switch(dir){
		 	case 4:
				 CabezaSnake.ChangeData(CabezaSnake.x,(CabezaSnake.y+1)%20);
				 posiciones.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		break;
		 	case 3:
		 		if(CabezaSnake.y-1<0){
		 			 CabezaSnake.ChangeData(CabezaSnake.x,19);
		 		 }
		 		else{
				 CabezaSnake.ChangeData(CabezaSnake.x,Math.abs(CabezaSnake.y-1)%20);
		 		}
				 posiciones.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		break;
		 	case 2:
		 		 if(CabezaSnake.x-1<0){
		 			 CabezaSnake.ChangeData(19,CabezaSnake.y);
		 		 }
		 		 else{
		 			 CabezaSnake.ChangeData(Math.abs(CabezaSnake.x-1)%20,CabezaSnake.y);
		 		 } 
		 		posiciones.add(new Metodos(CabezaSnake.x,CabezaSnake.y));

		 		break;
		 	case 1:
				 CabezaSnake.ChangeData(Math.abs(CabezaSnake.x+1)%20,CabezaSnake.y);
				 posiciones.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		 break;
		 }
	 }
	 
	 //Actualiza los cuadrados que debe ser
	 private void moveExterne(){
		 for(Metodos t : posiciones){
			 int y = t.getX();
			 int x = t.getY();
			 Cuadrado.get(x).get(y).Encender(0);
			 
		 }
	 }
	 
	 // Actualiza la cola de la serpiente, eliminando los datos en las posiciones de arrays
         // y actualizar la visualización de las cosas que se eliminan
	 private void deleteTail(){
		 int cmpt = tamañoSnake;
		 for(int i = posiciones.size()-1;i>=0;i--){
			 if(cmpt==0){
				 Metodos t = posiciones.get(i);
				 Cuadrado.get(t.y).get(t.x).Encender(2);
			 }
			 else{
				 cmpt--;
			 }
		 }
		 cmpt = tamañoSnake;
		 for(int i = posiciones.size()-1;i>=0;i--){
			 if(cmpt==0){
				 posiciones.remove(i);
			 }
			 else{
				 cmpt--;
			 }
		 }
	 }

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
