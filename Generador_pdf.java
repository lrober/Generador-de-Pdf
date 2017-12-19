import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author iluscode
 */
public class Generador_pdf {

    /*
        //esta plantilla se hace en Adobe Acrobat y tiene que ser obligadoria en pdf
        static String plantilla = "ubicacion de la plantilla.pdf";
        //si no existe una extension el documento se generara sin extension
        static String nombreSalida = "ubicacion del documento de salida.pdf";
        static String tag1 = "hola mundo";
        static String msjCreado = "valor del tag";
        static int tamanoFuente = 12;
        //posicion de x en tag1
        static int x1 = 449;
        //posicion de y en tag1
        static int y1 = 682;
     */
    BaseFont fuente() throws IOException, DocumentException {
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        return bf;
    }

    public void Generador(String plantilla, String nombreSalida, BaseFont fuente, int tamanoFuente, String msjCreado) {
        Document doc = new Document();
        try {
            //inicializa la plantilla y el valor de salida
            FileOutputStream fos = new FileOutputStream(nombreSalida);
            PdfReader reader = new PdfReader(plantilla);
            PdfStamper stamper = new PdfStamper(reader, fos);
            PdfContentByte cb = stamper.getUnderContent(1);

            cb.beginText();

            //tipo de fuente, tamaño de la fuente
            cb.setFontAndSize(fuente, tamanoFuente);

            //posicion(x,y)
            int x = 100;
            int y = 100;
            cb.setTextMatrix(x, y);

            //tag
            String tag = "";
            cb.showText(tag);

            // Indico que termine de agregar texto
            cb.endText();

            stamper.close();
            System.out.println(msjCreado);
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Error no se pudo crear el documento" + e);
        } catch (IOException e) {
            System.out.println("Error no se pudo crear el documento" + e);
        }

    }

    
    //*********   EJEMPLO    *********
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        Generador_pdf g = new Generador_pdf();
        BaseFont fuente=g.fuente();
        g.Generador("C:\\Doc\\Aprovechamiento Academico\\1.1. Solicitud por Aprovechamiento Académico.pdf", "Solicitud para Evaluación por Examen General de Egreso.pdf", fuente, 12, "El documento se ha creado con exito");
    }
}
