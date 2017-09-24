package co.edu.ufps.colossal.negocio;

import co.edu.ufps.colossal.entities.Componente;
import co.edu.ufps.colossal.entities.Metodo;
import co.edu.ufps.colossal.entities.Parametro;
import co.edu.ufps.colossal.entities.Tag;
import co.edu.ufps.colossal.entities.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.primefaces.model.UploadedFile;

/**
 *
 * Clase que permite manejar archivos en el sistema
 *
 * @author Jhon Vargas
 */
public class FileManager {

    /**
     * Permite subir un logo seleccionado por un usuario para su componente
     *
     * @param logo la referencia del logo a subir
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio el archivo inicialmente
     * @param componente el componente al que pertenece el archivo
     * @return OK si se hizo la subida correctamente, Error de otra forma.
     */
    public static String subirLogo(UploadedFile logo, String rootFolder, Usuario usuario, Componente componente) {

        try {
            // creamos su carpeta si no tiene
            String folder = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/logo";
            (new File(folder)).mkdirs();

            if (logo == null) {
                return "OK";
            }

            String rutaArchivo = folder + "/" + logo.getFileName();
            copiarArchivo(logo.getInputstream(), rutaArchivo);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

    /**
     * Permite copiar un archivo al sistema
     *
     * @param in un stream con los datos del archivo
     * @param rutaArchivo la ruta donde quedara el archivo una vez copiado
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void copiarArchivo(InputStream in, String rutaArchivo) throws FileNotFoundException, IOException {
        OutputStream out = new FileOutputStream(new File(rutaArchivo));
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        in.close();
        out.flush();
        out.close();
        out = null;
        in = null;
    }

    /**
     * Permite subir un conjunto de imagenes seleccionadas por el usuario para
     * su componente
     *
     * @param imagenes una lista con las imagenes a subir
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio los archivos inicialmente
     * @param componente el componente al que pertenece los archivos
     * @return OK si se hizo la subida correctamente, Error de otra forma.
     */
    public static String subirImagenes(List<UploadedFile> imagenes, String rootFolder, Usuario usuario, Componente componente) {
        try {
            String folder = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/imagenes";
            (new File(folder)).mkdirs();
            String rutaArchivo;
            for (UploadedFile imagen : imagenes) {
                rutaArchivo = folder + "/" + imagen.getFileName();
                copiarArchivo(imagen.getInputstream(), rutaArchivo);
            }

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: No se pudieron subir las imagenes!";
        }
    }

    /**
     * Pemite subir el archivo principal del componente
     *
     * @param archivoMain la referencia al archivo a subir
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio los archivos inicialmente
     * @param componente el componente al que pertenece los archivos
     * @return OK si se hizo la subida correctamente, Error de otra forma.
     */
    public static String subirArchivoMain(UploadedFile archivoMain, String rootFolder, Usuario usuario, Componente componente) {
        try {
            String folder = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/archivoMain";
            (new File(folder)).mkdirs();
            String rutaArchivo = folder + "/" + archivoMain.getFileName();
            copiarArchivo(archivoMain.getInputstream(), rutaArchivo);
            return "OK";
        } catch (Exception e) {
            return "Error: No se pudo subir el archivo principal";
        }
    }

    /**
     * Permite subir los archivos adicionales seleccionados por el usuario para
     * su componente
     *
     * @param archivos un lista con las referencias de los archivos
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio los archivos inicialmente
     * @param componente el componente al que pertenece los archivos
     * @return OK si se hizo la subida correctamente, Error de otra forma.
     */
    public static String subirArchivosAdicionales(List<UploadedFile> archivos, String rootFolder, Usuario usuario, Componente componente) {
        try {
            String folder = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/archivosAdicionales";
            (new File(folder)).mkdirs();
            String rutaArchivo;
            for (UploadedFile archivo : archivos) {
                rutaArchivo = folder + "/" + archivo.getFileName();
                copiarArchivo(archivo.getInputstream(), rutaArchivo);
            }

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: No se pudieron subir los archivos adicionales!";
        }
    }

    /**
     * Permite eliminar un archivo fisico del sistema
     *
     * @param rutaArchivo la ruta del archivo a eliminar
     */
    private static void removeFile(String rutaArchivo) {
        File temp;
        temp = new File(rutaArchivo);
        FileUtils.deleteQuietly(temp);
    }

    /**
     * Permite eliminar una carpeta en el sistema, sin importar si tiene o no
     * archivos.
     *
     * @param folderPath la ruta de la carpeta a eliminar
     * @return OK en caso de exito, Error de otra forma.
     */
    public static String removeFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error, no se pudo eliminar la carpeta!";
        }
    }

