/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.UUID;

import static com.github.jinahya.codec.IdCodecBase.RADIX_MAXIMUM;
import static com.github.jinahya.codec.IdCodecBase.RADIX_MINIMUM;
import static com.github.jinahya.codec.IdCodecBase.SCALE_MAXIMUM;
import static com.github.jinahya.codec.IdCodecBase.SCALE_MINIMUM;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class IdCodecTest {

    /**
     * Tests {@link IdEncoder#encode(long, Random)} method and {@link IdDecoder#decode(String)} method.
     */
    @Test
    void encodeDecodeLong() {
        final IdEncoder encoder = new IdEncoder();
        final IdDecoder decoder = new IdDecoder();
        for (int scale = SCALE_MINIMUM; scale <= SCALE_MAXIMUM; scale++) {
            encoder.scale(scale);
            decoder.scale(scale);
            for (int radix = RADIX_MINIMUM; radix <= RADIX_MAXIMUM; radix++) {
                encoder.radix(radix);
                decoder.radix(radix);
                final long expected = current().nextLong();
                final String encoded = encoder.encode(expected, current());
                final long actual = decoder.decode(encoded);
//                log.debug("scale: {}", scale);
//                log.debug("radix: {}", radix);
//                log.debug("expected: {}", expected);
//                log.debug("encoded: {}", encoded);
//                log.debug("actual: {}", actual);
                assertEquals(expected, actual);
            }
        }
    }

    /**
     * Tests {@link IdEncoder#encodeUuid(UUID, Random)} method and {@link IdDecoder#decodeUuid(String)} method.
     */
    @Test
    void encodeDecodeUuid() {
        final IdEncoder encoder = new IdEncoder();
        final IdDecoder decoder = new IdDecoder();
        for (int scale = SCALE_MINIMUM; scale <= SCALE_MAXIMUM; scale++) {
            encoder.scale(scale);
            decoder.scale(scale);
            for (int radix = RADIX_MINIMUM; radix <= RADIX_MAXIMUM; radix++) {
                encoder.radix(radix);
                decoder.radix(radix);
                final UUID expected = randomUUID();
                final String encoded = encoder.encodeUuid(expected, current());
                final UUID actual = decoder.decodeUuid(encoded);
//                log.debug("scale: {}", scale);
//                log.debug("radix: {}", radix);
//                log.debug("expected: {}", expected);
//                log.debug("encoded: {}", encoded);
//                log.debug("actual: {}", actual);
                assertEquals(expected, actual);
            }
        }
    }
}
