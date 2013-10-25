/*
 * Copyright 2012 onacit.
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


import java.util.UUID;
import org.testng.annotations.Test;


/**
 *
 * @author onacit
 */
public class IdDecoderTest {


    static long decodeLong(final String encoded) {

        return IdDecoder.decodeLong(encoded, IdDecoder.DEFAULT_RADIX,
                                    IdDecoder.DEFAULT_SCALE);
    }


    static UUID decodeUuid(final String encoded) {

        return IdDecoder.decodeUuid(encoded, IdDecoder.DEFAULT_RADIX,
                                    IdDecoder.DEFAULT_SCALE);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void decodeLong_nullEncoded_nullPointerException() {

        decodeLong(null);
    }


    @Test(expectedExceptions = {NullPointerException.class})
    public static void decodeUuid_nullEncoded_nulPointerException() {

        decodeUuid(null);
    }


}

