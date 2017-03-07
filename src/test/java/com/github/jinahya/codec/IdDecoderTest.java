/*
 * Copyright 2012 onacit.
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
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.testng.Assert.assertSame;
import org.testng.annotations.Test;

/**
 *
 * @author Jin Kwon
 */
public class IdDecoderTest {

    @Test(expectedExceptions = {NullPointerException.class})
    public static void decode_nullEncoded_nullPointerException() {
        new IdDecoder().decode(null);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public static void decodeUuid_nullEncoded_nulPointerException() {
        new IdDecoder().decodeUuid(null);
    }

    @Test
    public void radix() {
        final IdDecoder expected = new IdDecoder();
        final IdDecoder actual = expected.radix(current().nextInt(
                RADIX_MINIMUM, RADIX_MAXIMUM + 1));
        assertSame(actual, expected);
    }

    @Test
    public void scale() {
        final IdDecoder expected = new IdDecoder();
        final IdDecoder actual = expected.scale(current().nextInt(
                SCALE_MINIMUM, SCALE_MAXIMUM + 1));
        assertSame(actual, expected);
    }
}
