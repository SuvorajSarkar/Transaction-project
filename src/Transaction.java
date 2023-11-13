import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    //instance variable(global variable)
    private static final String UPDATE_BALANCE="update accounts set balance=? where account_number=?";
    public static void transferMoney(Connection connection,String fromAccountNumber,String toAccountNumber,double amount)throws SQLException{
        //fire query
        try( PreparedStatement updatefromaccount=connection.prepareStatement(UPDATE_BALANCE);
            PreparedStatement updatetoaccount=connection.prepareStatement(UPDATE_BALANCE)) {
           

            //check the sufficient balance in the source account
           double fromAccountBalance= getAccountBalance(connection,fromAccountNumber);
           if(fromAccountBalance<amount){
            throw new SQLException("INSUFFICIENT BALANCE "+fromAccountNumber);
           }
           //deduct amount account from the source account
           updatefromaccount.setDouble(1,fromAccountBalance-amount); //balance
           updatefromaccount.setString(2, fromAccountNumber);//account number
           updatefromaccount.executeUpdate();

           //add amount to the destination amount
           double toAccountbalance=getAccountBalance(connection, toAccountNumber);
           updatetoaccount.setDouble(1, toAccountbalance+amount);
           updatetoaccount.setString(2, toAccountNumber);
           updatetoaccount.executeUpdate();

           System.out.println("Transfer succesful " + amount + "from acount "+fromAccountNumber + "to account " + toAccountNumber);

        } 
    }
    private static double  getAccountBalance(Connection connection,String fromAccountNumber)throws SQLException{

        //retrieve the current balance of the account
        String selectBalance="select balance from accounts where account_number=?";
        try( PreparedStatement preparedStatement=connection.prepareStatement(selectBalance)) {
            //fire the query
           
            preparedStatement.setString(1, fromAccountNumber);
            try(ResultSet resultSet=preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getDouble("balance");
                }
                else{
                    throw new SQLException("account not found "+fromAccountNumber);
                }
            }

        }
    }
}
