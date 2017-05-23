package com.dev.task;

/**
 * Class represent the client.
 */
public class Client {

    private static Client client = null;
    private String email = null;
    private String password = null;

    private Client(){
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setEmail( String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static Client getInstance(){
        if (client == null){
            client = new Client();
        }
        return client;
    }
}
