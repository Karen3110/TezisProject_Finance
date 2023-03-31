package com.example.tezis.repository.excel;

import com.example.tezis.dao.model.excel.ExcelCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelCellRepository extends JpaRepository<ExcelCell, Integer> {
}
