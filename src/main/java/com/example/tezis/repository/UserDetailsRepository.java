package com.example.tezis.repository;

import com.example.tezis.dao.model.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    UserDetails getUserDetailsByUserLoginId(int id);

    @Modifying
    @Query(value = " INSERT INTO user_details_filled_files(user_details_id,filled_files_id) values(:userId, :fileId) ", nativeQuery = true)
    void assignFileToUser(@Param("userId") int userId, @Param("fileId") int fileId);

//
}
