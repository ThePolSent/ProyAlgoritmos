/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Administrador;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author PolSent
 */
public class ICRUDAdministrador {
    public ICRUDAdministrador(){
        
    }
    
    //Agregar cliente
    public void crearAdministrador(Administrador nuevoAdministrador){
        String consulta = "{ CALL usp_ModificarCliente(?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, nuevoAdministrador.getIdAdministrador());
            comando.setString(2, nuevoAdministrador.getNombre());
            comando.setString(3, nuevoAdministrador.getApellidoPaterno());
            comando.setString(4, nuevoAdministrador.getApellidoMaterno());
            comando.setString(5, nuevoAdministrador.getGenero());
            comando.setString(5, nuevoAdministrador.getFechaNac());
            comando.setString(5, nuevoAdministrador.getCorreo());
            comando.setString(6, nuevoAdministrador.getContrasena());
            comando.execute();
            System.out.println("Cliente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }
    
    
    //Modificar cliente
    public void modificarAdministrador(Administrador administrador){
        String consulta = "{ CALL usp_ModificarCliente(?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, administrador.getIdAdministrador());
            comando.setString(2, administrador.getNombre());
            comando.setString(3, administrador.getApellidoPaterno());
            comando.setString(4, administrador.getApellidoMaterno());
            comando.setString(5, administrador.getGenero());
            comando.setString(5, administrador.getFechaNac());
            comando.setString(5, administrador.getCorreo());
            comando.setString(6, administrador.getContrasena());
            comando.execute();
            System.out.println("Cliente modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
    }
    
    //Eliminar Cliente
    
    public void eliminarAdministrador(int idAdministrador){
        String consulta ="{ CALL usp_EliminarAdministrador(?) }";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, idAdministrador);
            comando.execute();
        }catch (SQLException e){
            System.out.println("Error al eliminar cliente: "+e.getMessage());
        }
    }
    
}
