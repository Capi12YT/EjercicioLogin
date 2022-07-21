/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.ejerciciologin.persistencia;

import java.sql.*;

/**
 *
 * @author jcapitan
 */
public class Conexion {
    
    public static Connection conexionBD(){
        
        Connection conx = null;
        
        String url="jdbc:mysql://localhost:3306/indra2022";
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    
       try 
       {
        
            conx=DriverManager.getConnection(url, "root", "root");
            if (conx != null) {
		System.out.println("Conexión establecida");
             }
           
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
       return conx;
    }
    
    
}
