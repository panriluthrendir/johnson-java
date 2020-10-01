public class IntPair {

    private final int left;
    private final int right;

    public IntPair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = o instanceof IntPair;
        if (result) {
            IntPair other = (IntPair)o;
            result = other.left == left && other.right == right;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}