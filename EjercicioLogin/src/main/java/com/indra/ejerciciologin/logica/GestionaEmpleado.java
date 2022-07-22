/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.ejerciciologin.logica;

import com.indra.ejerciciologin.modelo.Empleado;
import com.indra.ejerciciologin.modelo.IntentosFallidos;
import com.indra.ejerciciologin.persistencia.Conexion;
import java.sql.*;

/**
 *
 * @author jcapitan
 */
public class GestionaEmpleado {
    
   
    public static boolean login(Empleado empleado,int contador) throws IntentosFallidos {
  
        Connection con = Conexion.conexionBD();
        String query="select NAME,PASSWORD from empleados where NAME='"+empleado.getName()+"'"+" and PASSWORD='"+empleado.getPassword()+"'";
        try (Statement stmt=con.createStatement();
             ResultSet rs=stmt.executeQuery(query);){
 
            if (rs.next() == true) {
                return true;
            }else{
                 if (contador == 3) {
                     throw new IntentosFallidos("Numero de intentos agotados");
                 }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
       return false;
    }
}
