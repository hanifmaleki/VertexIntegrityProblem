package at.tuwien.ac.graph.tw;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

public class NTDBag<D> {

    public Set<NVertex<D>> vertices;

    public NTDBag(){
        vertices = new HashSet<NVertex<D>>();
    }

    public String format() {
        StringWriter writer = new StringWriter();
        int groupSize = (int)Math.ceil( Math.sqrt(vertices.size()) );
        int i=0;
        for( NVertex<D> v : vertices ) {
            if( i%groupSize==0 && i!=0 ) writer.write( "\\n" );
            writer.write( v.data.toString() + " " );
            ++i;
        }
        return writer.toString();
    }

}