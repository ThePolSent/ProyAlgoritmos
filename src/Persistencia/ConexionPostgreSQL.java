/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author PolSent
 */
public class ConexionPostgreSQL {
    private static final String url= "jdbc:postgresql://localhost:5432/SistemaVentas";
    private static final String user = "postgres";
    private static final String password= "Cabezax2006";
    
    private static Connection conexion = null;
    public static Connection getConnection(){
        if (conexion == null){
            try{
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(url, user, password);
                        
            }catch(Exception e){
                System.out.println("Error al conectar: "+e.getMessage());
            }
            
        }
        return conexion;
    }
    
    public static void main(String[] args) {
        System.out.println("Conectando a la base de datos. Por favor espere");
        
        Connection conexion = ConexionPostgreSQL.getConnection();
        
        if(conexion !=null){
            System.out.println("Conexion establecida");
        }else{
            System.out.println("Fallo la conexion");
        }
    }
}
