import java.sql.Connection;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
        Connection con = null;
        try {
            // get the connection
            con = JDBCUtil.getConnection();
    
            // start the transaction
            con.setAutoCommit(false);
    
            // simulate the transaction from account123 to account 12
            Transaction.transferMoney(con, "account123", "acount12", 600);
            // commit the transaction if everything is okay
            con.commit();
            System.out.println("Transaction committed successfully");
    
        } catch (Exception e1) {
            e1.printStackTrace();
    
            // rollback the transaction in case of an exception
            System.out.println("Rolling back the transaction");
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
