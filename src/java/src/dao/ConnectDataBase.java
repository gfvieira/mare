package src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import src.Erro;

public class ConnectDataBase {

    public static Connection connection = null;
    Erro log = null;

    public ConnectDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            //Class.forName("org.firebirdsql.jdbc.FBDriver");
            //Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
        } catch (ClassNotFoundException e) {
            log = new Erro();
            log.Gravar(e.getMessage(), "ConnectDataBase", "ConnectDataBase");

        } /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex); /*  catch (InstantiationException | IllegalAccessException ex) {
         java.util.logging.Error.getLogger(ConnectDataBase.class.getName()).log(Level.SEVERE, null, ex);
         }  */

    }

    public Connection openConection() {
        if (connection == null) {
            try {
//                connection = DriverManager.getConnection("jdbc:postgresql://10.5.185.11:5432/sigbase_new", "postgres", "bodeverde2001");
                //connection = DriverManager.getConnection("jdbc:postgresql://10.5.185.11:5432/sigbase_teste", "postgres", "bodeverde2001");  
                //connection = DriverManager.getConnection("jdbc:oracle:thin:@ip_servidor:1521:instancia","usuario","senha");  
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chm_mare", "postgres", "bodeverde2001"); 
                //connection = DriverManager.getConnection("jdbc:firebirdsql://localhost/3050:C:\\Firebird\\sisfolhan\\bd\\SISFOLHAN.GBD", "SYSDBA", "masterkey");  
                //connection = DriverManager.getConnection("jdbc:firebirdsql://10.5.176.4:3050/c:\\sistemas\\sisfolhan\\bd\\sisfolhan.gdb", "SYSDBA", "masterkey");  
            } catch (SQLException e) {
                log = new Erro();
                log.Gravar(e.getMessage(), "ConnectDataBase", "openConection");
                return null;
            }
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                log = new Erro();
                log.Gravar(e.getMessage(), "ConnectDataBase", "closeConnection");
            }
        }
    }
}
