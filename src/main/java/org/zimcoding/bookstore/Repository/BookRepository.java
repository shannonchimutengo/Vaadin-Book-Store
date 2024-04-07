/*
 * Copyright (c) 2024. This Project is free to use(You may sell,duplicate and edit).
 * Visit my Youtube channel @ZimCoding and enjoy more free projects like this cheers...
 */

package org.zimcoding.bookstore.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zimcoding.bookstore.Entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
