package eu.europa.ec.digit.search.improveperformance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NumberService {

    private static final int COLLECTION_SIZE = 100000;

    // this guy needs a bit more specific input-output requirements
    // eg what if it receives [0, 1, 2, 2, 1]? The logic is duplicated
    public Integer findSmallestDuplicate(List<Integer> data) {

        for (int i = 0; i < data.size(); i++) {

            for (int j = i + 1; j < data.size(); j++) {

                if (data.get(i).equals(data.get(j))) {

                    log.info("found number {}", data.get(j));
                    return data.get(j);
                }
            }
        }

        return 0;

    }

    public Integer findSmallestDuplicateImproved(List<Integer> data) {
        int size = data.size();
        // if elements can be larger than data.size(), switch to
        // slower approach with hashmap and its buckets
        byte[] duplicateIndices = new byte[size];
        Arrays.fill(duplicateIndices, (byte)0);
        int element;
        for (Integer datum : data) {
            element = datum;
            if (duplicateIndices[element] != 0) {
                log.info("found number {}", element);
                return element;
            }
            duplicateIndices[element]++;
        }

        return 0;

    }

    /**
     * Faster version of previous method, but it requires collections
     * with exactly one non-unique element
     */
    public Integer findSmallestDuplicateFaster(List<Integer> data) {
        return XorOps.findDuplicate(data);

    }

    public List<Integer> generateData() {
        return generateData(COLLECTION_SIZE);
    }

    List<Integer> generateData(int size) {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            data.add(i);
        }

        Random r = new Random();
        data.add(data.get(r.nextInt(data.size())));
        log.info("number is: {}", data.get(data.size() - 1));
        Collections.shuffle(data);

        return data;
    }
}
