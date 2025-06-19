/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Cliente;
import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
/**
 *
 * @author PolSent
 */
public class ICRUDCliente {
    public ICRUDCliente(){
        
    }
    
    //Agregar cliente
    public void crearCliente(Cliente nuevoCliente){
        String consulta ="{ CALL usp_CrearCliente(?,?,?,?)}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setString(1, nuevoCliente.getNombre());
            comando.setString(2, nuevoCliente.getApellidoPaterno());
            comando.setString(3, nuevoCliente.getApellidoMaterno());
            comando.setString(4, nuevoCliente.getGenero());
            comando.execute();
            System.out.println("Cliente insertado correctamente.");
            
        }catch(SQLException e){
            System.out.println("Error al insertar cliente: "+e.getMessage());
        }
                
    }
    
    
    //Modificar cliente
    public void modificarCliente(Cliente cliente){
        String consulta = "{ CALL usp_ModificarCliente(?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, cliente.getIdCliente());
            comando.setString(2, cliente.getNombre());
            comando.setString(3, cliente.getApellidoPaterno());
            comando.setString(4, cliente.getApellidoMaterno());
            comando.setString(5, cliente.getGenero());
            comando.execute();
            System.out.println("Cliente modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }
    }
    
    //Eliminar Cliente
    public void eliminarCliente(int idCliente) {
        String consulta = "{ CALL usp_EliminarCliente(?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, idCliente);
            comando.execute();
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
    
    //Listar Cliente
    public List<Cliente> listarClientes(){
        List<Cliente> lista = new ArrayList<>();
        String consulta = "{CALL usp_listarCliente()}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            ResultSet rs = comando.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellidoPaterno(rs.getString("apellidoPaterno"));
                cliente.setApellidoMaterno(rs.getString("apellidoMaterno"));
                cliente.setGenero(rs.getString("genero"));
                lista.add(cliente);
            }
        }catch(SQLException e){
            System.out.println("Error al listar clientes: "+e.getMessage());
        }
        return lista;
    }
    
}
