package com.funnygorilla.tinybankcustomer.repository.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author FunnyGorilla
 *
 */
public interface CustomerDAO extends JpaRepository<CustomerDB, Long> {
	
//	@Query("SELECT s FROM Student s WHERE s.name=(:name)")
//	List<StudentDB> findByLastName(@Param("name") String name);	

}
