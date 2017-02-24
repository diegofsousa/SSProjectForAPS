/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class Conexao {
     public static Connection getConnection() {
        String url = "jdbc:mysql://localhost/serralheria_sousa";
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "961100");
            System.out.println("Deu certo!");
        }catch(ClassNotFoundException ex){
            System.out.println("Drive não encontrado");
        }catch(SQLException ex){
            System.out.println("Erro ao conectar");
        }
        return con;
     }
     public static void main(String[] args) {
         
        Conexao.getConnection();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



