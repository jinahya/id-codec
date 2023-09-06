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

import java.util.UUID;

import static java.lang.Long.parseLong;

/**
 * A class for decoding identifiers.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdDecoder extends IdCodecBase {

    /**
     * Creates a new instance with specified radix and scale.
     *
     * @param scale the scale; between {@link #MIN_SCALE} and {@link #MAX_SCALE}, both inclusive.
     * @param radix the radix; between {@link #MIN_RADIX} and {@link #MAX_RADIX}, both inclusive.
     */
    public IdDecoder(final int scale, final int radix) {
        super(scale, radix);
    }

    /**
     * Decodes specified single block.
     *
     * @param encoded the block to decode
     * @return decoded output
     */
    private long block(final String encoded) {
        assert encoded != null;
        final StringBuilder builder = new StringBuilder();
        builder.append(parseLong(encoded, radix));
        builder.reverse();
        builder.delete(builder.length() - scale, builder.length());
        return parseLong(builder.toString());
    }

    /**
     * Decodes specified encoded value.
     *
     * @param encoded the encoded value to decode.
     * @return a decoded value.
     */
    public long decode(final String encoded) {
        if (encoded == null) {
            throw new NullPointerException("encoded is null");
        }
        final int index = encoded.indexOf('-');
        return (block(encoded.substring(0, index)) << Integer.SIZE) | (block(encoded.substring(index + 1)));
    }

    /**
     * Decodes specified encoded value.
     *
     * @param encoded the encoded value to decode.
     * @return a decoded value
     */
    public UUID decodeUuid(final String encoded) {
        if (encoded == null) {
            throw new NullPointerException("encoded is null");
        }
        final int first = encoded.indexOf('-');
        if (first == -1) {
            throw new IllegalArgumentException("illegal encoded value: " + encoded);
        }
        final int second = encoded.indexOf('-', first + 1);
        if (second == -1) {
            throw new IllegalArgumentException("illegal encoded value: " + encoded);
        }
        final long mostSignificantBits = decode(encoded.substring(0, second));
        final long leastSignificantBits = decode(encoded.substring(second + 1));
        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}
