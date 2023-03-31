package com.example.tezis.repository.excel;

import com.example.tezis.dao.model.excel.ExcelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelModelRepository extends JpaRepository<ExcelModel, Integer> {
}
