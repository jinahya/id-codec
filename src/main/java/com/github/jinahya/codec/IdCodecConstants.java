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
public final class IdCodecConstants {

    /**
     * The minimum value for {@code scale} which is {@value}.
     */
    public static final int MIN_SCALE = 1;

    /**
     * The maximum value for {@code scale} which is {@value}.
     */
    public static final int MAX_SCALE = 8;

    /**
     * The minimum value for {@code radix} which is {@value}.
     */
    public static final int MIN_RADIX = Character.MIN_RADIX;

    /**
     * The maximum value for {@code radix} which is {@value}.
     */
    public static final int MAX_RADIX = Character.MAX_RADIX;

    private IdCodecConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
