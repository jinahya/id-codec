package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static com.github.jinahya.codec.CouponNumber.*;
import static java.lang.String.format;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CouponNumberTest {

    @Test
    void encodeDecodeInt16() {
        final Set<String> set = new HashSet<>();
        for (int expected = 0; expected < 65536; expected++) {
            final String encoded = encodeInt4(expected);
            assertTrue(set.add(encoded));
            final int actual = decodeInt4(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecodeInt0() {
        IntStream.range(0, 128).forEach(expected -> {
            final String encoded = encodeInt8(expected);
            final int actual = decodeInt8(encoded);
            assertEquals(expected, actual);
            log.debug("int {} -> {}", expected, encoded);
        });
    }

    @Test
    void encodeDecodeIntRandom() {
        final Set<Integer> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 1024; i++) {
            final int expected = current().nextInt();
            final String encoded = encodeInt8(expected);
            log.debug("int {} -> {}", format("%08x", expected), encoded);
            assertSame(ds.add(expected), es.add(encoded));
            final int actual = decodeInt8(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecodeInt() {
        final Set<Integer> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 1048576; i++) {
            final int expected = current().nextInt();
            final String encoded = encodeInt8(expected);
//            log.debug("int {} -> {}", expected, encoded);
            assertSame(ds.add(expected), es.add(encoded));
            final int actual = decodeInt8(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecodeInt7() {
        final Set<Long> ds = new HashSet<>();
        final Set<String> es = new HashSet<>();
        for (int i = 0; i < 1; i++) {
            final long expected = current().nextLong();
            final String encoded = encodeInt7(expected);
            log.debug("int {} -> {}", expected, encoded);
            assertSame(ds.add(expected), es.add(encoded));
            final long actual = decodeInt7(encoded);
            assertEquals(expected, actual);
        }
    }
}
