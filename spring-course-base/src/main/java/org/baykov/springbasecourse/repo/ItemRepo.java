package org.baykov.springbasecourse.repo;

import org.baykov.springbasecourse.model.Item;
import org.baykov.springbasecourse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findByOwner(Person owner);
    List<Item> findByTitle(String name);
}
