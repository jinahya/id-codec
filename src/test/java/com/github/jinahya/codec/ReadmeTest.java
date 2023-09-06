package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.fromString;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ReadmeTest {

    @Test
    void testLong() {
        final int radix = Character.MAX_RADIX;
        final int scale = IdCodecConstants.MAX_SCALE;
        final IdEncoder encoder = new IdEncoder(scale, radix);
        final IdDecoder decoder = new IdDecoder(scale, radix);
        final long expected = -8775087020812241672L;
        for (int i = 0; i < 8; i++) {
            final String encoded = encoder.encode(expected, current());
            log.debug("encoded: {}", encoded);
            assertEquals(25, encoded.length());
            final long actual = decoder.decode(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testUuid() {
        final int radix = Character.MAX_RADIX;
        final int scale = IdCodecConstants.MAX_SCALE;
        final IdEncoder encoder = new IdEncoder(scale, radix);
        final IdDecoder decoder = new IdDecoder(scale, radix);
        final UUID expected = fromString("1b3a263e-0928-4ad1-b728-742d0d06506e");
        for (int i = 0; i < 8; i++) {
            final String encoded = encoder.encodeUuid(expected, current());
            log.debug("encoded: {}", encoded);
            final UUID actual = decoder.decodeUuid(encoded);
            assertEquals(expected, actual);
        }
    }
}
