package com.accent.dao.library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.accent.service.dto.library.Book;


@Repository
public class LibraryDaoImpl implements ILibraryDao{

  private  Map<Integer, Book> libBooksMap = new HashMap<>();
  private  Integer bookId = 0;
  private  static Logger logger = LoggerFactory.getLogger(LibraryDaoImpl.class);

/**
 * @{inheritDoc}
 */
  public void saveLibBook(Book book) {
    Integer libBookId = book.getBookId();

    if (libBookId == null) {
      book.setBookId(++bookId);

      Integer bookKey = book.getBookId();
      libBooksMap.put(bookKey, book);
    } else {
      libBooksMap.put(book.getBookId(), book);
    }
  }

  public Book getLibBook(Integer bookId) {
    return libBooksMap.get(bookId);
  }

  public boolean deleteLibBook(Integer bookId) {
    libBooksMap.remove(bookId);
    return true;
  }

  public List<Book> retriveLibBooks() {
    List<Book> libBookList = new ArrayList<>();

    for (Map.Entry<Integer, Book> entry : libBooksMap.entrySet()) {
      libBookList.add(entry.getValue());
      logger.debug("Entry Key "+entry.getKey() +"Entry Value "+entry.getValue());
    }
    return libBookList;
  }



}
