package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import ro.sdl.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: psilaghi
 * Date: 10/15/12
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDao {

    @Autowired
    @Qualifier("coreDataSource")
    private DataSource coreDataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(@Qualifier("coreDataSource") DataSource coreDataSource) {
        this.jdbcTemplate = new JdbcTemplate(coreDataSource);
    }

    public void save(User user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(coreDataSource);
            StringBuilder sqlToExecute = new StringBuilder("INSERT INTO USER (NAME, ROLE, STATE, PROJECT) VALUES ( ?, ?, ?, ?)");
            preparedStatement = connection.prepareStatement(sqlToExecute.toString());
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getRole().getValue());
            preparedStatement.setString(3,user.getState().getValue());
            preparedStatement.setInt(4, user.getProject().getId());
            preparedStatement.execute();
        } catch (Exception e) {

        } finally {
            DataSourceUtils.releaseConnection(connection, coreDataSource);
        }
    }
}
