package com.aspire.bo;

import java.util.List;

import com.aspire.model.Item;

public interface ItemBo {
	Item findById(Long id) throws Exception;

	void createOrUpdate(Item item) throws Exception;
	
	void delete(Item item) throws Exception;

	List<Item> getAllItems() throws Exception;
}
