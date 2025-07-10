package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;
public class GenericDAO {
    public GenericDAO() {
        try {

            /* Setup Banco de dados MySQL */
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {

        Dotenv dotenv = Dotenv.configure()
            .filename(".env")
            .directory("/usr/local/tomcat")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .systemProperties()
            .load();
        String host = dotenv.get("DB_HOST");
        String port = dotenv.get("DB_PORT");
        String db = dotenv.get("DB_NAME");
        String dbUser = dotenv.get("DB_USER");
        String dbpass = dotenv.get("DB_PASSWORD");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db + 
             "?useSSL=false" +
             "&serverTimezone=America/Sao_Paulo" +
             "&allowPublicKeyRetrieval=true" +
             "&connectionTimeOut=30000" +
             "&socketTimeOut=30000";
        
        return DriverManager.getConnection(url, dbUser, dbpass);
    }
}
