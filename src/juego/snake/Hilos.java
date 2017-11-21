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
	 Metodos CabezaSnake;
	 int sizeSnake=3;
	 long speed = 50;
	 public static int directionSnake ;
         private int score=0;
	
         

	 ArrayList<Metodos> positions = new ArrayList<Metodos>();
	 Metodos foodPosition;
	 
	 //Constructor de Hilos 
         Hilos(Metodos positionDepart){
		//Obtener todos los hilos
		Cuadrado=Ventana.Grid;
		
		CabezaSnake=new Metodos(positionDepart.x,positionDepart.y);
		directionSnake = 1;

		//!!! Puntero !!!!
		Metodos headPos = new Metodos(CabezaSnake.getX(),CabezaSnake.getY());
		positions.add(headPos);
		
		foodPosition= new Metodos(Ventana.height-1,Ventana.width-1);
		spawnFood(foodPosition);

	 }
	 
	 //parte importante :
	 public void run() {
		 while(true){
			 moveInterne(directionSnake);
			 checkCollision();
			 moveExterne();
			 deleteTail();
			 Pausa();
		 }
	 }
	 
	 //retraso entre cada movimiento de la serpiente
	 private void Pausa(){
		 try {
				sleep(speed);
		 } catch (InterruptedException e) {
				e.printStackTrace();
		 }
	 }
	 
	 //Comprobando si la serpiente se muerde o está comiendo
	 private void checkCollision() {
		 Metodos posCritique = positions.get(positions.size()-1);
		 for(int i = 0;i<=positions.size()-2;i++){
			 boolean mordida = posCritique.getX()==positions.get(i).getX() && posCritique.getY()==positions.get(i).getY();
			 if(mordida){
                             JOptionPane.showMessageDialog(null, "Has muerto");
				stopTheGame();
			 }
		 }
		 
		 boolean eatingFood = posCritique.getX()==foodPosition.y && posCritique.getY()==foodPosition.x;
		 if(eatingFood){
			 System.out.println("comi");  // controla puntos
                         score++;
                         JOptionPane.showMessageDialog(null, score);
                    	 sizeSnake=sizeSnake+1;
			 	foodPosition = getValAleaNotInSnake();

			 spawnFood(foodPosition);	
		 }
	 }
	 
	 //Detiene el juego
	 private void stopTheGame(){
		 System.out.println("choque! \n");
                 
		 while(true){
			 Pausa();
		 }
	 }
         
         //muestra score
         
         
	 
	 //Coloca la comida en una posición y la muestra
	 private void spawnFood(Metodos foodPositionIn){
		 	Cuadrado.get(foodPositionIn.x).get(foodPositionIn.y).Encender(1);
	 }
	 
	 //devolver una posición no ocupada por la serpiente
	 private Metodos getValAleaNotInSnake(){
		 Metodos p ;
		 int ranX= 0 + (int)(Math.random()*19); 
		 int ranY= 0 + (int)(Math.random()*19); 
		 p=new Metodos(ranX,ranY);
		 for(int i = 0;i<=positions.size()-1;i++){
			 if(p.getY()==positions.get(i).getX() && p.getX()==positions.get(i).getY()){
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
				 positions.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		break;
		 	case 3:
		 		if(CabezaSnake.y-1<0){
		 			 CabezaSnake.ChangeData(CabezaSnake.x,19);
		 		 }
		 		else{
				 CabezaSnake.ChangeData(CabezaSnake.x,Math.abs(CabezaSnake.y-1)%20);
		 		}
				 positions.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		break;
		 	case 2:
		 		 if(CabezaSnake.x-1<0){
		 			 CabezaSnake.ChangeData(19,CabezaSnake.y);
		 		 }
		 		 else{
		 			 CabezaSnake.ChangeData(Math.abs(CabezaSnake.x-1)%20,CabezaSnake.y);
		 		 } 
		 		positions.add(new Metodos(CabezaSnake.x,CabezaSnake.y));

		 		break;
		 	case 1:
				 CabezaSnake.ChangeData(Math.abs(CabezaSnake.x+1)%20,CabezaSnake.y);
				 positions.add(new Metodos(CabezaSnake.x,CabezaSnake.y));
		 		 break;
		 }
	 }
	 
	 //Actualiza los cuadrados que debe ser
	 private void moveExterne(){
		 for(Metodos t : positions){
			 int y = t.getX();
			 int x = t.getY();
			 Cuadrado.get(x).get(y).Encender(0);
			 
		 }
	 }
	 
	 // Actualiza la cola de la serpiente, eliminando los datos en las posiciones de arrays
         // y actualizar la visualización de las cosas que se eliminan
	 private void deleteTail(){
		 int cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 Metodos t = positions.get(i);
				 Cuadrado.get(t.y).get(t.x).Encender(2);
			 }
			 else{
				 cmpt--;
			 }
		 }
		 cmpt = sizeSnake;
		 for(int i = positions.size()-1;i>=0;i--){
			 if(cmpt==0){
				 positions.remove(i);
			 }
			 else{
				 cmpt--;
			 }
		 }
	 }
}
