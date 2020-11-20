package com.birkan.rig.repository;

import com.birkan.rig.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.pkid in :idList")
    List<Book> fetchBooksByIdList(List<Long> idList);

    @Override
    Optional<Book> findById(Long aLong);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT b FROM Book b WHERE b.pkid = :aLong")
    Optional<Book> findBookForSellingById(Long aLong);
}
