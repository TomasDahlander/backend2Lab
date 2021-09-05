package com.example.backend2lab;

import com.example.backend2lab.domain.model.Account;
import com.example.backend2lab.persistance.AccountRepository;
import com.example.backend2lab.security.JWTIssuer;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Value("${security.signingKey}")
    private String signingKey;

    @Value("${security.algorithm}")
    private String algorithm;

    @Value("${security.validMinutes}")
    private Integer validMinutes;


//    @Bean
//    public AccountRepository userRepository(BCryptPasswordEncoder passwordEncoder) {
//        AccountRepository userRepository = new InMemoryUserRepository();
//        userRepository.save(new Account("admin", passwordEncoder.encode("password"), 0));
//        return userRepository;
//    }

//    @Bean
//    public SignupService signupService(UserRepository userRepository) {
//        SignupService signupService = new SignupService(userRepository);
//        return signupService;
//    }

    @Bean
    public JWTIssuer jwtIssuer() {
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.forName(algorithm);
        final byte[] signingKeyBytes = Base64.encodeBase64(signingKey.getBytes());
        return new JWTIssuer(new SecretKeySpec(signingKeyBytes, signatureAlgorithm.getJcaName()), Duration.ofMinutes(validMinutes));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
