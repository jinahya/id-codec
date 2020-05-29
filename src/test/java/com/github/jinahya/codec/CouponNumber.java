package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;

import static java.lang.Character.MAX_RADIX;
import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.concurrent.ThreadLocalRandom.current;

@Slf4j
class CouponNumber {

    private static final int S_MAX_VALUE_DIGITS = Short.toString(Short.MAX_VALUE).length();

    private static final int I_MAX_VALUE_DIGITS = Integer.toString(Integer.MAX_VALUE).length();

    private static final int L_MAX_VALUE_DIGITS = Long.toString(Long.MAX_VALUE).length();

    static String encode16(final int decoded) {
        assert decoded >= 0;
        assert decoded < 65566;
        final StringBuilder builder = new StringBuilder();
        builder.ensureCapacity(builder.capacity() + I_MAX_VALUE_DIGITS);
        builder.append(decoded);
        int p;
        for (p = 0; builder.length() < S_MAX_VALUE_DIGITS; p++) {
            builder.append(current().nextInt(10)); // 0 - 9
        }
        assert p >= 0;
        assert p < S_MAX_VALUE_DIGITS;
        builder.append(format("%03d", current().nextInt(474)));
        builder.append(p);
        builder.append(p >= 1 ? 1 : current().nextInt(1, 3));
        builder.reverse();
        final String encoded = Integer.toString(parseInt(builder.toString()), MAX_RADIX);
        assert encoded.length() == 6;
        return encoded;
    }

    static int decode16(final String encoded) {
        assert encoded != null;
        assert encoded.length() == 6;
        final StringBuilder builder = new StringBuilder();
        builder.append(parseInt(encoded, MAX_RADIX));
        builder.delete(0, 1);
        final int p = getNumericValue(builder.charAt(0));
        builder.delete(0, p + (1 + 3));
        builder.reverse();
        final int decoded = parseInt(builder.toString());
        assert decoded >= 0;
        assert decoded < 65536;
        return decoded;
    }

    static <R> R encodeInt(final int decoded, final BiFunction<? super String, ? super String, ? extends R> function) {
        requireNonNull(function, "function is null");
        return function.apply(encode16(decoded >>> Short.SIZE), encode16(decoded & 0xFFFF));
    }

    static String encodeInt(final int decoded) {
        return encodeInt(decoded, (s1, s2) -> s1 + "-" + s2);
    }

    static int decodeInt(final String h, final String l) {
        // TODO: 2020/05/29 validate arguments!
        return (decode16(h) << Short.SIZE) | decode16(l);
    }

    static int decodeInt(final String encoded) {
        final int index = encoded.indexOf('-');
        assert index != -1;
        return decodeInt(encoded.substring(0, index), encoded.substring(index + 1));
    }
}
