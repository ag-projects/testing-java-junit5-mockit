package com.agharibi.petclinic.controllers;

public class IndexController {

    public String index(){

        return "index";
    }

    public String erroneousHandler(){
        throw new ValueNoFoundException();
    }
}
