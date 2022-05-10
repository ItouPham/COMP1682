package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.dto.req.AddNewAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.EditAccountReqDTO;
import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.exception.AccountNotFoundException;
import com.final_project.chriscosmetic.repository.AccountRepository;
import com.final_project.chriscosmetic.service.AccountService;
import com.final_project.chriscosmetic.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public void addNewAccount(AddNewAccountReqDTO addNewAccountReqDTO) {
        accountRepository.save(addNewAccountReqDTOtoAccount(addNewAccountReqDTO));
    }

    @Override
    public void editAccount(EditAccountReqDTO editAccountReqDTO) {
        accountRepository.save(editAccountReqDTOtoAccount(editAccountReqDTO));
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    private Account addNewAccountReqDTOtoAccount(AddNewAccountReqDTO addNewAccountReqDTO) {
        Account account = new Account();
        account.setLastName(addNewAccountReqDTO.getLastName());
        account.setFirstName(addNewAccountReqDTO.getFirstName());
        account.setEmail(addNewAccountReqDTO.getEmail());
        account.setPassword(addNewAccountReqDTO.getPassword());
        account.setRoles(Collections.singleton(roleService.findById(addNewAccountReqDTO.getRoleID())));
        account.setPassword(bCryptPasswordEncoder.encode(addNewAccountReqDTO.getPassword()));
        account.setAddress(addNewAccountReqDTO.getAddress());
        account.setTelephone(addNewAccountReqDTO.getTelephone());

        return account;
    }

    private Account editAccountReqDTOtoAccount(EditAccountReqDTO editAccountReqDTO) {
        Account account = new Account();
        account.setId(editAccountReqDTO.getId());
        account.setLastName(editAccountReqDTO.getLastName());
        account.setFirstName(editAccountReqDTO.getFirstName());
        account.setEmail(editAccountReqDTO.getEmail());
        account.setRoles(Collections.singleton(roleService.findById(editAccountReqDTO.getRoleID())));
        account.setAddress(editAccountReqDTO.getAddress());
        account.setTelephone(editAccountReqDTO.getTelephone());

        if (editAccountReqDTO.getPassword() != null) {
            account.setPassword(bCryptPasswordEncoder.encode(editAccountReqDTO.getPassword()));
        }

        return account;
    }
}
