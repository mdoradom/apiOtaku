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

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
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
        //addContact("aa", "123", "aa@aa.aa");
        //addContact("bb", "321", "bb@bb.bb");
        //delContact("aa");
        //listContact();
        //listEmailContact("aa@aa.aa");

        if (args.length == 0) {
            System.out.println("Error! inserta algún argumento");
        } else {
            switch (args[0]) {
                case "add":
                    addContact(args[1], args[2], args[3]);
                    break;
                case "del":
                    delContact(args[1]);
                    break;
                case "list":
                    listContact();
                    break;
                case "search":
                    listEmailContact(args[1]);
                    break;
                default:
                    System.out.println("Error! argumento/s no válido/s");
            }
        }

    }

    public static void addContact(String name, String phone, String email) throws IOException {
        try {
            agenda = objectMapper.readValue(file.toFile(), Agenda.class);
        } catch (Exception e) {
            agenda = new Agenda();
        }
        agenda.list.add(new Contact(name,phone,email));
        objectMapper.writeValue(file.toFile(), agenda);
    }

    public static void delContact(String name) throws IOException {
        try {
            agenda = objectMapper.readValue(file.toFile(), Agenda.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Agenda agendaTmp = new Agenda();
        agendaTmp.list.addAll(agenda.list);

        for (Contact contacto : agendaTmp.list) {
            if (contacto.name.equals(name)) {
                agenda.list.remove(contacto);
            }
        }

        objectMapper.writeValue(file.toFile(), agenda);

    }

    public static void listContact() {
        try {
            agenda = objectMapper.readValue(file.toFile(), Agenda.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Contact contacto : agenda.list) {
            System.out.println(contacto.toString());
        }

    }

    public static void listEmailContact(String email) {
        try {
            agenda = objectMapper.readValue(file.toFile(), Agenda.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Contactos encontrados con email: " + email);
        for (Contact contacto : agenda.list) {
            if (contacto.email.equals(email)) {
                System.out.println(contacto);
            }
        }

    }
 }