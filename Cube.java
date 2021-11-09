public class Cube {
    public Color[][] front = new Color[3][3];
    public Color[][] up = new Color[3][3];
    public Color[][] left = new Color[3][3];
    public Color[][] right = new Color[3][3];
    public Color[][] down = new Color[3][3];
    public Color[][] back = new Color[3][3];

    public Cube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                up[i][j] = Color.WHITE;
                front[i][j] = Color.RED;
                left[i][j] = Color.BLUE;
                right[i][j] = Color.GREEN;
                back[i][j] = Color.ORANGE;
                down[i][j] = Color.YELLOW;
            }
        }
    }

    public Cube(Cube in) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.front[i][j] = in.front[i][j];
                this.up[i][j] = in.up[i][j];
                this.left[i][j] = in.left[i][j];
                this.right[i][j] = in.right[i][j];
                this.back[i][j] = in.back[i][j];
                this.down[i][j] = in.down[i][j];

            }
        }
    }

    @Override
    public String toString() {
        String out = "";
        String space = "                       ";
        for (int i = 0; i < 3; i++) {
            out += space + up[i][0] + " " + up[i][1] + " " + up[i][2] + "\n";
        }
        for (int i = 0; i < 3; i++) {
            out += (left[i][0] + " " + left[i][1] + " " + left[i][2] + space).substring(0, 21) + "| "
                    + (front[i][0] + " " + front[i][1] + " " + front[i][2] + space).substring(0, 21) + "| "
                    + (right[i][0] + " " + right[i][1] + " " + right[i][2] + space).substring(0, 21) + " | "
                    + back[i][0] + " " + back[i][1] + " " + back[i][2] + "\n";
        }
        for (int i = 0; i < 3; i++) {
            out += space + down[i][0] + " " + down[i][1] + " " + down[i][2] + "\n";
        }
        return out;
    }
}