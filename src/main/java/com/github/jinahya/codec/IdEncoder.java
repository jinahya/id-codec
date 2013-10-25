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
 * A class for encoding identifiers.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdEncoder {


    /**
     * Returns a random single digit.
     *
     * @return a random ingle digit.
     */
    private static int sd() {

        return (int) (Math.random() * 10);
    }


    /**
     * Returns a random nonzero single digit.
     *
     * @return a random nonzero single digit.
     */
    private static int nzsd() {

        return (int) (Math.random() * 9) + 1;
    }


    private static String block(final long decoded, final int radix,
                                final int scale) {

        IdCodec.requireValidRadix(radix);

        IdCodec.requireValidScale(scale);

        final StringBuilder builder = new StringBuilder(Long.toString(decoded));

        builder.ensureCapacity(builder.capacity() + scale);
        for (int i = 0; i < scale - 1; i++) {
            builder.append(Integer.toString(sd()));
        }
        builder.append(Integer.toString(nzsd()));

        builder.reverse();

        return Long.toString(Long.parseLong(builder.toString()), radix);
    }


    public static String encodeLong(final long decoded, final int radix,
                                    final int scale) {

        return block(decoded >>> 0x20, radix, scale) + "-"
               + block(decoded & 0xFFFFFFFFL, radix, scale);
    }


//    /**
//     * Encodes given value with {@link #DEFAULT_RADIX} and
//     * {@link #DEFAULT_SCALE}.
//     *
//     * @param decoded the value to encode
//     *
//     * @return encoded output.
//     */
//    public static String encodeLong(final long decoded) {
//
//        return encodeLong(decoded, DEFAULT_RADIX, DEFAULT_SCALE);
//    }
    /**
     * Encodes given value with specified radix and scale.
     *
     * @param decoded the value
     * @param radix the radix
     * @param scale the scale
     *
     * @return encoded output.
     */
    protected static String encodeUuid(final UUID decoded, final int radix,
                                       final int scale) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        return encodeLong(decoded.getMostSignificantBits(), radix, scale) + "-"
               + encodeLong(decoded.getLeastSignificantBits(), radix, scale);
    }


//    /**
//     * Encodes given value with {@link #DEFAULT_RADIX} and
//     * {@link #DEFAULT_SCALE}.
//     *
//     * @param decoded the value
//     *
//     * @return encoded output
//     */
//    public static String encodeUUID(final UUID decoded) {
//
//        return encodeUUID(decoded, DEFAULT_RADIX, DEFAULT_SCALE);
//    }
    /**
     * Encodes given value with {@code radix} and {@code scale}.
     *
     * @param decoded the value
     *
     * @return encoded result.
     */
    public String encodeLong(final long decoded) {

        return encodeLong(decoded, radix, scale);
    }


    /**
     * Encodes given value with {@code radix} and {@code scale}.
     *
     * @param decoded the value
     *
     * @return encoded result.
     */
    public String encodeUuid(final UUID decoded) {

        return encodeUuid(decoded, radix, scale);
    }


    /**
     * Return current value of {@code radix}.
     *
     * @return the current value of {@code radix}.
     */
    public int getRadix() {

        return radix;
    }


    /**
     * Sets the current value of {@code radix} with given.
     *
     * @param radix new value of {@code radix}.
     */
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


    private int radix = IdCodec.RADIX_DEFAULT;


    private int scale = IdCodec.SCALE_DEFAULT;


}

