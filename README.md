id-codec
========
[![Build Status](https://travis-ci.org/jinahya/ossrh-parent.svg)](https://travis-ci.org/jinahya/ossrh-parent)
[![Dependency Status](https://www.versioneye.com/user/projects/5654390dff016c0033000eb2/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5654390dff016c0033000eb2)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/id-codec.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.jinahya%22%20AND%20a%3A%22id-codec%22)

A small library can encode/decode `long` values to/from partially randomized `String`s.
## Versions
version|site|apidocs
-------|----|-------
1.1.4-SNAPSHOT|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.4-SNAPSHOT/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.4-SNAPSHOT/apidocs/index.html)
1.1.3|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.3/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.3/apidocs/index.html)
1.1.2|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.2/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.1.2/apidocs/index.html)

## Usages
### Encoding/Decoding `long`s
```java
final long decoded;
final String encoded = new IdEncoder().encode(decoded);
assert new IdDecoder().decode(encoded) == decoded;
```

### Encoding/Decoding `UUID`s
```java
final UUID decoded;
final String encoded = new IdEncoder().encodeUuid(decoded);
assert new IdDecoder().decode(encoded).equals(decoded);
```
## Examples
### Encoding/Decoding `long`s
Not that those `encoded` values will be decoded to exactly the same value as `decoded`.
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
Not also that those `encoded` values will be decoded to exactly the same value as `decoded`.
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
