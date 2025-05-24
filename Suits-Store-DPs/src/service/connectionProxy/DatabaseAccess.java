package service.connectionProxy;

public interface DatabaseAccess {
    public boolean verifyCredentials();
    public String getSecureUsername();
    public String getSecurePassword();
}
