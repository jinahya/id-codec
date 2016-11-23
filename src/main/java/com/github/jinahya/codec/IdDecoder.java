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
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdDecoder extends IdCodecBase<IdDecoder> {

    @Override
    public IdDecoder scale(int scale) {
        return super.scale(scale);
    }

    @Override
    public IdDecoder radix(int radix) {
        return super.radix(radix);
    }

    /**
     * Decodes a single block.
     *
     * @param encoded the block to decode
     * @param radix the radix
     * @param scale the scale
     * @return decoded output
     */
    private long block(final String encoded) {
        final StringBuilder builder = new StringBuilder(
                Long.toString(Long.parseLong(encoded, getRadix())));
        builder.reverse();
        builder.delete(builder.length() - getScale(), builder.length());
        return Long.parseLong(builder.toString());
    }

    /**
     * Decodes given value.
     *
     * @param encoded the value to decode.
     * @return decoded value
     */
    public long decode(final String encoded) {
        final int index = encoded.indexOf('-');
        return (block(encoded.substring(0, index)) << Integer.SIZE)
               | (block(encoded.substring(index + 1)));
    }

    /**
     * Decodes given value.
     *
     * @param encoded the value to decode.
     * @return decoded value
     */
    public UUID decodeUuid(final String encoded) {
        final int first = encoded.indexOf('-');
        final int second = encoded.indexOf('-', first + 1);
        final long mostSignificantBits = decode(encoded.substring(0, second));
        final long leastSignificantBits = decode(encoded.substring(second + 1));
        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}
