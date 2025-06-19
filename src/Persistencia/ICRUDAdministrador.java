/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Administrador;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PolSent
 */
public class ICRUDAdministrador {
    public ICRUDAdministrador(){
        
    }
    
    //Agregar administrador
    public void crearAdministrador(Administrador nuevoAdministrador){
        String consulta = "{ CALL usp_AñadirAdministrador(?,?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setString(1, nuevoAdministrador.getNombre());
            comando.setString(2, nuevoAdministrador.getApellidoPaterno());
            comando.setString(3, nuevoAdministrador.getApellidoMaterno());
            comando.setString(4, nuevoAdministrador.getGenero());
            comando.setDate(5, nuevoAdministrador.getFechaNac());
            comando.setString(6, nuevoAdministrador.getCorreo());
            comando.setString(7, nuevoAdministrador.getContrasena());
            comando.execute();
            System.out.println("Cliente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar administrador: " + e.getMessage());
        }
    }
    
    
    //Modificar administrador
    public void modificarAdministrador(Administrador administrador){
        String consulta = "{ CALL usp_ModificarAdministrador(?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, administrador.getIdAdministrador());
            comando.setString(2, administrador.getNombre());
            comando.setString(3, administrador.getApellidoPaterno());
            comando.setString(4, administrador.getApellidoMaterno());
            comando.setString(5, administrador.getGenero());
            comando.setDate(5, administrador.getFechaNac());
            comando.setString(5, administrador.getCorreo());
            comando.setString(6, administrador.getContrasena());
            comando.execute();
            System.out.println("Cliente modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar adminsitrador: " + e.getMessage());
        }
    }
    
    //Eliminar administrador
    public void eliminarAdministrador(int idAdministrador){
        String consulta ="{ CALL usp_EliminarAdministrador(?) }";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, idAdministrador);
            comando.execute();
        }catch (SQLException e){
            System.out.println("Error al eliminar administrador: "+e.getMessage());
        }
    }
    
    
    //Listar Administrador
    public List<Administrador> listaAdministradores(){
        List<Administrador> lista = new ArrayList<>();
        String consulta = "{CALL usp_listarCliente()}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            ResultSet rs = comando.executeQuery();
            while(rs.next()){
                Administrador administrador = new Administrador();
                administrador.setIdAdministrador(rs.getInt("idAdministrador"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setApellidoPaterno(rs.getString("apellidoPaterno"));
                administrador.setApellidoMaterno(rs.getString("apellidoMaterno"));
                administrador.setGenero(rs.getString("genero"));
                administrador.setFechaNac(rs.getDate("FechaNac"));
                administrador.setCorreo(rs.getString("correo"));
                administrador.setContrasena(rs.getString("contrasena"));
                lista.add(administrador);
            }
        }catch(SQLException e){
            System.out.println("Error al listar administradores: "+e.getMessage());
        }
        return lista;
    }
}
