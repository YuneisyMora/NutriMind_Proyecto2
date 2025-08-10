/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ac.cr.uned.nutrimind;

import ac.cr.uned.nutrimind.resources.DatabaseManager;
import ac.cr.uned.nutrimind.vistas.Login_vista;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * 
 * 
 *
 */
public class NutriMind {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            
            Login_vista loginWindow = new Login_vista();
            loginWindow.setLocationRelativeTo(null);
            loginWindow.setResizable(false);
            loginWindow.setVisible(true);
        });
        
        try{
            for (String u : DatabaseManager.obtenerUsuarios()) {
                System.out.println("Usuario: " + u);
            }
        }catch (Exception ex){
             ex.printStackTrace();
        }
        
        
    }
    
}
