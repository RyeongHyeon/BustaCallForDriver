package com.example.user.bustacallfordriver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016-11-09.
 */
public class Rental_List {
    List<Rental> rental_list = new ArrayList<>();
    public List<Rental> getRental_list() {
        return rental_list;
    }

    public void setRental_list(List<Rental> rental_list) {
        this.rental_list = rental_list;
    }

}
