interface Authenticator {
    int isAuthenticated(String email, String password);

    void signUp(String email, String password);
}