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
 */
abstract class IdCodecBase {

    /**
     * Creates a new instance with specified radix and scale.
     *
     * @param scale the scale; between {@link IdCodecConstants#MIN_SCALE} and {@link IdCodecConstants#MAX_SCALE}, both
     *              inclusive.
     * @param radix the radix; between {@link IdCodecConstants#MIN_RADIX} and {@link IdCodecConstants#MAX_RADIX}, both
     *              inclusive.
     */
    IdCodecBase(final int scale, final int radix) {
        super();
        this.scale = IdCodecConstraints.requireValidScale(scale);
        this.radix = IdCodecConstraints.requireValidRadix(radix);
    }

//    /**
//     * Returns current value of {@code scale}.
//     *
//     * @return the current value of {@code scale}.
//     */
//    public int getScale() {
//        return scale;
//    }
//
//    /**
//     * Return current value of {@code radix}.
//     *
//     * @return the current value of {@code radix}.
//     */
//    public int getRadix() {
//        return radix;
//    }

    final int scale;

    final int radix;
}
