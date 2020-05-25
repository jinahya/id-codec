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

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
class IdEncoderTest extends IdCodecBaseTest<IdEncoder> {

    IdEncoderTest() {
        super(IdEncoder.class);
    }

    @Test
    void testEncode() {
        final long decoded = current().nextLong();
        final String encoded = instance().encode(decoded, current());
        assertNotNull(encoded);
    }

    @Test
    void assertEncodeUuidThrowNullPointerExceptionWhenDecodedIsNull() {
        assertThrows(NullPointerException.class, () -> instance().encodeUuid(null, current()));
    }

    @Test
    void testEncodeUUID() {
        final UUID decoded = randomUUID();
        final String encoded = instance().encodeUuid(decoded, current());
        assertNotNull(encoded);
    }
}
