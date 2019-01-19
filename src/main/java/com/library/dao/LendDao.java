package com.library.dao;

import com.library.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class LendDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private final static String RETURN_BOOK_ONE_SQL = "UPDATE lend_list SET back_date = ? WHERE book_id = ? AND reader_id = ? AND back_date is NULL";
    private final static String RETURN_BOOK_TWO_SQL = "UPDATE book_info SET number = number + 1 WHERE book_id = ? ";
    private final static String LEND_BOOK_ONE_SQL = "INSERT INTO lend_list VALUES (NULL , ? , ? , ? , NULL)";
    private final static String LEND_BOOK_TWO_SQL = "UPDATE book_info SET number = number - 1 WHERE book_id = ? ";
    private final static String LEND_LIST_SQL = "SELECT * FROM lend_list";
    private final static String MY_LEND_LIST_SQL = "SELECT * FROM lend_list WHERE reader_id = ? ";
    private final static String DELETE_LEND_SQL = "DELETE FROM lend_list WHERE ser_num = ?";

    public int returnBookOne(long bookId, long readerId) {
        return jdbcTemplate.update(RETURN_BOOK_ONE_SQL, df.format(new Date()), bookId, readerId);
    }

    public int returnBookTwo(long bookId) {
        return jdbcTemplate.update(RETURN_BOOK_TWO_SQL, bookId);
    }

    public int lendBookOne(long bookId, long readerId) {
        return jdbcTemplate.update(LEND_BOOK_ONE_SQL, bookId, readerId, df.format(new Date()));
    }

    public int lendBookTwo(long bookId) {
        return jdbcTemplate.update(LEND_BOOK_TWO_SQL, bookId);
    }

    private Lend getLendFromResult(ResultSet resultSet) throws SQLException {
        Lend lend = new Lend();
        lend.setBackDate(resultSet.getDate("back_date"));
        lend.setBookId(resultSet.getLong("book_id"));
        lend.setLendDate(resultSet.getDate("lend_date"));
        lend.setReaderId(resultSet.getInt("reader_id"));
        lend.setSer_num(resultSet.getLong("ser_num"));
        return lend;
    }

    public ArrayList<Lend> lendList() {
        final ArrayList<Lend> list = new ArrayList<>();
        jdbcTemplate.query(LEND_LIST_SQL, (resultSet) -> {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                list.add(getLendFromResult(resultSet));
            }
        });
        return list;
    }

    public ArrayList<Lend> myLendList(long readerId) {
        final ArrayList<Lend> list = new ArrayList<>();
        jdbcTemplate.query(MY_LEND_LIST_SQL, new Object[]{readerId}, (resultSet) -> {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                list.add(getLendFromResult(resultSet));
            }
        });
        return list;
    }

    public int deleteLend(long serNum) {
        return jdbcTemplate.update(DELETE_LEND_SQL, serNum);
    }
}
