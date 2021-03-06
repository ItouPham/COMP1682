package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.dto.req.AddNewAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.EditAccountReqDTO;
import com.final_project.chriscosmetic.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    Account findById(String id);
    Page<Account> findAll(int pageNumber);
    Account findByEmail(String email);
    boolean isExistsByEmail(String email);
    void addNewAccount(AddNewAccountReqDTO addNewAccountReqDTO);
    void editAccount(EditAccountReqDTO editAccountReqDTO);
    void deleteAccount(String id);
}
