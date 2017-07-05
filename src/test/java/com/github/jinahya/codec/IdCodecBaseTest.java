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

import static com.github.jinahya.codec.IdCodecConstants.RADIX_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.RADIX_MINIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MAXIMUM;
import static com.github.jinahya.codec.IdCodecConstants.SCALE_MINIMUM;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.annotations.Test;

abstract class IdCodecBaseTest<T extends IdCodecBase<T>> {

    private static final Logger logger = getLogger(lookup().lookupClass());

    // -------------------------------------------------------------------------
    public IdCodecBaseTest(final Class<T> type) {
        super();
        this.type = requireNonNull(type);
    }

    // -------------------------------------------------------------------------
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setScaleWithTooSmall() throws ReflectiveOperationException {
        type.newInstance().setScale(SCALE_MINIMUM - 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setScaleWithTooBigl() {
        new IdCodecBase() {
        }.setScale(SCALE_MAXIMUM + 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setRadixWithTooSmall() {
        new IdCodecBase() {
        }.setRadix(RADIX_MINIMUM - 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setRadixWithTooBigl() {
        new IdCodecBase() {
        }.setRadix(RADIX_MAXIMUM + 1);
    }

    // -------------------------------------------------------------------------
    private final Class<T> type;
}
