/**
 * Classe principale pour lancer le jeu
 * @author Allan
 * @version 1.0
 */
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		GestionDuJeu gestion=new GestionDuJeu();

		while(true){
			gestion.tourDuJoueur();
		}
	}
}
