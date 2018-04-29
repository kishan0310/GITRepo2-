package com.accent.controller.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accent.controller.dto.library.BookBean;
import com.accent.controller.dto.library.BookBeanList;
import com.accent.handler.GenericError;
import com.accent.handler.ValidationException;
import com.accent.mapper.ILibraryMapper;
import com.accent.mapper.LibraryMapper;
import com.accent.service.dto.library.Book;
import com.accent.service.library.ILibraryService;
import com.accent.service.library.LibraryServiceImpl;
import com.accent.validator.BookValidator;

import io.swagger.annotations.Api;

/**
 * This class indicates Rest controller which recieve inputs as http request and 
 * return http response in xml and json format along with status code.
 * 
 * @author Tushar
 * @version 1.0
 * @since 2017-01-14
 * 
 */
@RestController
@Api(description="Book Library")
public class LibraryController  {


	@Autowired
	private ILibraryService libService;
	@Autowired 
	private ILibraryMapper libMapper;
	@Autowired
	private BookValidator bookval;
	@Autowired
	private MessageSource messageSource;
	private  static Logger logger = LoggerFactory.getLogger(LibraryController.class);

	/**
	 * This addBookmethod is used  to add details of book in DB[hashmap] and
	 * returns status code on 201 on successfully creation.It accepts only xml and json format. 
	 *  
	 * @param bookBean This is first parameter to addBook method. 
	 * @param result   This is second parameter to addBook method.
	 * @return  This return different status code based on success or failure of method.
	 * @exception NullPointerException on ISBN null value.
	 * @see Book details.
	 */
	@PostMapping(value = {"/book"},consumes={"application/xml","application/json"})
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public void addBook(@RequestBody BookBean bookBean,BindingResult bindingResult) {
		logger.debug("inside add book method");
		Book book = new Book();
		libMapper.mapBookBeanToBook(bookBean,book);
		Integer libBookId = bookBean.getBookId();
		bookval.validate(bookBean, bindingResult);
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList();
			String locale="es";
			String value;
			for (Object object : bindingResult.getAllErrors()) {
				if(object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					logger.debug("Prints field error code");
					value = messageFromTemplate(fieldError.getCode(),locale);
					logger.debug("Prints field error value");
					errorList.add(value);
				}
			}
			throw new NullPointerException(errorList.toString());
		}
		if (libBookId != null) {
			book.setBookId(bookBean.getBookId());
		}
		libService.saveLibBook(book);
		logger.debug("Exit add book method");
	}

	/**
	 * This deleteBook method is used to delete book details based on ID and return status 
	 * code based on success or failure.
	 * @param bookId This parameter is BookId which is part of http request.
	 * @return Nothing.
	 * @exception Unused.
	 * 
	 */
	@RequestMapping(value = {"/book/{bookId}" },/*consumes={"application/xml","application/json"},*/ method = RequestMethod.DELETE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public void deleteBook(@PathVariable Integer bookId) {
		logger.debug("inside delete method");
		libService.deleteLibBook(bookId);
		logger.debug("Book deleted succesfully");
	}

	/**
	 * This modifyBook method is used to modify book details based on ID and return status 
	 * code based on success or failure.
	 * @param bookId This parameter is BookId input to modifyBook which is part of http request.
	 */
	@RequestMapping(value = { "/book/{bookId}" },consumes={"application/xml","application/json"}, method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)
	public void modifyBook(@RequestBody BookBean bookBean, @PathVariable Integer bookId) {
		logger.debug("inside modify method");
		Book book = new Book();
		libMapper.mapBookBeanToBook(bookBean,book);
		book.setBookId(bookId);
		libService.saveLibBook(book);
	}
	/**
	 * This showAllBook method is used to show all books of library.	
	 * @return booBeanList This returns the list of all books.
	 * 
	 */
	@RequestMapping(value = {"/books"},/*consumes={"application/xml","application/json"},*/
			produces={"application/xml"}, method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	public BookBeanList showAllBook() {
		logger.debug("inside showAll method");
		List<Book> books = libService.showAllLibBook();
		if(StringUtils.isEmpty(books)) {
			throw new ValidationException();
		}
		List<BookBean> bookBeans = libMapper.mapBooksToBookBeans(books);
		logger.debug("All Book displyed succesfully");
		BookBeanList bookBeanList = new BookBeanList();
		bookBeanList.setBookList(bookBeans);
		return bookBeanList;
	}
	/**
	 * This showBook method shows only one book based on bookId. 
	 * @param bookId This parameter take bookId as input from http request.
	 * @return This BookBean returns one book.
	 * @throws IOException if book is not available then throws IOException.
	 */
	@RequestMapping(value = "/book/{bookId}",/*consumes={"application/xml","application/json"},*/
			produces={"application/xml"}, method = RequestMethod.GET)
	@ResponseBody
	public BookBean showBook(@PathVariable Integer bookId) throws IOException {
		logger.debug("inside show method");
		Book book = libService.getLibraryBook(bookId);
		if(StringUtils.isEmpty(book)) {
			throw new IOException();
		}	
		BookBean bookBean = new BookBean();
		libMapper.mapBookToBookBean(bookBean,book);
		logger.debug("Book displyed succesfully");
		return bookBean;
	}
	/**
	 * This is default handler method which responded to bad URL.
	 * @param Unused.
	 * @exception  Generic error on bad request. 
	 * @throws GenericError if input is bad request then return Generic error.
	 * @see Generic error. 
	 */

	/**
	 * This messageFromTemplate resource key value from template.
	 * @param templateKey This is first parameter to messageFromTemplate. 
	 * @param locale This is second parameter to messageFromTemplate.
	 * @return This returns value respective to key passed.
	 */
	public String messageFromTemplate(String templateKey,String locale){
		logger.debug("inside messageFromTemplate");
		return messageSource.getMessage(templateKey, null, new Locale(locale));
	}
}
