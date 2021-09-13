package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService() {
        // 등록
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new StringToIntegerConverter());
        defaultConversionService.addConverter(new IntegerToStringConverter());
        defaultConversionService.addConverter(new StringToIpPortConverter());
        defaultConversionService.addConverter(new IpPortToStringConverter());

        // 사용
        Integer result = defaultConversionService.convert("10", Integer.class);
        Assertions.assertThat(result).isEqualTo(10);

        IpPort result2 = defaultConversionService.convert("101.123.123.123:8080", IpPort.class);
        Assertions.assertThat(result2).isEqualTo(new IpPort("101.123.123.123", 8080));
    }
}
