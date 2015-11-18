id-codec
========

## Versions
version|site|apidocs
-------|----|-------
1.0.2-SNAPSHOT|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.0.2-SNAPSHOT/index.html)|[jinahya.github.io](http://jinahya.github.io/id-codec/site/1.0.2-SNAPSHOT/apidocs/index.html)
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

## Examples
### Encoding/Decoding a `long`
~~~
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
       decoded:  8468775952159530071
   encoded[00]:      pipm45r-a3cnp0f ->  8468775952159530071
   encoded[01]:     756namn-1690umov ->  8468775952159530071
   encoded[02]:     17w8kxov-nvhvt5r ->  8468775952159530071
   encoded[03]:      gby4pe7-x29d7xb ->  8468775952159530071
   encoded[04]:      gby4pe7-ja453rz ->  8468775952159530071
   encoded[05]:     kxbverz-11nn3xb3 ->  8468775952159530071
   encoded[06]:     17w8kxov-eoqeee7 ->  8468775952159530071
   encoded[07]:      u43ctjj-x29d7xb ->  8468775952159530071
   encoded[08]:      u43ctjj-nvhvt5r ->  8468775952159530071
   encoded[09]:      u43ctjj-ja453rz ->  8468775952159530071
   encoded[10]:     756namn-11nn3xb3 ->  8468775952159530071
   encoded[11]:      kxbverz-ja453rz ->  8468775952159530071
   encoded[12]:     17w8kxov-eoqeee7 ->  8468775952159530071
   encoded[13]:      bqke00f-ja453rz ->  8468775952159530071
   encoded[14]:      yph3ixb-nvhvt5r ->  8468775952159530071
   encoded[15]:      u43ctjj-x29d7xb ->  8468775952159530071
~~~
