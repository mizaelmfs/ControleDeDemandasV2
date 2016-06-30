/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samf.app.util;

import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JTable;

/**
 *
 * @author Mizaelmfs
 */
public class ImprimirTable {
    
    public ImprimirTable() {
    }
     
    public static void onImprimirTable(JTable table){
        try{
            MessageFormat headerFormat = new MessageFormat("Demandas");
            MessageFormat footerFormat = new MessageFormat("-{0}-");
            table.print(JTable.PrintMode.FIT_WIDTH,headerFormat,footerFormat);
        }catch(PrinterException pr){}
    }
    
}
