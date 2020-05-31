package com.github.jinahya.codec;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Character.MAX_RADIX;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Long.parseUnsignedLong;
import static java.util.Objects.requireNonNull;

@Slf4j
class CouponNumber {

    private static final int S_MAX_VALUE_DIGITS = Short.toString(Short.MAX_VALUE).length();

    private static final int I_MAX_VALUE_DIGITS = Integer.toString(Integer.MAX_VALUE).length();

    private static final int L_MAX_VALUE_DIGITS = Long.toString(Long.MAX_VALUE).length();

    static String encode2(int decoded) {
        if (false) {
            return Integer.toString(((decoded >> 4) & 0xF) + 20, MAX_RADIX) + Integer.toString((decoded & 0xF) + 20, MAX_RADIX);
        }
        int v = 0;
        for (int i = 0; i < 2; i++) {
            v <<= 5;
            v |= 0b10000 | (decoded & 0b1111);
            decoded >>= 4;
        }
        return Integer.toString(v, MAX_RADIX);
    }

    static int decode2(final String encoded) {
        assert encoded != null;
        assert encoded.length() == 2;
        if (false) {
            return (Integer.parseInt(encoded.substring(0, 1), MAX_RADIX) - 20) << 4 | Integer.parseInt(encoded.substring(1), MAX_RADIX) - 20;
        }
        int decoded = 0;
        int v = parseInt(encoded, MAX_RADIX);
        for (int i = 0; i < 2; i++) {
            decoded <<= 4;
            decoded |= v & 0b1111;
            v >>= 5;
        }
        return decoded;
    }

    static String encode4(final int decoded) {
        return encode2(decoded >> Byte.SIZE) + encode2(decoded);
    }

    static int decode4(final String encoded) {
        assert encoded != null;
        assert encoded.length() == 4;
        return decode2(encoded.substring(0, 2)) << Byte.SIZE | decode2(encoded.substring(2));
    }

    static String encode8(final int decoded) {
        return encode4(decoded >> Short.SIZE).concat(encode4(decoded));
    }

    static int decode8(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 8) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 8");
        }
        return decode4(encoded.substring(0, 4)) << Short.SIZE | decode4(encoded.substring(4));
    }

    static String encode16(final long decoded) {
        return encode8((int) (decoded >> Integer.SIZE)).concat(encode8((int) decoded));
    }

    static long decode16(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 16) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 16");
        }
        return (long) decode8(encoded.substring(0, 8)) << Integer.SIZE | decode8(encoded.substring(8)) & 0xFFFFFFFFL;
    }

    static String encode7(final int decoded) {
        long d = decoded & 0xFFFFFFFFL;
        long v = 0;
        for (int i = 0; i < 3; i++) {
            v <<= 5;
            v |= 0b10000 | (d & 0b1111);
            d >>= 4;
        }
        for (int i = 0; i < 4; i++) {
            v <<= 5;
            v |= d & 0b11111;
            d >>= 5;
        }
        return Long.toUnsignedString(v, MAX_RADIX);
    }

    static int decode7(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 7) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 7");
        }
        int decoded = 0;
        long v = parseUnsignedLong(encoded, MAX_RADIX);
        for (int i = 0; i < 4; i++) {
            decoded <<= 5;
            decoded |= v & 0b11111;
            v >>= 5;
        }
        for (int i = 0; i < 3; i++) {
            decoded <<= 4;
            decoded |= v & 0b1111;
            v >>= 5;
        }
        return decoded;
    }

    static String encode13(long decoded) {
        final StringBuilder builder = new StringBuilder(13);
        builder.append(Long.toString(0b10000 | (decoded & 0b1111), MAX_RADIX));
        decoded >>= 4;
        for (int i = 0; i < 12; i++) {
            builder.append(Long.toString(decoded & 0b11111, MAX_RADIX));
            decoded >>= 5;
        }
        return builder.toString();
    }

    static long decode13(final String encoded) {
        if (requireNonNull(encoded, "encoded is null").length() != 13) {
            throw new IllegalArgumentException("encoded.length(" + encoded.length() + ") != 13");
        }
        long decoded = 0L;
        for (int i = 12; i > 0; i--) {
            decoded <<= 5;
            decoded |= parseLong(encoded.substring(i, i + 1), MAX_RADIX);
        }
        decoded <<= 4;
        decoded |= parseLong(encoded.substring(0, 1), MAX_RADIX) & 0b1111;
        return decoded;
    }
}
