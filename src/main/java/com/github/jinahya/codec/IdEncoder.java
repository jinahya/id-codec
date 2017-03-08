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

import java.io.PrintStream;
import static java.lang.Long.parseLong;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * A class for encoding identifiers.
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class IdEncoder extends IdCodecBase<IdEncoder> {

    static void encode(final String decoded, final PrintStream printer) {
        if (decoded == null) {
            throw new NullPointerException("null decoded");
        }
        if (printer == null) {
            throw new NullPointerException("null printer");
        }
        try {
            final String encoded
                    = new IdEncoder().encodeUuid(UUID.fromString(decoded));
            printer.println(encoded);
            return;
        } catch (final Exception e) {
        }
        final String encoded = new IdEncoder().encode(parseLong(decoded));
        printer.println(encoded);
    }

    /**
     * Encodes command line arguments and prints each encoded value to
     * {@code System.out}.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        for (final String arg : args) {
            encode(arg, System.out);
        }
    }

    private String block(final long decoded) {
        final StringBuilder builder = new StringBuilder(Long.toString(decoded));
        final Random random = new SecureRandom();
        builder.ensureCapacity(builder.length() + getScale());
        for (int i = 0; i < getScale() - 1; i++) {
            builder.append(Integer.toString(random.nextInt(10)));
        }
        builder.append(Integer.toString(random.nextInt(9) + 1));
        builder.reverse();
        return Long.toString(Long.parseLong(builder.toString()), getRadix());
    }

    /**
     * Encodes given value.
     *
     * @param decoded the value to encode.
     * @return encoded output.
     */
    public String encode(final long decoded) {
        return block(decoded >>> Integer.SIZE) + "-"
               + block(decoded & 0xFFFFFFFFL);
    }

    /**
     * Encodes given value.
     *
     * @param decoded the value to encode
     * @return encoded output.
     */
    public String encodeUuid(final UUID decoded) {
        return encode(decoded.getMostSignificantBits()) + "-"
               + encode(decoded.getLeastSignificantBits());
    }
}
