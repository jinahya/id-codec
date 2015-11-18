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
import java.util.concurrent.ThreadLocalRandom;


/**
 * A class for encoding identifiers.
 *
 * @author Jin Kwon
 */
public class IdEncoder extends IdCodecBase {


    /**
     * Returns a random single digit.
     *
     * @return a random ingle digit.
     */
    private static int sd() {

        //return (int) (Math.random() * 10);
        return ThreadLocalRandom.current().nextInt(10);
    }


    /**
     * Returns a random nonzero single digit.
     *
     * @return a random nonzero single digit.
     */
    private static int nzsd() {

        //return (int) (Math.random() * 9) + 1;
        return ThreadLocalRandom.current().nextInt(1, 10);
    }


    /**
     *
     * @param decoded the block to encode
     * @param radix the radix
     * @param scale the scale
     *
     * @return encoded output
     */
    private static String block(final long decoded, final int radix,
                                final int scale) {

        requireValidRadix(radix);
        requireValidScale(scale);

        final StringBuilder builder = new StringBuilder(Long.toString(decoded));

        builder.ensureCapacity(builder.length() + scale);
        for (int i = 0; i < scale - 1; i++) {
            builder.append(Integer.toString(sd()));
        }
        builder.append(Integer.toString(nzsd()));

        builder.reverse();

        return Long.toString(Long.parseLong(builder.toString()), radix);
    }


    /**
     * Encodes given value with specified radix and scale.
     *
     * @param decoded the value to encode
     * @param radix the radix
     * @param scale the scale
     *
     * @return encoded value.
     */
    public static String encodeLong(final long decoded, final int radix,
                                    final int scale) {

        requireValidRadix(radix);

        requireValidScale(scale);

        return block(decoded >>> 0x20, radix, scale) + "-"
               + block(decoded & 0xFFFFFFFFL, radix, scale);
    }


    /**
     * Encodes given value with specified radix and scale.
     *
     * @param decoded the value to encode
     * @param radix the radix
     * @param scale the scale
     *
     * @return encoded value.
     */
    protected static String encodeUuid(final UUID decoded, final int radix,
                                       final int scale) {

        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }

        requireValidRadix(radix);

        requireValidScale(scale);

        return encodeLong(decoded.getMostSignificantBits(), radix, scale) + "-"
               + encodeLong(decoded.getLeastSignificantBits(), radix, scale);
    }


    /**
     * Encodes given value.
     *
     * @param decoded the value to encode.
     *
     * @return encoded output.
     */
    public String encodeLong(final long decoded) {

        return encodeLong(decoded, getRadix(), getScale());
    }


    /**
     * Encodes given value.
     *
     * @param decoded the value to encode
     *
     * @return encoded output.
     */
    public String encodeUuid(final UUID decoded) {

        return encodeUuid(decoded, getRadix(), getScale());
    }


}

