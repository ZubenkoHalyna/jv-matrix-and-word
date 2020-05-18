public class Matrix {
    private static final int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char[][] matrix;

    public Matrix(String str) {
        int size = (int) Math.sqrt(str.length());
        if (size * size != str.length()) {
            System.out.println("Illegal matrix size");
            matrix = new char[0][0];
            return;
        }
        char[][] result = new char[size][size];
        for (int i = 0; i < str.length(); i++) {
            result[i / size][i % size] = str.charAt(i);
        }
        matrix = result;
    }

    public boolean isValid() {
        return matrix.length > 0;
    }

    public String startSearch(String word) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    String res = findPath(word.substring(1), getStepMsg(i, j), i, j);
                    if (!res.isEmpty()) {
                        return res;
                    }
                }
            }
        }
        return "There are no path";
    }

    private String findPath(String word, String path, int posX, int posY) {
        if (word.isEmpty()) {
            return path;
        }

        for (int[] step : steps) {
            String res = tryStep(word, path, posX, posY, step[0], step[1]);
            if (!res.isEmpty()) {
                return res;
            }
        }

        return "";
    }

    private String tryStep(String word, String path, int posX, int posY, int dx, int dy) {
        int newX = posX + dx;
        int newY = posY + dy;
        if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix.length) {
            return "";
        }
        String stepMsg;
        if (matrix[newX][newY] == word.charAt(0)
                && !path.contains(stepMsg = getStepMsg(newX, newY))) {
            return findPath(word.substring(1), path + "->" + stepMsg, newX, newY);
        }
        return "";
    }

    private String getStepMsg(int posX, int posY) {
        return "[" + posX + "," + posY + "]";
    }
}
