package com.ind.weighing.Indo_weighing.repository;



import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import com.ind.weighing.Indo_weighing.domain.UserDTO;



public interface UserRepo extends MongoRepository<UserDTO, Long>{
	UserDTO findByUsernameAndPassword(String username, String password);
	
	
//	List<UserDTO> findByUsernameAndPassword(String username, String password); 
	
	UserDTO findByFName(String fName);
	
	List<UserDTO> findByLNameLike(String lName);
	
	List<UserDTO> findByAgeGreaterThan(int age);
	
	List<UserDTO> findByLNameOrFName(String lName, String fName);
	
	int deleteByfName(String fName); // not available so we can use modidying
	
	UserDTO findByUsername(String username);

	List<UserDTO> findByAddress(String address);

//	Optional<UserDTO> findById(String user_id);

	Optional<UserDTO> findByUserId(Integer user_id);


	UserDTO findByEmailAndPassword(String email, String password);


	int countByUsername(String username);


	int countByEmail(String email);

//	int updateByLName(String getfName, String address, String getlName);
	
	/*@Modifying
	@Transactional
	@Query("update UserDTO u set u.fName= ?1, u.address = ?2 where u.lName= ?3")
	int updateByLName(String fName, String address, String lName);
	
	@Query("select u from #{#entityName} u where u.address = ?1")
	  List<UserDTO> findByAddress(String address);*/
}
