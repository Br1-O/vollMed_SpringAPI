package med.voll.api.model.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.model.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWT_tokenService {

    //env variable
        @Value("${api.security.secret}")
        private String JWTSecret;

    //generate JWT
        public String generateJWT(User user){
            try {
                Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
                return JWT.create()
                        .withIssuer("voll_med")
                        .withSubject(user.getUsername())
                        .withClaim("id", user.getId())
                        .withExpiresAt(expiresAt(2))
                        .sign(algorithm);
            } catch (JWTCreationException exception){
                return "¡Error creating JWT!";
            }
        }

    //generate expiration date
        public Instant expiresAt(int hours){
            return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-05:00"));
        }

    //get username from JWT
        public String getSubject(String token) {

            if(token== null){
                throw new RuntimeException("JWT is required!");
            }

            DecodedJWT verifier;

            try {
                Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
                verifier = JWT.require(algorithm)
                        .withIssuer("voll_med")
                        .build()
                        .verify(token);
                verifier.getSubject();
            } catch (JWTVerificationException exception) {
                return "¡Error fetching subject from JWT!";
            }

            if (verifier.getSubject() == null) {
                throw new RuntimeException("¡Subject in JWT is required!");
            }
            return verifier.getSubject();
        }

}
