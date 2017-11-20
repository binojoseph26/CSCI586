import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.StmtIteratorImpl;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;



public class JenaSparql {

    public static void sparQLtest(){
        Model model=FileManager.get().loadModel("/Users/binojoseph/Documents/WSP1WS8.ttl");
        //model.write(System.out,"TURTLE");

        String queryString =
        "PREFIX  videoOntPrefix: <http://www.semanticweb.org/binojoseph/ontologies/2017/10/video:> "+
        "PREFIX  owl: <http://www.w3.org/2002/07/owl#> "+
        "SELECT * WHERE {"+
        "?movie videoOntPrefix:title ?a ."+
                
                "?movie videoOntPrefix:hasGenre ?ac  ."+
                "?ac videoOntPrefix:action '1' "+
                "ORDER BY videoOntPrefix:popularity"+

        "}";

        Query query= QueryFactory.create(queryString);
        QueryExecution qexec= QueryExecutionFactory.create(query,model);
        //StmtIterator stmts = null;
        try {
            ResultSet results = qexec.execSelect();
            int count=0;
            while (results.hasNext()) {
                count++;
                QuerySolution soln = results.nextSolution();
                 Resource name = soln.getResource("ac");
                Literal title=soln.getLiteral("a");
                StmtIterator stmts=name.listProperties();
                //System.out.println(name);
                System.out.println(title);
                while(stmts.hasNext())
                {
                   System.out.println(stmts.next());

                }
                System.out.println("Break");





               //PropertyImpl prop=new PropertyImpl("<http://www.semanticweb.org/binojoseph/ontologies/2017/10/video:Genre>","comedy");

                //System.out.println( name.getProperty(prop));
            }
            System.out.println("Count:"+count);

            /*for(StmtIterator j = stmts; j.hasNext();){
                Statement s =j.next();
                System.out.print(" "+ s.getPredicate().getLocalName()+"-->");
                if(s.getObject().isLiteral()) {
                    System.out.println(s.getLiteral().getLexicalForm());
                }
                else {
                    System.out.println(s.getObject());
                }
            }*/
        }finally{
                qexec.close();
            }


    }
    public static void main(String[] args) {

        sparQLtest();
        System.out.println("Hello");

    }
}

