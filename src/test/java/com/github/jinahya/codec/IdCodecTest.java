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


import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IdCodecTest {


    /**
     * logger.
     */
    private static final Logger LOGGER
        = LoggerFactory.getLogger(IdCodecTest.class);


    private static void encodeDecode(final long expected, final boolean print) {

        final String encoded = IdEncoderTest.encodeLong(expected);
        final long actual = IdDecoderTest.decodeLong(encoded);
        if (print) {
            System.out.printf("%40d %40s\n", expected, encoded);
        }
        Assert.assertEquals(actual, expected);
    }


    private static void encodeDecode(final long expected) {

        System.out.printf("%40s %40s\n",
                          "----------------------------------------",
                          "----------------------------------------");

        for (int i = 0; i < 5; i++) {
            encodeDecode(expected, true);
        }

        for (int i = 0; i < 1024; i++) {
            encodeDecode(expected, false);
        }
    }


    private static void encodeDecode(final UUID expected, final boolean print) {

        final String encoded = IdEncoderTest.encodeUuid(expected);
        final UUID actual = IdDecoderTest.decodeUuid(encoded);
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

        for (int i = 0; i < 5; i++) {
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

        for (int i = IdCodec.RADIX_MINIMUM; i <= IdCodec.RADIX_MAXIMUM; i++) {
            LOGGER.trace("radix: {}", i);
            encoder.setRadix(i);
            decoder.setRadix(i);
            final String encoded = encoder.encodeLong(expected);
            LOGGER.trace("encoded: {}", encoded);
            final long actual = decoder.decodeLong(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


    @Test
    public void encodedDecode_allAvailableScales() {

        final IdEncoder encoder = new IdEncoder();
        final IdDecoder decoder = new IdDecoder();

        final long expected = ThreadLocalRandom.current().nextInt();

        for (int i = IdCodec.SCALE_MINIMUM; i <= IdCodec.SCALE_MAXIMUM; i++) {
            LOGGER.trace("scale: {}", i);
            encoder.setScale(i);
            decoder.setScale(i);
            final String encoded = encoder.encodeLong(expected);
            LOGGER.trace("encoded: {}", encoded);
            final long actual = decoder.decodeLong(encoded);
            Assert.assertEquals(actual, expected);
        }
    }


}