    /**
     * Permite eliminar el logo de un componente en el sistema
     *
     * @param logo una cadena con el nombre del logo
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio el archivo inicialmente
     * @param componente el componente al que pertenece el archivo
     */
    public static void eliminarLogo(String logo, String rootFolder, Usuario usuario, Componente componente) {
        String rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/logo/" + logo;
        removeFile(rutaArchivo);
    }

    /**
     * Permite eliminar el archivo principal de un componente en el sistema
     *
     * @param archivo el nombre del archivo a eliminar
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio el archivo inicialmente
     * @param componente el componente al que pertenece el archivo
     */
    public static void eliminarArchivoPrincipal(String archivo, String rootFolder, Usuario usuario, Componente componente) {
        String rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/archivoMain/" + archivo;
        removeFile(rutaArchivo);
    }

    /**
     * Permite eliminar un conjunto de imagenes pertenecientes a un componente
     *
     * @param imagenes una lista con las referencias a las imagenes a eliminar
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio las imagenes inicialmente
     * @param componente el componente al que pertenecen las imagenes
     */
    public static void eliminarImagenes(List<UploadedFile> imagenes, String rootFolder, Usuario usuario, Componente componente) {

        String rutaArchivo;
        for (UploadedFile imagen : imagenes) {
            rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/imagenes/" + imagen.getFileName();
            removeFile(rutaArchivo);
        }

    }

    /**
     * Permite eliminar un conjunto de archivos adicionales ubicados en el
     * sistema
     *
     * @param archivos una lista con las referencias a los archivos a eliminar
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio los archivos inicialmente
     * @param componente el componente al que pertenecen los archivos
     */
    public static void eliminarArchivos(List<UploadedFile> archivos, String rootFolder, Usuario usuario, Componente componente) {

        String rutaArchivo;
        for (UploadedFile archivo : archivos) {
            rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/archivosAdicionales/" + archivo.getFileName();
            removeFile(rutaArchivo);
        }

    }

    /**
     * Permite eliminar un archivo adicional de la carpeta principal del sistema
     *
     * @param archivo la ruta del archivo a eliminar
     * @param rootFolder la ruta principal del sistema
     * @param usuario el usuario que subio el archivo inicialmente
     * @param componente el componente al que pertenece el archivo
     */
    public static void eliminarArchivo(String archivo, String rootFolder, Usuario usuario, Componente componente) {

        String rutaArchivo;
        rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/archivosAdicionales/" + archivo;
        removeFile(rutaArchivo);

    }

