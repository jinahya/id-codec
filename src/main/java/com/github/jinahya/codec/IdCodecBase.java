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


import static com.github.jinahya.codec.IdCodecConstants.RADIX_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.RADIX_MINIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MINIMUM;


/**
 * An abstract base class.
 *
 * @param <T> implementation type parameter
 */
abstract class IdCodecBase<T extends IdCodecBase<T>> {


    /**
     * Return current value of {@code radix}.
     *
     * @return the current value of {@code radix}.
     */
    public int getRadix() {

        return radix;
    }


    /**
     * Replaces the current {@code radix} value with given.
     *
     * @param radix new {@code radix} value between
     * {@link IdCodecConstants#RADIX_MINIMUM} (inclusive) and
     * {@link IdCodecConstants#RADIX_MAXIMUM} (inclusive).
     */
    public void setRadix(final int radix) {

        if (radix < RADIX_MINIMUM) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") < " + RADIX_MINIMUM);
        }

        if (radix > RADIX_MAXIMUM) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") > " + RADIX_MAXIMUM);
        }

        this.radix = radix;
    }


    @SuppressWarnings("unchecked")
    T radix(final int radix) {

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
     * Replaces the current {@code scale} value with given.
     *
     * @param scale new {@code scale} between
     * {@link IdCodecConstants#SCALE_MINIMUM} (inclusive) and
     * {@link IdCodecConstants#SCALE_MAXIMUM} (inclusive).
     */
    public void setScale(final int scale) {

        if (scale < SCALE_MINIMUM) {
            throw new IllegalArgumentException(
                "scale(" + scale + ") < " + SCALE_MINIMUM);
        }

        if (scale > SCALE_MAXIMUM) {
            throw new IllegalArgumentException(
                "scale(" + scale + ") > " + SCALE_MAXIMUM);
        }

        this.scale = scale;
    }


    @SuppressWarnings("unchecked")
    T scale(final int scale) {

        setScale(scale);

        return (T) this;
    }


    private int radix = RADIX_MAXIMUM;


    private int scale = SCALE_MINIMUM;

}

