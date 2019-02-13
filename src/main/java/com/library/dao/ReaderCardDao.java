package com.library.dao;

import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReaderCardDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "com.library.dao.ReaderCardDao.";

    public int getIdMatchCount(final long reader_id, final String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatchCount", map);
    }

    public ReaderCard findReaderByReaderId(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderByReaderId", reader_id);
    }

    public int resetPassword(final long reader_id, final String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", newPassword);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", map);
    }

    public int addReaderCard(final ReaderInfo readerInfo, final String password) {
        String username = readerInfo.getName();
        long reader_id = readerInfo.getReaderId();
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("username", username);
        map.put("password", password);
        return sqlSessionTemplate.update(NAMESPACE + "addReaderCard", map);
    }

    public String getPassword(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", reader_id);
    }

    public int deleteReaderCard(final long reader_id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderCard", reader_id);
    }
}
