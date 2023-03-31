package com.egor.shop.repo;


import com.egor.shop.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository <Item,Long> {

}
