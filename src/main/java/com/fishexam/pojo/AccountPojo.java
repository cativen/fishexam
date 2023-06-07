package com.fishexam.pojo;

public class AccountPojo {
    private int id;
    private String username;
    private String password;
    private int level;

    @Override
    public String toString() {
        return "AccountPojo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                '}';
    }

    public AccountPojo() {
        super();
    }

    public AccountPojo(int id, String username, String password, int level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
