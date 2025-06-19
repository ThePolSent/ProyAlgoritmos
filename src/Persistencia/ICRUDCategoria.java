/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;
import Modelo.Categoria;
/**
 *
 * @author PolSent
 */
public class ICRUDCategoria {
    public ICRUDCategoria(){
        
    }
    
    //Crear Categoria
    public void crearCategoria(Categoria nuevaCategoria){
        String consulta ="{ CALL usp_CrearCategoria(?,?,?)}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setString(1, nuevaCategoria.getNombre());
            comando.setString(2, nuevaCategoria.getDescripcion());
            comando.execute();
            System.out.println("Cliente insertado correctamente.");
            
        }catch(SQLException e){
            System.out.println("Error al insertar cliente: "+e.getMessage());
        }
                
    }
        
   
    //Modificar Categoria
    public void modificarCategoria(Categoria categoria){
        String consulta = "{ CALL usp_ModificarCategoria(?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, categoria.getIdCategoria());
            comando.setString(2, categoria.getNombre());
            comando.setString(3, categoria.getDescripcion());
            comando.execute();
            System.out.println("Cliente modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
    }
    
    //Eliminar Categoria
    public void eliminarCategoria(int idCategoria){
        String consulta ="{ CALL usp_EliminarAdministrador(?) }";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, idCategoria);
            comando.execute();
        }catch (SQLException e){
            System.out.println("Error al eliminar cliente: "+e.getMessage());
        }
    }     
        
    //Listar Categoria
}
