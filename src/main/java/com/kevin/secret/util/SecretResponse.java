package com.kevin.secret.util;

/**
 * @author dengkai
 */
public class SecretResponse {
    private String encryptKey;
    private String encryptData;
    private String sign;

    public SecretResponse() {
    }

    public String getEncryptKey() {
        return this.encryptKey;
    }

    public String getEncryptData() {
        return this.encryptData;
    }

    public String getSign() {
        return this.sign;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public void setEncryptData(String encryptData) {
        this.encryptData = encryptData;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SecretResponse)) {
            return false;
        } else {
            SecretResponse other = (SecretResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$encryptKey = this.getEncryptKey();
                    Object other$encryptKey = other.getEncryptKey();
                    if (this$encryptKey == null) {
                        if (other$encryptKey == null) {
                            break label47;
                        }
                    } else if (this$encryptKey.equals(other$encryptKey)) {
                        break label47;
                    }

                    return false;
                }

                Object this$encryptData = this.getEncryptData();
                Object other$encryptData = other.getEncryptData();
                if (this$encryptData == null) {
                    if (other$encryptData != null) {
                        return false;
                    }
                } else if (!this$encryptData.equals(other$encryptData)) {
                    return false;
                }

                Object this$sign = this.getSign();
                Object other$sign = other.getSign();
                if (this$sign == null) {
                    if (other$sign != null) {
                        return false;
                    }
                } else if (!this$sign.equals(other$sign)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SecretResponse;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $encryptKey = this.getEncryptKey();
        result = result * 59 + ($encryptKey == null ? 43 : $encryptKey.hashCode());
        Object $encryptData = this.getEncryptData();
        result = result * 59 + ($encryptData == null ? 43 : $encryptData.hashCode());
        Object $sign = this.getSign();
        result = result * 59 + ($sign == null ? 43 : $sign.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "SecretResponse(encryptKey=" + this.getEncryptKey() + ", encryptData=" + this.getEncryptData() + ", sign=" + this.getSign() + ")";
    }
}
