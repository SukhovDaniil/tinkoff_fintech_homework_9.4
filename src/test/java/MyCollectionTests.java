import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyCollectionTests {

    @Test
    public void notContainsIntegerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertFalse(collection.contains(10));
    }

    @Test
    public void containsNullTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.contains(null));
    }

    @Test
    public void notContainsAllIntegersTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertFalse(collection.contains(Arrays.asList(2, 10)));
    }

    @Test
    public void containsAllNullTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Collection<Integer> containsCollection = new ArrayList<>();

        containsCollection.add(null);

        Assertions.assertTrue(collection.containsAll(containsCollection));
    }

    @Test
    public void notRemoveIntegerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertFalse(collection.remove(10));
    }

    @Test
    public void removeNullTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.remove(null));
    }

    @Test
    public void removeAllIntegerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.removeAll(Arrays.asList(2)));
    }

    @Test
    public void removeAllIntegersTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.removeAll(Arrays.asList(2, 5)));
    }

    @Test
    public void notRemoveAllIntegerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertFalse(collection.removeAll(Arrays.asList(10)));
    }

    @Test
    public void retainAllIntegerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.retainAll(Arrays.asList(2)));
    }

    @Test
    public void retainAllIntegersTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertTrue(collection.retainAll(Arrays.asList(2, 5)));
    }

    @Test
    public void toArraySmallerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertEquals(
                Arrays.toString(new Integer[]{0, 2, null, 5, 2}),
                Arrays.toString(collection.toArray(new Integer[5]))
        );
    }

    @Test
    public void toArrayBiggerTest() {
        MyCollection<Integer> collection = new MyCollection<>();

        collection.add(0);
        collection.add(2);
        collection.add(null);
        collection.add(5);
        collection.add(2);

        Assertions.assertEquals(
                Arrays.toString(new Integer[]{0, 2, null, 5, 2, 0, 0}),
                Arrays.toString(collection.toArray(new Integer[]{0, 0, 0, 0, 0, 0, 0}))
        );
    }

}
