package com.example.tezis.repository;

import com.example.tezis.dao.model.excel.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<ExcelFile, Integer> {

    ExcelFile findByFileName(String fileName);




}
