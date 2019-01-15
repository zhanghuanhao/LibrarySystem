package com.library.dao;

import com.library.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class ReaderInfoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String ADD_READER_INFO_SQL = "INSERT INTO reader_info ( name, sex, birth, address, phone ) VALUES(?,?,?,?,?)";
    private final static String GET_READER_ID_SQL = "SELECT reader_id FROM reader_info where name = ? and sex = ? and birth = ? and address = ? and phone = ? ";
    private final static String DELETE_READER_INFO_SQL = "DELETE FROM reader_info where reader_id = ? ";
    private final static String GET_READER_INFO_SQL = "SELECT * FROM reader_info where reader_id = ? ";
    private final static String UPDATE_READER_INFO = "UPDATE reader_info set name = ? ,sex = ? ,birth = ? ,address = ? ,phone = ? where reader_id = ? ";
    private final static String ALL_READER_INFO_SQL = "SELECT * FROM reader_info";

    private ReaderInfo getInfoFromResult(ResultSet resultSet) throws SQLException {
        ReaderInfo reader = new ReaderInfo();
        reader.setAddress(resultSet.getString("address"));
        reader.setBirth(resultSet.getDate("birth"));
        reader.setName(resultSet.getString("name"));
        reader.setReaderId(resultSet.getLong("reader_id"));
        reader.setSex(resultSet.getString("sex"));
        reader.setPhone(resultSet.getString("phone"));
        return reader;
    }

    private Object[] getObjectFromInfo(ReaderInfo readerInfo) {
        String address = readerInfo.getAddress();
        Date birth = readerInfo.getBirth();
        String name = readerInfo.getName();
        String sex = readerInfo.getSex();
        String phone = readerInfo.getPhone();
        return new Object[]{name, sex, birth, address, phone};
    }

    public ArrayList<ReaderInfo> getAllReaderInfo() {
        final ArrayList<ReaderInfo> readers = new ArrayList<>();
        jdbcTemplate.query(ALL_READER_INFO_SQL, (resultSet) -> {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                readers.add(getInfoFromResult(resultSet));
            }
        });
        return readers;
    }

    public ReaderInfo findReaderInfoByReaderId(long readerId) {
        final ArrayList<ReaderInfo> readers = new ArrayList<>();
        jdbcTemplate.query(GET_READER_INFO_SQL, new Object[]{readerId}, (resultSet) -> {
            readers.add(getInfoFromResult(resultSet));
        });
        return readers.get(0);
    }

    public int deleteReaderInfo(long readerId) {
        return jdbcTemplate.update(DELETE_READER_INFO_SQL, readerId);
    }

    public int editReaderInfo(ReaderInfo readerInfo) {
        long readerId = readerInfo.getReaderId();
        Object[] objects = new Object[6];
        System.arraycopy(getObjectFromInfo(readerInfo), 0, objects, 0, 5);
        objects[5] = readerId;
        return jdbcTemplate.update(UPDATE_READER_INFO, objects);
    }

    //返回reader_id
    public long addReaderInfo(ReaderInfo readerInfo) {
        Object[] objects = getObjectFromInfo(readerInfo);
        if (jdbcTemplate.update(ADD_READER_INFO_SQL, objects) > 0) {
            return jdbcTemplate.queryForObject(GET_READER_ID_SQL, objects, Long.class);
        } else {
            return -1;
        }
    }
}
