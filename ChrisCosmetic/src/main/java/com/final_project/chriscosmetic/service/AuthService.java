package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.dto.req.RegisterReqDTO;
import com.final_project.chriscosmetic.entity.Account;

public interface AuthService {
    void register(RegisterReqDTO registerReqDTO);
    void save(Account account);
}
