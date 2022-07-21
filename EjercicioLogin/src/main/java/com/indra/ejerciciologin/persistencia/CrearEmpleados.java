/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.indra.ejerciciologin.persistencia;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
/**
 *
 * @author jcapitan
 */
public class CrearEmpleados {
    public static void main(String[] args) {
     String url="jdbc:mysql://localhost:3306/indra2022";
     
        String addTable="create table empleados "+
                "(ID int,NAME varchar(32), " +
                "PASSWORD varchar(64),"
                + "PRIMARY KEY(ID))";
       
        String dropTable="drop table empleados";
        
        String sqlEmpleado="insert into empleados (ID,NAME,PASSWORD) values (?,?,?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
                
        try (Connection con=DriverManager.getConnection(url, "root", "root");
             Statement stmt=con.createStatement();
             PreparedStatement ps=con.prepareStatement(sqlEmpleado);
             ){
            
            stmt.execute(dropTable);
            stmt.executeUpdate(addTable);
            ps.setInt(1, 1);
            ps.setString(2, "jesus");
            ps.setString(3, CrearEmpleados.getSHA256("jesus"));
            ps.executeUpdate();
            ps.setInt(1, 2);
            ps.setString(2, "beatriz");
            ps.setString(3, CrearEmpleados.getSHA256("beatriz"));
           
            int i = ps.executeUpdate();
             if (i>0) {
                 System.out.println("alta exitosa");
             }else{
                 System.out.println("no se puede realizar alta");
                 }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }     
    }
    
    	public static String getSHA256(String input){

		String toReturn = null;
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");// sha: secure hash algorithm
		    digest.reset();
		    digest.update(input.getBytes("utf8"));
		    toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return toReturn;
	    }
    
}
