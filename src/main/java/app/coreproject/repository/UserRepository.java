/**
 *
 */
package app.coreproject.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import app.coreproject.model.User;
import app.coreproject.model.UserHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author toduc
 *
 */
@Repository
@Slf4j
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Transactional
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO `users` (`username`, `password`, `permission`, `email`, `phone`, `name`, `twitter_username`, `telegram_username`, `extra_data`, `version`, `status`) " +
                "VALUES (:username, :password, :permission, :email, :phone, :name, :twitterUsername, :telegramUsername, :extraData, :version, :status);";
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, params, keyHolder);

        int userId = keyHolder.getKey().intValue();
        User userCreate = findByUserId(userId).get();
        UserHistory userHistory = new UserHistory(userCreate);
        String sqlHistory = "INSERT INTO `user_history` (`id`, `username`, `password`, `permission`, `email`, `phone`, `name`, `twitter_username`, `telegram_username`, `extra_data`, `version`, `status`) " +
                "VALUES (:id, :username, :password, :permission, :email, :phone, :name, :twitterUsername, :telegramUsername, :extraData, :version, :status);";;
        SqlParameterSource historyParams = new BeanPropertySqlParameterSource(userHistory);
        jdbcTemplate.update(sqlHistory, historyParams);

        return userCreate;
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users u where u.username =:username;";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("username", username);
        try {
            return Optional
                    .ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<>(User.class)));
        } catch (Exception e) {
            log.error("findByusername exception ", e);
            return Optional.ofNullable(null);
        }
    }

    public Optional<User> findByUserId(int userId) {
        String sql = "SELECT * FROM users where id =:id;";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("id", userId);
        try {
            return Optional
                    .ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<User>(User.class)));
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    public Optional<User> findByPhone(String phone) {
        String sql = "SELECT * FROM users where phone =:phone;";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("phone", phone);
        try {
            return Optional
                    .ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<>(User.class)));
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    public User findByEmail(String email) {
        String sql = "SELECT *\r\n"
                + "FROM users \r\n"
                + "WHERE email=:email ;";
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("email", email);
        List<User> users = jdbcTemplate.query(sql, argMap, new BeanPropertyRowMapper<User>(User.class));
        if (users.isEmpty() || users == null) {
            return null;
        } else return users.get(0);
    }

    @Transactional
    public int update(User user) {
        user.setVersion(user.getVersion() + 1);
        //Change query
        String sql = "UPDATE users t\n" +
                "SET t.`group`    = :group,\n" +
                "    t.password   = :password,\n" +
                "    t.permission = :permission,\n" +
                "    t.phone      = :phone,\n" +
                "    t.email      = :email,\n" +
                "    t.name       = :name,\n" +
                "    t.version    = :version,\n" +
                "    t.updated_at = :updatedAt,\n" +
                "    t.status     = :status\n" +
                "WHERE t.id = :id;\n";
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, params);

        User userCreate = findByUserId(user.getId()).get();
        UserHistory userHistory = new UserHistory(userCreate);
        //Change query history
        String sqlHistory = "INSERT INTO user_history (id, username, `group`, password, permission, phone, email, debit, name, version,\n" +
                "                                  created_at, updated_at, status)\n" +
                "VALUES (:id, :username, :group, :password, :permission, :phone, :email, :debit, :name, :version, :createdAt, :updatedAt, :status);";
        SqlParameterSource historyParams = new BeanPropertySqlParameterSource(userHistory);
        return jdbcTemplate.update(sqlHistory, historyParams);
    }


}
