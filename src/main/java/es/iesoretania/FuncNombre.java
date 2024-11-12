package es.iesoretania;

import java.sql.*;

public class FuncNombre{
    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection
                ("jdbc:mysql://localhost/practica", "root", "practica");

            //Recuperamos el parámetro del main
            String dep = args[0];
            
            //Preparamos la llamada
            String sql = "{ ? = call nombre_dep(?) }";

            //Preparamos la llamada
            CallableStatement llamada = conexion.prepareCall(sql);

            //Registramos el parámetro resultado
            //valor devuelto
            llamada.registerOutParameter(1, Types.VARCHAR);
            
            //parámetro de entrada
            llamada.setInt(2, Integer.parseInt(dep));

            //Ejecutamos el procedimiento
            llamada.executeUpdate();
            System.out.printf("Nombre Dep: %s %n", 
                        llamada.getString(1));           
            llamada.close();
            conexion.close();
        } 
        //catch (ClassNotFoundException cn) { cn.printStackTrace();}
        catch(SQLException e){e.printStackTrace();}  
    }
}