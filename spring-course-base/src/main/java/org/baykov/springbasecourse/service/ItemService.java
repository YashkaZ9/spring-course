package org.baykov.springbasecourse.service;

import org.baykov.springbasecourse.model.Item;
import org.baykov.springbasecourse.model.Person;
import org.baykov.springbasecourse.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepo itemRepo;

    @Autowired
    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<Item> findByOwner(Person owner) {
        return itemRepo.findByOwner(owner);
    }

    public List<Item> findByTitle(String name) {
        return itemRepo.findByTitle(name);
    }
}

