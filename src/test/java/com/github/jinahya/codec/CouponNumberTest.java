package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.jinahya.codec.CouponNumber.*;
import static java.lang.String.format;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CouponNumberTest {

    @Test
    void encodeDecode2() {
        final Set<String> set = new HashSet<>();
        for (int expected = 0; expected < 256; expected++) {
            final String encoded = CouponNumber.encode2(expected);
            assertNotNull(encoded);
            assertEquals(2, encoded.length());
            log.debug("{} -> {}", format("0x%02x", expected), encoded);
            assertTrue(set.add(encoded));
            final int actual = CouponNumber.decode2(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecode4() {
        final Set<String> set = new HashSet<>();
        for (int expected = 0; expected < 65536; expected++) {
            final String encoded = encode4(expected);
            assertNotNull(encoded);
            assertEquals(4, encoded.length());
            assertTrue(set.add(encoded));
            final int actual = decode4(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecode8() {
        final Set<Integer> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            final int expected = current().nextInt();
            final String encoded = encode8(expected);
            log.debug(" 8: {} -> {}", format("0x%08x", expected), encoded);
            assertNotNull(encoded);
            assertEquals(8, encoded.length());
            assertSame(ds.add(expected), es.add(encoded));
            final int actual = decode8(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecode16() {
        final Set<Long> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            final long expected = current().nextLong();
            final String encoded = encode16(expected);
            log.debug("16: {} -> {}", format("0x%016X", expected), encoded);
            assertNotNull(encoded);
            assertEquals(16, encoded.length());
            assertEquals(ds.add(expected), es.add(encoded));
            final long actual = decode16(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testEncodeDecode7() {
        final Set<Integer> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            final int expected = current().nextInt();
            final String encoded = encode7(expected);
            log.debug("7: {} -> {}", format("%08x", expected), encoded);
            assertNotNull(encoded);
            assertEquals(7, encoded.length());
            assertSame(ds.add(expected), es.add(encoded));
            final int actual = decode7(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testEncodeDecode7WithMagicNumbers() {
        final Set<Integer> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        final int[] magicNumbers = new int[]{Integer.MIN_VALUE, -2, -1, 0, 1, 2, Integer.MAX_VALUE};
        for (final int magicNumber : magicNumbers) {
            final String encoded = encode7(magicNumber);
            log.debug("{} -> {}", format("%08x", magicNumber), encoded);
            assertNotNull(encoded);
            assertEquals(7, encoded.length());
            assertSame(ds.add(magicNumber), es.add(encoded));
            final int actual = decode7(encoded);
            assertEquals(magicNumber, actual);
        }
    }


    @Test
    void testEncodeDecode13() {
        final Set<Long> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 128; i++) {
            final long expected = current().nextLong();
            final String encoded = encode13(expected);
            log.debug("{} -> {}", format("0x%016x", expected), encoded);
            assertNotNull(encoded);
            assertEquals(13, encoded.length());
            assertSame(ds.add(expected), es.add(encoded));
            final long actual = decode13(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testEncodeDecode13WithMagicNumbers() {
        final Set<Long> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        final long[] magicNumbers = new long[]{Long.MIN_VALUE, -2L, -1L, 0L, 1L, 2L, Long.MAX_VALUE};
        for (final long magicNumber : magicNumbers) {
            final String encoded = encode13(magicNumber);
            log.debug("{} -> {}", format("0x%016x", magicNumber), encoded);
            assertNotNull(encoded);
            assertEquals(13, encoded.length());
            assertSame(ds.add(magicNumber), es.add(encoded));
            final long actual = decode13(encoded);
            assertEquals(magicNumber, actual);
        }
    }

    @RepeatedTest(1)
    void testBase36() {
//        for (int i = 0; i < 256; i++) {
//            log.debug("{} -> {}", format("%3d", i), format("%4s", Integer.toString(i, Character.MAX_RADIX)));
//        }
//        log.debug(Integer.toString(0b11, Character.MAX_RADIX));
//        log.debug(Integer.toString(0b0_11111, Character.MAX_RADIX));
//        log.debug(Integer.toString(0b1_000001, Character.MAX_RADIX));
//        log.debug(Integer.toString(0b1_000000, Character.MAX_RADIX));
        final Set<Integer> ys = new HashSet<>();
        final int e = 0b1_0100; // 20
//        final int e = 0b10_0011; // 35
//        final int e = 0b10_1100; // 40
        for (int i = 0; i < 16; i++) {
            int x = i << 1;
            final String xb = Integer.toBinaryString(x);
            final int y = x ^ e;
            final String yb = Integer.toBinaryString(y);
            assertTrue(ys.add(y));
            assertTrue(y <= 35);
            final int z = y ^ e;
            assertEquals(x, z);
        }
        log.debug("ys: {}", ys);
    }
}
