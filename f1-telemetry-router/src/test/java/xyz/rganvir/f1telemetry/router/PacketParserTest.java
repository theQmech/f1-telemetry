package xyz.rganvir.f1telemetry.router;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import xyz.rganvir.f1telemetry.router.messages.ByteBufferParser;
import xyz.rganvir.f1telemetry.router.messages.PacketHeader;
import xyz.rganvir.f1telemetry.router.messages.PacketParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

class PacketParserTest {

    private static Stream<Arguments> binaryCapturesSmallSet() {
        return IntStream.range(0, 100).mapToObj(i -> {
            try {
                return Files.readAllBytes(Paths.get("src", "test", "resources", i + ".bin"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource("binaryCapturesSmallSet")
    void shouldParseHeader(byte[] rawData) {
        ByteBufferParser buffer = new ByteBufferParser(rawData, 0, rawData.length);
        assertThat(PacketParser.getPacketHeader(buffer)).extracting(PacketHeader::packetFormat).isEqualTo(2023);
    }

    @ParameterizedTest
    @MethodSource("binaryCapturesSmallSet")
    void shouldParsePacketIfSupported(byte[] rawData) {
        assertThatNoException().isThrownBy(() -> PacketParser.parse(rawData, rawData.length));
    }
}
