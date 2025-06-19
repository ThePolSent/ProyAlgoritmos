/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import java.sql.CallableStatement;
import java.sql.SQLException;
import Modelo.Empleado;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PolSent
 */
public class ICRUDEmpleado {
    public ICRUDEmpleado(){
        
    }
    
    //Añadir Empleado
    public void añadirEmpleado(Empleado nuevoEmpleado){
        String consulta = "{CALL usp_nuevoEmpleado(?,?,?,?)}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setString(1,nuevoEmpleado.getNombre());
            comando.setString(2,nuevoEmpleado.getApellidoPaterno());
            comando.setString(3,nuevoEmpleado.getApellidoMaterno());
            comando.setString(4,nuevoEmpleado.getGenero());
            comando.setDate(5,nuevoEmpleado.getFechaNac());
            comando.setDouble(6,nuevoEmpleado.getSalario());
            comando.setString(7, nuevoEmpleado.getTelefono());
            comando.setString(8, nuevoEmpleado.getCorreo());
            comando.setString(9, nuevoEmpleado.getContrasena());
            comando.execute();
            System.out.println("Empleado insertado Correctamente.");
        }catch(SQLException e){
            System.out.println("Error al crear nuevo Empleado: "+e.getMessage());
        }
    }
       
    //Modificar Empleado
    public void modificarEmpleado(Empleado empleado){
        String consulta = "{ CALL usp_ModificarEmpleado(?,?,?,?,?,?,?,?,?) }";
        try {
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
          comando.setString(1,empleado.getNombre());
            comando.setString(2,empleado.getApellidoPaterno());
            comando.setString(3,empleado.getApellidoMaterno());
            comando.setString(4,empleado.getGenero());
            comando.setDate(5,empleado.getFechaNac());
            comando.setDouble(6,empleado.getSalario());
            comando.setString(7, empleado.getTelefono());
            comando.setString(8, empleado.getCorreo());
            comando.setString(9, empleado.getContrasena());
            comando.execute();
            System.out.println("Empleado modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar Empleado: " + e.getMessage());
        }
    }
    
    //Eliminar empleado
    public void eliminarAdministrador(int idEmpleado){
        String consulta ="{ CALL usp_EliminarEmpleado(?) }";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            comando.setInt(1, idEmpleado);
            comando.execute();
        }catch (SQLException e){
            System.out.println("Error al eliminar Empleado: "+e.getMessage());
        }
    }
    
    
    //Listar Administrador
    public List<Empleado> listarEmpleado(){
        List<Empleado> lista = new ArrayList<>();
        String consulta = "{CALL usp_listarCliente()}";
        try{
            CallableStatement comando = ConexionPostgreSQL.getConnection().prepareCall(consulta);
            ResultSet rs = comando.executeQuery();
            while(rs.next()){
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellidoPaterno(rs.getString("apellidoPaterno"));
                empleado.setApellidoMaterno(rs.getString("apellidoMaterno"));
                empleado.setGenero(rs.getString("genero"));
                empleado.setFechaNac(rs.getDate("FechaNac"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setContrasena(rs.getString("contrasena"));
                lista.add(empleado);
            }
        }catch(SQLException e){
            System.out.println("Error al listar administradores: "+e.getMessage());
        }
        return lista;
    }
    
}
