package com.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String MATCH_ADMIN_ID_SQL = "SELECT COUNT(*) FROM admin where admin_id = ? and password = ? ";
    private static final String RE_PASSWORD_SQL = "UPDATE admin set password = ? where admin_id = ? ";
    private static final String GET_PASSWORD_SQL = "SELECT password from admin where admin_id = ?";
    private static final String GET_USERNAME_SQL = "SELECT username from admin where admin_id = ?";

    public int getMatchCount(long adminId, String password) {
        return jdbcTemplate.queryForObject(MATCH_ADMIN_ID_SQL, new Object[]{adminId, password}, Integer.class);
    }

    public int resetPassword(long adminId, String password) {
        return jdbcTemplate.update(RE_PASSWORD_SQL, password, adminId);
    }

    public String getPassword(long adminId) {
        return jdbcTemplate.queryForObject(GET_PASSWORD_SQL, new Object[]{adminId}, String.class);
    }

    public String getUsername(long adminId) {
        return jdbcTemplate.queryForObject(GET_USERNAME_SQL, new Object[]{adminId}, String.class);
    }

}
