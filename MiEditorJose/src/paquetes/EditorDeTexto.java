package paquetes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.Popup;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.UndoManager;



public class EditorDeTexto extends JFrame {


	private static  EditorDeTexto miEditor;

	//Main
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {

				miEditor = new EditorDeTexto();
				miEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				miEditor.setExtendedState(6);
				miEditor.setVisible(true);



			}
		});
	}
	//-----------------------------------------------------------------------------


	JPanel laminaColor;
	JPanel laminaColor2;
	JPanel laminaMenu;
	JPanel laminaMenuInferior;
	JFileChooser fileChooser;

	JTextPane pagina;
	JScrollPane scroll;
	JMenu menuArchivo;
	JButton bAbrir;
	JButton bNuevo;
	JButton bGuardar;
	JButton bPrint;
	JButton bCut;
	JButton bCopy;
	JButton bPaste;
	JButton bUndo;
	JButton bRedo;
	JButton bFind;
	JButton bFontColor;
	JButton bDestacar;
	JButton bBuscarInferior;
	JComboBox cbFuentes;
	JComboBox cbTamanyos;
	String[] nombreFuentes;
	ArrayList<Integer> arrayTamanyos;

	JButton bBold;
	JButton bItalic;
	JButton bUnderline;
	JToggleButton bLeftAlign;
	JToggleButton bJustify;
	JToggleButton bRightAlign;
	JToggleButton bCenterAlign;
	ButtonGroup grupoAlineado;
	JToolBar botones;
	JToolBar menuBarraInferior;

	JMenuItem itemOpen;
	JMenuItem itemNew;
	JMenuItem itemSave;
	JMenuItem itemSalir;
	JMenuItem itemPrint;
	

	JMenu menuEdit;
	JMenuItem itemFind;
	JMenuItem itemRedo;
	JMenuItem itemUndo;
	JMenuItem itemCopy;
	JMenuItem itemCut;
	JMenuItem itemPaste;
	
    JButton bEspanyol;
    JButton bEnglish;
    JMenuItem itemEnglish;
    JMenuItem itemEspanyol;
    JMenu menuLanguage;
    
    JPopupMenu popUpMenu;
    JMenuItem itemCopyPop;
    JMenuItem itemCutPop;
    JMenuItem itemPastePop;
    JMenuItem itemBoldPop;
    JMenuItem itemItalicPop;
    JMenuItem itemUnderlinePop;
    
  
    String inputText,search;
    boolean isAlinDer = false;
    boolean isAlinCent = false;
    boolean isAlinIzq =false;
    boolean isJustificado = false;
	
	JMenu menuSkins;
	JMenuItem itemNimbus;
	JMenuItem itemMetal;
	JMenuItem itemWindows;
	

	JMenu menuParrafo;
	JMenuItem itemLeftAlign;
	JMenuItem itemRightAlign;
	JMenuItem itemCenterAlign;
	JMenuItem itemJustify;
	JMenu menuFormato;
	JMenuItem itemBold;
	JMenuItem itemUnderline;
	JMenuItem itemItalic;
	JMenuItem itemFontColor;
	JMenuItem itemDestacar;
	Utiles utiles;
	UndoManager um;
	Highlighter highLighterManager;
	RoundJTextField jTextFieldInferior;

	
	SimpleAttributeSet attributeSet;
	Integer array[];
	JLabel labelPalabras;
	JLabel nPalabras;
	JLabel labelCaracteres;
	JLabel nCaracteres;
	JMenuBar menuBarra;



	//Iniciamos componentes
	public EditorDeTexto() {


		initComponents();


	}

	//---------------------------------------------------------------------------------
	private void initComponents() {


		this.setIconImage (new ImageIcon(getClass().getResource("/res/icon.png")).getImage());
		setTitle("Word - Sin título");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dimension);
		utiles = new Utiles();
		um = new UndoManager();
		pagina = new JTextPane();
	



		//Iniciamos comboBoxes
		//Fuentes
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		nombreFuentes=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		array = new Integer[nombreFuentes.length];
		for(int i=1;i<=nombreFuentes.length;i++) {
		
			array[i-1] = i;
		}

		cbFuentes = new JComboBox(array);
		ComboBoxRenderar renderar = new ComboBoxRenderar();
		cbFuentes.setRenderer(renderar);
		cbFuentes.setSelectedIndex(75);
		cbFuentes.setMaximumSize(cbFuentes.getPreferredSize());
		

		//Tamaño de la fuente
		arrayTamanyos = new ArrayList<Integer>();
		for(int i=8;i<72;i+=2){arrayTamanyos.add(i);}
		Integer[] tamanyosArray = new Integer[arrayTamanyos.size()];
		tamanyosArray = arrayTamanyos.toArray(new Integer[arrayTamanyos.size()]);
		cbTamanyos = new JComboBox(tamanyosArray);
		cbTamanyos.setSelectedItem(18);
		cbTamanyos.setMaximumSize(cbTamanyos.getPreferredSize());

		//

		
		
		//Creamos los botones y le damos las características que queremos, imagenes, tamanyos..
		try {
		bNuevo = new JButton(utiles.reescalar("newfile",24,24));
		bNuevo.setBorderPainted(false);
		bNuevo.setFocusPainted(false);
		bAbrir = new JButton(new ImageIcon("src/res/carpeta.png"));
		bAbrir.setBorderPainted(false);
		bAbrir.setFocusPainted(false);
		bGuardar = new JButton(new ImageIcon("src/res/guardar.png"));
		bGuardar.setFocusPainted(false);
		bGuardar.setBorderPainted(false);



		bPrint = new JButton(new ImageIcon("src/res/imprimir.png"));
		bPrint.setBorderPainted(false);
		bPrint.setFocusPainted(false);

		
		bCut = new JButton(new ImageIcon("src/res/tijeras.png"));
		bCut.setBorderPainted(false);
		bCut.setFocusPainted(false);
		bCopy = new JButton(new ImageIcon("src/res/copy.png"));
		bCopy.setBorderPainted(false);
		bCopy.setFocusPainted(false);
		bPaste = new JButton(new ImageIcon("src/res/paste.png"));
		bPaste.setBorderPainted(false);
		bPaste.setFocusPainted(false);


		bUndo = new JButton(new ImageIcon("src/res/undo.png"));
		bUndo.setBorderPainted(false);
		bUndo.setFocusPainted(false);
		bRedo = new JButton(new ImageIcon("src/res/redo.png"));
		bRedo.setBorderPainted(false);
		bRedo.setFocusPainted(false);
		bFind = new JButton(utiles.reescalar("find", 24, 24));
		bFind.setBorderPainted(false);
		bFind.setFocusPainted(false);
	

		bBold = new JButton(new ImageIcon("src/res/bold.png"));
		bBold.setFocusPainted(false);
		bItalic = new JButton(new ImageIcon("src/res/italic.png"));
		bItalic.setFocusPainted(false);
		bUnderline = new JButton(new ImageIcon("src/res/underline.png"));
		bUnderline.setFocusPainted(false);
		attributeSet = new SimpleAttributeSet();
		

		bFontColor = new JButton(new ImageIcon("src/res/fontcolor.png"));
		bFontColor.setBorderPainted(false);
		bFontColor.setFocusPainted(false);
		bDestacar = new JButton(new ImageIcon("src/res/resaltar.png"));
		bDestacar.setBorderPainted(false);
		bDestacar.setFocusPainted(false);

		bLeftAlign = new JToggleButton(new ImageIcon("src/res/alingleft.png"));
		bLeftAlign.setToolTipText("Alinear izquierda ");
		bJustify = new JToggleButton(new ImageIcon("src/res/justify.png"));
		bJustify.setToolTipText("Justificar");
		bRightAlign = new JToggleButton(new ImageIcon("src/res/alignrigth.png"));
		bRightAlign.setToolTipText("Alinear derecha");
		bCenterAlign = new JToggleButton(new ImageIcon("src/res/center.png"));
		bCenterAlign.setToolTipText("Alinear derecha");
		} catch (Exception o) {o.printStackTrace();}


		grupoAlineado = new ButtonGroup();
		bLeftAlign.setSelected(true);
		grupoAlineado.add(bLeftAlign);
		grupoAlineado.add(bRightAlign);
		grupoAlineado.add(bCenterAlign);
		grupoAlineado.add(bJustify);


		//Menu
		try {
		menuBarra = new JMenuBar();
		menuArchivo = new JMenu("Archivo");
		itemOpen = new JMenuItem("Abrir");
		itemOpen.setIcon(utiles.reescalar("carpeta",16,16));
		itemOpen.setIconTextGap(10);
		itemNew = new JMenuItem("Nuevo");
		itemNew.setIcon(utiles.reescalar("newfile",16,16));
		itemNew.setIconTextGap(10);
		itemSave = new JMenuItem("Guardar");
		itemSave.setIcon(utiles.reescalar("guardar",16,16));
		itemSave.setIconTextGap(10);
		itemPrint = new JMenuItem("Imprimir");
		itemPrint.setIcon(utiles.reescalar("imprimir",16,16));
		itemPrint.setIconTextGap(10);
		itemSalir = new JMenuItem("Salir");
		itemSalir.setIcon(utiles.reescalar("cerrar", 16, 16));
		itemSalir.setIconTextGap(10);
		itemFind = new JMenuItem("Buscar");
		itemFind.setIcon(utiles.reescalar("find",16,16));
		itemFind.setIconTextGap(10);
		} catch (Exception o) {o.printStackTrace();}

		menuArchivo.add(itemNew);
		menuArchivo.add(itemOpen);
		menuArchivo.add(itemSave);
		menuArchivo.add(new JSeparator(SwingConstants.HORIZONTAL));
		menuArchivo.add(itemPrint);
		menuArchivo.add(itemFind);
		menuArchivo.add(itemSalir);

		try {
		menuEdit = new JMenu ("Editar");
		itemRedo = new JMenuItem("Rehacer");
		itemRedo.setIcon(utiles.reescalar("redo",16,16));
		itemRedo.setIconTextGap(10);
		itemUndo = new JMenuItem("Deshacer");
		itemUndo.setIcon(utiles.reescalar("undo",16,16));
		itemUndo.setIconTextGap(10);
		itemCut = new JMenuItem("Cortar");
		itemCut.setIcon(utiles.reescalar("tijeras",16,16));
		itemCut.setIconTextGap(10);
		itemCopy = new JMenuItem("Copiar");
		itemCopy.setIcon(utiles.reescalar("copy",16,16));
		itemCopy.setIconTextGap(10);
		itemPaste = new JMenuItem("Pegar");
		itemPaste.setIcon(utiles.reescalar("paste",16,16));
		itemPaste.setIconTextGap(10);
		} catch (Exception o) {o.printStackTrace();}

		menuEdit.add(itemUndo);
		menuEdit.add(itemRedo);
		menuEdit.add(new JSeparator(SwingConstants.HORIZONTAL));
		menuEdit.add(itemCut);
		menuEdit.add(itemCopy);
		menuEdit.add(itemPaste);
		menuEdit.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		try {
		menuParrafo = new JMenu ("Parrafo");
		itemLeftAlign = new JMenuItem("Alinear izquierda");
		itemLeftAlign.setIcon(utiles.reescalar("alingleft",16,16));
		itemLeftAlign.setIconTextGap(10);
		itemCenterAlign = new JMenuItem("Centrar el texto");
		itemCenterAlign.setIcon(utiles.reescalar("center",16,16));
		itemCenterAlign.setIconTextGap(10);
		itemRightAlign = new JMenuItem("Alinear derecha");
		itemRightAlign.setIcon(utiles.reescalar("alignrigth",16,16));
		itemRightAlign.setIconTextGap(10);
		itemJustify = new JMenuItem("Justificar");
		itemJustify.setIcon(utiles.reescalar("justify",16,16));
		itemJustify.setIconTextGap(10);
		
		} catch (Exception o) {o.printStackTrace();}
		
		//Menu inferior
		laminaMenuInferior = new JPanel();
		laminaMenuInferior.setLayout(new BorderLayout());
		menuBarraInferior = new JToolBar();
		menuBarraInferior.setFloatable(false);

		jTextFieldInferior = new RoundJTextField(15);
		jTextFieldInferior.setPreferredSize(new Dimension(200, 25));
		bBuscarInferior = new JButton();
		bBuscarInferior.setIcon(utiles.reescalar("flechaBuscar",18,18));
		bBuscarInferior.setBorderPainted(false);
		bBuscarInferior.setFocusable(false);
		bBuscarInferior.addActionListener(buttonListener());
		
	
		jTextFieldInferior.setHint("Buscar");
		menuBarraInferior.add(jTextFieldInferior);
		menuBarraInferior.add(bBuscarInferior);
		laminaMenuInferior.add(menuBarraInferior, BorderLayout.WEST);

		//...


		menuParrafo.add(itemLeftAlign);
		menuParrafo.add(itemCenterAlign);
		menuParrafo.add(itemRightAlign);
		menuParrafo.add(itemJustify);
		
		
		try {
		menuFormato = new JMenu ("Formato");
		itemBold = new JMenuItem("Negrita");
		itemBold.setIcon(utiles.reescalar("bold",16,16));
		itemBold.setIconTextGap(10);
		itemItalic = new JMenuItem("Cursiva");
		itemItalic.setIcon(utiles.reescalar("italic",16,16));
		itemItalic.setIconTextGap(10);
		itemUnderline = new JMenuItem("Subrayar");
		itemUnderline.setIcon(utiles.reescalar("underline",16,16));
		itemUnderline.setIconTextGap(10);
		itemFontColor = new JMenuItem("Color de fuente");
		itemFontColor.setIcon(utiles.reescalar("fontcolor",16,16));
		itemFontColor.setIconTextGap(10);
		itemDestacar = new JMenuItem("Destacar");
		itemDestacar.setIcon(utiles.reescalar("resaltar",16,16));
		itemDestacar.setIconTextGap(10);
		} catch (Exception o) {o.printStackTrace();}

		menuFormato.add(itemBold);
		menuFormato.add(itemItalic);
		menuFormato.add(itemUnderline);
		menuFormato.add(new JSeparator(SwingConstants.HORIZONTAL));
		menuFormato.add(itemFontColor);
		menuFormato.add(itemDestacar);
		
		menuSkins = new JMenu ("Skins");
		itemWindows =new JMenuItem("Windows");
		itemNimbus = new JMenuItem("Nimbus");
		itemMetal = new JMenuItem("Metal");
		
		menuSkins.add(itemWindows);
		menuSkins.add(itemMetal);
		menuSkins.add(itemNimbus);
		
		try {
	    bEspanyol = new JButton((utiles.reescalar("espana", 16, 16)));
	    bEnglish = new JButton((utiles.reescalar("uk", 16, 16)));
	    
	    itemEnglish = new JMenuItem(utiles.reescalar("uk", 16, 16));
	    itemEnglish.setIconTextGap(10);
		itemEspanyol = new JMenuItem(utiles.reescalar("espana", 16, 16));
		itemEspanyol.setIconTextGap(10);
	    menuLanguage = new JMenu();
	    menuLanguage.add(itemEnglish);
	    menuLanguage.add(itemEspanyol);
	    
	    popUpMenu = new JPopupMenu();
	    itemCopyPop = new JMenuItem((utiles.reescalar("copy", 16, 16)));
	    itemCutPop = new JMenuItem((utiles.reescalar("tijeras", 16, 16)));
	    itemPastePop = new JMenuItem(utiles.reescalar("paste", 16, 16));
	    itemBoldPop = new JMenuItem(utiles.reescalar("bold", 16, 16)); 
	    itemItalicPop = new JMenuItem(utiles.reescalar("italic", 16, 16));
	    itemUnderlinePop = new JMenuItem(utiles.reescalar("underline", 16, 16));
		} catch (Exception o) {o.printStackTrace();}
	    popUpMenu.add(itemCopyPop);
	    popUpMenu.add(itemCutPop);
	    popUpMenu.add(itemPastePop);
	    popUpMenu.add(new JSeparator(SwingConstants.HORIZONTAL));
	    popUpMenu.add(itemBoldPop);
	    popUpMenu.add(itemItalicPop);
	    popUpMenu.add(itemUnderlinePop);
	    


		//Listener 
		itemNew.addActionListener(buttonListener());
		bNuevo.addActionListener(buttonListener());
		itemOpen.addActionListener(buttonListener());
		itemSave.addActionListener(buttonListener());
		bAbrir.addActionListener(buttonListener());
		bGuardar.addActionListener(buttonListener());
		itemRedo.addActionListener(buttonListener());
		bRedo.addActionListener(buttonListener());
		bUndo.addActionListener(buttonListener());
		itemUndo.addActionListener(buttonListener());
		itemCopy.addActionListener(buttonListener());
		bCopy.addActionListener(buttonListener());
		itemPaste.addActionListener(buttonListener());
		bPaste.addActionListener(buttonListener());
		itemCut.addActionListener(buttonListener());
		bCut.addActionListener(buttonListener());
		itemSalir.addActionListener(buttonListener());
		bPrint.addActionListener(buttonListener());
		itemPrint.addActionListener(buttonListener());
		itemLeftAlign.addActionListener(buttonListener());
		bLeftAlign.addActionListener(buttonListener());
		itemCenterAlign.addActionListener(buttonListener());
		bCenterAlign.addActionListener(buttonListener());
		itemRightAlign.addActionListener(buttonListener());
		bRightAlign.addActionListener(buttonListener());
		itemJustify.addActionListener(buttonListener());
		bJustify.addActionListener(buttonListener());
		bBold.addActionListener(buttonListener());
		itemBold.addActionListener(buttonListener());
		bItalic.addActionListener(buttonListener());
		itemItalic.addActionListener(buttonListener());
		bUnderline.addActionListener(buttonListener());
		itemUnderline.addActionListener(buttonListener());
		
		bFontColor.addActionListener(buttonListener());
		bFind.addActionListener(buttonListener());
		itemFind.addActionListener(buttonListener());
		cbFuentes.addActionListener(buttonListener());
		cbTamanyos.addActionListener(buttonListener());
		
		itemMetal.addActionListener(skinsListener());
		itemNimbus.addActionListener(skinsListener());
		itemWindows.addActionListener(skinsListener());
		
		itemEnglish.addActionListener(idioma());
		itemEspanyol.addActionListener(idioma());
		


		//Agregamos atajos de teclado
		itemBold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemItalic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemUnderline.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		itemRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));


		//Lamina de menu superior

		laminaMenu = new JPanel();
		laminaMenu.setLayout(new BorderLayout());
		menuBarra.add(menuArchivo);
		menuBarra.add(menuEdit);
		menuBarra.add(menuParrafo);
		menuBarra.add(menuFormato);
		menuBarra.add(menuSkins);
		menuBarra.add(menuLanguage);
		//...

	

		//Botones y listener

		botones = new JToolBar();
		botones.setFloatable(false);
		botones.add(bNuevo);
		botones.add(bAbrir);
		botones.add(bGuardar);
		botones.addSeparator();
		botones.add(bPrint);
		botones.addSeparator();
		botones.add(bCut);
		botones.add(bCopy);
		botones.add(bPaste);
		botones.addSeparator();
		botones.add(bUndo);
		botones.add(bRedo);
		botones.addSeparator();
		botones.add(bFind);
		botones.addSeparator();
		botones.add(cbFuentes);
		botones.add(cbTamanyos);
		botones.add(bBold);
		botones.add(bItalic);
		botones.add(bUnderline);
		botones.addSeparator();
		botones.add(bFontColor);
		botones.add(bDestacar);
		botones.addSeparator();
		botones.add(bLeftAlign);
		botones.add(bCenterAlign);
		botones.add(bRightAlign);
		botones.add(bJustify);



		//Area
		laminaColor = new JPanel();
		laminaColor.setBackground(Color.LIGHT_GRAY);

		laminaColor2 = new JPanel();
		laminaColor2.setBackground(Color.LIGHT_GRAY);
		


		scroll = new JScrollPane(pagina);

		int insetTop= 100, insetLeft= 100, insetBottom= 100, insetRight = 100;
		pagina.setMargin(new Insets(insetTop , insetLeft, insetBottom, insetRight));
		MouseListenerConfig listenerMouse = new MouseListenerConfig();
		KeyboardListenerConfig listenerKeyboard = new KeyboardListenerConfig();
		pagina.setFont(new Font("Helvetica",Font.PLAIN , 18));
		pagina.addMouseListener(listenerMouse);
		pagina.addKeyListener(listenerKeyboard);
		
	
	
		
		//Mouse Listener que agrega el popUp
		
			pagina.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e ) {
    			if(e.getButton() == e.BUTTON3|| SwingUtilities.isRightMouseButton(e)) {
    				popUpMenu.show(pagina, e.getX(), e.getY());}}});
    	pagina.add(popUpMenu);	
    	
		
 
		
		//Añadimos la barra de menu y la barra de botones a la lamina superior
		laminaMenu.setLayout(new BorderLayout());
		laminaMenu.add(menuBarra,BorderLayout.NORTH);
		laminaMenu.add(botones,BorderLayout.SOUTH);

		//...
		
		
		//Añadimos todos los componentes al frame
		add(laminaMenu, BorderLayout.NORTH);
		add(laminaColor, BorderLayout.EAST);
		add(scroll,BorderLayout.CENTER);
		add(laminaColor2, BorderLayout.WEST);
		add(laminaMenuInferior, BorderLayout.SOUTH);
		
		pagina.getDocument().addUndoableEditListener(um);

		//Ponemos por defecto el 
		defaultIdioma();

		
	}

	//Listener que controla la gran mayoría de botones
	private ActionListener buttonListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				if(e.getSource()==itemNew || e.getSource()==bNuevo && !pagina.getText().equals("")) { 

					String[] options = {"Sí", "No"};

					int x = JOptionPane.showOptionDialog(null, "Va a crear un nuevo documento, ¿Desea guardar los cambios?",
							"",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, (utiles.reescalar("newfile",75,75)) ,
							options, options[0]);

					if(x==0) {

						utiles.guardarArchivo(fileChooser, miEditor);
						pagina.setText("");
						miEditor.setTitle("Word - Sin título");

					}
					if(x==1) {

						pagina.setText("");
						miEditor.setTitle("Word - Sin título");
					}
				}
				
				
				
				if(e.getSource()==bBuscarInferior) {utiles.metodoBuscar(pagina, jTextFieldInferior.getText(), miEditor);}
				if(e.getSource()==itemOpen || e.getSource()==bAbrir) {utiles.abrirArchivo(fileChooser, miEditor);}
				if (e.getSource()==itemSave || e.getSource()==bGuardar) {utiles.guardarArchivo(fileChooser, miEditor);}
				if(e.getSource()==bCopy || e.getSource()==itemCopy || e.getSource()==itemCopyPop) {{pagina.copy();}}
				if(e.getSource()==bPaste || e.getSource()==itemPaste||e.getSource()==itemPastePop) {{pagina.paste();}}
				if(e.getSource()==bCut || e.getSource()==itemCut || e.getSource()==itemCutPop) {{pagina.cut();}}
				if(e.getSource()==bBold || e.getSource()==itemBold || e.getSource()==itemBoldPop) { Action actionBold = new StyledEditorKit.BoldAction(); pagina.grabFocus();actionBold.actionPerformed(e); bBold.setSelected(true); }
				if(e.getSource()==bItalic || e.getSource()==itemItalic || e.getSource()==itemItalicPop) { Action actionItalic = new StyledEditorKit.ItalicAction(); pagina.grabFocus();actionItalic.actionPerformed(e);}
				if(e.getSource()==bUnderline || e.getSource()==itemUnderline || e.getSource()==itemUnderlinePop) { Action actionUnderline = new StyledEditorKit.UnderlineAction(); pagina.grabFocus();actionUnderline.actionPerformed(e);}
				if(e.getSource()==bUndo || e.getSource()==itemUndo) {if (um.canUndo()) {um.undo();} else {System.out.println("No se puede deshacer");}}
				if(e.getSource()==bRedo || e.getSource()==itemRedo) {if (um.canRedo()) {um.redo();} else {System.out.println("No se puede rehacer");}}			
				if(e.getSource()==itemSalir){System.exit(0);}
				if(e.getSource()==itemPrint || e.getSource()== bPrint){try {pagina.print();} catch (PrinterException e1) {e1.printStackTrace();}}
				if(e.getSource()==bFontColor) { JColorChooser colores = new JColorChooser(); Color color = JColorChooser.showDialog(null, "Seleccione un color", Color.black);
				Action actionColor = new StyledEditorKit.ForegroundAction("Color", color);
				actionColor.actionPerformed(e);}
				if(e.getSource()==cbFuentes) {
				Action fontAction = new StyledEditorKit.FontFamilyAction("FuenteFamilia", nombreFuentes[(int) cbFuentes.getSelectedItem()-1]);
				fontAction.actionPerformed(e); pagina.grabFocus();}
				if(e.getSource()==cbTamanyos) {Action sizeAction = new StyledEditorKit.FontSizeAction("Tamanyo", Integer.parseInt(cbTamanyos.getSelectedItem().toString())); 
				sizeAction.actionPerformed(e); pagina.grabFocus(); }
				if(e.getSource()==bFind || e.getSource()==itemFind ) {
				 String palabras=(String) JOptionPane.showInputDialog(null,inputText,search,JOptionPane.INFORMATION_MESSAGE,(utiles.reescalar("find",75,75)),null,"");
					utiles.metodoBuscar(pagina, palabras, miEditor);}
				
			}
		};
	}
	
	
	//Clase que crea un combobox personalizado

	public class ComboBoxRenderar extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, 
				Object value, 
				int index, 
				boolean isSelected, 
				boolean cellHasFocus) {
			int offset = ((Integer)value).intValue() - 1 ;
			String name = nombreFuentes[offset];
			setText(name);
			
			setFont(new Font(name,Font.PLAIN,18));
			return this;
		}
		
	}
	
	//Listener de teclado
	public class KeyboardListenerConfig implements KeyListener{
		
		
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {comprobacionTeclado();}
		@Override
		public void keyReleased(KeyEvent e) {comprobacionTeclado();}
		
	}

		//Listener de mouse
	public class MouseListenerConfig implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {comprobacionMouse();}
		@Override
		public void mousePressed(MouseEvent e) {comprobacionMouse();}		
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}


	}
	
	 //Comprobacion de teclado que comprueba que botones tiene que ilumnar 
	public void comprobacionTeclado() {
		
		int posicion = pagina.getCaretPosition();
		AttributeSet style = pagina.getStyledDocument().getCharacterElement(posicion-1).getAttributes();

		if(pagina.getStyledDocument().getFont(style).isBold()==true){bBold.setSelected(true); itemBold.setBackground(Color.LIGHT_GRAY);}
		else {bBold.setSelected(false); itemBold.setBackground(Color.white);}
		
		if(pagina.getStyledDocument().getFont(style).isItalic()==true){bItalic.setSelected(true); itemItalic.setBackground(Color.LIGHT_GRAY);}
		else {bItalic.setSelected(false); itemItalic.setBackground(Color.white);}
		
		if(style.containsAttribute(StyleConstants.Underline, true)){bUnderline.setSelected(true); itemUnderline.setBackground(Color.LIGHT_GRAY);}
		else {bUnderline.setSelected(false); itemUnderline.setBackground(Color.white);}
		
		if(style.containsAttribute(StyleConstants.ALIGN_LEFT, true)){bLeftAlign.setSelected(true); itemLeftAlign.setBackground(Color.LIGHT_GRAY);}
		else {bLeftAlign.setSelected(false); itemLeftAlign.setBackground(Color.white);
		}
		
		if(style.containsAttribute(StyleConstants.ALIGN_CENTER, true)){bCenterAlign.setSelected(true); itemCenterAlign.setBackground(Color.LIGHT_GRAY);}
		else {bCenterAlign.setSelected(false); itemCenterAlign.setBackground(Color.white);
		}
		
		if(style.containsAttribute(StyleConstants.ALIGN_RIGHT, true)){bRightAlign.setSelected(true); itemRightAlign.setBackground(Color.LIGHT_GRAY);}
		else {bRightAlign.setSelected(false); itemLeftAlign.setBackground(Color.white);
		}
		if(style.containsAttribute(StyleConstants.ALIGN_JUSTIFIED, true)){bJustify.setSelected(true); itemJustify.setBackground(Color.LIGHT_GRAY);}
		else {bJustify.setSelected(false); itemJustify.setBackground(Color.white);
		}
		highLighterManager = pagina.getHighlighter();
		highLighterManager.removeAllHighlights();
		
		
		
	}
	
	
	 //Comprobacion de teclado que comprueba que botones tiene que ilumnar 
	public void comprobacionMouse() {
		
		int posicionMouse = pagina.getCaretPosition();
		AttributeSet style = pagina.getStyledDocument().getCharacterElement(posicionMouse -1).getAttributes();
		String fuenteActual = pagina.getStyledDocument().getFont(style).getFamily();
		int tamanyoActual = pagina.getStyledDocument().getFont(style).getSize();

		if(pagina.getStyledDocument().getFont(style).isBold()==true){bBold.setSelected(true); itemBold.setBackground(Color.LIGHT_GRAY);}
		else {bBold.setSelected(false); itemBold.setBackground(Color.white);}
		

		if(pagina.getStyledDocument().getFont(style).isItalic()==true){bItalic.setSelected(true); itemItalic.setBackground(Color.LIGHT_GRAY);}
		else {bItalic.setSelected(false); itemItalic.setBackground(Color.white);}
		
		if(style.containsAttribute(StyleConstants.Underline, true)){bUnderline.setSelected(true); itemUnderline.setBackground(Color.LIGHT_GRAY);}
		else {bUnderline.setSelected(false); itemUnderline.setBackground(Color.white);}
		
		if(style.containsAttribute(StyleConstants.ALIGN_LEFT, false)){bLeftAlign.setSelected(true); itemLeftAlign.setBackground(Color.LIGHT_GRAY);}
		else {bLeftAlign.setSelected(false); itemLeftAlign.setBackground(Color.white);
		}
		
		if(style.containsAttribute(StyleConstants.ALIGN_CENTER, true)){bCenterAlign.setSelected(true); itemCenterAlign.setBackground(Color.LIGHT_GRAY);}
		else {bCenterAlign.setSelected(false); itemCenterAlign.setBackground(Color.white);
		}
		
		if(style.containsAttribute(StyleConstants.ALIGN_RIGHT, true)){bRightAlign.setSelected(true); itemRightAlign.setBackground(Color.LIGHT_GRAY);}
		else {bRightAlign.setSelected(false); itemLeftAlign.setBackground(Color.white);
		}
		if(style.containsAttribute(StyleConstants.ALIGN_JUSTIFIED, true)){bJustify.setSelected(true); itemJustify.setBackground(Color.LIGHT_GRAY);}
		else {bJustify.setSelected(false); itemJustify.setBackground(Color.white);
		}
		
		for (int i = 0; i < nombreFuentes.length; i++) {
			if(fuenteActual.equals(nombreFuentes[i])) {
				cbFuentes.setSelectedIndex(i);	}}
		
		
		for (int i = 0; i < arrayTamanyos.size(); i++) {
			if(tamanyoActual==arrayTamanyos.get(i)) {
				cbTamanyos.setSelectedIndex(i);}}
		
		
		highLighterManager = pagina.getHighlighter();
		highLighterManager.removeAllHighlights();

	}
	
	//Setters y getters para la pagina para poder acceder a ella en clases complementarias
	public JTextPane getPagina() {return pagina;}
	public void setPagina(JTextPane pagina) {this.pagina = pagina;}
	
	//Le ponemos por defecto un idioma, si el usuario cambia el idioma y quiere volver a poner el español, llamaremos de nuevo al método
	private void defaultIdioma() {
	
            
	       
	        menuArchivo.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_archivo"));
	       
	        itemNew.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_nuevo"));
	        bNuevo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_nuevo"));
	        itemOpen.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_abrir"));
	        bAbrir.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_abrir"));
	        itemSave.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_guardar"));
	        bGuardar.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_guardar"));
	        itemPrint.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_imprimir"));
	        bPrint.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_imprimir"));
	        itemSalir.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_salir"));
	        itemFind.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_buscar"));
	        bFind.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_buscar"));
	        inputText = (ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_inputText"));
	        search = (ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_search"));
	        
	        menuEdit.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_edit"));
	        itemRedo.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_redo"));
	        bRedo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_redo"));
	        itemUndo.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_undo"));
	        bUndo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_undo"));
	        
	        itemCopy.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_copy"));
	        bCopy.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_copy"));
	        itemPaste.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_paste"));
	        bPaste.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_paste"));
	        itemCut.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cut"));
	        bCut.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cut"));
	        
	        menuParrafo.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_parrafo"));
	        itemLeftAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_left"));
	        bLeftAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_left"));
	        itemCenterAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_center"));
	        bCenterAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_center"));
	        itemRightAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_right"));
	        bRightAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_right"));
	        itemJustify.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_justify"));
	        bJustify.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_justify"));
	        
	        menuFormato.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_formato"));
	        itemBold.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_negrita"));
	        bBold.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_negrita"));
	        itemItalic.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cursiva"));
	        bItalic.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cursiva"));
	        itemUnderline.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_subrayado"));
	        bUnderline.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_subrayado"));
	        itemFontColor.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_fontcolor"));
	        bFontColor.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_fontcolor"));
	        itemDestacar.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_destacar"));
	        bDestacar.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_destacar"));
	        menuSkins.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_aspectos"));
	        
	        
	        itemCopyPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_copy"));
		    itemCutPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cut"));
		    itemPastePop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_paste"));
		    itemBoldPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_negrita"));
		    itemItalicPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_cursiva"));
		    itemUnderlinePop.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_subrayado"));
	        
		    jTextFieldInferior.setHint(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_hint")); 
  	        menuLanguage.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menu_idioma"));
  	        itemEnglish.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_english"));
  	        itemEspanyol.setText(ResourceBundle.getBundle("paquetes.Etiquetas").getString("etiqueta_menuItem_espanyol"));

	 
            }

	 private ActionListener idioma(){
	        return new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if(e.getSource()==bEspanyol || e.getSource()==itemEspanyol){                    
	                    defaultIdioma();
	                } else if (e.getSource()==bEnglish || e.getSource()==itemEnglish) {
	                	
	                	
	                
	                	 menuArchivo.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_archivo"));
	          	       
	         	        itemNew.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_nuevo"));
	         	        bNuevo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_nuevo"));
	         	        itemOpen.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_abrir"));
	         	        bAbrir.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_abrir"));
	         	        itemSave.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_guardar"));
	         	        bGuardar.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_guardar"));
	         	        itemPrint.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_imprimir"));
	         	        bPrint.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_imprimir"));
	         	        itemSalir.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_salir"));
	         	        itemFind.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_buscar"));
	         	        bFind.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_buscar"));
	         	        
	         	        menuEdit.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_edit"));
	         	        itemRedo.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_redo"));
	         	        bRedo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_redo"));
	         	        itemUndo.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_undo"));
	         	        bUndo.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_undo"));
	         	        
	         	        itemCopy.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_copy"));
	         	        bCopy.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_copy"));
	         	        itemPaste.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_paste"));
	         	        bPaste.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_paste"));
	         	        itemCut.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cut"));
	         	        bCut.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cut"));
	         	        
	         	        menuParrafo.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_parrafo"));
	         	        itemLeftAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_left"));
	         	        bLeftAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_left"));
	         	        itemCenterAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_center"));
	         	        bCenterAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_center"));
	         	        itemRightAlign.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_right"));
	         	        bRightAlign.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_right"));
	         	        itemJustify.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_justify"));
	         	        bJustify.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_justify"));
	         	        
	         	        menuFormato.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_formato"));
	         	        itemBold.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_negrita"));
	         	        bBold.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_negrita"));
	         	        itemItalic.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cursiva"));
	         	        bItalic.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cursiva"));
	         	        itemUnderline.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_subrayado"));
	         	        bUnderline.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_subrayado"));
	         	        itemFontColor.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_fontcolor"));
	         	        bFontColor.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_fontcolor"));
	         	        itemDestacar.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_destacar"));
	         	        bDestacar.setToolTipText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_destacar"));
	         	        menuSkins.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_aspectos"));
	         	       inputText = (ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_inputText"));
	         	       search = (ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_search"));
	         	       
	         	      itemCopyPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_copy"));
	      		    itemCutPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cut"));
	      		    itemPastePop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_paste"));
	      		    itemBoldPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_negrita"));
	      		    itemItalicPop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_cursiva"));
	      		    itemUnderlinePop.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_subrayado"));
	         	        
	      		    	jTextFieldInferior.setHint(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_hint"));           	        menuLanguage.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menu_idioma"));
	           	        itemEnglish.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_english"));
	           	        itemEspanyol.setText(ResourceBundle.getBundle("paquetes.Etiquetas",Locale.US).getString("etiqueta_menuItem_espanyol"));
	                }
	            }            
	        };
	    }
	
	 
	 //Button Listener de las skins con su respectivo try catch
	 
	 private ActionListener skinsListener(){
	        return new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {    
	            	try{
	            	JFrame.setDefaultLookAndFeelDecorated(true);
	            	if(e.getSource()==itemNimbus) {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}
	            	if(e.getSource()==itemMetal) {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");;}
	            	if(e.getSource()==itemWindows) {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}}
	        
	            	catch (Exception o) {o.printStackTrace();}
	     
	            }
	        };
	    }

}