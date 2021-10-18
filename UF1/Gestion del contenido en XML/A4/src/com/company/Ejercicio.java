package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

class Contact {
    public String name;
    public String phone;
    public String email;

    public Contact() {}

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}

class Agenda {
    public ArrayList<Contact> list = new ArrayList<>();
}

public class Ejercicio {

    static Agenda agenda;
    static Path file = Paths.get("agenda.json");
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main (String[] args) throws IOException {






            // add, eliminar contactos

            // guardar el objecto agenda en el fichero json*/

    }

    public void addContact(String name, String phone, String email) {
        agenda.list.add(new Contact(name,phone,email));
        try {
            agenda = objectMapper.readValue(file.toFile(), Agenda.class);
        } catch (Exception e) {
            agenda = new Agenda();
        }
    }

    public boolean delContact(String name) {
        return agenda.list.remove(name);
    }

    public void listContact() {
        System.out.println(agenda.list);
    }

    public void listEmailContact() {

    }
 }