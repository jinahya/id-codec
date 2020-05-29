package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static com.github.jinahya.codec.CouponNumber.decode16;
import static com.github.jinahya.codec.CouponNumber.decodeInt;
import static com.github.jinahya.codec.CouponNumber.encode16;
import static com.github.jinahya.codec.CouponNumber.encodeInt;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class CouponNumberTest {

    @Test
    void encodeDecodeInt16() {
        for (int expected = 0; expected < 65536; expected++) {
            final String encoded = encode16(expected);
            final int actual = decode16(encoded);
            assertEquals(expected, actual);
        }
    }

    @RepeatedTest(8192)
    void encodeDecodeInt() {
        final int expected = current().nextInt();
        final String encoded = encodeInt(expected);
        final int actual = decodeInt(encoded);
        assertEquals(expected, actual);
    }

    @Test
    void encodeDecodeInt_duplicate() {
        final Set<String> set = new HashSet<>();
        for (int i = 0; i < 8192; i++) {
            final int expected = current().nextInt();
            final String encoded = encodeInt(expected);
            assertTrue(set.add(encoded));
            final int actual = decodeInt(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void encodeDecodeInt_debug() {
        final int expected = current().nextInt();
        log.debug("expected: {}", expected);
        final String encoded = encodeInt(expected);
        log.debug("encoded: {}", encoded);
        final int actual = decodeInt(encoded);
        assertEquals(expected, actual);
    }
}