    public static void eliminarPDF(String archivo, String rootFolder, Usuario usuario, Componente componente) {

        String rutaArchivo;
        rutaArchivo = rootFolder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/" + "especificaciones-componente-" + componente.getIdComponente() + ".pdf";
        removeFile(rutaArchivo);

    }

//    public static String generarPdfEspecificaciones(String root_folder, String cadena, int idComponente, Usuario usuario) {
//        Document document = null;
//        InputStream is = null;
//        OutputStream file = null;
//        PdfWriter writer = null;
//        try {
//            String folder = root_folder + "componentes/" + usuario.getCodigo() + "/" + idComponente + "/pdf";
//            //(new File(folder)).mkdirs();
//            FileUtils.forceMkdir(new File(folder));
//            file = new FileOutputStream(new File(folder + "/" + "Componente-" + new Date().getTime() + ".pdf"));//" + this.componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/"
//            document = new Document();
//            writer = PdfWriter.getInstance(document, file);
//            document.open();
//            is = new ByteArrayInputStream(cadena.getBytes());
//
//            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
//            document.close();
//            is.close();
//            file.flush();
//            file.close();
//            writer.close();
//            document = null;
//            is = null;
//            file = null;
//            writer = null;
//            return "OK";
//
//        } catch (Exception e) {
//            try {
//                file.flush();
//                file.close();
//                document.close();
//                writer.close();
//                is.close();
//                document = null;
//                is = null;
//                file = null;
//                writer = null;
//                e.printStackTrace();
//            } catch (Exception ee) {
//                document = null;
//                is = null;
//                file = null;
//                writer = null;
//
//            }
//        }
//        document = null;
//        is = null;
//        file = null;
//        writer = null;
//        return "Error, no se pudo generar el PDF!";
//    }
//
//    /**
//     * Permite crear una cadena usando HTML que tendra el cuerpo del archivo PDF
//     * con las espeficicaciones del componente
//     *
//     * @param componente el componente relacionado
//     * @return una cadena con las especificaciones del componente en HTML
//     */
//    public static String getCadenaPDF(Componente componente) {
//
//        Usuario u = componente.getPeticionSubidaList().get(0).getUsuarioCodigo();
//        StringBuilder str = new StringBuilder();
//        String cadena = "<html>"
//                + "<head>"
//                + "<style>"
//                + "body,td,th{"
//                + "padding: 5px 5px 5px 5px;"
//                + "text-size:15px;"
//                + "font-family: Helvetica, 'Nimbus Sans L', Arial, sans-serif!Important;"
//                + "}"
//                + "pre{"
//                + "text-size:15px;"
//                + "font-family: Helvetica, 'Nimbus Sans L', Arial, sans-serif!Important;"
//                + "}"
//                + "th{"
//                + "color:white;"
//                + "background-color:#D61117"
//                + "}"
//                + "</style>"
//                + ""
//                + "</head>"
//                + "<body>"
//                + "<br/>"
//                + "<div style='text-align:center'><h3 style='text-align:center'>Especificaciones del Componente</h3></div>"
//                + "<br/>"
//                + "<table style='margin:0 auto;width:100%;border-collapse:colla' border='1'>"
//                + "<tr>"
//                + "<th style='width:15%'>Nombre</th>"
//                + "<td><pre>" + componente.getNombre() + "</pre></td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Autor</th>"
//                + "<td><pre>" + u.getNombre() + "</pre></td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Descripción</th>"
//                + "<td><pre>" + componente.getDescripcion() + "</pre></td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Instrucciones</th>"
//                + "<td><pre>" + componente.getInstrucciones() + "</pre></td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Versión</th>"
//                + "<td>" + componente.getVersion() + "</td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Tecnología</th>"
//                + "<td><pre>" + componente.getTecnologia() + "</pre></td>"
//                + "</tr>"
//                + "<tr>"
//                + "<th style='width:15%'>Grado de Reutilización</th>"
//                + "<td><pre>" + componente.getGradoReutilizacion() + "</pre></td>"
//                + "</tr>";
//
//        String tags = "";
//        int i = 0;
//        for (ComponenteHasTag t : componente.getComponenteHasTagList()) {
//            if (i == 0) {
//                tags += " " + t.getTag().getNombre();
//            } else {
//                tags += ", " + t.getTag().getNombre();
//            }
//            i++;
//        }
//
//        cadena += "<tr>"
//                + "<th style='width:15%'>Tags</th>"
//                + "<td><pre>" + tags + "</pre></td>"
//                + "</tr>";
//
//        i = 0;
//        String dependencias = "";
//        if (!componente.getComponenteHasDependencyList().isEmpty()) {
//            for (ComponenteHasDependency chd : componente.getComponenteHasDependencyList()) {
//                if (i == 0) {
//                    dependencias += " " + chd.getComponenteDependency().getNombre();
//                } else {
//                    dependencias += ", " + chd.getComponenteDependency().getNombre();
//                }
//                i++;
//            }
//            cadena += "<tr>"
//                    + "<th style='width:15%'>Dependencias</th>"
//                    + "<td><pre>" + dependencias + "</pre></td>"
//                    + "</tr>";
//
//        }
//        cadena += "</table>";
//        cadena += "<h4>Interfaz del Componente</h4>"
//                + "";
//        for (Metodo m : componente.getMetodoList()) {
//            cadena += "<table style='margin:0 auto;width:100%;border-collapse:colla' border='1'>";
//            cadena += "<tr>"
//                    + "<th stye='text-align:center'><pre>" + m.getCabecera() + "</pre></th>"
//                    + "</tr>"
//                    + "<tr>"
//                    + "<td><pre>" + m.getDescripcion() + "</pre></td>"
//                    + "</tr>";
//            cadena += "</table>";
//
//            if (m.getParametroList().size() > 0) {
//
//                cadena += "<table style='margin:0 auto;width:100%;border-collapse:colla' border='1'>"
//                        + "<tr>"
//                        + "<th colspan='4' style='text-align:center'>Parametros</th>"
//                        + "</tr>"
//                        + "<tr>"
//                        + "<th>Parametro</th>"
//                        + "<th style='text-align:center'>Tipo</th>"
//                        + "<th style='text-align:center;width:40%'>Detalles</th>"
//                        + "<th style='text-align:center'>Opcional</th>"
//                        + "</tr>";
//                for (Parametro p : m.getParametroList()) {
//                    cadena += "<tr>"
//                            + "<td><pre>" + p.getParametro() + "</pre></td>";
//                    cadena += "<td style='text-align:center'><pre>" + p.getTipo() + "</pre></td>";
//                    cadena += "<td style='text-align:center'><pre>" + p.getDetalle() + "</pre></td>";
//                    String val = "";
//                    if (p.getValorOpcional() == 1) {
//                        val = "Si";
//                    } else {
//                        val = "No";
//                    }
//                    cadena += "<td style='text-align:center'>" + val + "</td>"
//                            + "</tr>";
//                }
//                cadena += "</table>";
//            }
//            cadena += "<br/>";
//        }
//        cadena += "</body>"
//                + "</html>";
//        return (cadena);
//    }
    /**
     * Permite obtener la url del pdf con las especificaciones del componente
     *
     * @param componente el componente relacionado
     * @return la url del pdf con las especificaciones del componente
     */
    public static String getURLPDF(Componente componente) {

        try {
            String folder = "componentes/" + componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getCodigo() + "/" + componente.getIdComponente() + "/pdf/";
            List<File> files = (List<File>) FileUtils.listFiles(new File(FacesContext.getCurrentInstance().getExternalContext()
                    .getRealPath("/") + folder), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            for (File file : files) {
                folder += file.getName();
                break;
            }
            return folder;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Permite crear una celda en rojo para el pdf
     *
     * @param titulo el contenido de la celda
     * @param alineacion el tipo de alineacion del contenido
     * @return una referencia con la celda
     */
    private static PdfPCell crearCeldaPropiedades(String titulo, int alineacion) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell c1 = new PdfPCell(new Phrase(titulo, font));
        c1.setHorizontalAlignment(alineacion);
        c1.setPaddingBottom(7);
        c1.setPaddingLeft(5);
        c1.setBackgroundColor(new BaseColor(214, 17, 23));
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return c1;
    }

    /**
     * Permite crear una celda en blanco para el pdf
     *
     * @param titulo el contenido de la celda
     * @param alineacion el tipo de alineacion del contenido
     * @return una referencia con la celda
     */
    private static PdfPCell crearCeldaContenido(String titulo, int alineacion) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
        PdfPCell c1 = new PdfPCell(new Phrase(titulo, font));
        c1.setHorizontalAlignment(alineacion);
        c1.setPaddingBottom(7);
        c1.setPaddingLeft(5);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return c1;
    }

    /**
     * Permite crear un pdf con las especificaciones tecnicas de un componente
     *
     * @param componente el componente relacionado
     * @param root_folder la ruta principal del sistema
     * @param usuario el usuario dueño del componente
     * @param dependencias las dependencias del componente
     * @return Ok si se pudo crear, un mensaje de error de otra forma.
     */
    public static String createPdf(Componente componente, String root_folder, Usuario usuario, List<Componente> dependencias, List<Tag> tags) {

        String msg;
        Document document = null;
        try {
            String archivoDestino = root_folder + "componentes/" + usuario.getCodigo() + "/" + componente.getIdComponente() + "/pdf/Componente-" + new Date().getTime() + ".pdf";
            File file = new File(archivoDestino);
            file.getParentFile().mkdirs();

            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(archivoDestino));
            document.open();

            // SE AÑADE EL TITULO
            Font ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.BOLD);
            Paragraph para = new Paragraph();
            para.add(new Phrase("COLOSSAL UFPS - BIBLIOTECA DE COMPONENTES", ffont));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            // SE AÑADE EL TITULO

            document.add(new Paragraph(" "));

            // SE AÑADE EL TITULO: ESPECIFICACIONES DEL COMPONENTE
            ffont = new Font(Font.FontFamily.UNDEFINED, 14, Font.BOLD);
            para = new Paragraph();
            para.add(new Phrase("Especificaciones del Componente", ffont));
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            // SE AÑADE EL TITULO: ESPECIFICACIONES DEL COMPONENTE

            // SE AÑADE SALTO DE LINEA ENTRE EL TITULO Y LA TABLA PRINCIPAL
            document.add(new Paragraph(" "));
            // SE AÑADE SALTO DE LINEA ENTRE EL TITULO Y LA TABLA PRINCIPAL

            // SE CREA LA TABLA PRINCIPAL
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new float[]{1, 4}); // hace que la segunda celda sea n veces mas grande que la primera
            table.setWidthPercentage(100);

