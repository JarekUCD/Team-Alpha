//Team name: alpha, Student Numbers: 20377771 20709465 20394476

public class Block {


    public int length; //Number of squares the shape takes up
    public String name; //The name of the block;
    public char[][] shape; //An array used to store the original shape of the block
    public char[][] shapeChange; //An array used to store the changed shape of the block
    public int[] pivot = {0, 0}; //The pivot point of the shape
    public int[] pivotChange = {0, 0}; //The changing pivot point of shapeChange


    //Constructor
    public Block(int l, String n){
        length = l;
        shape = new char[l][l];
        shapeChange = new char[l][l];
        name = n;


        for(int i=0; i<l; i++){
            for (int j = 0; j<l; j++){
                shape[i][j] = ' ';
                shapeChange[i][j] = ' ';
            }

        }
    }

    public void rotate(int r){ //Rotates the blocks around the centre of the array
        if(r != 0){
            char[][] tempArr = new char[length][length];
            int tempInt;

            //Rotates the pivot
            tempInt = pivotChange[0];
            pivotChange[0] = pivotChange[1];
            pivotChange[1] = (length - 1) - tempInt;

            //Rotates the shape
            for(int i = 0; i < length; i++){
                for(int j = 0; j < length; j++){
                    tempArr[i][j] = shapeChange[((length - 1) - j)][i];
                }
            }
            shapeChange = tempArr;
            r--;
            rotate(r);
        }
    }

    public void flip(){ //Rotates the blocks around the centre of the array
        char[][] tempArr = new char[length][length];

        //Rotates the pivot
        pivotChange[1] = (length - 1) - pivotChange[1];

        //flips the shape
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                tempArr[i][j] = shapeChange[i][(length - 1) - j];
            }
        }
        shapeChange = tempArr;
    }


    public void resetShape(){
        pivotChange[0] = pivot[0];
        pivotChange[1] = pivot[1];

        for(int i = 0; i < length; i++){
            System.arraycopy(shape[i], 0, shapeChange[i], 0, length);
        }
    }

    //Accessors
    public int[] getPivot() {
        return pivotChange;
    }

    public char[][] getShape() {
        return shapeChange;
    }

    public int getLength() {
        return length;
    }

    public void setShape(int i, int... input){
        //Sets the pivot
        int row = i / 4;
        int col = i % 4;
        shape[row][col] = '@';
        shapeChange[row][col] = '@';
        pivot[0] = row;
        pivot[1] = col;
        pivotChange[0] = row;
        pivotChange[1] = col;

        //Sets the rest of the shape
        for (int j : input) {
            row = j / 4;
            col = j % 4;
            shape[row][col] = 'A';
            shapeChange[row][col] = 'A';
        }
    }
    public String getName(){
        return name;
    }

    public String printShape() {
        String s = "";
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                s = s + shapeChange[i][j] + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        return name;
    }
}

