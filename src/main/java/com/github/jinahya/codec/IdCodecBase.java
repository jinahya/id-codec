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


/**
 * An abstract base class.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
abstract class IdCodecBase {


    /**
     * The minimum value for {@code radix}.
     */
    public static final int RADIX_MINIMUM = Character.MIN_RADIX;


    /**
     * The maximum value for {@code radix}.
     */
    public static final int RADIX_MAXIMUM = Character.MAX_RADIX;


    /**
     * The default value for {@code radix}.
     */
    public static final int RADIX_DEFAULT = RADIX_MAXIMUM;


    /**
     * The minimum value for {@code scale}.
     */
    public static final int SCALE_MINIMUM = 1;


    /**
     * The maximum value for {@code scale}.
     */
    public static final int SCALE_MAXIMUM
        = Long.toString(Long.MAX_VALUE).length()
          - Integer.toString(Integer.MAX_VALUE).length() - 1;


    /**
     * The default value for {@code scale}.
     */
    public static final int SCALE_DEFAULT = SCALE_MINIMUM;


    /**
     * Checks that the specified radix value is valid.
     *
     * @param radix the radix value to check for validity
     *
     * @return {@code radix} if valid
     *
     * @throws IllegalArgumentException if {@code radix} is less than
     * {@value #RADIX_MINIMUM} or greater than {@value #RADIX_MAXIMUM}.
     */
    static int requireValidRadix(final int radix) {

        if (radix < RADIX_MINIMUM) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") < " + RADIX_MINIMUM);
        }

        if (radix > RADIX_MAXIMUM) {
            throw new IllegalArgumentException(
                "radix(" + radix + ") > " + RADIX_MAXIMUM);
        }

        return radix;
    }


    /**
     * Checks that the specified scale value is valid.
     *
     * @param scale the scale value to check for validity
     *
     * @return {@code scale} if valid
     *
     * @throws IllegalArgumentException if {@code scale} is less than
     * {@value #SCALE_MINIMUM} or greater than {@value #SCALE_MAXIMUM}.
     */
    static int requireValidScale(final int scale) {

        if (scale < SCALE_MINIMUM) {
            throw new IllegalArgumentException(
                "scale(" + scale + ") < " + SCALE_MINIMUM);
        }

        if (scale > SCALE_MAXIMUM) {
            throw new IllegalArgumentException(
                "scale(" + scale + ") > " + SCALE_MAXIMUM);
        }

        return scale;
    }


    static {
        requireValidRadix(RADIX_DEFAULT);
        requireValidScale(SCALE_DEFAULT);
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
     * Replaces the current value of {@code radix} with given.
     *
     * @param radix new value for {@code radix}.
     *
     * @see #requireValidRadix(int)
     */
    public void setRadix(final int radix) {

        this.radix = requireValidRadix(radix);
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
     * Replaces the current value of {@code scale} with given.
     *
     * @param scale new value for {@code scale} between {@value #SCALE_MINIMUM}
     * (inclusive) and {@value #SCALE_MAXIMUM} (inclusive).
     *
     * @see #requireValidScale(int)
     */
    public void setScale(final int scale) {

        this.scale = requireValidScale(scale);
    }


    /**
     * radix.
     */
    private int radix = RADIX_DEFAULT;


    /**
     * scale.
     */
    private int scale = SCALE_DEFAULT;


}

