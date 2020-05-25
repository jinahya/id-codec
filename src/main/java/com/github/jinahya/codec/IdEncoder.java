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
public class IdEncoder extends IdCodecBase<IdEncoder> {

    private String block(final long decoded, final Random random) {
        if (decoded < 0L) {
            throw new IllegalArgumentException("decoded(" + decoded + " < 0");
        }
        if (random == null) {
            throw new NullPointerException("random is null");
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(decoded);
        builder.ensureCapacity(builder.length() + getScale());
        for (int i = 0; i < getScale() - 1; i++) {
            builder.append(random.nextInt(10)); // 0 - 9
        }
        builder.append(random.nextInt(9) + 1); // 1 - 9
        builder.reverse();
        return Long.toString(parseLong(builder.toString()), getRadix());
    }

    /**
     * Encodes given value.
     *
     * @param decoded the value to encode.
     * @param random  a random to use.
     * @return encoded output.
     */
    public String encode(final long decoded, final Random random) {
        return block(decoded >>> Integer.SIZE, random) + "-" + block(decoded & 0xFFFFFFFFL, random);
    }

//    /**
//     * Encodes given value.
//     *
//     * @param decoded the value to encode.
//     * @return encoded output.
//     */
//    public String encode(final long decoded) {
//        return encode(decoded, new Random());
//    }

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

//    /**
//     * Encodes given value.
//     *
//     * @param decoded the value to encode
//     * @return encoded output.
//     */
//    public String encodeUuid(final UUID decoded) {
//        return encodeUuid(decoded, new Random());
//    }
}
