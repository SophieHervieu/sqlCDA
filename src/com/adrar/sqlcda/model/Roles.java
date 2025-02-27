package com.adrar.sqlcda.model;

public class Roles {
    private int id;
    private String rolesName;

    public Roles(){}

    public Roles(int id, String rolesName){
        this.id = id;
        this.rolesName = rolesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "rolesName='" + rolesName + '\'' +
                '}';
    }
}
