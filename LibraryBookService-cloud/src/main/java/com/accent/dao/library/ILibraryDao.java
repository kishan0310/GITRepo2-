package com.accent.dao.library;

import java.util.List;
import com.accent.service.dto.library.Book;

public interface ILibraryDao {

	/**
	 * Method desc.
	 * @param bookId- <b>  xyz </b>
	 * @return - 
	 * @exception -BookNotFound Exception
	 */
	public void saveLibBook(Book book);
	public Book getLibBook(Integer bookId);
	public boolean deleteLibBook(Integer bookId);
	public List<Book> retriveLibBooks();

}
