/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.controller;

import com.samf.app.dao.DemandaDao;
import com.samf.app.dao.IDemandaDao;
import com.samf.app.entity.Demanda;
import java.util.List;

/**
 *
 * @author Mizael
 */
public class DemandaController {

    private final IDemandaDao controller;

    public DemandaController() {
        this.controller = new DemandaDao();
    }

    public int addDemanda(Demanda demanda) {
        return this.controller.save(demanda);
    }

    public int alterarDemanda(Demanda demanda) {
        return this.controller.update(demanda);
    }

    public int removerDemanda(long codigo) {
        return this.controller.remove(codigo);
    }

      public List<Demanda> findDemandas() {
        return this.controller.findAll();
    }

    /**
     *
     * @return
     */
    public List<Demanda> findFirst() {
        return this.controller.findFirst();
    }

    public List<Demanda> findMonth(int mes, int ano) {
        return this.controller.findMonth(mes, ano);
    }
    
    public List<Demanda> find(String objeto) {
        return this.controller.find(objeto);
    } 
}
