package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.dto.req.AddNewAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.EditAccountReqDTO;
import com.final_project.chriscosmetic.entity.Account;

import java.util.List;

public interface AccountService {
    Account findById(String id);
    List<Account> findAll();
    Account findByEmail(String email);
    boolean isExistsByEmail(String email);
    void addNewAccount(AddNewAccountReqDTO addNewAccountReqDTO);
    void editAccount(EditAccountReqDTO editAccountReqDTO);
    void deleteAccount(String id);
}
