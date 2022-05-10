package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.constant.Role;
import com.final_project.chriscosmetic.dto.req.RegisterReqDTO;
import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.exception.EmailAlreadyExistException;
import com.final_project.chriscosmetic.repository.AccountRepository;
import com.final_project.chriscosmetic.repository.RoleRepository;
import com.final_project.chriscosmetic.service.AuthService;
import com.final_project.chriscosmetic.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private AccountRepository accountRepository;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(AccountRepository accountRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(RegisterReqDTO registerReqDTO) {
        if(accountRepository.existsByEmail(registerReqDTO.getEmail())){
            throw new EmailAlreadyExistException();
        }

        Account account = new Account();
        account.setEmail(registerReqDTO.getEmail());
        account.setFirstName(registerReqDTO.getFirstName());
        account.setLastName(registerReqDTO.getLastName());
        account.setRoles(Collections.singleton(roleService.findByRoleName(Role.CUSTOMER.name())));
        account.setPassword(bCryptPasswordEncoder.encode(registerReqDTO.getPassword()));
        accountRepository.save(account);
    }
}
