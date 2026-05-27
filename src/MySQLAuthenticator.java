import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLAuthenticator implements Authenticator {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/ctrl_sched?useSSL=false&serverTimezone=UTC";

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "Almas@3663";

    private boolean userExists(String email) {

        String cleanEmail = (email != null) ? email.trim() : "";

        if (cleanEmail.isEmpty()) {
            return false;
        }

        String query = "SELECT email FROM users WHERE email = ?";

        try (
                Connection conn = DriverManager.getConnection(
                        DB_URL,
                        DB_USER,
                        DB_PASSWORD
                );

                PreparedStatement stmt =
                        conn.prepareStatement(query)
        ) {

            stmt.setString(1, cleanEmail);

            try (ResultSet rs = stmt.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            System.err.println(
                    "DB Error (userExists): " + e.getMessage()
            );
        }

        return false;
    }

    // 1 = admin
    // 0 = normal user
    // -1 = invalid login

    @Override
    public int isAuthenticated(
            String email,
            String password
    ) {

        String cleanEmail =
                (email != null) ? email.trim() : "";

        String cleanPassword =
                (password != null) ? password.trim() : "";

        if (
                cleanEmail.isEmpty()
                        ||
                        cleanPassword.isEmpty()
        ) {

            System.out.println(
                    "DEBUG: Empty email/password"
            );

            return -1;
        }

        String query =
                "SELECT password, admin FROM users WHERE email = ?";

        try (
                Connection conn = DriverManager.getConnection(
                        DB_URL,
                        DB_USER,
                        DB_PASSWORD
                );

                PreparedStatement stmt =
                        conn.prepareStatement(query)
        ) {

            stmt.setString(1, cleanEmail);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    String storedPass =
                            rs.getString("password");

                    int adminInt =
                            rs.getInt("admin");

                    boolean isAdmin =
                            (adminInt == 1);

                    System.out.println(
                            "DEBUG: Stored pass: '"
                                    + storedPass
                                    + "', Input: '"
                                    + cleanPassword
                                    + "'"
                    );

                    if (
                            storedPass != null
                                    &&
                                    storedPass.equals(cleanPassword)
                    ) {

                        System.out.println(
                                "DEBUG: Login SUCCESS"
                        );

                        return isAdmin ? 1 : 0;

                    } else {

                        System.out.println(
                                "DEBUG: Password mismatch"
                        );
                    }

                } else {

                    System.out.println(
                            "DEBUG: User not found: "
                                    + cleanEmail
                    );
                }
            }

        } catch (SQLException e) {

            System.err.println(
                    "DB Error (isAuthenticated): "
                            + e.getMessage()
            );
        }

        return -1;
    }

    @Override
    public void signUp(
            String email,
            String password
    ) {

        String cleanEmail =
                (email != null) ? email.trim() : "";

        String cleanPassword =
                (password != null) ? password.trim() : "";

        if (
                cleanEmail.isEmpty()
                        ||
                        cleanPassword.isEmpty()
        ) {

            System.out.println(
                    "DEBUG: Empty signup fields"
            );

            return;
        }

        if (userExists(cleanEmail)) {

            System.out.println(
                    "DEBUG: User already exists: "
                            + cleanEmail
            );

            return;
        }

        String query =
                "INSERT INTO users (email, password, admin) VALUES (?, ?, 0)";

        try (
                Connection conn = DriverManager.getConnection(
                        DB_URL,
                        DB_USER,
                        DB_PASSWORD
                );

                PreparedStatement stmt =
                        conn.prepareStatement(query)
        ) {

            stmt.setString(1, cleanEmail);

            stmt.setString(2, cleanPassword);

            int result = stmt.executeUpdate();

            if (result > 0) {

                System.out.println(
                        "DEBUG: Signup SUCCESS - "
                                + cleanEmail
                );

            } else {

                System.out.println(
                        "DEBUG: Signup failed - no rows affected"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "DEBUG: Signup SQL Error: "
                            + e.getMessage()
            );
        }
    }
}