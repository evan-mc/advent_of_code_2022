import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Day1
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\src\\main\\java\\input.txt"));
        List<String> lines = bufferedReader.lines().toList();
        printProblem1(lines);
        printProblem2(lines);
    }

    public static void printProblem1(List<String> lines) {
        System.out.println(lines.stream()
                .map(str -> str.split(" "))
                .map(Day1::playProblem1)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    public static void printProblem2(List<String> lines) {
        System.out.println(lines.stream()
                .map(str -> str.split(" "))
                .map(Day1::playProblem2)
                .mapToInt(Integer::intValue)
                .sum()
        );
    }

    public static int playProblem1(String[] players) {
        Guide opponent = Guide.valueOf(players[0]);
        Guide player = Guide.valueOf(players[1]);

        if(player.shape == opponent.shape) {
            return player.shape.points + 3;
        } else if(player.shape.beats() == opponent.shape) {
            return player.shape.points + 6;
        } else {
            return player.shape.points;
        }
    }

    public static int playProblem2(String[] players) {
        Guide opponent = Guide.valueOf(players[0]);
        Guide player = Guide.valueOf(players[1]);

        if(player == Guide.X) {
            return opponent.shape.beats().points;
        } else if(player == Guide.Y) {
            return opponent.shape.points + 3;
        } else {
            return opponent.shape.beats().beats().points + 6;
        }
    }

    enum Guide {
        A(Shape.ROCK),
        B(Shape.PAPER),
        C(Shape.SCISSORS),
        X(Shape.ROCK),
        Y(Shape.PAPER),
        Z(Shape.SCISSORS);
        public final Shape shape;

        private Guide(Shape shape) {
            this.shape = shape;
        }
    }

    enum Shape {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        public final int points;

        private Shape(int points) {
            this.points = points;
        }

        public Shape beats() {
            return switch(this) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                case SCISSORS -> PAPER;
            };
        }
    }
}
