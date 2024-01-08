package com.project.tabling.service;

import com.project.tabling.model.Partner;
import com.project.tabling.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PartnerAuthService implements UserDetailsService {

    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerAuthService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public void register(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner partner = partnerRepository.findByUsername(username);
        if (partner == null) {
            throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                partner.getUsername(),
                partner.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_PARTNER"))
        );
    }

}
