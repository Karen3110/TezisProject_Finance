package com.example.tezis.repository;

import com.example.tezis.dao.model.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    UserDetails getUserDetailsByUserLoginId(int id);

    @Modifying
    @Query(value = " INSERT INTO user_details_filled_files(user_details_id,filled_files_id,orignal_file_id) values(:userId, :fileId,:originalId) ",
            nativeQuery = true)
    void assignFileToUser(@Param("userId") int userId, @Param("fileId") int fileId, @Param("originalId") int originalId);

    @Modifying
    @Query(value = " SELECT * FROM user_details_filled_files WHERE user_details_id=:userId AND orignal_file_id=:fileId",
            nativeQuery = true)
    List<Object> getAssignedFileId(@Param("userId") int userId, @Param("fileId") int fileId);
}
