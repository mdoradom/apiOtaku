package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

class Contact {
    String name;
    String phone;
    String email;
}

class Agenda {
    ArrayList<Contact> list;
}

public class Ejercicio {
    public static void main (String[] args) throws IOException {

        /*ObjectMapper objectMapper = new ObjectMapper();
        // leer el fichero json y cargarlo en un objeto "agenda"
        Contact contact = objectMapper.readValue(Paths.get("agenda.json").toFile(), Contact.class);
        Agenda agenda = new Agenda();

        agenda.list = new ArrayList<>();
        agenda.list.add(contact);

        System.out.println(agenda.list);

        // add, eliminar contactos

        // guardar el objecto agenda en el fichero json*/

        Contact contact = new Contact();
        contact.name = "pepe";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(Paths.get("agenda.json").toFile(), contact);

    }
}