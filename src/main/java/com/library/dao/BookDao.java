package com.library.dao;

import com.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String ADD_BOOK_SQL = "INSERT INTO book_info VALUES(NULL ,?,?,?,?,?,?,?,?,?,?)";
    private final static String EDIT_BOOK_SQL = "update book_info set name= ? ,author= ? ,publish= ? ,ISBN= ? ,introduction= ? ,language= ? ,price= ? ,pub_date= ? ,class_id= ? ,number= ?  where book_id= ? ";
    private final static String QUERY_ALL_BOOKS_SQL = "SELECT * FROM book_info ";
    private final static String QUERY_BOOK_SQL = "SELECT * FROM book_info WHERE book_id like  ?  or name like ?   ";
    private final static String MATCH_BOOK_SQL = "SELECT count(*) FROM book_info WHERE book_id like ?  or name like ?  ";
    private final static String GET_BOOK_SQL = "SELECT * FROM book_info where book_id = ? ";

    public int matchBook(String searchWord) {
        String search = "%" + searchWord + "%";
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL, new Object[]{search, search}, Integer.class);
    }

    private Book getBookFromResult(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setAuthor(resultSet.getString("author"));
        book.setBookId(resultSet.getLong("book_id"));
        book.setClassId(resultSet.getInt("class_id"));
        book.setIntroduction(resultSet.getString("introduction"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setLanguage(resultSet.getString("language"));
        book.setName(resultSet.getString("name"));
        book.setPubdate(resultSet.getDate("pub_date"));
        book.setPrice(resultSet.getBigDecimal("price"));
        book.setNumber(resultSet.getInt("number"));
        book.setPublish(resultSet.getString("publish"));
        return book;
    }

    private Object[] getObjectFromBook(Book book) {
        String name = book.getName();
        String author = book.getAuthor();
        String publish = book.getPublish();
        String isbn = book.getIsbn();
        String introduction = book.getIntroduction();
        String language = book.getLanguage();
        BigDecimal price = book.getPrice();
        Date pubdate = book.getPubdate();
        int classId = book.getClassId();
        int number = book.getNumber();
        return new Object[]{name, author, publish, isbn, introduction, language, price, pubdate, classId, number};
    }


    public ArrayList<Book> queryBook(String searchWord) {
        String search = "%" + searchWord + "%";
        final ArrayList<Book> books = new ArrayList<>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{search, search}, (resultSet) ->
        {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                books.add(getBookFromResult(resultSet));
            }
        });
        return books;
    }

    public ArrayList<Book> getAllBooks() {
        final ArrayList<Book> books = new ArrayList<>();
        jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, (resultSet) -> {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                books.add(getBookFromResult(resultSet));
            }
        });
        return books;

    }

    public int addBook(Book book) {
        return jdbcTemplate.update(ADD_BOOK_SQL, getObjectFromBook(book));
    }

    public Book getBook(long bookId) {
        final ArrayList<Book> books = new ArrayList<>();
        jdbcTemplate.query(GET_BOOK_SQL, new Object[]{bookId}, (resultSet) -> {
                    books.add(getBookFromResult(resultSet));
                }
        );
        return books.get(0);
    }

    public int editBook(Book book) {
        long bookId = book.getBookId();
        Object[] objects = new Object[11];
        System.arraycopy(getObjectFromBook(book), 0, objects, 0, 10);
        objects[10] = bookId;
        return jdbcTemplate.update(EDIT_BOOK_SQL, objects);
    }

}
