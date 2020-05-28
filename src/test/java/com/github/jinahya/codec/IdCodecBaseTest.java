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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link IdCodecBase} class.
 *
 * @param <T> class type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
abstract class IdCodecBaseTest<T extends IdCodecBase> {

    /**
     * Creates a new instance.
     *
     * @param clazz the class to test.
     */
    IdCodecBaseTest(final Class<T> clazz) {
        super();
        this.clazz = requireNonNull(clazz);
    }

    /**
     * Applies an instance of {@link #clazz}, constructed with specified radix and scale, to specified function.
     *
     * @param radix    the radix.
     * @param scale    the scale.
     * @param function the function.
     * @param <R>      result type parameter
     * @return the result of the function.
     * @see #instance(int, int)
     */
    <R> R instance(final int radix, final int scale, final Function<? super T, ? extends R> function) {
        try {
            return function.apply(instance(radix, scale));
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * Creates a new instance of {@link #clazz} with specified radix and scale.
     *
     * @param radix the radix.
     * @param scale the scale.
     * @return a new instance.
     * @see #constructor()
     */
    T instance(final int radix, final int scale)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return constructor().newInstance(radix, scale);
    }

    /**
     * Returns the constructor of {@link IdCodecBase#IdCodecBase(int, int)}.
     *
     * @return the constructor of {@link #clazz}.
     * @throws NoSuchMethodException if the constructor not found.
     * @see Class#getConstructor(Class[])
     */
    Constructor<T> constructor() throws NoSuchMethodException {
        if (constructor == null) {
            constructor = clazz.getConstructor(int.class, int.class);
        }
        return constructor;
    }

    private final Class<T> clazz;

    private Constructor<T> constructor;
}
