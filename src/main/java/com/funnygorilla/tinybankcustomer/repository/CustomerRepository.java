package com.funnygorilla.tinybankcustomer.repository;  

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funnygorilla.tinybankcustomer.repository.dataaccess.CustomerDAO;
import com.funnygorilla.tinybankcustomer.repository.dataaccess.CustomerDB;
import com.funnygorilla.tinybankcustomer.services.domain.entity.CustomerBO;
 

@Repository
public class CustomerRepository  
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerDAO customerDAO;

	/**
	 * 
	 * @return
	 */
	public List<CustomerBO> getAllCustomers(){
		List<CustomerDB> dbs = this.customerDAO.findAll();
		
		if (dbs == null) return null;
		
		List <CustomerBO> bos = new ArrayList<CustomerBO> ();
		
		CustomerBO bo = null;
		
		Iterator<CustomerDB> r = dbs.iterator();
		while (r.hasNext()) {
			bo = new CustomerBO();
			// simple data model transformation
			BeanUtils.copyProperties(r.next(), bo);
			bos.add(bo);
		}
		
		return bos;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public CustomerBO getCustomerByID(Long id) {
		CustomerDB db = this.getCustomerDB(id);
		
		// simple data model transformation from table entity to business object
		CustomerBO bo = new CustomerBO();
		BeanUtils.copyProperties(db, bo);
		
		return bo;
	}
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public Long createCustomer(CustomerBO student) {
		CustomerDB db = new CustomerDB();
		BeanUtils.copyProperties(student, db);
		db = this.customerDAO.saveAndFlush(db);
		logger.debug("--> customer " + db.toString() +  " created.");
		return db.getId();
	}
	/**
	 * 
	 * @param customer
	 * @return
	 */
	public CustomerBO updateCustomer (CustomerBO sbo ) {
		Long targetID = sbo.getId();
		CustomerDB sdb = new CustomerDB();
		
		// retrieve student from repository
		sdb = this.getCustomerDB(targetID);
		
		// if it does not exist return null;
		if (sdb == null) {
			logger.warn("--> customer " + targetID +  " does not exist!");
			return null;
		}
		
		// if it exist, update it and return updated record.
		BeanUtils.copyProperties(sbo, sdb);
        sdb = this.customerDAO.saveAndFlush(sdb);
        
        CustomerBO updatedSt = new CustomerBO();
        BeanUtils.copyProperties(sdb, updatedSt);
	        
        logger.debug("--> customer " + updatedSt.toString() +  " updated.");
		return updatedSt;
	}
	/**
	 * 
	 * @param custoemrId
	 */
	public boolean deleteCustomer (Long studentId) {
		CustomerDB sdb = this.getCustomerDB(studentId);
		
		if (null == sdb) {
			logger.debug("--> customer " + studentId +  " does not exit and nothing has been deleted.");
			return false;
		}
		
		this.customerDAO.deleteById(studentId);
		logger.debug("--> customer " + studentId +  " deleted.");
		return true;
	}
	/**
	 * retrieve customer record by id.
	 * @param id
	 * @return
	 */
	private CustomerDB getCustomerDB (Long id) {
		
		CustomerDB sdb = this.customerDAO.findById(id).orElse(null);
		if (null == sdb) {
			logger.warn("--> customer " + id +  " does not exist!");
		}
		return sdb;
	}

}