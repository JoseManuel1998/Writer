package paquetes;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;



public class Utiles {

		Highlighter.HighlightPainter myHighlightPainter = new GenerarColor(Color.yellow);
		
		
			public Utiles() {}

		
		public ImageIcon reescalar(String name, int alto, int ancho) {
			
	
			//Metodo para reescarlar las imagenes y meterlas bien a al men�.
			ImageIcon imageIcon = new ImageIcon("src/res/" + name +".png"); //Carga la imagen a imageIcon
			Image image = imageIcon.getImage(); // La transforma 
			Image newimg = image.getScaledInstance(alto, ancho,  java.awt.Image.SCALE_SMOOTH); // Escalamos las imagenes para ahorrar recursos
			imageIcon = new ImageIcon(newimg); 
			
			return imageIcon;
		}
		
		
		//Metodos de abrir y guardar ficheros en clase complementaria para liberar de codigo la clase principal
		public void  guardarArchivo(JFileChooser fileChooser, EditorDeTexto editor) {
			
			 if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
			       
		
			            String rutaFichero = fileChooser.getSelectedFile().getAbsolutePath();
			            StyledDocument styledDocument = (StyledDocument) editor.getPagina().getDocument();
			            RTFEditorKit kit = new RTFEditorKit();
			            BufferedOutputStream bufferedOutputStream;
			           try {
			               bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(rutaFichero));
			               kit.write(bufferedOutputStream, styledDocument, styledDocument.getStartPosition().getOffset(), styledDocument.getLength());
			               bufferedOutputStream.flush();
			               bufferedOutputStream.close();
			           }catch (IOException | BadLocationException e) {e.printStackTrace();}
			        }

		}
		
		public void  abrirArchivo(JFileChooser fileChooser, EditorDeTexto editor) {
		
		 if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
             try {
                 BufferedReader origen = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getPath()));
                 String textoTotal="";
                 String linea;
                 
                 editor.setTitle("Word - " + fileChooser.getSelectedFile().getName());
                 while((linea=origen.readLine()) != null) {
                     textoTotal += linea;
                 }
                 editor.getPagina().setText(textoTotal);
                 origen.close();
             } catch (Exception ex) {}
             
             
         }

		}
		
		private class GenerarColor extends DefaultHighlighter.DefaultHighlightPainter {
			
			public GenerarColor(Color color) {
				super(color);
			}
		}


		//Metodo de busca, el cual le pasamos un JTextPane y el string a buscar. Se colorea donde coincidan la cadena del string con alguna posicion del JTextPane
		public void metodoBuscar(JTextPane textComp, String pattern, EditorDeTexto editor) {
			try {
				Highlighter hilite = textComp.getHighlighter();
				hilite.removeAllHighlights();
				Document doc = textComp.getDocument();
				String text = doc.getText(0, doc.getLength());
				
				int pos = 0;

				while ((pos = text.toUpperCase().indexOf(pattern.toUpperCase(), pos)) >= 0) {
					hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
					pos += pattern.length();
				}
			} catch (Exception e) {
			}
		}
		
		
	
}


