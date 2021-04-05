package jdbcs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.ResourceBundle;

public class Users {
    static ResourceBundle bundle = ResourceBundle.getBundle("jdbcs.conf");

    public static void main(String[] args) throws SQLException, IOException {

        new Thread(() -> {
            String sql = "INSERT INTO users (name) VALUES(?)";
            try (
                    Connection c = getConnection();
                    PreparedStatement st = c.prepareStatement(sql);
            ) {
                st.setString(1, "John");
                st.executeUpdate();
                System.out.println("Insert John");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                st.setString(1, "Johanna");
                st.executeUpdate();
                System.out.println("Insert Johanna");
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }).start();

        Thread scroll = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String sqlCount = "SELECT count(*) from users";
            String sql = "SELECT id, name from users";
            try (
                    Connection c = getConnection();
                    PreparedStatement stCount = c.prepareStatement(sqlCount);
                    PreparedStatement st = c.prepareStatement(sql);
            ) {
                ResultSet countSet = st.executeQuery(sqlCount);
                countSet.next();
                int count = countSet.getInt(1);
                System.out.println("Records: " + count);
                if (count > 0) {
                    ResultSet result = st.executeQuery(sql);
                    String outPattern = bundle.getString("out.user");
                    while (result.next()) {
                        System.out.println("Select next");
                        System.out.println(
                                MessageFormat.format(
                                        outPattern,
                                        result.getString("id"),
                                        result.getString("name")
                                )
                        );
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });
        scroll.start();
        try {
            scroll.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (
                Connection c = getConnection();
                PreparedStatement st = c.prepareStatement("TRUNCATE table users");
        ) {
            System.out.println(st.executeUpdate());
        }
    }

    /**
     * Add a file with structure
     *  connection.string=jdbc:mysql://HOST:PORT/DB
     *  connection.user=
     *  connection.password=
     *
     * Add env var to point to this file
     * @return
     */
    static Connection getConnection() throws SQLException, IOException {
        String propFile = System.getenv(bundle.getString("connection.env"));

        try (InputStream input = new FileInputStream(propFile)) {
            Properties props = new Properties();

            props.load(input);

            return DriverManager.getConnection(
                    props.getProperty("connection.string"),
                    props.getProperty("connection.user"),
                    props.getProperty("connection.password")
            );
        }
    }
}