            // CELDA NOMBRE
            table.addCell(crearCeldaPropiedades("Nombre", Element.ALIGN_LEFT));
            // CELDA NOMBRE

            // CELDA CONTENIDO NOMBRE
            table.addCell(crearCeldaContenido(componente.getNombre(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO NOMBRE

            //CELDA AUTOR
            table.addCell(crearCeldaPropiedades("Autor", Element.ALIGN_LEFT));
            //CELDA AUTOR

            // CELDA CONTENIDO AUTOR
            table.addCell(crearCeldaContenido(componente.getPeticionSubidaList().get(0).getUsuarioCodigo().getNombre(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO AUTOR

            //CELDA DESCRIPTCION
            table.addCell(crearCeldaPropiedades("Descripción", Element.ALIGN_LEFT));
            //CELDA DESCRIPTCION

            // CELDA CONTENIDO DESCRIPTCION
            table.addCell(crearCeldaContenido(componente.getDescripcion(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO DESCRIPTCION

            //CELDA INTRUCCIONES
            table.addCell(crearCeldaPropiedades("Instrucciones", Element.ALIGN_LEFT));
            //CELDA INTRUCCIONES

            // CELDA CONTENIDO INTRUCCIONES
            table.addCell(crearCeldaContenido(componente.getInstrucciones(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO INTRUCCIONES

            //CELDA VERSIÓN
            table.addCell(crearCeldaPropiedades("Versión", Element.ALIGN_LEFT));
            //CELDA VERSIÓN

            // CELDA CONTENIDO VERSION
            table.addCell(crearCeldaContenido(componente.getVersion(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO VERSION

            //CELDA TECNOLOGIA
            table.addCell(crearCeldaPropiedades("Tecnología", Element.ALIGN_LEFT));
            //CELDA TECNOLOGIA

            // CELDA CONTENIDO TECNOLOGIA
            table.addCell(crearCeldaContenido(componente.getTecnologia(), Element.ALIGN_LEFT));
            // CELDA CONTENIDO TECNOLOGIA

            //CELDA GRADO
            table.addCell(crearCeldaPropiedades("Grado de Reutilización", Element.ALIGN_LEFT));
            //CELDA GRADO

            // CELDA CONTENIDO GRADO
            table.addCell(crearCeldaContenido(componente.getGradoReutilizacion() + "", Element.ALIGN_LEFT));
            // CELDA CONTENIDO GRADO

            //CELDA TAGS
            table.addCell(crearCeldaPropiedades("Tags", Element.ALIGN_LEFT));
            //CELDA TAGS

            // CELDA CONTENIDO TAGS
            StringBuilder sb = new StringBuilder();
            short t = 0;
            
            for (Tag tag : tags) {
                sb.append(tag.getNombre());
                if(t!= tags.size()-1){
                    sb.append(",");
                }
                t++;
                
            }
            
//            for (ComponenteHasTag componenteHasTag : componente.getComponenteHasTagList()) {
//
//                sb.append(componenteHasTag.getTag().getNombre());
//                if (t != componente.getComponenteHasTagList().size() - 1) {
//                    sb.append(",");
//                }
//
//                t++;
//            }

            table.addCell(crearCeldaContenido(sb.toString(), Element.ALIGN_JUSTIFIED));
            // CELDA CONTENIDO TAGS

            //CELDA DEPENDENCIAS
            table.addCell(crearCeldaPropiedades("Dependencias", Element.ALIGN_LEFT));
            //CELDA DEPENDENCIAS

            // CELDA CONTENIDO DEPENDENCIAS
            sb = new StringBuilder();

            if (dependencias.isEmpty()) {
                sb.append("Ninguna");
            } else {
                t = 0;
                for (Componente dp : dependencias) {

                    sb.append(dp.getNombre());
                    if (t != dependencias.size() - 1) {
                        sb.append(",");
                    }

                    t++;
                }
            }

            table.addCell(crearCeldaContenido(sb.toString(), Element.ALIGN_JUSTIFIED));
            // CELDA CONTENIDO DEPENDENCIAS

            document.add(table);
            // SE CREA LA TABLA PRINCIPAL

            // SE AÑADE SALTO DE LINEA 
            document.add(new Paragraph(" "));
            // SE AÑADE SALTO DE LINEA 

            // SE AÑADE EL TITULO: INTERFAZ DEL COMPONENTE
            Font ffont2 = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase("Interfaz del Componente", ffont2));
            document.add(para2);
            // SE AÑADE EL TITULO: INTERFAZ DEL COMPONENTE

            // SE AÑADE SALTO DE LINEA 
            document.add(new Paragraph(" "));
            // SE AÑADE SALTO DE LINEA 

            for (Metodo metodo : componente.getMetodoList()) {
                PdfPTable tablaMethods = new PdfPTable(1);
                tablaMethods.setWidthPercentage(100);

                PdfPCell c1 = crearCeldaPropiedades(metodo.getCabecera(), Element.ALIGN_LEFT);
                PdfPCell c2 = new PdfPCell(new Phrase(metodo.getDescripcion()));
                c2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                c2.setPaddingBottom(7);
                c2.setPaddingLeft(5);

                tablaMethods.addCell(c1);
                tablaMethods.addCell(c2);

                if (metodo.getParametroList().size() > 0) {
                    PdfPCell c3 = crearCeldaPropiedades("Parámetros", Element.ALIGN_CENTER);
                    tablaMethods.addCell(c3);
                    document.add(tablaMethods);

                    PdfPTable tablaHeadersPam = new PdfPTable(4);
                    tablaHeadersPam.setWidths(new float[]{1, 1, 3, 1}); // hace que la segunda celda sea n veces mas grande que la primera
                    tablaHeadersPam.setWidthPercentage(100);

                    tablaHeadersPam.addCell(crearCeldaPropiedades("Parámetro", Element.ALIGN_CENTER));
                    tablaHeadersPam.addCell(crearCeldaPropiedades("Tipo", Element.ALIGN_CENTER));
                    tablaHeadersPam.addCell(crearCeldaPropiedades("Detalles", Element.ALIGN_CENTER));
                    tablaHeadersPam.addCell(crearCeldaPropiedades("Opcional", Element.ALIGN_CENTER));

                    for (Parametro parametro : metodo.getParametroList()) {
                        tablaHeadersPam.addCell(crearCeldaContenido(parametro.getParametro(), Element.ALIGN_LEFT));
                        tablaHeadersPam.addCell(crearCeldaContenido(parametro.getTipo(), Element.ALIGN_LEFT));
                        tablaHeadersPam.addCell(crearCeldaContenido(parametro.getDetalle(), Element.ALIGN_JUSTIFIED));
                        tablaHeadersPam.addCell(crearCeldaContenido(parametro.getValorOpcional() == 0 ? "No" : "Si", Element.ALIGN_CENTER));
                    }

                    document.add(tablaHeadersPam);
                } else {
                    document.add(tablaMethods);
                }

                // SE AÑADE SALTO DE LINEA 
                document.add(new Paragraph(" "));
                // SE AÑADE SALTO DE LINEA 

            }

            msg = "OK";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error al crear el pdf";

        } finally {
            // SE CIERRA EL DOCUMENTO
            document.close();
            // SE CIERRA EL DOCUMENTO
        }

        return msg;

    }

}
