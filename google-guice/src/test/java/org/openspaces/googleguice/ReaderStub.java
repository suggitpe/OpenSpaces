package org.openspaces.googleguice;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 13:38
 */
public class ReaderStub implements Reader {

    private int numberToReturn;

    public ReaderStub(int aNumberToReturn) {
        numberToReturn = aNumberToReturn;
    }

    @Override
    public ThingaMeBob readThingaMeBob() {
        if (numberToReturn >= 1) {
            numberToReturn--;
            return new ThingaMeBob("test stuff");
        }
        return null;
    }

    public int getNumberToReturn() {
        return numberToReturn;
    }

}
