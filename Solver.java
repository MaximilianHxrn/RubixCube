import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Solver {

    private static Cube cube;
    private static ArrayList<String> moves = new ArrayList<>();

    public static void main(String[] args) {
        init();
        print();
        randomize();
        makeMoves();
        solve();
        print();
    }

    private static void solve() {
        cross();
        f2l();
        yCross();
        corner();
        turn();
    }

    private static void turn() {
    }

    private static void corner() {
    }

    private static void yCross() {
    }

    private static void f2l() {
    }

    private static void cross() {
        findEdge(Color.WHITE);
    }

    private static String findEdge(Color one) {
        // Front
        if (cube.front[0][1] == one) {
            if (cube.up[2][1] == Color.RED) {
                moves.add("F'");
                moves.add("L'");
                moves.add("U'");
            }
            if (cube.up[2][1] == Color.BLUE) {
                moves.add("F");
                moves.add("R");
            }
            if (cube.up[2][1] == Color.GREEN) {
                moves.add("F'");
                moves.add("L'");
            }
            if (cube.up[2][1] == Color.ORANGE) {
                moves.add("F'");
                moves.add("L'");
                moves.add("U");
                moves.add("U");
            }
        }
        if (cube.front[1][0] == one) {

        }
        if (cube.front[1][2] == one) {

        }
        if (cube.front[2][1] == one) {

        }
        // Left
        if (cube.front[0][1] == one) {
            
        }
        if (cube.front[1][0] == one) {

        }
        if (cube.front[1][2] == one) {

        }
        if (cube.front[2][1] == one) {

        }
    }

    private static void randomize() {
        String[] alphabet = { "U", "U'", "R", "R'", "F", "F'", "L", "L'", "B", "B'", "D", "D'" };
        SecureRandom r = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            moves.add(alphabet[r.nextInt(alphabet.length)]);
            System.out.println(moves.get(i));
        }
    }

    private static void makeMoves() {
        for (String s : moves) {
            rotate(s);
        }
    }

    private static void init() {
        cube = new Cube();
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
        case "B":
        case "B'": {
            cube.left = temp.back;
            cube.front = temp.left;
            cube.right = temp.front;
            cube.back = temp.right;
            cube.up = turnFaceCCW(cube.up);
            cube.down = turnFace(cube.down);
            if (face.equals("B")) {
                rotate(" ", true);
            } else {
                rotate(" ", false);
            }
            temp = new Cube(cube);
            cube.front = temp.right;
            cube.left = temp.front;
            cube.back = temp.left;
            cube.right = temp.back;
            cube.up = turnFace(cube.up);
            cube.down = turnFaceCCW(cube.down);
            break;
        }
        case "U":
        case "U'": {
            cube.front = turnFaceCCW(cube.front);
            cube.back = turnFace(cube.back);
            cube.left = turnFace(temp.up);
            cube.right = turnFace(temp.down);
            cube.up = turnFace(temp.right);
            cube.down = turnFace(temp.left);
            if (face.equals("U")) {
                rotate(" ", true);
            } else {
                rotate(" ", false);
            }
            temp = new Cube(cube);
            cube.front = turnFace(cube.front);
            cube.left = turnFaceCCW(temp.down);
            cube.back = turnFaceCCW(cube.back);
            cube.right = turnFaceCCW(temp.up);
            cube.up = turnFaceCCW(temp.left);
            cube.down = turnFaceCCW(temp.right);
            break;
        }
        case "D":
        case "D'": {
            cube.left = turnFaceCCW(temp.down);
            cube.front = turnFace(cube.front);
            cube.back = turnFaceCCW(cube.back);
            cube.right = turnFaceCCW(temp.up);
            cube.up = turnFaceCCW(temp.left);
            cube.down = turnFaceCCW(temp.right);
            if (face.equals("D")) {
                rotate(" ", true);
            } else {
                rotate(" ", false);
            }
            temp = new Cube(cube);
            cube.front = turnFaceCCW(cube.front);
            cube.back = turnFace(cube.back);
            cube.left = turnFace(temp.up);
            cube.right = turnFace(temp.down);
            cube.down = turnFace(temp.left);
            cube.up = turnFace(temp.right);
            break;
        }
        case " ": {
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

    public static Color[][] turnFace2(Color[][] mat) {
        Color[][] ret = turnFace(mat);
        ret = turnFace(ret);
        return ret;
    }

    public static Color[][] turnFaceCCW(Color[][] in) {
        Color[][] matrix = new Color[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                matrix[3 - 1 - j][i] = in[i][j];
        return matrix;
    }
}
