package com.company;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

class Meme {
    public String name;
    public String species;
    public String gender;
}

class MemeList {
    public ArrayList<Meme> memeList = new ArrayList<>();
}

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String url = "http://hp-api.herokuapp.com/api/characters";

        MemeList memeList = objectMapper.readValue(new URL(url), MemeList.class);

        System.out.println(memeList.memeList.size());

        for (Meme meme : memeList.memeList) {
            System.out.println("villagerID: " + meme.name + "\n" +
                    "Name: " + meme.gender + "\n" +
                    "Birthday: " + meme.species + "\n");
        }

    }

}
