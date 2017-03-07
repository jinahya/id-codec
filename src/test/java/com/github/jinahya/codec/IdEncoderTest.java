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

import static com.github.jinahya.codec.IdCodecConstants.RADIX_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.RADIX_MINIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MINIMUM;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.testng.Assert.assertSame;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdEncoderTest {

    @Test(invocationCount = 1024)
    public void encode() {
        final long decoded = ThreadLocalRandom.current().nextLong();
        final String encoded = new IdEncoder().encode(decoded);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void encodeUUID_nullDecoded_nullPointerException() {
        new IdEncoder().encodeUuid(null);
    }

    @Test(invocationCount = 1024)
    public void encodeUUID() {
        final UUID decoded = UUID.randomUUID();
        final String encoded = new IdEncoder().encodeUuid(decoded);
    }

    @Test
    public void radix() {
        final IdEncoder expected = new IdEncoder();
        final IdEncoder actual = expected.radix(current().nextInt(
                RADIX_MINIMUM, RADIX_MAXIMUM + 1));
        assertSame(actual, expected);
    }

    @Test
    public void scale() {
        final IdEncoder expected = new IdEncoder();
        final IdEncoder actual = expected.scale(current().nextInt(
                SCALE_MINIMUM, SCALE_MAXIMUM + 1));
        assertSame(actual, expected);
    }
}
