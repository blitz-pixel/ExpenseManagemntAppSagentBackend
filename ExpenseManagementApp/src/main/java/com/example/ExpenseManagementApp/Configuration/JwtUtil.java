package com.example.ExpenseManagementApp.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;


// Comment for testing
@Component
public class JwtUtil {

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret"; // 256-bit secret key

    // Generate token with the given username
    public String generateToken(String userName) {
        return JWT.create()
                .withSubject(userName)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Token valid for 30 minutes
                .sign(Algorithm.HMAC256(SECRET_KEY));  // Use HMAC256 algorithm with the secret key
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
//        return username;
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        final DecodedJWT decodedJWT = decodeToken(token);
        return claimsResolver.apply(decodedJWT);
    }

    // Decode the token and get the claims
    private DecodedJWT decodeToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build(); // Create the verifier with the specified algorithm and secret key
        return verifier.verify(token); // Verify and decode the token
    }

    // Check if the token is expired
    public Boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, DecodedJWT::getExpiresAt); // Correct method to get expiration date
        return expiration.before(new Date());
    }

    // Validate the token by checking the username and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
