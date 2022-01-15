public class TelVerbindung {
    public final int c;
    public final TelKnoten u;
    public final TelKnoten v;

    public TelVerbindung(TelKnoten u, TelKnoten v, int c){
        this.u = u;
        this. v = v;
        this.c = c;
    }

    @Override
    public java.lang.String toString(){
        var builder = new StringBuilder();
        builder
                .append(u)
                .append(" --> ")
                .append(v)
                .append(" cost = ")
                .append(c)
                .append("\n");
        return builder.toString();
    }
}
