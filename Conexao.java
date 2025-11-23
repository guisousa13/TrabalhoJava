/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 *
 * @author alunocmc
 */
public class Conexao {
    
    public Connection getConexao(){
    
    
        try{
        
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
            Statement stmt = conn.createStatement();
            return conn;
                    
        }catch(Exception e){
            System.out.println("Erro ao conectar "+ e.getMessage());
            return null;
        }
    
    }
    
}
