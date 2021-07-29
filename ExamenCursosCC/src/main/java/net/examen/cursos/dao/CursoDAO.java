package net.examen.cursos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.examen.cursos.model.Curso;

public class CursoDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ciencias_computacion";
	private String jdbcUsername = "root";
	private String jdbcPassword = "YJAPFsupdsiqms24101997";
	private static final String INSERT_USERS_SQL = "INSERT INTO cursos" + "(nombre,profesor,semestre,pre_requisito) VALUES" + "(?,?,?,?);";
	private static final String SELECT_USER_BY_ID = "SELECT id,nombre,profesor,semestre,pre_requisito FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM cursos";
    private static final String DELETE_USERS_SQL = "DELETE FROM cursos WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE cursos SET nombre = ?, profesor = ?, semestre =?, pre_requisito = ? WHERE id = ?;";
    public CursoDAO() {}
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void insertCurso(Curso curso) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setString(2, curso.getProfesor());
            preparedStatement.setString(3, curso.getSemestre());
            preparedStatement.setString(4, curso.getPre_requisito());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    public Curso selectCurso(int id) {
        Curso curso = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String profesor = rs.getString("profesor");
                String semestre = rs.getString("semestre");
                String pre_requisito = rs.getString("pre_requisito");
                curso = new Curso(id, nombre, profesor, semestre, pre_requisito);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return curso;
    }
    public List < Curso > selectAllCursos() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Curso > cursos = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String profesor = rs.getString("profesor");
                String semestre = rs.getString("semestre");
                String pre_requisito = rs.getString("pre_requisito");
                cursos.add(new Curso(id, nombre, profesor, semestre, pre_requisito));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cursos;
    }
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(Curso curso) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, curso.getNombre());
            statement.setString(2, curso.getProfesor());
            statement.setString(3, curso.getSemestre());
            statement.setString(3, curso.getPre_requisito());
            statement.setInt(4, curso.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}