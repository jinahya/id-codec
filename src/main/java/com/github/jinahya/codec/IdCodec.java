package com.github.jinahya.codec;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.WeakHashMap;

public final class IdCodec {

    private static final Map<Integer, Map<Integer, IdEncoder>> ENCODERS =
            Collections.synchronizedMap(new WeakHashMap<Integer, Map<Integer, IdEncoder>>());

    private static final Map<Integer, Map<Integer, IdDecoder>> DECODERS =
            Collections.synchronizedMap(new WeakHashMap<Integer, Map<Integer, IdDecoder>>());

    private static final int DEFAULT_SCALE = IdCodecConstants.MIN_SCALE;

    private static final int DEFAULT_RADIX = IdCodecConstants.MAX_RADIX;

    private static final String THREAD_LOCAL_RANDOM_CLASS_NAME = "java.util.concurrent.ThreadLocalRandom";

    private static Class<?> threadLocalRandomClass = null;

    private static Method threadLocalRandomCurrent = null;

    private static Random getThreadLocalRandom() {
        Class<?> c = threadLocalRandomClass;
        if (c == null) {
            try {
                threadLocalRandomClass = c = Class.forName(THREAD_LOCAL_RANDOM_CLASS_NAME);
            } catch (final ClassNotFoundException cnfe) {
                throw new RuntimeException(cnfe);
            }
        }
        Method m = threadLocalRandomCurrent;
        if (m == null) {
            try {
                threadLocalRandomCurrent = m = c.getMethod("current");
                if (!m.isAccessible()) {
                    m.setAccessible(true);
                }
            } catch (final NoSuchMethodException nsme) {
                throw new RuntimeException(nsme);
            }
        }
        try {
            return (Random) m.invoke(null);
        } catch (final Exception e) {
            throw new RuntimeException("failed to invoke " + threadLocalRandomCurrent, e);
        }
    }

    public static String encodeLong(final int scale, final int radix, final long decoded, final Random random) {
        IdCodecConstraints.requireValidScale(scale);
        IdCodecConstraints.requireValidRadix(radix);
        if (random == null) {
            throw new NullPointerException("random is null");
        }
        final Map<Integer, IdEncoder> encoders;
        synchronized (ENCODERS) {
            Map<Integer, IdEncoder> m = ENCODERS.get(scale);
            if (m == null) {
                m = Collections.synchronizedMap(new WeakHashMap<Integer, IdEncoder>());
                ENCODERS.put(scale, m);
            }
            encoders = m;
        }
        final IdEncoder encoder;
        synchronized (encoders) {
            IdEncoder e = encoders.get(radix);
            if (e == null) {
                e = new IdEncoder(scale, radix);
                encoders.put(radix, e);
            }
            encoder = e;
        }
        return encoder.encode(decoded, random);
    }

    public static String encodeLong(final long decoded, final Random random) {
        return encodeLong(DEFAULT_SCALE, DEFAULT_RADIX, decoded, random);
    }

    public static String encodeLong(final long decoded) {
        return encodeLong(decoded, getThreadLocalRandom());
    }

    public static long decodeLong(final int scale, final int radix, final String decoded) {
        final Map<Integer, IdDecoder> decoders;
        synchronized (DECODERS) {
            Map<Integer, IdDecoder> m = DECODERS.get(scale);
            if (m == null) {
                m = Collections.synchronizedMap(new WeakHashMap<Integer, IdDecoder>());
                DECODERS.put(scale, m);
            }
            decoders = m;
        }
        final IdDecoder decoder;
        synchronized (decoders) {
            IdDecoder e = decoders.get(radix);
            if (e == null) {
                e = new IdDecoder(scale, radix);
                decoders.put(radix, e);
            }
            decoder = e;
        }
        return decoder.decode(decoded);
    }

    public static long decodeLong(final String decoded) {
        return decodeLong(DEFAULT_SCALE, DEFAULT_RADIX, decoded);
    }

    public static String encodeUuid(final int scale, final int radix, final UUID decoded, final Random random) {
        IdCodecConstraints.requireValidScale(scale);
        IdCodecConstraints.requireValidRadix(radix);
        if (decoded == null) {
            throw new NullPointerException("decoded is null");
        }
        if (random == null) {
            throw new NullPointerException("random is null");
        }
        final Map<Integer, IdEncoder> encoders;
        synchronized (ENCODERS) {
            Map<Integer, IdEncoder> m = ENCODERS.get(scale);
            if (m == null) {
                m = Collections.synchronizedMap(new WeakHashMap<Integer, IdEncoder>());
                ENCODERS.put(scale, m);
            }
            encoders = m;
        }
        final IdEncoder encoder;
        synchronized (encoders) {
            IdEncoder e = encoders.get(radix);
            if (e == null) {
                e = new IdEncoder(scale, radix);
                encoders.put(radix, e);
            }
            encoder = e;
        }
        return encoder.encodeUuid(decoded, random);
    }

    public static String encodeUuid(final UUID decoded, final Random random) {
        return encodeUuid(DEFAULT_SCALE, DEFAULT_RADIX, decoded, random);
    }

    public static String encodeUuid(final UUID decoded) {
        return encodeUuid(decoded, getThreadLocalRandom());
    }

    public static UUID decodeUuid(final int scale, final int radix, final String decoded) {
        final IdDecoder decoder;
        final Map<Integer, IdDecoder> decoders;
        synchronized (DECODERS) {
            Map<Integer, IdDecoder> m = DECODERS.get(scale);
            if (m == null) {
                m = Collections.synchronizedMap(new WeakHashMap<Integer, IdDecoder>());
                DECODERS.put(scale, m);
            }
            decoders = m;
        }
        synchronized (decoders) {
            IdDecoder e = decoders.get(radix);
            if (e == null) {
                e = new IdDecoder(scale, radix);
                decoders.put(radix, e);
            }
            decoder = e;
        }
        return decoder.decodeUuid(decoded);
    }

    public static UUID decodeUuid(final String decoded) {
        return decodeUuid(DEFAULT_SCALE, DEFAULT_RADIX, decoded);
    }

    private IdCodec() {
        throw new AssertionError("instantiation is not allowed");
    }
}
