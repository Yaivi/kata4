package software.ulpgc.kata4.control;

import software.ulpgc.kata4.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;

public class SQLTitleReader implements TitleReader {
    private final Connection connection;
    private final PreparedStatement selectStatement;

    public SQLTitleReader(File dbFile) throws IOException {
        try {
            this.connection = openConnection(dbFile);
            this.selectStatement = connection.prepareStatement("SELECT * FROM titles");
            System.out.println(this.selectStatement.toString());
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public Iterator<Title> readTitles() throws IOException {
        final ResultSet resultSet = executeQuery();
        return new Iterator<Title>() {
            @Override
            public boolean hasNext() {
                try {
                    return resultSet.next();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public Title next() {
                try {
                    return new Title(
                            resultSet.getString(1),
                            Title.TitleType.valueOf(resultSet.getString(2)),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getInt(8)
                    );
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    private ResultSet executeQuery() throws IOException {
        try {
            return selectStatement.executeQuery();
        } catch (SQLException e) {
            throw new IOException("Error al ejecutar la consulta", e);
        }
    }

    private Connection openConnection(File file) throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
    }

    // Cerrar los recursos al finalizar
    public void close() throws SQLException {
        if (selectStatement != null) {
            selectStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
