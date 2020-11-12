import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
                Tree.createTreeAlphaBeta(15, Tree.mainElement);
            Tree.dfs(Tree.mainElement, false);

        Tree.finder.get(10).coeficientOfTaken *= 10;

//        for (Element q: Tree.finder)
//            System.out.println(q.coeficientOfTaken);

        GUI app  = new GUI();
        app.setVisible(true);
    }
}