package com.example.tezis.dao.model.excel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class ExcelRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @OneToMany
    private List<ExcelCell> cells = new LinkedList<>();

    public ExcelRow(Row row) {
        for (Cell cell : row) {
            ExcelCell excelCell = new ExcelCell(cell);
            cells.add(excelCell);
        }
    }
}
