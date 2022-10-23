package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BookManager {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }

    public Book saveDTO(BookDTO bookDTO) {
        Author author;
        List<Author> authorList = authorRepository.findAuthorByNameAndSurname(bookDTO.getAuthor_name(), bookDTO.getAuthor_surname());
        if (authorList.isEmpty()) {
            author = authorRepository.save(new Author(bookDTO.getAuthor_name(), bookDTO.getAuthor_surname()));
        }
        else {
            author = authorList.get(0);
        }

        Category category = categoryRepository.findByName(bookDTO.getCategory_name()).orElse(null);
        if (category == null) {
            category = categoryRepository.save(new Category(bookDTO.getCategory_name()));
        }

        Book book = new Book(bookDTO.getTitle(), bookDTO.getPrice(), bookDTO.getYear(), bookDTO.getDescription(), author, category, null);
        return save(book);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public List<Book> findByTitleContainingIgnoreCase(String name) {
        return bookRepository.findByTitleContainingIgnoreCase(name);
    }

    public List<Book> searchForBook(String name) {
        List<Book> titleSearch = bookRepository.findByTitleContainingIgnoreCase(name);

        List<Author> authorListName = authorRepository.findAuthorByNameContainingIgnoreCase(name);
        List<Author> authorListSurname = authorRepository.findAuthorBySurnameContainingIgnoreCase(name);

        Set<Author> authorSet = new LinkedHashSet<>(authorListName);
        authorSet.addAll(authorListSurname);
        List<Author> authorList = new ArrayList<>(authorSet);

        Set<Book> bookSet = new LinkedHashSet<>(titleSearch);

        for (Author author : authorList) {
            bookSet.addAll(author.getBooks());
        }

        return new ArrayList<>(bookSet);

    }

    public List<Book> findByCategoryId(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    public Page<Book> findProductsWithPagination(int offset, int pageSize) {
        return bookRepository.findAll(PageRequest.of(offset, pageSize));
    }

}
