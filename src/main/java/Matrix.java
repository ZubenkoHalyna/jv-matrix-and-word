public class Matrix {
    private static final int[][] steps = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public char[][] createMatrix(String str) {
        int size = (int) Math.sqrt(str.length());
        if (size * size != str.length()) {
            System.out.println("Illegal matrix size");
            return new char[0][0];
        }
        char[][] result = new char[size][size];
        for (int i = 0; i < str.length(); i++) {
            result[i / size][i % size] = str.charAt(i);
        }
        return result;
    }

    public boolean isValid(char[][] matrix) {
        return matrix.length > 0;
    }

    public String startSearch(char[][] matrix, String word) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    String res = findPath(matrix, word.substring(1), getStepMsg(i, j), i, j);
                    if (!res.isEmpty()) {
                        return res;
                    }
                }
            }
        }
        return "There are no path";
    }

    private String findPath(char[][] matrix, String word, String path,
                            int posX, int posY) {
        if (word.isEmpty()) {
            return path;
        }

        for (int[] step : steps) {
            String res = tryStep(matrix, word, path, posX, posY, step[0], step[1]);
            if (!res.isEmpty()) {
                return res;
            }
        }

        return "";
    }

    private String tryStep(char[][] matrix, String word, String path,
                           int posX, int posY, int dx, int dy) {
        int newX = posX + dx;
        int newY = posY + dy;
        if (newX < 0 || newX >= matrix.length || newY < 0 || newY >= matrix.length) {
            return "";
        }
        String stepMsg;
        if (matrix[newX][newY] == word.charAt(0)
                && !path.contains(stepMsg = getStepMsg(newX, newY))) {
            return findPath(matrix, word.substring(1), path + "->" + stepMsg, newX, newY);
        }
        return "";
    }

    private String getStepMsg(int posX, int posY) {
        return "[" + posX + "," + posY + "]";
    }
}
