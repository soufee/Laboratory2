package general;

import essences.Gamer;
import essences.ObjectFactory;
import essences.Question;
import utils.DBParser;
import utils.JaxBParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.jar.JarEntry;

/**
 * Created by Shoma on 15.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        DBParser dbParser = new DBParser();
//dbParser.cleanGamers();
dbParser.fromXMLtoDBGamer();

    }
}
