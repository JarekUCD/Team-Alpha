public class Board {

    private String[][] layout = new String[14][14];

    Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i< 14; i++){
            for (int j = 0; j<14; j++){
                layout[i][j] = "·";
            }
        }
    }

    @Override
    public String toString() {
        // This prints out the board, with the appropriate numbering on the columns/rows

        String s = "\n";
        StringBuilder b = new StringBuilder(s);

        for (int i = 0; i < 14; i++) {

            //y index numbering (including some formatting based on no of digits)
            b.append(14 - i);
            if (14 - i < 10){
                b.append(" ");
            }

            //Displaying contents of array
            for (int j = 0; j < 14; j++) {
                b.append(" ").append(layout[i][j]).append(" ");
            }
           //b.append("\n").append(s);
            b.append(s);

        }

        //x index numbering (including some formatting based on no of digits)
        b.append("   ");
        for (int i = 1; i < 15; i++) {
            b.append(i);
            if (i<9){
                b.append("  ");
            } else {
                b.append(" ");
            }
        }
        return b.toString();
    }
}