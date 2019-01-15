package com.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String MATCH_ADMIN_ID_SQL="SELECT COUNT(*) FROM admin where admin_id = ? and password = ? ";
    private static final String MATCH_ADMIN_NAME_SQL="SELECT COUNT(*) FROM admin where username = ? and password = ? ";
    private static final String RE_PASSWORD_SQL="UPDATE admin set password = ? where admin_id = ? ";
    private static final String GET_PASSWODD_SQL="SELECT password from admin where admin_id = ?";

    public int getIdMatchCount(long admin_id,String password){
        return jdbcTemplate.queryForObject(MATCH_ADMIN_ID_SQL,new Object[]{admin_id,password},Integer.class);
    }

    public int getNameMatchCount(String username,String password){
        return jdbcTemplate.queryForObject(MATCH_ADMIN_NAME_SQL,new Object[]{username,password},Integer.class);
    }

    public int resetPassword(long admin_id,String password){
        return jdbcTemplate.update(RE_PASSWORD_SQL,password,admin_id);
    }
    public String getPassword(long admin_id){
        return jdbcTemplate.queryForObject(GET_PASSWODD_SQL,new Object[]{admin_id},String.class);
    }

}
