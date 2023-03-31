package com.example.tezis.repository.excel;

import com.example.tezis.dao.model.excel.ExcelSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelSheetRepository extends JpaRepository<ExcelSheet, Integer> {
}
