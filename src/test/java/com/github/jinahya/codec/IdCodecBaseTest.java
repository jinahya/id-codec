/*
 * Copyright 2014 Jin Kwon.
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
import org.junit.jupiter.api.Test;

import static com.github.jinahya.codec.IdCodecBase.RADIX_MAXIMUM;
import static com.github.jinahya.codec.IdCodecBase.RADIX_MINIMUM;
import static com.github.jinahya.codec.IdCodecBase.SCALE_MAXIMUM;
import static com.github.jinahya.codec.IdCodecBase.SCALE_MINIMUM;
import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
abstract class IdCodecBaseTest<T extends IdCodecBase<T>> {

    IdCodecBaseTest(final Class<T> clazz) {
        super();
        this.clazz = requireNonNull(clazz);
    }

    @Test
    void setRadixWithTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> instance().setRadix(RADIX_MINIMUM - 1));
    }

    @Test
    void setRadixWithTooBig() {
        assertThrows(IllegalArgumentException.class, () -> instance().setRadix(RADIX_MAXIMUM + 1));
    }

    @Test
    void testSetRadix() {
        final T instance = instance();
        rangeClosed(RADIX_MINIMUM, RADIX_MAXIMUM).forEach(instance::setRadix);
    }

    @Test
    void testRadix() {
        final T instance = instance();
        rangeClosed(RADIX_MINIMUM, RADIX_MAXIMUM).forEach(r -> assertSame(instance, instance.radix(r)));
    }

    @Test
    void setScaleWithTooSmall() {
        assertThrows(IllegalArgumentException.class, () -> instance().setScale(SCALE_MINIMUM - 1));
    }

    @Test
    void setScaleWithTooBig() {
        assertThrows(IllegalArgumentException.class, () -> instance().setScale(SCALE_MAXIMUM + 1));
    }

    @Test
    void testSetScale() {
        final T instance = instance();
        rangeClosed(SCALE_MINIMUM, SCALE_MAXIMUM).forEach(instance::setScale);
    }

    @Test
    void testScale() {
        final T instance = instance();
        rangeClosed(SCALE_MINIMUM, SCALE_MAXIMUM).forEach(r -> assertSame(instance, instance.scale(r)));
    }

    T instance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private final Class<T> clazz;
}
