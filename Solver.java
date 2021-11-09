import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Solver {

    private static Cube cube;

    public static void main(String[] args) {
        cube = new Cube();
        init();
        print();
        rotate("F");
        rotate("F'");
    }

    private static void init() {
        PrintStream fileStream = null;
        try {
            fileStream = new PrintStream("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(fileStream);
    }

    private static void print() {
        System.out.println(cube.toString());
    }

    static void rotate(String face) {
        rotate(face, true);
    }

    private static void rotate(String face, boolean forward) {
        Cube temp = new Cube(cube);
        switch (face) {
        case "L": {
            rotate(" ", true);
            break;
        }
        case "L'": {
            rotate(" ", false);
            break;
        }
        case "R":
        case "R'": {
            cube.left = temp.right;
            cube.right = temp.left;
            cube.front = temp.back;
            cube.back = temp.front;
            for (int i = 0; i < 2; i++) {
                cube.up = turnFace(cube.up);
                cube.down = turnFace(cube.down);
            }
            if (face.equals("R")) {
                rotate(" ", true); // R' = L mit Rot.
            } else {
                rotate(" ", false); // R = L' mit Rot.
            }
            temp = new Cube(cube);
            cube.left = temp.right;
            cube.right = temp.left;
            cube.front = temp.back;
            cube.back = temp.front;
            for (int i = 0; i < 2; i++) {
                cube.up = turnFace(cube.up);
                cube.down = turnFace(cube.down);
            }
            break;
        }
        case "F":
        case "F'": {
            cube.left = temp.front;
            cube.back = temp.left;
            cube.right = temp.back;
            cube.front = temp.right;
            cube.up = turnFace(cube.up);
            cube.down = turnFaceCCW(cube.down);
            if (face.equals("F")) {
                rotate(" ", true);
            } else {
                rotate(" ", false);
            }
            temp = new Cube(cube);
            cube.front = temp.left;
            cube.left = temp.back;
            cube.back = temp.right;
            cube.right = temp.front;
            cube.up = turnFaceCCW(cube.up);
            cube.down = turnFace(cube.down);
            break;
        }
        case " ": {
            System.out.println("Before:");
            print();
            if (forward) {
                temp = new Cube(cube);
                for (int i = 0; i < 3; i++) {
                    cube.front[i][0] = temp.up[i][0];
                    cube.down[i][0] = temp.front[i][0];
                    cube.back[i][2] = temp.down[3 - i - 1][0];
                    cube.up[i][0] = temp.back[3 - i - 1][2];
                }
                cube.left = turnFace(cube.left);
            } else {
                temp = new Cube(cube);
                for (int i = 0; i < 3; i++) {
                    cube.front[i][0] = temp.down[i][0];
                    cube.down[i][0] = temp.back[3 - i - 1][2];
                    cube.back[i][2] = temp.up[3 - i - 1][0];
                    cube.up[i][0] = temp.front[i][0];
                }
                cube.left = turnFaceCCW(cube.left);
            }
            System.out.println("After:");
            print();
            break;
        }
        }
    }

    public static Color[][] turnFace(Color[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        Color[][] ret = new Color[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M - 1 - r] = mat[r][c];
            }
        }
        return ret;
    }

    public static Color[][] turnFaceCCW(Color[][] matrix) {
        int n = matrix.length;
        int half = n / 2;

        for (int layer = 0; layer < half; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                int j = last - offset;
                Color top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[j][first];

                // bottom -> left
                matrix[j][first] = matrix[last][j];

                // right -> bottom
                matrix[last][j] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
        return matrix;
    }
}
