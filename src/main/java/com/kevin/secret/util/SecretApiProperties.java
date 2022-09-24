package com.kevin.secret.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author dengkai
 */
@ConfigurationProperties("com.cihai.secret-api")
public class SecretApiProperties {
    private String privateKey;
    private String publicKey;

    public SecretApiProperties() {
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
