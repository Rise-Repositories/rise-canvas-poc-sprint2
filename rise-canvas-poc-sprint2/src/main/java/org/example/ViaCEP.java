package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCEP {
    private static final HttpClient client = HttpClient.newHttpClient();
    public static Endereco buscarCEP(String cep) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                JSONObject json = new JSONObject(response.body());
                Endereco endereco = new Endereco();

                endereco.setCep(json.getString("cep"));
                endereco.setLogradouro(json.getString("logradouro"));
                endereco.setComplemento(json.getString("complemento"));
                endereco.setBairro(json.getString("bairro"));
                endereco.setLocalidade(json.getString("localidade"));
                endereco.setUf(json.getString("uf"));
                endereco.setIbge(json.getString("ibge"));
                endereco.setGia(json.getString("gia"));
                endereco.setDdd(json.getString("ddd"));
                endereco.setSiafi(json.getString("siafi"));

                // Retorne o objeto Endereco
                return endereco;
            } catch (IOException | InterruptedException | JSONException e) {
                // Trate a exceção aqui
                e.printStackTrace();
                return null;
            }
        }

}
