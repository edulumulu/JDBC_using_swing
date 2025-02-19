
package GestionBBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Empleado;


/**
 *
 * @author edualumulu
 */
public class Gestion_BBDD {
    
    //Variables que almacenan en forma de string los datos para realizar la conexión con el servidor 
    //------ ATENCIÓN YO HE TRABAJADO CON EL PUERTO 3308, CAMBIAR SI UTILIZAS EL POR DEFECTO MYSQL (3306) --------
    //    private final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private final String JDBC_URL = "jdbc:mysql://localhost:3308";
    private final String JDBC_COMMU_OPT = "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "";

    //NOMBRE DE la base de Datos y la tabla
    private final String JDBC_DDBB = "M07_Empresa";
    private final String JDBC_TABLE = "Empleados";
    private final String JDBC_DDBB_TABLE = JDBC_DDBB + "." + JDBC_TABLE;
    
    /**
     * Se conecta con mysql a traves del puerto, url, usuario y pasword indicados. 
     * Crea la base de datos y la tabla si no existiera.
     *
     * @return ---> Connetion
     */
    public Connection Conectarse(){
        
        Connection conexion = null;
        
        try {
            conexion= DriverManager.getConnection(JDBC_URL + JDBC_COMMU_OPT, JDBC_USER, JDBC_PASSWORD);
            crearBBDD(conexion);
            //Hago directamente la ejecución de la orden usar esta base de datos
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("USE " + JDBC_DDBB);  
            stmt.close();
            crearTablaEmpleados(conexion);
            System.out.println("Conexión y base de datos creada correctamente");
            
            //Metodo para cargar varios empleados por defecto
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error con la conexión");
        }
         
        return conexion ;
    }
    /**
     * Ejecuta la instruccion para crear una base de datos con el nombre
     * seleccionado como variable JDBC.
     *
     * @param conexion
     * @throws SQLException
     */
    public void crearBBDD(Connection conexion) throws SQLException {

        String instruction = "create database if not exists " + JDBC_DDBB + ";";
        Statement stmt = null;
        stmt = conexion.createStatement();  //creo un objeto a partir de la conexion introducida como parámetro
        stmt.executeUpdate(instruction);    //Ejecuto la query en la base de  datos

        stmt.close(); // libero recursos
    }
    
