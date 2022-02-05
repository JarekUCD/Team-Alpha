import java.util.Objects;

public class Board {

    private char[][] layout = new char[14][14];

    Board(){
        // Blokus Duo has 196 squares which is 14x14

        //Create array. Fill with blank symbol
        for (int i = 0; i< 14; i++){
            for (int j = 0; j<14; j++){
                layout[i][j] = '·';
            }
        }
        layout[9][9] = 'O';
        layout[4][4] = 'O';
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

    public void place(int x, int y, int r, String n, Player p){
        int c, i , j;

        for(c = 0; c < p.getPieces().size(); c++){
            System.out.println(n + "|");
            System.out.println(p.getPieces().get(c).getName() + "|");
            if(Objects.equals(n, p.getPieces().get(c).getName())){
                System.out.println("test");
                break;
            }
        }
        if(!(c < p.getPieces().size())){
            System.out.println("test2");
            return;
        }


        p.getPieces().get(c).rotate(r);

        for(i = 0; i < p.getPieces().get(c).getLength(); i++){
            for(j = 0; j <  p.getPieces().get(c).getLength(); j++){
                if(p.getPieces().get(c).getShape()[i][j] == 'A'){
                    layout[y + i][x + j] = p.getPieces().get(c).getShape()[i][j];
                }
            }
        }

    }

    //Accessor for layout
    public char[][] getLayout() {
        return layout;
    }
}