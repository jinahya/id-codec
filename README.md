id-codec
========

## Versions
version|site|apidocs
-------|----|-------
1.1.3-SNAPSHOT|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.3-SNAPSHOT/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.3-SNAPSHOT/apidocs/index.html)
1.1.2|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.2/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.2/apidocs/index.html)

## Usages
### Encoding
```java
final IdEncoder encoder = new IdEncoder();

// long
final long decoded;
final String encoded = encoder.encode(decoded);

// UUID
final UUID decoded;
final String encoded = encoder.encodeUuid(decoded);
```

### Decoding
```java
final IdDecoder decoder = new IdDecoder();

// long
final String encoded;
final long decoded = decoder.decode(encoded);

// UUID
final String encoded;
final UUID decoded = decoder.decodeUuid(encoded);
```

## Examples
### Encoding/Decoding `long`s
~~~
decoded: -8775087020812241672

encoded:      gon58uq-mg7v18d
encoded:     13njurrm-d9gdmgt
encoded:     188xlh5e-d9gdmgt
encoded:     pvemnma-108d35dp
encoded:    188xlh5e-14tqturh
encoded:     13njurrm-huu4bul
encoded:      z2642du-8o2mx31
encoded:      7hvnu36-mg7v18d
encoded:      7hvnu36-vmzcfzx
encoded:      ugsdd02-d9gdmgt
encoded:      ugsdd02-huu4bul
encoded:    188xlh5e-14tqturh
encoded:      pvemnma-vmzcfzx
encoded:      ugsdd02-huu4bul
encoded:    188xlh5e-108d35dp
encoded:     ugsdd02-19f4kk59
~~~
### Encoding/Decoding `UUID`s
~~~
decoded:     1b3a263e-0928-4ad1-b728-742d0d06506e

encoded:          2riq02e-1ps1ian-dcsnc9b-1ki82sk
encoded:          1dwll92-40gwuzj-mjk4r0v-2hkz0o4
encoded:         2riq02e-1ps1ian-19igu9xr-13yulus
encoded:         1ufz26u-33e5x3z-19igu9xr-3enpyjo
encoded:          1ufz26u-198o1cv-r4xvgen-211ljqc
encoded:         3823h06-40gwuzj-14x33kjz-1ki82sk
encoded:           xd84ba-40gwuzj-vqbm5sf-211ljqc
encoded:          3olgxxy-4h0abxb-dcsnc9b-13yulus
encoded:           2riq02e-2musg67-8rewmvj-nfh4x0
encoded:           3olgxxy-spakf3-mjk4r0v-3enpyjo
encoded:           gtundi-33e5x3z-hy6e1n3-13yulus
encoded:          3olgxxy-2musg67-8rewmvj-13yulus
encoded:          1ufz26u-1ps1ian-mjk4r0v-1ki82sk
encoded:          3823h06-40gwuzj-r4xvgen-13yulus
encoded:          gtundi-3jxje1r-10bpcv67-2y4chlw
encoded:         1ufz26u-26bez8f-10bpcv67-1ki82sk
~~~
