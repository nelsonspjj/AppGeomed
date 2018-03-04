package com.example.desenvolvimento.teste;

import java.security.PrivateKey;
import java.util.Date;

/**
 * Created by Capitão on 02/04/2017.
 */

public class Atendimento {
    private String genero, perfil, avaliacao, pulso, respiracao, hemorragia, tpocorrecia, obs, dataini, datafin;
    //  private Date data;

    public Atendimento() {

    }

    public Atendimento(String genero, String perfil, String avaliacao, String pulso, String respiracao, String hemorragia, String tpocorrecia, String obs, String dataini, String datafin) {
        this.genero = genero;
        this.perfil = perfil;
        this.avaliacao = avaliacao;
        this.pulso = pulso;
        this.respiracao = respiracao;
        this.hemorragia = hemorragia;
        this.tpocorrecia = tpocorrecia;
        this.obs = obs;
        this.dataini = dataini;
        this.datafin = datafin;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getPulso() {
        return pulso;
    }

    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    public String getRespiracao() {
        return respiracao;
    }

    public void setRespiracao(String respiracao) {
        this.respiracao = respiracao;
    }

    public String getHemorragia() {
        return hemorragia;
    }

    public void setHemorragia(String hemorragia) {
        this.hemorragia = hemorragia;
    }

    public String getTpocorrecia() {
        return tpocorrecia;
    }

    public void setTpocorrecia(String tpocorrecia) {
        this.tpocorrecia = tpocorrecia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDataini() {
        return dataini;
    }

    public void setDataini(String data) {
        this.dataini = data;
    }

    public String getDatafin() {
        return datafin;
    }

    public void setDatafin(String data) {
        this.datafin = data;
    }

}
