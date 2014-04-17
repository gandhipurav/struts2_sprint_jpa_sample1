package com.aspire.web.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.aspire.bo.ItemBo;
import com.aspire.model.Item;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SaveItemAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected Logger log = Logger.getLogger(SaveItemAction.class.getName());

	protected String FAILURE = "failure";
	protected String INVALID = "inValid";

	ItemBo itemBo;
	HttpServletRequest request;
	Map<String, Object> session = ActionContext.getContext().getSession();

	public void setItemBo(ItemBo itemBo) {
		this.itemBo = itemBo;
	}

	Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Save Item
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		log.info("Executing method : saveItem()");
		String forward = INVALID;
		if (session.get("user") != null) {
			forward = FAILURE;
			try {
				item.setCreatedDate(new Timestamp(new Date().getTime()));
				item.setModifiedDate(new Timestamp(new Date().getTime()));
				item.getColor().setItem(item);
				itemBo.createOrUpdate(item);
				List<Item> items = itemBo.getAllItems();
				session.put("items", items);
				forward = SUCCESS;
			} catch (Exception e) {
				clearActionErrors();
				log.error("Error in saving item " + e);
				addActionError(getText("error.item.save"));
			}
		}
		return forward;
	}

	/**
	 * Edit Item
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		log.info("Executing method  : edit()");
		String forward = INVALID;
		if (session.get("user") != null) {
			forward = FAILURE;
			try {
				request = (HttpServletRequest) ActionContext.getContext().get(
						ServletActionContext.HTTP_REQUEST);
				item = itemBo.findById(Long.parseLong(request
						.getParameter("id")));
				forward = SUCCESS;
			} catch (Exception e) {
				clearActionErrors();
				log.error("Error editting item " + e);
				addActionError(getText("error.item.edit"));
			}
		}
		return forward;
	}

	/**
	 * Delete Item
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		log.info("Executing method  : delete()");
		String forward = INVALID;
		if (session.get("user") != null) {
			forward = FAILURE;
			try {
				request = (HttpServletRequest) ActionContext.getContext().get(
						ServletActionContext.HTTP_REQUEST);
				Item item = itemBo.findById(Long.parseLong(request
						.getParameter("id")));
				if (item != null) {
					itemBo.delete(item);
				}
				List<Item> items = itemBo.getAllItems();
				session.put("items", items);
				forward = SUCCESS;
			} catch (Exception e) {
				clearActionErrors();
				log.error("Error deleting item " + e);
				addActionError(getText("error.item.view"));
			}
		}
		return forward;
	}

	/**
	 * View Item
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		log.info("Executing method  : view()");
		String forward = INVALID;
		if (session.get("user") != null) {
			forward = FAILURE;
			try {
				List<Item> items = itemBo.getAllItems();
				session.put("items", items);
				forward = SUCCESS;
			} catch (Exception e) {
				clearActionErrors();
				log.error("Error viewing item " + e);
				addActionError(getText("error.item.view"));
			}
		}
		return forward;
	}

	/**
	 * Add Item
	 * 
	 * @return
	 */
	public String add() {
		log.info("Executing method  : add()");
		String forward = INVALID;
		if (session.get("user") != null) {
			forward = SUCCESS;
		}
		return forward;

	}

	public void validateSave() {
		log.info("Executing method : validate()");
		clearFieldErrors();
		if (item.getName() == null || item.getName().trim().equals("")) {
			addFieldError("item.name", getText("errors.require.name"));
		}
		if (item.getDescriptions() == null
				|| item.getDescriptions().trim().equals("")) {
			addFieldError("item.descriptions",
					getText("errors.require.description"));
		}
		if (item.getColor().getColor() == null
				|| item.getColor().getColor().trim().equals("")) {
			addFieldError("item.color.color", getText("errors.require.color"));
		}
	}
}
