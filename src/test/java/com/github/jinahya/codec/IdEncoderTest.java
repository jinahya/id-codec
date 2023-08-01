/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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

import static com.github.jinahya.codec.IdCodecBase.MAX_SCALE;
import static com.github.jinahya.codec.IdCodecBase.MIN_SCALE;
import static java.lang.Character.MAX_RADIX;
import static java.lang.Character.MIN_RADIX;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * A class for testing {@link IdEncoder} class.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
@Slf4j
class IdEncoderTest extends IdCodecBaseTest<IdEncoder> {

    /**
     * Creates a new instance.
     */
    IdEncoderTest() {
        super(IdEncoder.class);
    }

    @Test
    void __LongMax() {
//        final long decoded = current().nextLong();
        final long decoded = Long.MAX_VALUE;
        {
            final String encoded = instance(MAX_RADIX, MIN_SCALE, v -> v.encode(decoded, current()));
            log.debug("encoded: {}", encoded);
        }
        {
            final String encoded = instance(MAX_RADIX, MAX_SCALE, v -> v.encode(decoded, current()));
            log.debug("encoded: {}", encoded);
        }
    }

    /**
     * Tests {@link IdEncoder#encode(long, Random)} method.
     */
    @Test
    void testEncode() {
        for (int radix = MIN_RADIX; radix <= MAX_RADIX; radix++) {
            for (int scale = MIN_SCALE; scale <= MAX_SCALE; scale++) {
                final long decoded = current().nextLong();
                final String encoded = instance(radix, scale, v -> v.encode(decoded, current()));
                log.debug("radix: {}, encoded: {}", radix, encoded);
                assertNotNull(encoded);
            }
        }
    }

    /**
     * Tests {@link IdEncoder#encodeUuid(UUID, Random)} method.
     */
    @Test
    void testEncodeUUID() {
        for (int radix = MIN_RADIX; radix <= MAX_RADIX; radix++) {
            for (int scale = MIN_SCALE; scale <= MAX_SCALE; scale++) {
                final UUID decoded = randomUUID();
                final String encoded = instance(radix, scale, v -> v.encodeUuid(decoded, current()));
                assertNotNull(encoded);
            }
        }
    }
}
