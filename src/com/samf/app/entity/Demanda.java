/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.entity;

import java.util.Date;

/**
 *
 * @author Mizael
 */
public class Demanda {

    private Long id;
    private Date data;
    private Long codigo;
    private String concluido;
    private String tramitado;
    private String setor;
    private String descricao;

    public Demanda(Long id,Date data, Long codigo, String concluido, String tramitado, String setor, String descricao) {
        this.id = id;
        this.data = data;
        this.codigo = codigo;
        this.concluido = concluido;
        this.tramitado = tramitado;
        this.setor = setor;
        this.descricao = descricao;
    }

    public Demanda() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getConcluido() {
        return concluido;
    }

    public void setConcluido(String concluido) {
        this.concluido = concluido;
    }

    public String getTramitado() {
        return tramitado;
    }

    public void setTramitado(String tramitado) {
        this.tramitado = tramitado;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
