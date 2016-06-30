/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.dao;

import com.samf.app.entity.Demanda;
import java.util.List;

/**
 *
 * @author Mizael
 */
public interface IDemandaDao {

    int save(Demanda demanda);

    int update(Demanda demanda);

    int remove(long id);

    List<Demanda> findAll();

    List<Demanda> findFirst();

    List<Demanda> find(String objeto);

    List<Demanda> findMonth(int mes, int ano);
}
