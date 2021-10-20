package com.company;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

class ACvillager {
    public String villagerID;
    public String nameEUes;
    public String birthday;
    public String gender;
    public String hobby;
    public String saying;

}

class ACvillagersList {
    public ArrayList<ACvillager> villagersList = new ArrayList<>();
}

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String url = "http://acnhapi.com/v1/villagers/";

        ACvillagersList aCvillagersList = objectMapper.readValue(new URL(url), ACvillagersList.class);

        System.out.println(aCvillagersList.villagersList.size());

        /*for (ACvillager villager : aCvillagersList.villagersList) {
            System.out.println("villagerID: " + villager.villagerID + "\n" +
                    "Name: " + villager.nameEUes + "\n" +
                    "Birthday: " + villager.birthday + "\n" +
                    "Gender: " + villager.gender + "\n" +
                    "Hobby: " + villager.hobby + "\n" +
                    "Saying: " + villager.saying + "\n");
        }*/

    }

}
