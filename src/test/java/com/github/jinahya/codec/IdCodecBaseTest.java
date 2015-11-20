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


import static java.lang.invoke.MethodHandles.lookup;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon
 */
public class IdCodecBaseTest {


    private static final Logger logger = getLogger(lookup().lookupClass());


    @Test
    public void radix() {

        logger.info("RADIX_MINIMUM: {}", IdCodecBase.RADIX_MINIMUM);
        logger.info("RADIX_MAXIMUM: {}", IdCodecBase.RADIX_MAXIMUM);
        logger.info("RADIX_DEFAULT: {}", IdCodecBase.RADIX_DEFAULT);
    }


    @Test
    public void scale() {

        logger.info("SCALE_MINIMUM: {}", IdCodecBase.SCALE_MINIMUM);
        logger.info("SCALE_MAXIMUM: {}", IdCodecBase.SCALE_MAXIMUM);
        logger.info("SCALE_DEFAULT: {}", IdCodecBase.SCALE_DEFAULT);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setScaleWithTooSmall() {

        new IdCodecBase() {
        }.setScale(IdCodecBase.SCALE_MINIMUM - 1);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setScaleWithTooBigl() {

        new IdCodecBase() {
        }.setScale(IdCodecBase.SCALE_MAXIMUM + 1);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setRadixWithTooSmall() {

        new IdCodecBase() {
        }.setRadix(IdCodecBase.RADIX_MINIMUM - 1);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setRadixWithTooBigl() {

        new IdCodecBase() {
        }.setRadix(IdCodecBase.RADIX_MAXIMUM + 1);
    }


}

