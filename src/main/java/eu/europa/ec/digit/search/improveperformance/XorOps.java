package eu.europa.ec.digit.search.improveperformance;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
final class XorOps {

    private XorOps() {}

    // 1 ^ 2 ^ ... ^ n, where ^ means XOR
    static int xorSum(int n) {
        return IntStream
                .range(0, n + 1)
                // this can be optimized
                .reduce(0, (x, y) -> x ^ y);
    }

    // XOR math to detect duplicate
    // Benefits:
    // * memory-friendly - no additional collections, immutable input
    // * fast (faster than array-based or hashset-based options)
    // * stable time-wise
    // Drawbacks:
    // * iterates over all collections
    // * relies on the claim that collection contains all 0...n elements
    // * unfortunately, does not able to deal with collections w/o duplicates
    static int findDuplicate(List<Integer> oneDuplicateAndZero) {
        // a ^ b ^ c, all unique
        int uniqueXorAccumulator = xorSum(oneDuplicateAndZero.size() - 2);
        // a ^ b ^ c ^ a == a ^ a ^ b ^ c == b ^ c, condition - there is only
        // one non-unique number
        int xorAccumulator = oneDuplicateAndZero
                .stream()
                .reduce(0, (x, y) -> x ^ y);
        // (a ^ b ^ c) ^ (b ^ c) == a ^ b ^ b ^ c ^ c == a
        return uniqueXorAccumulator ^ xorAccumulator;

    }

}
