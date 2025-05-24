package service.connectionProxy;

public class DatabaseAccessProxy implements DatabaseAccess{
    private RealDatabaseAccessVerifier realDatabaseAccessVerifier;
    private String username, password, secureUsername, securePassword;
    
    public DatabaseAccessProxy(String username, String password){
        this.username = username;
        this.password = password;
    }

    public RealDatabaseAccessVerifier connect(){
        if(realDatabaseAccessVerifier == null){
            this.realDatabaseAccessVerifier = new RealDatabaseAccessVerifier(secureUsername, password);
        }
        return realDatabaseAccessVerifier;
    }
    public boolean verifyCredentials(){

        // verify with db and return false if not existent
        return realDatabaseAccessVerifier.verifyCredentials();
    }

    @Override
    public String getSecurePassword() {
        return realDatabaseAccessVerifier.getSecurePassword();
    }

    public String getSecureUsername() {
        return realDatabaseAccessVerifier.getSecureUsername();
    }
}
