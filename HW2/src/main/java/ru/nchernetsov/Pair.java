package ru.nchernetsov;

public final class Pair<F, S> {

    private final F first;

    private final S second;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Pair<F, S> of(F left, S right) {
        return new Pair<>(left, right);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }
}
