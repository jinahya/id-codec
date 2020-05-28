package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.github.jinahya.codec.IdCodecBase.MAX_SCALE;
import static java.lang.Character.MAX_RADIX;
import static java.util.UUID.fromString;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ReadmeTest {

    @Test
    void testLong() {
        final int radix = MAX_RADIX;
        final int scale = MAX_SCALE;
        final IdEncoder encoder = new IdEncoder(radix, scale);
        final IdDecoder decoder = new IdDecoder(radix, scale);
        final long expected = -8775087020812241672L;
        for (int i = 0; i < 8; i++) {
            final String encoded = encoder.encode(expected, current());
            log.debug("encoded: {}", encoded);
            final long actual = decoder.decode(encoded);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testUuid() {
        final int radix = MAX_RADIX;
        final int scale = MAX_SCALE;
        final IdEncoder encoder = new IdEncoder(radix, scale);
        final IdDecoder decoder = new IdDecoder(radix, scale);
        final UUID expected = fromString("1b3a263e-0928-4ad1-b728-742d0d06506e");
        for (int i = 0; i < 8; i++) {
            final String encoded = encoder.encodeUuid(expected, current());
            log.debug("encoded: {}", encoded);
            final UUID actual = decoder.decodeUuid(encoded);
            assertEquals(expected, actual);
        }
    }
}
