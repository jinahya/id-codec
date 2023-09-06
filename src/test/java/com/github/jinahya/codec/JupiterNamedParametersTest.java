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

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JupiterNamedParametersTest {

    private static Stream<Arguments> arguments1() {
        return Stream.of(
                Arguments.of(0, 0)
        );
    }

    private static Stream<Arguments> arguments2() {
        return Stream.of(
                Arguments.of(Named.of("a", 1), Named.of("b", 1))
        );
    }

    private static Stream<Arguments> arguments3() {
        return arguments2().map(a -> {
            final Object[] got = a.get();
            return Arguments.of(
                    ((Named<?>) got[0]).getPayload(),
                    ((Named<?>) got[1]).getPayload()
            );
        });
    }

    @MethodSource({"arguments1", "arguments2", "arguments3"})
    @ParameterizedTest
    void test1(int a, int b) {
    }

    @MethodSource({"arguments1", "arguments2", "arguments3"})
    @ParameterizedTest(name = "[index] a: {0}, b: {1}")
    void test2(int a, int b) {
    }
}
