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

import static java.lang.Character.MAX_RADIX;
import static java.lang.Character.MIN_RADIX;

/**
 * An abstract base class.
 *
 * @param <T> self type parameter
 */
abstract class IdCodecBase<T extends IdCodecBase<T>> {

    /**
     * The minimum value for {@code radix} property which is {@value}.
     */
    public static final int RADIX_MINIMUM = MIN_RADIX;

    /**
     * The maximum value for {@code radix} property which is {@value}.
     */
    public static final int RADIX_MAXIMUM = MAX_RADIX;

    /**
     * The default value for {@code radix} property. The value is {@value}.
     */
    public static final int RADIX_DEFAULT = RADIX_MAXIMUM;

    static {
        assert RADIX_DEFAULT >= RADIX_MINIMUM && RADIX_DEFAULT <= RADIX_MAXIMUM;
    }

    /**
     * The minimum value for {@code scale} which is {@value}.
     */
    public static final int SCALE_MINIMUM = 1;

    /**
     * The maximum value for {@code scale} which is {@value}.
     */
    public static final int SCALE_MAXIMUM = 8;

    /**
     * The default value for {@code scale} property. The value is {@value}.
     */
    public static final int SCALE_DEFAULT = SCALE_MINIMUM;

    static {
        assert SCALE_DEFAULT >= SCALE_MINIMUM && SCALE_DEFAULT <= SCALE_MAXIMUM;
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
     * Replaces the current value of {@code radix} property with with given value.
     *
     * @param radix new value for {@code radix} property; between {@link #RADIX_MINIMUM} and {@link #RADIX_MAXIMUM},
     *              both inclusive.
     * @see #radix(int)
     */
    public void setRadix(final int radix) {
        if (radix < RADIX_MINIMUM) {
            throw new IllegalArgumentException("radix(" + radix + ") < " + RADIX_MINIMUM);
        }
        if (radix > RADIX_MAXIMUM) {
            throw new IllegalArgumentException("radix(" + radix + ") > " + RADIX_MAXIMUM);
        }
        this.radix = radix;
    }

    /**
     * Replaces the current value of {@code radix} property with given value and return this instance.
     *
     * @param radix new value for {@code radix} property.
     * @return this instance.
     * @see #setRadix(int)
     */
    @SuppressWarnings({"unchecked"})
    public T radix(final int radix) {
        setRadix(radix);
        return (T) this;
    }

    /**
     * Returns current value of {@code scale}.
     *
     * @return the current value of {@code scale}.
     */
    public int getScale() {
        return scale;
    }

    /**
     * Replaces the current value of {@code scale} property with given value.
     *
     * @param scale new value for {@code scale} property; between {@link #SCALE_MINIMUM} and {@link #SCALE_MAXIMUM},
     *              both inclusive.
     * @see #scale(int)
     */
    public void setScale(final int scale) {
        if (scale < SCALE_MINIMUM) {
            throw new IllegalArgumentException("scale(" + scale + ") < " + SCALE_MINIMUM);
        }
        if (scale > SCALE_MAXIMUM) {
            throw new IllegalArgumentException("scale(" + scale + ") > " + SCALE_MAXIMUM);
        }
        this.scale = scale;
    }

    /**
     * Replace the current value of {@code scale} property with given value and return this instance.
     *
     * @param scale new value for {@code scale} property.
     * @return this instance.
     * @see #setScale(int)
     */
    @SuppressWarnings({"unchecked"})
    public T scale(final int scale) {
        setScale(scale);
        return (T) this;
    }

    private int radix = RADIX_DEFAULT;

    private int scale = SCALE_DEFAULT;
}
