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
 */
abstract class IdCodecBase {

    /**
     * The minimum value for {@code scale} which is {@value}.
     */
    public static final int MIN_SCALE = 1;

    /**
     * The maximum value for {@code scale} which is {@value}.
     */
    public static final int MAX_SCALE = 8;

    /**
     * Creates a new instance with specified radix and scale.
     *
     * @param radix the radix; between {@link Character#MIN_RADIX} and {@link Character#MAX_RADIX}, both inclusive.
     * @param scale the scale; between {@link #MIN_SCALE} and {@link #MAX_SCALE}, both inclusive.
     */
    IdCodecBase(final int radix, final int scale) {
        super();
        if (radix < MIN_RADIX) {
            throw new IllegalArgumentException("radix(" + radix + ") < " + MIN_RADIX);
        }
        if (radix > MAX_RADIX) {
            throw new IllegalArgumentException("radix(" + radix + ") > " + MAX_RADIX);
        }
        if (scale < MIN_SCALE) {
            throw new IllegalArgumentException("scale(" + scale + ") < " + MIN_SCALE);
        }
        if (scale > MAX_SCALE) {
            throw new IllegalArgumentException("scale(" + scale + ") > " + MAX_SCALE);
        }
        this.radix = radix;
        this.scale = scale;
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
     * Returns current value of {@code scale}.
     *
     * @return the current value of {@code scale}.
     */
    public int getScale() {
        return scale;
    }

    final int radix;

    final int scale;
}
