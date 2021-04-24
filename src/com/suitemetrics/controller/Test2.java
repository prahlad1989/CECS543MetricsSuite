/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.suitemetrics.controller;

/**
 *
 * @author prahlad
 */
public class Test2 extends Test1 {
    public Integer a =20;

    
    public static void main(String args[]){
        Test1 t2=new Test2() ;
        System.out.println(t2.getSum());
    }
}
