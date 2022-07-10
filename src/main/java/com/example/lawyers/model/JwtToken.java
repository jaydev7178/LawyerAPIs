package com.example.lawyers.model;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

// import org.jose4j.jca.ProviderContext.SignatureAlgorithmOverride;
// import org.jose4j.jwa.Algorithm;
// import org.jose4j.jwa.AlgorithmConstraints;
// import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
// import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
// import org.jose4j.jwe.JsonWebEncryption;
// import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
// import org.jose4j.keys.AesKey;
// import org.jose4j.lang.ByteUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.example.lawyers.repository.LawyerRepository;
import com.example.lawyers.service.LawyerService;
import com.google.gson.Gson;

public class JwtToken {
    private String token;
    private int id;
    private String role;
    private String error;
    private String validDateTime;
    @Autowired
    private static LawyerService service =new LawyerService();
    private LawyerRepository repo;

    public JwtToken(String token, int id, String role, String validDateTime, String error) {
        //this.token = token;
        this.id = id;
        this.role = role;
        this.validDateTime = validDateTime;
        this.error = error;
    }

    public JwtToken() {
    }
    public static String genrateToken(int id, String role)
    {
        try {
            // Key key = new AesKey(ByteUtil.randomBytes(16));
            // JsonWebEncryption jwe = new JsonWebEncryption();
            Algorithm algorithmHS = Algorithm.HMAC256("ABCD");
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            //Date date = format.parse(LocalDateTime.now());
            String validDateTime=myDateObj.plusMonths(1).toString();
            
            Map<String, Object> payloadClaims = new HashMap<>();
            payloadClaims.put("id", id);
            payloadClaims.put("role", role);
            payloadClaims.put("validDateTime", validDateTime);
            String token = JWT.create()
                    // .withClaim("id",id)
                    // .withClaim("role",role)
                    // .withClaim("validDateTime",validDateTime)
                    .withPayload(payloadClaims)
                    .sign(algorithmHS);
            //JwtToken jwtToken=new JwtToken(null, id, role, validDateTime);
            //Gson gson = new Gson();
            
            // jwe.setPayload(gson.toJson(jwtToken).toString());
            // jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
            // jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
            // jwe.setKey(key);
            // String serializedJwe = jwe.getCompactSerialization();
            //String serializedJwe =""; 
            //System.out.println("Serialized Encrypted JWE: " + serializedJwe);    
            return token;
        } catch (Exception e) {
            System.out.println("Error is : " + e.getMessage());    
            return "Error : "+e.getMessage();
        }
        
        

    }

    public static JwtToken validateToken(String token, String role )
    {
        
        //LawyerService service=new LawyerService(); 
                    
        try {
             
            // JsonWebEncryption jwe = new JsonWebEncryption();
            // Key key = new AesKey(ByteUtil.randomBytes(16));
            // jwe = new JsonWebEncryption();
            // jwe.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT, KeyManagementAlgorithmIdentifiers.A128KW));
            // jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT, ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256));
            // jwe.setKey(key);
            // jwe.setCompactSerialization(token);
             Algorithm algorithmHS = Algorithm.HMAC256("ABCD");
        //     JWTVerifier verifier = JWT.require(algorithmHS)
        //             .withClaim("role",role)
        //             .withClaim("validDateTime",validDateTime)
        //     .withClaim("role", (claim, decodedJWT) -> role.equals(claim.asString())) //can be used to run custom verification
        //     .build();
        // DecodedJWT jwt = verifier.verify("my.jwt.token");
        JWTVerifier verifier = JWT.require(algorithmHS).build();
        DecodedJWT jwt = verifier.verify(token);
        String strJson=jwt.getPayload();
        Map<String, Claim> headerClaims =jwt.getClaims();
        String strRole=headerClaims.get("role").asString();
        String strId=headerClaims.get("id").toString();
        String strValidDateTime=headerClaims.get("validDateTime").asString();

             Gson gson = new Gson();
         JwtToken jwtToken= new JwtToken(token, Integer.parseInt(strId), strRole, strValidDateTime,null);
            if(LocalDateTime.now().isBefore(LocalDateTime.parse(jwtToken.getValidDateTime())))
            {
                if(jwtToken.getRole().equals("lawyer"))
                {
                    
                        return jwtToken;
                    
                }else{
                    JwtToken output=new JwtToken(token,0,null,null, "error: Invalid user login.");
                    return output ;    
                }
            }else
            {
                JwtToken output=new JwtToken(token,0,null,null, "error: token experienced.");
                return output ;
            }

        } catch (Exception e) {
            //JwtToken jwtToken =new JwtToken();
            //jwtToken.setError(e.getMessage());
            JwtToken output=new JwtToken(token,0,null,null, "error: "+e.getMessage() +". "+e.getStackTrace());
            return output ;
            
            //TODO: handle exception
        }     
    }

    // public String getToken() {
    //     return token;
    // }

    // public void setToken(String token) {
    //     this.token = token;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getValidDateTime() {
        return validDateTime;
    }

    public void setValidDateTime(String validDateTime) {
        this.validDateTime = validDateTime;
    }

     
    
}

