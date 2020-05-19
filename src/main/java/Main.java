public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Illegal arguments");
            return;
        }
        Matrix matrix = new Matrix();
        char[][] chars = matrix.createMatrix(args[0]);
        if (matrix.isValid(chars)) {
            String word = args[1];
            System.out.println("Path: " + matrix.startSearch(chars, word));
        }
    }
}
