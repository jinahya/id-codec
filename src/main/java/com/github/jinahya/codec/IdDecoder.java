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


/**
 * A class for decoding identifiers.
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a> <a
 * href="http://goo.gl/jEcetr">donate</a>
 */
public class IdDecoder extends IdCodecBase {


    /**
     * Decodes a single block.
     *
     * @param encoded the block to decode
     * @param radix the radix
     * @param scale the scale
     *
     * @return decoded output
     */
    private static long block(final String encoded, final int radix,
                              final int scale) {

        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }

        requireValidRadix(radix);

        requireValidScale(scale);

        final StringBuilder builder = new StringBuilder(
            Long.toString(Long.parseLong(encoded, radix)));

        builder.reverse();

        builder.delete(builder.length() - scale, builder.length());

        return Long.parseLong(builder.toString());
    }


    /**
     * Decodes given value with specified radix and scale.
     *
     * @param encoded the value to decode
     * @param radix the radix
     * @param scale the scale
     *
     * @return decoded output
     *
     * @see #requireValidRadix(int)
     * @see #requireValidScale(int)
     */
    public static long decodeLong(final String encoded, final int radix,
                                  final int scale) {

        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }

        final int index = encoded.indexOf('-');
        if (index == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        return (block(encoded.substring(0, index), radix, scale) << 32)
               | (block(encoded.substring(index + 1), radix, scale));
    }


    /**
     * Decodes given value with specified radix and scale.
     *
     * @param encoded the value to decode
     * @param radix the radix
     * @param scale the scale
     *
     * @return decoded output
     *
     * @see #requireValidRadix(int)
     * @see #requireValidScale(int)
     */
    public static UUID decodeUuid(final String encoded, final int radix,
                                  final int scale) {

        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }

        final int first = encoded.indexOf('-');
        if (first == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        final int second = encoded.indexOf('-', first + 1);
        if (second == -1) {
            throw new IllegalArgumentException("wrong encoded: " + encoded);
        }

        final long mostSignificantBits
            = decodeLong(encoded.substring(0, second), radix, scale);
        final long leastSignificantBits
            = decodeLong(encoded.substring(second + 1), radix, scale);

        return new UUID(mostSignificantBits, leastSignificantBits);
    }


    /**
     * Creates a new instance.
     */
    public IdDecoder() {

        super();
    }


    /**
     * Decodes given value.
     *
     * @param encoded the value to decode.
     *
     * @return decoded value
     */
    public long decodeLong(final String encoded) {

        return decodeLong(encoded, getRadix(), getScale());
    }


    /**
     * Decodes given value.
     *
     * @param encoded the value to decode.
     *
     * @return decoded value
     */
    public UUID decodeUuid(final String encoded) {

        return decodeUuid(encoded, getRadix(), getScale());
    }


}

