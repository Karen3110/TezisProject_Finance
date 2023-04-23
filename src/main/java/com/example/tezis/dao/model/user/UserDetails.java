package com.example.tezis.dao.model.user;


import com.example.tezis.dao.model.excel.ExcelFile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    private int userLoginId;
    //todo: karen: when saving file, create new one in db
    @ManyToMany
    private List<ExcelFile> filledFiles = new LinkedList<>();

    public UserDetails(String name, String surname) {
        this.id = 0;
        this.name = name;
        this.surname = surname;
    }

    public boolean hasAssignedFile(int fileId){
        return filledFiles.stream().anyMatch(item -> item.getId() == fileId);
    }


}
