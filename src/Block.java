public class Block {


    public int length; //Number of squares the shape takes up
    public char colour; //The colour of the piece, (It will be the temporary colour for placement)

    public char[][] shape; //An array used to store the shape of the block

    //Constructor
    public Block(int l, char c){
        length = l;
        colour = c;
        shape = new char[l][l];

        for(int i=0; i<l; i++){
            for (int j = 0; j<l; j++){
                shape[i][j] = ' ';
            }

        }
    }

    public void rotate(){ //Rotates the blocks around the centre of the array
        char[][] temp = new char[length][length];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                temp[i][j] = shape[((length - 1) - j)][i];
            }
        }
        shape = temp;
    }

    //Accessors
    public char[][] getShape() {
        return shape;
    }

    public char getColour() {
        return colour;
    }

    public int getLength() {
        return length;
    }

    public void setShape(int... input){
        for (int j : input) {
            int row = j / length;
            int col = j % length;
            shape[row][col] = 'A';
        }
    }


    public void printShape() {
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                System.out.print(shape[i][j]);
            }
            System.out.print("\n");
        }
    }
}
