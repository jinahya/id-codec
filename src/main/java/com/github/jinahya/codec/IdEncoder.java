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

import java.util.Random;
import java.util.UUID;

import static java.lang.Long.parseLong;

/**
 * A class for encoding identifiers.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdEncoder extends IdCodecBase {

    /**
     * Creates a new instance with specified radix and scale.
     *
     * @param radix the radix; between {@link Character#MIN_RADIX} and {@link Character#MAX_RADIX}, both inclusive.
     * @param scale the scale; between {@link #MIN_SCALE} and {@link #MAX_SCALE}, both inclusive.
     */
    public IdEncoder(final int radix, final int scale) {
        super(radix, scale);
    }

    private String block(final long decoded, final Random random) {
        if (decoded < 0L) {
            throw new IllegalArgumentException("decoded(" + decoded + " < 0");
        }
        if (random == null) {
            throw new NullPointerException("random is null");
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(decoded);
        builder.ensureCapacity(builder.length() + scale);
        for (int i = 0; i < scale - 1; i++) {
            builder.append(random.nextInt(10)); // 0 - 9
        }
        builder.append(random.nextInt(9) + 1); // 1 - 9
        builder.reverse();
        return Long.toString(parseLong(builder.toString()), radix);
    }

    /**
     * Encodes specified value using specified random intance.
     *
     * @param decoded the value to encode.
     * @param random  the random to use.
     * @return encoded output.
     */
    public String encode(final long decoded, final Random random) {
        return block(decoded >>> Integer.SIZE, random) + "-" + block(decoded & 0xFFFFFFFFL, random);
    }

    /**
     * Encodes given value.
     *
     * @param decoded the value to encode
     * @param random  a random to use.
     * @return encoded output.
     */
    public String encodeUuid(final UUID decoded, final Random random) {
        if (decoded == null) {
            throw new NullPointerException("decoded is null");
        }
        if (random == null) {
            throw new NullPointerException("random is null");
        }
        return encode(decoded.getMostSignificantBits(), random) + "-"
               + encode(decoded.getLeastSignificantBits(), random);
    }
}
