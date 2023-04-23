package com.example.tezis.repository;

import com.example.tezis.dao.model.excel.ExcelFile;
import com.microsoft.schemas.office.office.CTOLEObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<ExcelFile, Integer> {

    ExcelFile findByFileName(String fileName);



}
