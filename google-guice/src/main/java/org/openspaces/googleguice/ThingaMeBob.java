package org.openspaces.googleguice;

/**
 * User: suggitpe
 * Date: 25/07/12
 * Time: 12:01
 */
public class ThingaMeBob {
    private String stuff;

    public ThingaMeBob(String someStuff) {
        stuff = someStuff;
    }

    public String getStuff() {
        return stuff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThingaMeBob that = (ThingaMeBob) o;

        if (stuff != null ? !stuff.equals(that.stuff) : that.stuff != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return stuff != null ? stuff.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ThingaMeBob{" +
                "stuff='" + stuff + '\'' +
                '}';
    }
}
