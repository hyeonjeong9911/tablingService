package com.project.tabling.web;

import com.project.tabling.model.Partner;
import com.project.tabling.service.PartnerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartnerAuthController {

    private final PartnerAuthService partnerAuthService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public PartnerAuthController(PartnerAuthService partnerAuthService, AuthenticationManager authenticationManager) {
        this.partnerAuthService = partnerAuthService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/partner/signIn")
    public String register(@RequestBody Partner partner) {
        partnerAuthService.register(partner);
        return "파트너 가입에 성공하였습니다.";
    }

    @PostMapping("/partner/logIn")
    public String login(@RequestBody Partner partner) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(partner.getUsername(), partner.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = partnerAuthService.loadUserByUsername(partner.getUsername());
        return "파트너 로그인에 성공하였습니다.";
    }

}
