package com.library.dao;

import com.library.domain.ReaderCard;
import com.library.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderCardDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_READER_ID_SQL = "select count(*) from reader_card where reader_id = ? and password = ? ";
    private final static String MATCH_READER_NAME_SQL = "select count(*) from reader_card where username = ? and password = ? ";
    private final static String FIND_READER_BY_USERID = "select * from reader_card where reader_id = ? ";
    private final static String RE_PASSWORD_SQL = "UPDATE reader_card set password = ? where reader_id = ? ";
    private final static String ADD_READERCARD_SQL = "INSERT INTO reader_card values ( ? , ? , ?)";
    private final static String UPDATE_READER_USERNAME_SQL = "UPDATE reader_card set username = ? where reader_id = ?";

    public int getIdMatchCount(long readerId, String password) {
        return jdbcTemplate.queryForObject(MATCH_READER_ID_SQL, new Object[]{readerId, password}, Integer.class);
    }

    public int getNameMatchCount(String username, String password) {
        return jdbcTemplate.queryForObject(MATCH_READER_NAME_SQL, new Object[]{username, password}, Integer.class);
    }

    public ReaderCard findReaderByReaderId(long readerId) {
        final ReaderCard readerCard = new ReaderCard();
        jdbcTemplate.query(FIND_READER_BY_USERID, new Object[]{readerId},
                (resultSet) -> {
                    readerCard.setReaderId(resultSet.getInt("reader_id"));
                    readerCard.setPassword(resultSet.getString("password"));
                    readerCard.setName(resultSet.getString("username"));
                });
        return readerCard;
    }

    public int resetPassword(long readerId, String newPassword) {
        return jdbcTemplate.update(RE_PASSWORD_SQL, newPassword, readerId);
    }

    public int addReaderCard(ReaderInfo readerInfo,String password) {
        String username = readerInfo.getName();
        long readerId = readerInfo.getReaderId();
        return jdbcTemplate.update(ADD_READERCARD_SQL, readerId, username, password);
    }
    public int updateUsername(long readerId, String username) {
        return jdbcTemplate.update(UPDATE_READER_USERNAME_SQL, username, readerId);
    }
}
