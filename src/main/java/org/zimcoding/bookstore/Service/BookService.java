/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.bookstore.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import org.zimcoding.bookstore.Entity.Book;
import org.zimcoding.bookstore.Repository.BookRepository;


import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements CrudListener<Book> {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
