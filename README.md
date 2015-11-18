id-codec
========

### Site and Apidocs
* `${project.version}` ([site](http://jinahya.github.io/${project.artifactId}/site/${project.version}/index.html)) ([apidocs](http://jinahya.github.io/${project.artifactId}/site/${project.version}/apidocs/index.html))

## Encoding
```java
final IdEncoder encoder = new IdEncoder();

// long
final long decoded;
final String encoded = encoder.encodeLong(decoded);

// UUID
final UUID decoded;
final String encoded = encoder.encodeUuid(decoded);
```

## Decoding
```java
final IdDecoder decoder = new IdDecoder();

// long
final String encoded;
final long decoded = decoder.decodeLong(encoded);

// UUID
final String encoded;
final UUID decoded = decoder.decodeUuid(encoded);
```
