import java.util.Objects;

public class TelKnoten  implements Comparable<TelKnoten> {
    public final int x;
    public final int y;

    public TelKnoten(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(java.lang.Object obj){
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TelKnoten)) {
            return false;
        }
        TelKnoten temp = (TelKnoten) obj;

        return temp.x == x && temp.y ==y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    @Override
    public java.lang.String toString(){
        var builder = new StringBuilder();
        builder
                .append("(")
                .append(x)
                .append(",")
                .append(y)
                .append(")");
        return builder.toString();
    }


    @Override
    public int compareTo(TelKnoten o) {
        if(this == o) {
            return 0;
        }
        return -1;
    }
}
