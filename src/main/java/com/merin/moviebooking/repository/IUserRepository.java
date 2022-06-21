package com.merin.moviebooking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.merin.moviebooking.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>
{

	@Query("SELECT u FROM User u WHERE u.userName LIKE :userInfo")
	public User fetchDataForLogin(@Param("userInfo") String username);

	
	public boolean existsByUserName(String username);

	
	

}