    /**
     * Ejecuta la query de mysql para crear la tabla empleados. 
     *
     * @param conexion
     * @throws SQLException
     */
    public void crearTablaEmpleados(Connection conexion) throws SQLException {
        String instruccion = "create table if not exists " + JDBC_DDBB_TABLE + "("
                + "id BIGINT primary key auto_increment, " //el id se incrementará cada vez que se agregue un alumno obligatoriamente
                + "user VARCHAR(15), "
                + "password VARCHAR (15), "
                + "name VARCHAR (30), "
                + "surname VARCHAR (50), "
                + "tlf int, "
                + "email VARCHAR (40));";

        Statement stmt = null;
        stmt = conexion.createStatement();
        stmt.executeUpdate(instruccion);
        stmt.close();
    }
    /**
     * Cierra la conexión que se le introduzca como parámetro
     *
     * @param conexion
     */
    public void desconectarse(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexiond esconectada");
            } catch (SQLException ex) {
                System.out.println("No se puede desconectar de la base de datos " + JDBC_DDBB);
            }
        }
    }
    
    /**
     * Método que carga la lista de empleados de la base de datos a un array de objetos Empleado
     * @param con
     * @return 
     */
    public ArrayList<Empleado> cargar_listado_empleados(Connection con){
        ArrayList<Empleado> lista_empleados = new ArrayList<Empleado>();
        try{
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Empleados");
            
                while(rs.next()){
                    int id = rs.getInt("id");
                    String user = rs.getString("user");
                    String password = rs.getString("password");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    int tlf = rs.getInt("tlf");
                    String email = rs.getString("email");

                    Empleado emp = new Empleado(id, user, password, name, surname, tlf, email);
                    lista_empleados.add(emp);
                }
            
            
        }catch(SQLException e){
            System.out.println("Error con la consulta sobre la base de datos");
            e.printStackTrace();
        }
        return lista_empleados;
    }
    
    /**
     * Método que elimina un empleado según su id
     * @param con
     * @param id
     * @return 
     */
    public boolean borrar_empleado (Connection con, int id){
         
        try{
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM Empleados WHERE id="+id);
            stm.close();
            return true;
            
        }catch(SQLException e){
            System.out.println("Error eliminar empleado");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método para modificar un parámetro de un registro de la báse de datos
     * @param con --> Conexión
     * @param pass --> modificación
     * @param id --> Id del registro de la BBDD
     * @param campo --> Campo a modificar
     * @return --> True o False
     */
    public boolean modificar_campo(Connection con , String cambio, int id ,String campo ){
        
        try{
            String query = "UPDATE " + JDBC_DDBB_TABLE + " SET "+campo+" = ? WHERE (id = ?);";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, cambio);
            stm.setInt(2, id);
            
            stm.executeUpdate();
            
            stm.close();
            return true;
            
        }catch(SQLException e){
            System.out.println("Error al modificar contraseña");
            e.printStackTrace();
            return false;
        }

    }
    /**
     * Metodo para insertar un empleado nuevo
     * @param con
     * @param em
     * @return 
     */
    public boolean Insertar_empleado (Connection con, Empleado em){
         
        try{
            String orden = "INSERT INTO " + JDBC_DDBB_TABLE + " (user, password, name, surname, tlf, email) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement stm = con.prepareStatement(orden);

            stm.setString(1, em.getUser());
            stm.setString(2, em.getPassword());
            stm.setString(3, em.getName());
            stm.setString(4, em.getSurname());
            stm.setInt(5, em.getTlf());
            stm.setString(6, em.getEmail());
            
            stm.executeUpdate();
            
            stm.close();
            return true;
            
        }catch(SQLException e){
            System.out.println("Error eliminar empleado");
            e.printStackTrace();
            return false;
        }
    }
    
    
    /**
     * Metodo para insertar ejemplos de datos àra la tabla Empleados
     * @param conn 
     */
    public void Insertar_empleados_predeterminado(Connection conn) {
        
        ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
        try {
            
            Statement sta = conn.createStatement();
            
            listaEmpleados.add(new Empleado("e.lucas", "3333", "Eduardo", "lucas Muñoz de Lucas", 947348943, "edu@edu.es"));
            listaEmpleados.add(new Empleado("N.Martin", "1233", "Nuria", "Martín Lopez", 96666666, "nuria@edu.es"));
            listaEmpleados.add(new Empleado("C.Ysaven", "4123", "Carolina", "Yavén", 622222222, "carol@edu.es"));
            listaEmpleados.add(new Empleado("m.rodriguez", "9876", "María", "Rodríguez Fernández", 654321987, "maria@edu.es"));
            listaEmpleados.add(new Empleado("j.perez", "5678", "Javier", "Pérez Gómez", 612345678, "javier@edu.es"));
            listaEmpleados.add(new Empleado("s.lopez", "abcd", "Sofía", "López Martínez", 698745632, "sofia@edu.es"));
            listaEmpleados.add(new Empleado("a.garcia", "4321", "Alejandro", "García Ruiz", 678123456, "alejandro@edu.es"));

            for( Empleado emp : listaEmpleados){
                String sql = "INSERT INTO Empleados (`user`, `password`, name, surname, tlf, email) VALUES ('" 
                     + emp.getUser() + "', '" + emp.getPassword()+ "', '" + emp.getName() + "', '" + emp.getSurname() + "', " + emp.getTlf() + ", '" + emp.getEmail() + "')";
                sta.executeUpdate(sql);
            }
            
            sta.close();
        } catch (SQLException ex) {
            System.out.println("ERROR:al hacer un Insert");
            ex.printStackTrace();
        }
    }
}
