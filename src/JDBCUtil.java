/**
 * JDBCUTIL
 */
/*
 * this class is stabilish for connecting with server
 * it is a boiler plate
 */
import java.sql.Connection;
import java.sql.DriverManager;
public class JDBCUtil {
        private static final String DB_DRIVER ="com.mysql.cj.jdbc.Driver";
        private static final String DB_URL ="jdbc:mysql://localhost:3306/employDataSet";
        private static final String DB_USERNAME ="root";
        private static final String DB_PASSWORD ="Suvaraj1@";

    /**
     * @return
     */
    public static Connection getConnection(){

        //JDBC AND DATABASE PROPERTIES
       


        //connection interface - obj
        Connection con=null;
        try {
           //register jdbc driver
           Class.forName(DB_DRIVER);
            //OPEN THE CONNECTION
            con=DriverManager.getConnection( DB_URL,DB_USERNAME,DB_PASSWORD);

            //check the connection stabilished or not
            if(con!=null){
                System.out.println("connection estabilished succesfully");
            }
            else{
                System.out.println("connection failed");
            }
        } catch (Exception e) {
            
        }
        return con;
        
    }
}
