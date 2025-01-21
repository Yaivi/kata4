package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;


import java.io.File;
import java.io.IOException;
import java.sql.*;



public class SQLiteTitleWriter implements TitleWriter {
    private final Connection connection;
    private static final String createTable = """   
            CREATE TABLE IF NOT EXISTS titles (
                id TEXT PRIMARY KEY,
                titleType TEXT NOT NULL,
                primaryTitle TEXT NOT NULL,
                originalTitle TEXT NOT NULL,
                isAdult INTEGER,
                startYear INTEGER NOT NULL,
                endYear INTEGER,
                runtime INTEGER NOT NULL)
            """;

    private final String insertSQL = "INSERT INTO titles(id, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtime) VALUES(?,?,?,?,?,?,?,?)";
    private PreparedStatement insertStatement;

    public SQLiteTitleWriter(File file) throws IOException {
        this.connection = openConnection(file);
        preparedDatabase();
    }

    private Connection openConnection(File file) throws IOException {
        try{
            return DriverManager.getConnection("jdbc:sqlite:"+ file.getAbsolutePath());
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private void preparedDatabase() throws IOException {
        try{
            Statement statement = connection.createStatement();
            statement.execute(createTable);
            connection.setAutoCommit(false);
            insertStatement = connection.prepareStatement(this.insertSQL);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Title title) throws IOException {
        try{
            String checkSQL = "SELECT COUNT(*) FROM titles WHERE id = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSQL)) {
                checkStatement.setString(1, title.getId());
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // El título ya existe, no se inserta
                    return;
                }
            }

            insertStatement.setString(1, title.getId());
            insertStatement.setString(2, title.getTitleType().toString());
            insertStatement.setString(3, title.getPrimaryTitle());
            insertStatement.setString(4, title.getOriginalTitle());
            insertStatement.setInt(5, title.getIsAdult());
            insertStatement.setInt(6, title.getStartYear());
            insertStatement.setInt(7, title.getEndYear());
            insertStatement.setInt(8, title.getRuntime());
            insertStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}