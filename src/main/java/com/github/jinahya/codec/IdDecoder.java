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
 * Decoder for Database IDs.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdDecoder {


    static final int DEFAULT_RADIX = Character.MAX_RADIX;


    static final int DEFAULT_SCALE = 1;


    /**
     * Decodes a single block.
     *
     * @param encoded the block to decode
     * @param radix
     * @param scale the number random prefixes
     *
     * @return decoded block
     */
    private static long block(final String encoded, final int radix,
                              final int scale) {

        if (encoded == null) {
            throw new NullPointerException("encoded == null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") < " + Character.MIN_RADIX);
        }

        if (radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") > " + Character.MAX_RADIX);
        }

        if (scale < 1) {
            throw new IllegalArgumentException("scale(" + scale + ") < 1");
        }

        final StringBuilder builder = new StringBuilder(
            Long.toString(Long.parseLong(encoded, radix)));

        builder.reverse();

        builder.delete(builder.length() - scale, builder.length());
        //builder.deleteCharAt(builder.length() - 1);
        //builder.deleteCharAt(builder.length() - 1);

        return Long.parseLong(builder.toString());
    }


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
     * Decodes given {@code decoded}.
     *
     * @param encoded encoded value to decode.
     *
     * @return decoded value
     */
    public long decodeLong(final String encoded) {

        return decodeLong(encoded, radix, scale);
    }


    public UUID decodeUuid(final String encoded) {

        return decodeUuid(encoded, radix, scale);
    }


    public int getRadix() {

        return radix;
    }


    public void setRadix(final int radix) {

        if (radix < Character.MIN_RADIX) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") < " + Character.MIN_RADIX);
        }

        if (radix > Character.MAX_RADIX) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") > " + Character.MAX_RADIX);
        }

        this.radix = radix;
    }


    public int getScale() {

        return scale;
    }


    public void setScale(final int scale) {

        if (scale < 1) {
            throw new IllegalArgumentException("scale(" + scale + ") < 1");
        }

        this.scale = scale;
    }


    private int radix = DEFAULT_RADIX;


    private int scale = DEFAULT_SCALE;


}

