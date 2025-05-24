package service.connectionProxy;

public class RealDatabaseAccessVerifier implements DatabaseAccess{
    private String username, password, secureUsername, securePassword;

    public RealDatabaseAccessVerifier(String username, String password){
        this.username = username;
        this.password = password;

        this.secureUsername = "" + username.hashCode();
        this.password = "" + password.hashCode();
    }
    public boolean verifyCredentials(){
        // verify with db and return false if not existent
        return true;
    }

    @Override
    public String getSecurePassword() {
        // TODO Auto-generated method stub
        return securePassword;
    }

    public String getSecureUsername() {
        return secureUsername;
    }
}
