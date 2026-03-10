package edu.touro.las.mcon364.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StreamsTest {

    private static BasicStreamsQuiz model = new BasicStreamsQuiz();

    @Test
    public void testSortedCourseNames() {
        var list = model.getSortedCourseNames();
        assertEquals(4, list.size());
        assertEquals("Algorithms",  list.get(0));
        assertEquals("Databases", list.get(1));
        assertEquals("Java",  list.get(2));
        assertEquals("Networks", list.get(3));
    }

    @Test
    public void testScoresAtLeast() {
        var num = model.countScoresAtLeast(84);
        assertEquals(9, num);
        num = model.countScoresAtLeast(100);
        assertEquals(0, num);
    }

    @Test
    public void testFirstLongWord() {
        var list = List.of("", "  ", "erty", "rt", "rtyuio", "eeeeeee", "rrrrrrrr");
        var word = model.firstLongWord(list,6);
        assertEquals(list.get(5), word.get());
    }

    @Test
    public void testFirstLongWord_None() {
        var list = List.of("", "gh");
        var word = model.firstLongWord(list,2);
        assertEquals(Optional.empty(), word);
    }

    @Test
    public void testSquareAll() {
        var nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        nums = model.squareAll(nums);
        assertEquals(9, nums.size());
        var squares = List.of(1, 4, 9, 16, 25, 36, 49, 64, 81);
        assertEquals(squares, nums);
    }

    @Test
    public void testAveragePassingScore() {
        var average = model.averagePassingScore();
        var rounded = Math.round(average);
        assertEquals(85, rounded);
    }
}
