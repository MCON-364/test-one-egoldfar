package edu.touro.las.mcon364.test;

import java.util.*;

public class StudyTracker {

    private final Map<String, List<Integer>> scoresByLearner = new HashMap<>();
    private final Deque<UndoStep> undoStack = new ArrayDeque<>();
    // Helper methods already provided for tests and local inspection.
    public Optional<List<Integer>> scoresFor(String name) {
        return Optional.ofNullable(scoresByLearner.get(name));
    }

    public Set<String> learnerNames() {
        return scoresByLearner.keySet();
    }
    /**
     * Problem 11
     * Add a learner with an empty score list.
     *
     * Return:
     * - true if the learner was added
     * - false if the learner already exists
     *
     * Throw IllegalArgumentException if name is null or blank.
     */
    public boolean addLearner(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        if (scoresByLearner.containsKey(name)) {
            return false;
        }
        scoresByLearner.put(name, new ArrayList<>());
        return true;
    }

    /**
     * Problem 12
     * Add a score to an existing learner.
     *
     * Return:
     * - true if the score was added
     * - false if the learner does not exist
     *
     * Valid scores are 0 through 100 inclusive.
     * Throw IllegalArgumentException for invalid scores.
     *
     * This operation should be undoable.
     */
    public boolean addScore(String name, int score) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        if (!scoresByLearner.containsKey(name)) {
            return false;
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("score is out of range");
        }
        var scores = scoresByLearner.get(name);
        scores.add(score);
        undoStack.push(() -> scoresByLearner.get(name).remove((Integer) score));
        return true;
    }

    /**
     * Problem 13
     * Return the average score for one learner.
     *
     * Return Optional.empty() if:
     * - the learner does not exist, or
     * - the learner has no scores
     */
    public Optional<Double> averageFor(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");
        }
        if (!scoresByLearner.containsKey(name) || scoresByLearner.get(name).isEmpty()) {
            return Optional.empty();
        }
        var average = scoresByLearner.get(name).stream()
                .mapToDouble(Double::valueOf)
                .average();
        return Optional.of(average.getAsDouble());
    }

    /**
     * Problem 14
     * Convert a learner average into a letter band.
     *
     * A: 90+
     * B: 80-89.999...
     * C: 70-79.999...
     * D: 60-69.999...
     * F: below 60
     *
     * Return Optional.empty() when no average exists.
     */
    public Optional<String> letterBandFor(String name) {

        var average = averageFor(name);
        if (average.isPresent()) {
            return switch ((int) average.get().doubleValue()/10) {
                case 9,10 -> Optional.of("A");
                case 8 -> Optional.of("B");
                case 7 -> Optional.of("C");
                case 6 -> Optional.of("D");
                default -> Optional.of("F");
            };
        } else {
            return Optional.empty();
        }
    }

    /**
     * Problem 15
     * Undo the most recent state-changing operation.
     *
     * Return true if something was undone.
     * Return false if there is nothing to undo.
     */
    public boolean undoLastChange() {
        if (undoStack.isEmpty()) {
            return false;
        }
        undoStack.pop().undo();
        return true;
    }


}
