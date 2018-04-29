package com.accent.mapper;

import java.util.List;
import com.accent.controller.dto.library.BookBean;
import com.accent.service.dto.library.Book;

public interface ILibraryMapper {
	public List<BookBean> mapBooksToBookBeans(List<Book> books);
	public void mapBookBeanToBook(BookBean bookBean,Book book);
	public void mapBookToBookBean(BookBean bookBean,Book book);
}
