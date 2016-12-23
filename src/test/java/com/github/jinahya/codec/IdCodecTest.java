/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.Long.parseLong;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.concurrent.ThreadLocalRandom.current;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class IdCodecTest {

    private static final Logger logger = getLogger(lookup().lookupClass());

    private static void encodeDecode(final long expected, final boolean print) {
        final String encoded = new IdEncoder().encode(expected);
        final long actual = new IdDecoder().decode(encoded);
        if (print) {
            System.out.printf("%40d %40s\n", expected, encoded);
        }
        Assert.assertEquals(actual, expected);
    }

    private static void encodeDecode(final long expected) {
        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");
        for (int i = 0; i < 10; i++) {
            encodeDecode(expected, true);
        }
        for (int i = 0; i < 1024; i++) {
            encodeDecode(expected, false);
        }
    }

    private static void encodeDecode(final UUID expected, final boolean print) {
        final String encoded = new IdEncoder().encodeUuid(expected);
        final UUID actual = new IdDecoder().decodeUuid(encoded);
        if (print) {
            System.out.printf("%40s %40s\n", expected, encoded);
        }
        Assert.assertEquals(actual, expected);
        Assert.assertEquals(actual.getLeastSignificantBits(),
                            expected.getLeastSignificantBits());
        Assert.assertEquals(actual.getMostSignificantBits(),
                            expected.getMostSignificantBits());
    }

    private static void encodeDecode(final UUID expected) {
        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");
        for (int i = 0; i < 10; i++) {
            encodeDecode(expected, true);
        }
        for (int i = 0; i < 1024; i++) {
            encodeDecode(expected, false);
        }
    }

    @Test(invocationCount = 1)
    public static void encodeDecoded_long() {
        final long expected = ThreadLocalRandom.current().nextLong();
        encodeDecode(expected);
    }

    @Test(invocationCount = 1)
    public static void encodeDecode_long_magic() {
        encodeDecode(Long.MIN_VALUE);
        encodeDecode(Long.MAX_VALUE);
        encodeDecode(-32L);
        encodeDecode(-16L);
        encodeDecode(-2L);
        encodeDecode(-1L);
        encodeDecode(0L);
        encodeDecode(1L);
        encodeDecode(2L);
        encodeDecode(15L);
        encodeDecode(31L);
    }

    @Test(invocationCount = 1)
    public static void encodeDecode_UUID() {
        final UUID expected = UUID.randomUUID();
        encodeDecode(expected);
    }

    @Test
    public void encodedDecode_allAvailableRadices() {
        final IdEncoder encoder = new IdEncoder();
        final IdDecoder decoder = new IdDecoder();
        final long expected = ThreadLocalRandom.current().nextInt();
        for (int scale = RADIX_MINIMUM; scale <= RADIX_MAXIMUM; scale++) {
            logger.trace("radix: {}", scale);
            encoder.setRadix(scale);
            decoder.setRadix(scale);
            final String encoded = encoder.encode(expected);
            logger.trace("encoded: {}", encoded);
            final long actual = decoder.decode(encoded);
            Assert.assertEquals(actual, expected);
        }
    }

    @Test
    public void encodedDecode_allAvailableScales() {
        final IdEncoder encoder = new IdEncoder();
        final IdDecoder decoder = new IdDecoder();
        final long expected = ThreadLocalRandom.current().nextInt();
        for (int scale = SCALE_MINIMUM; scale <= SCALE_MAXIMUM; scale++) {
            logger.trace("scale: {}", scale);
            encoder.setScale(scale);
            decoder.setScale(scale);
            final String encoded = encoder.encode(expected);
            logger.trace("encoded: {}", encoded);
            final long actual = decoder.decode(encoded);
            assertEquals(actual, expected);
        }
    }

    @Test
    public void documentation() {
        {
            final long expected = ThreadLocalRandom.current().nextLong();
            System.out.printf("%s: %20d\n", "decoded", expected);
            for (int i = 0; i < 16; i++) {
                final String encoded = new IdEncoder().encode(expected);
                final long actual = new IdDecoder().decode(encoded);
                System.out.printf("%s: %20s\n", "encoded", encoded);
                assertEquals(actual, expected);
            }
        }
        System.out.println("=================================================");
        {
            final UUID expected = UUID.randomUUID();
            System.out.printf("%s: %40s\n", "decoded", expected);
            for (int i = 0; i < 16; i++) {
                final String encoded = new IdEncoder().encodeUuid(expected);
                final UUID actual = new IdDecoder().decodeUuid(encoded);
                System.out.printf("%s: %40s\n", "encoded", encoded);
                assertEquals(actual, expected);
            }
        }
    }

    @Test(enabled = false)
    public static void encodeDecodeMain() throws IOException {
        final PrintStream out = System.out;
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            final long expected = current().nextLong();
            IdEncoder.main(Long.toString(expected));
            final String encoded = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(baos.toByteArray()))).readLine();
            baos.reset();
            IdDecoder.main(encoded);
            final String decoded = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(baos.toByteArray()))).readLine();
            assertEquals(parseLong(decoded), expected);
        } finally {
            System.setOut(out);
        }
    }
//
//    @Test
//    public static void encodeDecodeMainUuid() {
//        final PrintStream out = System.out;
//        try {
//            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            System.setOut(new PrintStream(baos));
//        } finally {
//            System.setOut(out);
//        }
//    }
}
