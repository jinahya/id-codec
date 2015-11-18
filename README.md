id-codec
========

## Versions
version|site|apidocs
-------|----|-------
1.0.1|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.0.1/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.0.1/apidocs/index.html)

## Usages
### Encoding
```java
final IdEncoder encoder = new IdEncoder();

// long
final long decoded;
final String encoded = encoder.encodeLong(decoded);

// UUID
final UUID decoded;
final String encoded = encoder.encodeUuid(decoded);
```

### Decoding
```java
final IdDecoder decoder = new IdDecoder();

// long
final String encoded;
final long decoded = decoder.decodeLong(encoded);

// UUID
final String encoded;
final UUID decoded = decoder.decodeUuid(encoded);
```
