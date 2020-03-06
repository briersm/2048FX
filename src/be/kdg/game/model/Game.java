package be.kdg.game.model;

import java.util.*;
import java.util.List;

public class Game extends javafx.scene.canvas.Canvas {
    public Cell[] cells;
    public boolean win = false;
    public boolean lose = false;
    public int score =0;


    public Cell[] getCells() {
        return cells;
    }

    public Game() {
        super(330, 390); //zet canvas size (super verwijst naar parent)
        setFocused(true);
        resetGame();
    }

    protected Game(double width, double height) {
        super(width, height);
        setFocused(true);
        resetGame();
    }


    public void resetGame() {
        score = 0;
        win = false;
        lose = false;
        cells = new Cell[4 * 4];
        for (int cell = 0; cell < cells.length; cell++) {
            cells[cell] = new Cell();                       //bord vullen met tegels
        }
        addCell();      //starten met twee tegels
        addCell();      // ^^^^^
    }

    protected void addCell() {
        List<Cell> list = availableSpace();
        if(!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size(); //casten naar int
            Cell emptyCell = list.get(index);
            emptyCell.number = Math.random() < 0.9 ? 2 : 4;     //10% kans dat een 4 spawnt 90% kans op 2
        }

    }

    protected List<Cell> availableSpace() {
        List<Cell> list = new ArrayList<>(16);      //(4x4)16 tegels
        for(Cell c : cells)
            if(c.isEmpty())
                list.add(c);
        return list;
    }

    protected boolean isFull() {
        return availableSpace().size() == 0;
    }

    protected Cell cellAt(int x, int y) {
        return cells[x + y * 4];
    }

    public boolean canMove() {
        if(!isFull()) return true;          //als het bord niet vol is kan je altijd bewegen
        for(int x = 0; x < 4; x++) {    //iteren over alle tegels
            for (int y = 0; y < 4; y++) {
                Cell cell = cellAt(x, y);
                if ((x < 3 && cell.number == cellAt(x + 1, y).number) ||
                        (y < 3) && cell.number == cellAt(x, y + 1).number) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean compare(Cell[] line1, Cell[] line2) {
        if(line1 == line2) {
            return true;
        }
        if (line1.length != line2.length) {
            return false;
        }

        for(int i = 0; i < line1.length; i++) {
            if(line1[i].number != line2[i].number) {
                return false;
            }
        }
        return true;
    }

    protected Cell[] rotate(int angle) {
        Cell[] tiles = new Cell[4 * 4];
        int offsetX = 3;
        int offsetY = 3;
        if(angle == 90) {
            offsetY = 0;
        } else if(angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for(int x = 0; x < 4; x++) {
            for(int y = 0; y < 4; y++) {
                int newX = (x*cos) - (y*sin) + offsetX;
                int newY = (x*sin) + (y*cos) + offsetY;
                tiles[(newX) + (newY) * 4] = cellAt(x, y);
            }
        }
        return tiles;
    }

    protected Cell[] moveLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<Cell>();
        for(int i = 0; i < 4; i++) {
            if(!oldLine[i].isEmpty()){
                list.addLast(oldLine[i]);
            }
        }

        if(list.size() == 0) {
            return oldLine;
        } else {
            Cell[] newLine = new Cell[4];
            while (list.size() != 4) {
                list.add(new Cell());
            }
            for(int j = 0; j < 4; j++) {
                newLine[j] = list.removeFirst();
            }
            return newLine;
        }
    }

    protected Cell[] mergeLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<Cell>();
        for(int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].number;
            if (i < 3 && oldLine[i].number == oldLine[i+1].number) {
                num *= 2;
            score +=num;
            if (num == 2048) {
                    win = true;
                }
                i++;
            }
            list.add(new Cell(num));
        }

        if(list.size() == 0) {
            return oldLine;
        } else {
            while (list.size() != 4) {
                list.add(new Cell());
            }
            return list.toArray(new Cell[4]);
        }
    }

    protected Cell[] getLine(int index) {
        Cell[] result = new Cell[4];
        for(int i = 0; i < 4; i++) {
            result[i] = cellAt(i, index);
        }
        return result;
    }

    protected void setLine(int index, Cell[] re) {
        System.arraycopy(re, 0, cells, index * 4, 4);
    }

    public void left() {
        boolean needAddCell = false;
        for(int i = 0; i < 4; i++) {
            Cell[] line = getLine(i);
            Cell[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if( !needAddCell && !compare(line, merged)) {
                needAddCell = true;
            }
        }
        if(needAddCell) {
            addCell();
        }
    }

    public void right() {
        cells = rotate(180);
        left();
        cells = rotate(180);
    }

    public void up() {
        cells = rotate(270);
        left();
        cells = rotate(90);
    }

    public void down() {
        cells = rotate(90);
        left();
        cells = rotate(270);
    }

    public int getScore() {
        return score;
    }
}