package com.aspire.bo.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.bo.ItemBo;
import com.aspire.dao.GenericDao;
import com.aspire.model.Item;

public class ItemBoImpl implements ItemBo {

	private Logger log = Logger.getLogger(ItemBoImpl.class.getName());
	@Autowired
	GenericDao<Item, Long> itemDao;

	/*
	 * Read the item from the database
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.bo.ItemBo#read(java.lang.Long)
	 */
	@Transactional
	@Override
	public Item findById(Long id) throws Exception {
		log.info("Finding Item with ID = " + id);
		return (Item) itemDao.findById(id);
	}

	/*
	 * Create or update the item
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.bo.ItemBo#createOrUpdate(com.crud.model.Item)
	 */
	@Override
	public void createOrUpdate(Item item) throws Exception {
		log.info("Executing method : createOrUpdate()");
		if (item.getId() != null && !item.getId().equals("")) {
			itemDao.update(item);
		} else {
			itemDao.create(item);
		}
	}

	/*
	 * Get the list of all items
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.bo.ItemBo#getAllItems()
	 */
	@Override
	public List<Item> getAllItems() throws Exception {
		log.info("Executing method : getAllItems()");
		return itemDao.findAll();
	}

	/*
	 * Delete the item
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.crud.bo.ItemBo#delete(com.crud.model.Item)
	 */
	@Override
	public void delete(Item item) throws Exception {
		log.info("Deleting item with id = " + item.getId());
		itemDao.delete(item);
	}

}
