public class Block {


    public int length; //Number of squares the shape takes up
    public String name; //The name of the block;
    public char[][] shape; //An array used to store the shape of the block
    public int[] pivot = {0, 0}; //The pivot point of the shape


    //Constructor
    public Block(int l, String n){
        length = l;
        shape = new char[l][l];
        name = n;


        for(int i=0; i<l; i++){
            for (int j = 0; j<l; j++){
                shape[i][j] = ' ';
            }

        }
    }

    public void rotate(int r){ //Rotates the blocks around the centre of the array
        char[][] tempArr = new char[length][length];
        int tempInt;

        for(int c = 0; c < r; c++){
            //Rotates the pivot
            tempInt = pivot[1];
            pivot[1] = pivot[0];
            pivot[0] = (length - 1) - tempInt;

            //Rotates the shape
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    tempArr[i][j] = shape[((length - 1) - j)][i];
                }
            }
            shape = tempArr;
        }
    }

    //Accessors
    public char[][] getShape() {
        return shape;
    }

    public int getLength() {
        return length;
    }

    public void setShape(int i, int... input){
        //Sets the pivot
        int row = i / 4;
        int col = i % 4;
        shape[row][col] = '@';
        pivot[0] = row;
        pivot[1] = col;

        //Sets the rest of the shape
        for (int j : input) {
            row = j / 4;
            col = j % 4;
            shape[row][col] = 'A';
        }
    }
    public String getName(){
        return name;
    }

    //Prints the name
    public void printName() {
        System.out.println(name);
    }

    public void printShape() {
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                System.out.print(shape[i][j]);
            }
            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
