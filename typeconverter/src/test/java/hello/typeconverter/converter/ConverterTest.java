package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer integer = converter.convert("10");
        Assertions.assertThat(integer).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String string = converter.convert(10);
        Assertions.assertThat(string).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort convert = converter.convert("123.412.541.212:8080");
        assert convert != null;
        Assertions.assertThat(convert.getIp()).isEqualTo("123.412.541.212");
        Assertions.assertThat(convert.getPort()).isEqualTo(8080);
        Assertions.assertThat(convert).isEqualTo(new IpPort("123.412.541.212", 8080)); //@EqualsAndHashCode
    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String convert = converter.convert(new IpPort("123.123.123.123", 8080));
        Assertions.assertThat(convert).isEqualTo("123.123.123.123:8080");
    }

}
