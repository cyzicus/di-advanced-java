package generics;

import java.awt.*;

/**
 * Created by jason1.miller on 2/17/16.
 */
public class ClothingStore {

    public static void main(String[] args) {

        Pair<Glove> pg1 = new Pair<>(new Glove(4, Color.black), new Glove(5, Color.black));
        Pair<Glove> pg2 = new Pair<>(new Glove(4, Color.black), new Glove(4, Color.black));
        Pair<Glove> pg3 = new Pair<>(new Glove(4, Color.red), new Glove(4, Color.black));
        System.out.println(pg1.isMatched());
        System.out.println(pg2.isMatched());
        System.out.println(pg3.isMatched());

    }
}
