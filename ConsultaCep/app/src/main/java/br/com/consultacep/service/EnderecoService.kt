package br.com.consultacep.service

import br.com.consultacep.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    //BASE_URL https://viacep.com.br/ws/

    ////https://viacep.com.br/ws/01001000/json
    @GET("{cep}/json/")
    fun getEnderecoByCep(@Path("cep") cep: String): Call<Endereco>

    //https://viacep.com.br/ws/RS/Porto%20Alegre/Domingos/json/
    @GET("{uf}/{cidade}/{rua}/json/")
    fun getEnderecosByUfCidade(
        @Path("uf")
        uf: String,
        @Path("cidade")
        cidade: String,
        @Path("rua")
        rua: String
    ): Call<List<Endereco>>

}