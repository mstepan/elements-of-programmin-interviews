package com.max.algs.epi.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AddBinaryStringsTest {

    @Test
    public void addBinaryStrings() {
        assertEquals("10011", AddBinaryStrings.addBinaryStrings("0110", "1101"));
    }

}