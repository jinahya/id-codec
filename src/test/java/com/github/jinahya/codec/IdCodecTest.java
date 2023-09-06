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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Slf4j
class IdCodecTest {

    static IntStream getScaleStream() {
        return rangeClosed(IdCodecConstants.MIN_SCALE, IdCodecConstants.MAX_SCALE);
    }

    static IntStream getRadixStream() {
        return rangeClosed(IdCodecConstants.MIN_RADIX, IdCodecConstants.MAX_RADIX);
    }

    static Stream<Arguments> getScaleRadixArgumentsStream() {
        // https://github.com/junit-team/junit5/issues/2606
        return getScaleStream()
//                .mapToObj(s -> getRadixStream().mapToObj(r -> arguments(named("scale: " + s, s), named("radix: " + r, r))))
                .mapToObj(s -> getRadixStream().mapToObj(r -> arguments(named("scale", s), named("radix", r))))
//                .mapToObj(s -> getRadixStream().mapToObj(r -> arguments(s, r)))
                .flatMap(Function.identity());
    }

    @DisplayName("(encode|decode)Long(scale, radix, ...)")
    @MethodSource({"getScaleRadixArgumentsStream"})
    @ParameterizedTest(name = "{index}: scale: {0}, radix: {1}")
    void encodeDecodeLong(final int scale, final int radix) {
        final long expected = current().nextLong();
        final String encoded = IdCodec.encodeLong(scale, radix, expected, current());
        final long actual = IdCodec.decodeLong(scale, radix, encoded);
//        log.debug("scale: {}", scale);
//        log.debug("radix: {}", radix);
//        log.debug("expected: {}", expected);
//        log.debug("encoded: {}", encoded);
//        log.debug("actual: {}", actual);
        assertEquals(expected, actual);
    }

    @Test
    void encodeDecodeLong__WithRandom() {
        final long expected = current().nextLong();
        final String encoded = IdCodec.encodeLong(expected, current());
        final long actual = IdCodec.decodeLong(encoded);
        assertEquals(expected, actual);
    }

    @Test
    void encodeDecodeLong__WithoutRandom() {
        final long expected = current().nextLong();
        final String encoded = IdCodec.encodeLong(expected);
        final long actual = IdCodec.decodeLong(encoded);
        assertEquals(expected, actual);
    }

    @MethodSource({"getScaleRadixArgumentsStream"})
    @ParameterizedTest(name = "{index}: scale: {0}, radix: {1}")
    void encodeDecodeUuid(final int scale, final int radix) {
        final UUID expected = randomUUID();
        final String encoded = IdCodec.encodeUuid(scale, radix, expected, current());
        final UUID actual = IdCodec.decodeUuid(scale, radix, encoded);
        log.debug("scale: {}", scale);
        log.debug("radix: {}", radix);
        log.debug("expected: {}", expected);
        log.debug("encoded: {}", encoded);
        log.debug("actual: {}", actual);
        assertEquals(expected, actual);
    }

    @Test
    void encodeDecodeUuid_WithRandom() {
        final UUID expected = randomUUID();
        final String encoded = IdCodec.encodeUuid(expected, current());
        final UUID actual = IdCodec.decodeUuid(encoded);
        assertEquals(expected, actual);
    }

    @Test
    void encodeDecodeUuid_WithoutRandom() {
        final UUID expected = randomUUID();
        final String encoded = IdCodec.encodeUuid(expected);
        final UUID actual = IdCodec.decodeUuid(encoded);
        assertEquals(expected, actual);
    }

    private static List<Arguments> arguments1() {
        return Collections.singletonList(
                Arguments.of(Named.of("a", 0), Named.of("b", 0))
        );
    }

    private static List<Arguments> arguments2() {
        return Collections.singletonList(
                Arguments.of(0, 0)
        );
    }

    @MethodSource({"arguments1", "arguments2"})
    @ParameterizedTest
    void test1(int a, int b) {
    }

    @MethodSource({"arguments1", "arguments2"})
    @ParameterizedTest(name = "[index] a: {0}, b: {1}")
    void test2(int a, int b) {
    }
}
