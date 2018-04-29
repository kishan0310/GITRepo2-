package com.accent.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.accent.controller.dto.library.BookBean;
import com.accent.service.dto.library.Book;

@Component
public class LibraryMapper implements ILibraryMapper {

  public List<BookBean> mapBooksToBookBeans(List<Book> books) {
    List<BookBean> bookbeans = new ArrayList<>();
    if (books != null) {
      for (Book book : books) {
        BookBean bookBean = new BookBean();
        bookBean.setBookAuthor(book.getBookAuthor());
        bookBean.setBookId(book.getBookId());
        bookBean.setBookISBN(book.getBookISBN());
        bookBean.setBookName(book.getBookName());
        bookBean.setBookPublisher(book.getBookPublisher());
        bookbeans.add(bookBean);
      }
    }
    return bookbeans;
  }

  public void mapBookBeanToBook(BookBean bookBean,Book book) {
    book.setBookName(bookBean.getBookName());
    book.setBookAuthor(bookBean.getBookAuthor());
    book.setBookPublisher(bookBean.getBookPublisher());
    book.setBookISBN(bookBean.getBookISBN());
  }
  public void mapBookToBookBean(BookBean bookBean,Book book) {
    bookBean.setBookAuthor(book.getBookAuthor());
    bookBean.setBookId(book.getBookId());
    bookBean.setBookName(book.getBookName());
    bookBean.setBookISBN(book.getBookISBN());
    bookBean.setBookPublisher(book.getBookPublisher());
  }
}
