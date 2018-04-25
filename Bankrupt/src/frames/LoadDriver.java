package frames;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class LoadDriver {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            Connection conn = null;
            try {
                conn =
                   DriverManager.getConnection("jdbc:mysql://localhost/bankrupt?" +
                                               "user=root&password=");

                Statement stmt = null;
                ResultSet rs = null;

                try {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery("SELECT * FROM user");

                    // or alternatively, if you don't know ahead of time that
                    // the query will be a SELECT...

                    if (stmt.execute("SELECT * FROM user")) {
                        rs = stmt.getResultSet();
                    }
                    
                    while (rs.next()) {
                    	   String nom = rs.getString("firstname") ;
                    	   String prenom = rs.getString("lastname") ;
                    	   System.out.println(nom + prenom);
                   }

                    // Now do something with the ResultSet ....
                }
                catch (SQLException ex){
                    // handle any errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                finally {
                    // it is a good idea to release
                    // resources in a finally{} block
                    // in reverse-order of their creation
                    // if they are no-longer needed

                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException sqlEx) { } // ignore

                        rs = null;
                    }

                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException sqlEx) { } // ignore

                        stmt = null;
                    }
                }

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        } catch (Exception ex) {
           System.out.println("error");
        }
    }
    
}