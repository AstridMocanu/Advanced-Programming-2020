import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }
    private void testCreateSave() {
        Catalog catalog = new Catalog("Java Resources", "/home/asttrid/IdeaProjects/lab5/catalog.ser");
        Document doc = new Document("java1", "Java Course 1","https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);
       try {
           CatalogUtil.save(catalog);

            }
       catch(IOException e){
           System.err.println("Exception for in/out");
           e.printStackTrace();


       };
    }

    private void testLoadView() {
        try{
            Catalog catalog = CatalogUtil.load("/home/asttrid/IdeaProjects/lab5/catalog.ser");
            Document doc = catalog.findById("java1");
            CatalogUtil.view(doc);
        }
        catch(InvalidCatalogException | FileNotFoundException e){

            System.err.println("The catalog is invalid!");

        }

    }
}