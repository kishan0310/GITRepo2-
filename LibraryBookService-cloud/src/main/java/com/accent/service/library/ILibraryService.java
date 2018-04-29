package com.accent.service.library;

import java.util.List;
import com.accent.service.dto.library.Book;

public interface ILibraryService {

  /**
   * Method desc.
   * @param bookId- <b>  xyz </b>
   * @return - 
   * @exception -BookNotFound Exception
   */
  public void deleteLibBook(Integer bookId);
  public List<Book> showAllLibBook();
  public void saveLibBook(Book book);
  public Book getLibraryBook(Integer bookId);
}
