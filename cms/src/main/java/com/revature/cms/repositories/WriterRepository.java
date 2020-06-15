package com.revature.cms.repositories;


import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.revature.cms.models.Writer;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer>{

//	
	 List<Writer> writers = new ArrayList<Writer>();
//	 
//	 public default List<Writer> findAll(){
//		 return writers;
//	 }
//	 
//	 public Writer save(Writer writer) {
//		 if(writer.getId()==null) {
//			 
//		 }
//	 }
//	 
	 
	//
//	 Writer findByName(String name);
//
//	  // We can also write methods and annotate them with @Query, which lets us write queries to
//	  // implement a method
//	  // This uses HQL, which is Hibernate Query Language. Its like SQL except it refers to objects
//
//	  // This is a findAll that sorts by catId:
//	  @Query("select c from Cat c order by c.catId")
//	  List<Writer> findAllSorted();
//
//	  // Retrieve all cats with names in a Set:
//	  // equivalent SQL: SELECT * FROM cat.cats WHERE name in <set>
//	  // if you want your query to be native instead of HQL, set native=true in the annotation
//	  @Query("select * from Writers where 1")
//	  List<Writer> findCatsWithNamesInSet(Set<String> catNames);
}
