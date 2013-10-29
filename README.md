id-codec
========

(Wanna donate some?)[https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=GWDFLJNSZSEGG&lc=KR&item_name=github&currency_code=USD&bn=PP%2dDonationsBF%3abtn_donateCC_LG%2egif%3aNonHosted]

### Apidocs
* 1.0.1-SNAPSHOT ([github](http://jinahya.github.io/id-codec/site/1.0.1-SNAPSHOT/apidocs/index.html)) ([jinahya](https://jinahya.com/mvn/site/com.github.jinahya/id-codec/1.0.1-SNAPSHOT/apidocs/index.html))
* 1.0 ([github](http://jinahya.github.io/id-codec/site/1.0/apidocs/index.html)) ([jinahya](https://jinahya.com/mvn/site/com.github.jinahya/id-codec/1.0/apidocs/index.html))

## Simple Encoding
```java
final IdEncoder encoder = new IdEncoder();

// long
final long decoded;
final String encoded = encoder.encodeLong(decoded);

// UUID
final UUID decoded;
final String encoded = encoder.encodeUuid(decoded);
```

## Simple Decoding
```java
final IdDecoder decoder = new IdDecoder();

// long
final String encoded;
final long decoded = decoder.decodeLong(encoded);

// UUID
final String encoded;
final UUID decoded = decoder.decodeUuid(encoded);
```
