package com.example.authservice.service;

import com.example.authservice.dto.request.AuthenticationRequest;
import com.example.authservice.dto.request.IntrospectRequest;
import com.example.authservice.dto.response.AuthenticationResponse;
import com.example.authservice.dto.response.IntrospectResponse;
import com.example.authservice.entity.Account;
import com.example.authservice.exception.AppException;
import com.example.authservice.exception.ErrorCode;
import com.example.authservice.repository.AccountRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    AccountRepository accountRepository;
    @NonFinal
    @Value("${jwt.secret}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = accountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).build();
    }
    private String generateToken(Account user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                //xac dinh tai khoan dang nhap
                .subject(user.getUsername())
                //domain-service
                .issuer("identity-service")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                //cusstom JWT
                .claim("customClaiim","Custom")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch(JOSEException e){
            log.error("Can not create tole",  e);
            throw new RuntimeException(e);
        }
    }
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        //xac nhan khoa cua token
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        //ma hoa token
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verifired = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verifired && expityTime.after(new Date()))
                .build();
    }
}
