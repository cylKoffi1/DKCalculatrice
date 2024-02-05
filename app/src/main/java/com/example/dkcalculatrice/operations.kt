package com.example.dkcalculatrice

import android.widget.Button

@Suppress("DIVISION_BY_ZERO")
class operations {
    private val a:Int = 0;
    private val b:Int = 0;

    fun addition():Int{

        return a+b;
    }


    //Souostraction
    fun soustraction():Int{
        return a-b
    }

    //division
    fun division():Int{

        return if (a>b)  a/b;
        else {
            b/a
        }
    }


    //multiplication
    fun multiplication():Int{
        return a*b
    }

//Modulo
fun modulo(){

}





}