/*
 * Discalimer
 */
package com.accent.service.library;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accent.dao.library.ILibraryDao;
import com.accent.service.dto.library.Book;

/**
 * 
 * @author tushar.gade
 *@see ILibraryDao
 */
@Service
public class LibraryServiceImpl implements ILibraryService {



	@Autowired
	private ILibraryDao libDao;


	/**
	 * {@inheritDoc}
	 */
	public void deleteLibBook(Integer bookId) {
		libDao.deleteLibBook(bookId);
	}

	public List<Book> showAllLibBook() {
		/*
		 */
	List<Book> libBook = libDao.retriveLibBooks();
	System.out.println(libBook);
	return libBook;
	}

	public Book getLibraryBook(Integer bookId) {
		return libDao.getLibBook(bookId);
	}

	public void saveLibBook(Book book) {
		libDao.saveLibBook(book);
	}

	public void setLibDao(ILibraryDao libDao) {
		this.libDao = libDao;
	}

}
