/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.UIandField;

/**
 * Пары
 *
 * @author Admin 
 */
public class Pair<A, B> {

    private A first;
    private B second;

    /**
     * Конструктор
     *
     * @param first первый элемент пары
     * @param second второй элемент пары
     */
    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }
    /**
     * Первый геттер
     */
    public A getFirst() {
        return first;
    }

    /**
     * Второй геттер
     */
    public B getSecond() {
        return second;
    }
    /**
     * Первый сеттер
     *
     * @param first - первый элемент
     */
    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * Сеттер для пары
     *
     * @param second - второй элемент
     */
    public void setSecond(B second) {
        this.second = second;
    }

    /**
     * Сравнение
     *
     * @param other - другая пара
     */
    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return ((this.first == otherPair.first
                    || (this.first != null && otherPair.first != null
                    && this.first.equals(otherPair.first)))
                    && (this.second == otherPair.second
                    || (this.second != null && otherPair.second != null
                    && this.second.equals(otherPair.second))));
        }

        return false;
    }

  

}
