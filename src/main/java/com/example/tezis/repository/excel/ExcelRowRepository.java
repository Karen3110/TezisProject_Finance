package com.example.tezis.repository.excel;

import com.example.tezis.dao.model.excel.ExcelRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRowRepository extends JpaRepository<ExcelRow, Integer> {
}
