package org.example;

import org.json.JSONException;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {
        System.out.println("Hello World!");


        Endereco e = ViaCEP.buscarCEP("09220740");
        System.out.println(e.toString());



    }
}
