package eu.europa.ec.digit.search.improveperformance;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static eu.europa.ec.digit.search.improveperformance.XorOps.findDuplicate;
import static eu.europa.ec.digit.search.improveperformance.XorOps.xorSum;

import static java.util.Collections.emptyList;

import org.junit.jupiter.api.Test;

import java.util.List;

class XorOpsUnitTest {

    @Test
    void validateMath() {
        assertAll(
                () -> assertEquals(2 ^ 3, 1 ^ 1 ^ 2 ^ 3),
                () -> assertEquals(1 ^ 2 ^ 3, 2 ^ 3 ^ 1)
        );
    }

    @Test
    void validateXorSum() {
        assertAll(
                () -> assertEquals(0, xorSum(0)),
                () -> assertEquals(0 ^ 1, xorSum(1)),
                () -> assertEquals(0 ^ 1 ^ 2, xorSum(2))
        );
    }

    @Test
    void duplicateFound() {
        assertAll(
                () -> assertEquals(0, findDuplicate(emptyList())),
                () -> assertEquals(0, findDuplicate(List.of(0))),
                () -> assertEquals(0, findDuplicate(List.of(0, 0))),
                () -> assertEquals(0, findDuplicate(List.of(0, 0, 1))),
                () -> assertEquals(0, findDuplicate(List.of(0, 1, 0))),
                () -> assertEquals(1, findDuplicate(List.of(0, 1, 1))),
                () -> assertEquals(2, findDuplicate(List.of(0, 1, 2, 2)))
        );
    }

    @Test
    void performance() {
        System.out.println("Do you have enough memory?");
        System.out.println("Generating data");
        List<Integer> random = new NumberService()
                .generateData(100_000_000);

        System.out.println("Starting!");
        // data will fluctuate because of Random
        long start = System.nanoTime();
        System.out.println(findDuplicate(random));
        System.out.printf("Real perf: %d",
                (System.nanoTime() - start) / 1_000_000);
    }
}
