package com.kubernetesdemo.awsuser.common.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void print() {
        Item s = new Item();
        String actual = s.print();
        String expected = "Hello";
        assertEquals(expected, actual);

    }

    @Test
    void add() {
        Item s = new Item();
        assertEquals(5, s.add(2,3));

    }
}