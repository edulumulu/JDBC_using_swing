/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author eduardolucasmunozdelucas
 */
public class Empleado {
    
    private int id;
    private String user;
    private String password;
    private String name;
    private String surname;
    private int tlf;
    private String email;

    public Empleado(int id, String user, String password, String name, String surname, int tlf, String email) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.tlf = tlf;
        this.email = email;
    }

    
    public Empleado(String user, String password, String name, String surname, int tlf, String email) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.tlf = tlf;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empleado --> {" +
                "id='" + id + '\'' +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tlf=" + tlf +
                ", email='" + email + '\'' +
                '}';
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
