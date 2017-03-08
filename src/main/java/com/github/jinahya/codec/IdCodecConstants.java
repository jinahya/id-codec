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
 * Constants.
 */
public final class IdCodecConstants {

    // ------------------------------------------------------------------- RADIX
    /**
     * The minimum value for {@code radix} which is {@value #RADIX_MINIMUM}.
     */
    public static final int RADIX_MINIMUM = Character.MIN_RADIX;

    /**
     * The maximum value for {@code radix} which is {@value #RADIX_MAXIMUM}.
     */
    public static final int RADIX_MAXIMUM = Character.MAX_RADIX;

    // ------------------------------------------------------------------- SCALE
    /**
     * The minimum value for {@code scale} which is {@value #SCALE_MINIMUM}.
     */
    public static final int SCALE_MINIMUM = 1;

    /**
     * The maximum value for {@code scale} which is {@value #SCALE_MAXIMUM}.
     */
    public static final int SCALE_MAXIMUM = 8;
//        = Long.toString(Long.MAX_VALUE).length()
//          - Integer.toString(Integer.MAX_VALUE).length() - 1;

    // -------------------------------------------------------------------------
    private IdCodecConstants() {
        super();
    }
}
