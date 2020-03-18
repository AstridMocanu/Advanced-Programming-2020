import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {

    public static void save (Catalog catalog)
            throws IOException {
        try (var out = new ObjectOutputStream(new FileOutputStream(catalog.getPath()))) {

            /**Save: in streamul creat anterior se adauga obiectul de tip catalog
             * NOTA: Se adauga obiectul catalog "pur si simplu",
             * anume nu este necesara adaugarea separata a fiecarui atribut al obiectului
             * Desi cred ca se poate -DE TESTAT
             */

            out.writeObject(catalog);
            out.flush();
        }
    }


        public static Catalog load(String path)
                throws InvalidCatalogException, FileNotFoundException {
            try(var in = new ObjectInputStream(new FileInputStream(path))){
                /**
                 * Load trebuie sa cuprinda extragerea
                 obiectului catalog
                 Si returnarea obiectului de tip catalog
                 prevazute cu metode de "prindere" a exceptiilor aparute- in ex dat fisierul cautat poate lipsi
                 */

                    try {

                        Catalog catalog=(Catalog) in.readObject();
                        return catalog;


                    }
                    catch(FileNotFoundException e){
                        System.err.println("The file " + path+ "is missing!");
                        return null;


                }
                    /**
                    //string id
                    //string name
                    //string location
                    apartin de document
                     sunt si ele extrase din serializare
                     AUTOMAT
                        */




            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;

            }


        }
        public static void view(Document doc) {
            Desktop desktop = Desktop.getDesktop();
            //File file = new File(doc.getLocation());
            URI uri = URI.create(doc.getLocation());
            try {
                desktop.browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
}
