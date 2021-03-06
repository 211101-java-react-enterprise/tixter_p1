package com.revature.tixter.models;

import com.revature.nimble.annotations.Column;
import com.revature.nimble.annotations.Key;
import com.revature.nimble.annotations.Table;

@Table(tableName = "tixter_users")
public class Users {
    @Column(columnName = "user_id")
    public String user_id;
    @Column(columnName = "firstname")
    public String firstname;
    @Column(columnName = "lastname")
    public String lastname;
    @Key(keyName = "email")
    public String email;
    @Column(columnName = "password")
    public String password;
    @Column(columnName = "user_age")
    public int age;
    @Column(columnName = "type_id")
    public int type_id;
    @Column(columnName = "role_id")
    public int role_id;

    public Users(){}

    public Users(String firstname,String lastname,String email,String password,int age){
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.password=password;
        this.age=age;
    }

    //___________________________Getter Section_________________________________
    public String getUser_id() {return user_id;}
    public String getFirstname() {return firstname;}
    public String getLastname() {return lastname;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public int getAge() {return age;}
    public int getType_id() {return type_id;}
    public int getRole_id() {return role_id;}
    //___________________________Setter Section_________________________________
    public void setUser_id(String user_id) {this.user_id = user_id;}
    public void setFirstname(String firstname) {this.firstname = firstname;}
    public void setLastname(String lastname) {this.lastname = lastname;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setAge(int age) {this.age = age;}
    public void setType_id(int type_id) {this.type_id = type_id;}
    public void setRole_id(int role_id) {this.role_id = role_id;}
}
