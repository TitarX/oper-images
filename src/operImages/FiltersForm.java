/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FiltersForm.java
 *
 * Created on 10.05.2010, 17:19:45
 */
package operImages;

import oiHelp.*;
import java.net.MalformedURLException;
import oiDialog.*;
import oiException.*;
import filters.*;
import oiSearch.SearchForm;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author TitarX
 */
public class FiltersForm extends JFrame implements WindowListener,ComponentListener,Runnable
{

    public Image img=null;
    private Image image=null;
    private Image imageOriginal=null;
    public String fileNameOpen=null;
    private String fileNameSave;
    private String suffixOpen;
    private String suffixSave;
    private String str;
    public String fileURL=null;
    private String nameFilter="";
    private int widthImg;
    private int heightImg;
    private int indexSuffix;
    private int countSharpness=0;
    private int indexCollectionMenuHistory=0;
    private int countMenuHistory=0;
    private boolean isRulers=false;
    private boolean isScroll=true;
    private boolean isTempFileFilterWrite=false;
    private boolean isTempFileDownloadWrite=false;
    private boolean isFileSave=true;
    private boolean isFileSaveAs=true;
    public boolean isImageLoaded=false;
    private JScrollPane sp;
    private ImageViewer iv;
    private MediaTracker mt;
    private JFileChooser jfch;
    private Ruler column;
    private Ruler row;
    private JToggleButton isMetric;
    private JPanel buttonCorner;
    private BufferedImage bufImg;
    public FiltersForm ff;
    private JDialog de=null;
    private InterfaceForFilters iff;
    private File tempFileFilter=null;
    private File tempFileDownload=null;
    private FileNameExtensionFilter allFilesImages;
    private URL url;
    private JMenuItem[] collectionMenuHistory=new JMenuItem[10];
    public Thread threadDownload=null;
    private SearchForm sf;
    private DialogHelp dh;

//    private boolean isImageDownload=true;
//    private File_Filter fileFilterPNG;
//    private File_Filter fileFilterGIF;
//    private File_Filter fileFilterJPG;
    /** Creates new form FiltersForm */
    public FiltersForm()
    {
        initComponents();
        textFieldCoord.setForeground(Color.black);
        textFieldCoord.setEditable(false);
        textFieldCondition.setForeground(Color.black);
        textFieldCondition.setEditable(false);
        //panel.setBackground(Color.white);

        this.addWindowListener(this);
        this.addComponentListener(this);

        Localization();
    }

    public FiltersForm(String str)
    {
        super(str);
        initComponents();
        textFieldCoord.setForeground(Color.black);
        textFieldCoord.setEditable(false);
        textFieldCondition.setForeground(Color.black);
        textFieldCondition.setEditable(false);
        //panel.setBackground(Color.white);

        this.addWindowListener(this);
        this.addComponentListener(this);

        Localization();
    }

    public void Localization()
    {
        jfch=new JFileChooser();
        UIManager.put("FileChooser.openDialogTitleText","Открыть изображение (JPG JPEG GIF PNG)");
        UIManager.put("FileChooser.saveDialogTitleText","Сохранить изображение как (JPG JPEG GIF PNG)");
        UIManager.put("FileChooser.lookInLabelText","Папка:");
        UIManager.put("FileChooser.saveInLabelText","Сохранить в:");
        UIManager.put("FileChooser.openButtonText","Открыть");
        UIManager.put("FileChooser.openButtonToolTipText","Открыть выбранное изображение");
        UIManager.put("FileChooser.saveButtonText","Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText","Сохранить текущее изображение в файл");
        UIManager.put("FileChooser.cancelButtonText","Отмена");
        UIManager.put("FileChooser.filesOfTypeLabelText","Тип файла:");
        UIManager.put("FileChooser.fileNameLabelText","Имя файла:");
        UIManager.put("FileChooser.acceptAllFileFilterText","Все файлы");
        UIManager.put("FileChooser.upFolderToolTipText","Вверх");
        UIManager.put("FileChooser.homeFolderToolTipText","Рабочий стол");
        UIManager.put("FileChooser.newFolderToolTipText","Создать новую папку");
        UIManager.put("FileChooser.detailsViewButtonToolTipText","Таблица");
        UIManager.put("FileChooser.listViewButtonToolTipText","Список");
        UIManager.put("FileChooser.cancelButtonToolTipText","Закрыть диалог");
        jfch.updateUI();
        jfch.setFileFilter(new FileNameExtensionFilter("JPG (*.jpg, *.jpeg)","jpg","jpeg"));
        jfch.setFileFilter(new FileNameExtensionFilter("GIF (*.gif)","gif"));
        jfch.setFileFilter(new FileNameExtensionFilter("PNG (*.png)","png"));
        jfch.setFileFilter(allFilesImages=new FileNameExtensionFilter("Все файлы изображений (JPG, GIF, PNG)","jpg","jpeg","gif","png"));
        jfch.setAcceptAllFileFilterUsed(false);
        jfch.setFileFilter(allFilesImages);
//        fileFilterJPG=new File_Filter("JPG (*.jpg)","jpg");
//        fileFilterGIF=new File_Filter("GIF (*.gif)","gif");
//        fileFilterPNG=new File_Filter("PNG (*.png)","png");
//        jfch.addChoosableFileFilter(fileFilterJPG);
//        jfch.addChoosableFileFilter(fileFilterGIF);
//        jfch.addChoosableFileFilter(fileFilterPNG);
//        jfch.setAcceptAllFileFilterUsed(true);
        jfch.setAccessory(new ImagePreview(jfch));

        UIManager.put("OptionPane.yesButtonText","Да");
        UIManager.put("OptionPane.noButtonText","Нет");
        UIManager.put("OptionPane.cancelButtonText","Отмена");
        UIManager.put("OptionPane.okButtonText","Закрыть");
    }

    public void setFF(FiltersForm ff)
    {
        this.ff=ff;
    }

    public void setSizeOperImages()
    {
        if(!isRulers&&!isScroll)
        {
            setSize(widthImg+11,heightImg+85);
            setLocationRelativeTo(null);
        }
        if(!isRulers&&isScroll)
        {
            setSize(widthImg+26,heightImg+100);
            setLocationRelativeTo(null);
        }
        if(isRulers&&!isScroll)
        {
            setSize(widthImg+46,heightImg+120);
            setLocationRelativeTo(null);
        }
        if(isRulers&&isScroll)
        {
            setSize(widthImg+61,heightImg+135);
            setLocationRelativeTo(null);
        }
    }

    public void setFullScreenOperImages()
    {
        int w=640;
        int h=480;

        if((Toolkit.getDefaultToolkit().getScreenSize().width)<(widthImg+60+50))
        {
            w=Toolkit.getDefaultToolkit().getScreenSize().width-50;
        }else
        {
            if(!isRulers&&!isScroll)
            {
                w=widthImg+11;
            }
            if(!isRulers&&isScroll)
            {
                w=widthImg+26;
            }
            if(isRulers&&!isScroll)
            {
                w=widthImg+46;
            }
            if(isRulers&&isScroll)
            {
                w=widthImg+61;
            }
        }
        if((Toolkit.getDefaultToolkit().getScreenSize().height)<(heightImg+135+50))
        {
            h=Toolkit.getDefaultToolkit().getScreenSize().height-50;
        }else
        {
            if(!isRulers&&!isScroll)
            {
                h=heightImg+85;
            }
            if(!isRulers&&isScroll)
            {
                h=heightImg+100;
            }
            if(isRulers&&!isScroll)
            {
                h=heightImg+120;
            }
            if(isRulers&&isScroll)
            {
                h=heightImg+135;
            }
        }

        setSize(w,h);
        setLocationRelativeTo(null);
    }

    public void Exit()
    {
        if((!isFileSave)&&(fileNameOpen!=null))
        {
            switch(JOptionPane.showConfirmDialog(null,"Сохранить изменение в файле?\n"+fileNameOpen,"Файл изменён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    Save();
                    break;
                case 2:
                    return;
            }
        }
        if(!isFileSaveAs)
        {
            switch(JOptionPane.showConfirmDialog(null,"Хотите сохранить загруженное изображение в файл?","Файл не сохранён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    SaveAs();
                    break;
                case 2:
                    return;
            }
        }
        if(tempFileFilter!=null)
        {
            tempFileFilter.delete();
        }
        if(tempFileDownload!=null)
        {
            tempFileDownload.delete();
        }
        System.exit(0);
    }

    public void Open()
    {
        addInHistory();
        if((!isFileSave)&&(fileNameOpen!=null))
        {
            switch(JOptionPane.showConfirmDialog(null,"Сохранить изменение в файле?\n"+fileNameOpen,"Файл изменён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    Save();
                    break;
                case 1:
                    isFileSave=true;
                    break;
                case 2:
                    return;
            }
        }
        if(!isFileSaveAs)
        {
            switch(JOptionPane.showConfirmDialog(null,"Хотите сохранить загруженный файл?","Файл не сохранён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    SaveAs();
                    break;
                case 1:
                    isFileSaveAs=true;
                    break;
                case 2:
                    return;
            }
        }

        de=null;
        if(jfch.showOpenDialog(this)==jfch.APPROVE_OPTION)
        {
            fileNameOpen=jfch.getSelectedFile().getPath();
            try
            {
                suffixOpen=getSuffix(fileNameOpen);
            }catch(UnknownFormatException ufe)
            {
                de=new DialogErrorOpenFile(this,"Произошла ошибка",true,ufe.getMessage(),ff);
                de.setSize(380,120);
                de.setLocationRelativeTo(null);
                de.setVisible(true);
            }catch(NotSupportedFormatException nsfe)
            {
                de=new DialogErrorOpenFile(this,"Произошла ошибка",true,nsfe.getMessage(),ff);
                de.setSize(380,120);
                de.setLocationRelativeTo(null);
                de.setVisible(true);
            }finally
            {
                //
            }
        }else
        {
            return;
        }

        getImage();
    }

    public void getImage()
    {
        img=Toolkit.getDefaultToolkit().getImage(fileNameOpen);
        try
        {
            mt=new MediaTracker(this);
            mt.addImage(img,0);
            mt.waitForAll();
            isImageLoaded=true;
        }catch(InterruptedException ex)
        {
            if(img!=null)
            {
                isImageLoaded=true;
                showView();
            }else
            {
                clearingPanel();
            }
        }
        showImage(false);
    }

    public void Download()
    {
        addInHistory();
        if((!isFileSave)&&(fileNameOpen!=null))
        {
            switch(JOptionPane.showConfirmDialog(null,"Сохранить изменение в файле?\n"+fileNameOpen,"Файл изменён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    Save();
                    break;
                case 1:
                    isFileSave=true;
                    break;
                case 2:
                    return;
            }
        }
        if(!isFileSaveAs)
        {
            switch(JOptionPane.showConfirmDialog(null,"Хотите сохранить загруженный файл?","Файл не сохранён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    SaveAs();
                    break;
                case 1:
                    isFileSaveAs=true;
                    break;
                case 2:
                    return;
            }
        }

        if(threadDownload!=null&&threadDownload.isAlive())
        {
            threadDownload.stop();
            threadDownload=null;
            threadDownload=new Thread(this,"Поток визуализации процесса загрузки");
            threadDownload.start();
        }else
        {
            threadDownload=new Thread(this,"Поток визуализации процесса загрузки");
            threadDownload.start();
        }
        if(threadDownload!=null&&threadDownload.isAlive())
        {
            menuFile.setEnabled(false);
            menuFilters.setEnabled(false);
            menuKind.setEnabled(false);
            menuHistory.setEnabled(false);
            menuHelp.setEnabled(false);
        }
    }

    public void setEnabledMenu()
    {
        menuFile.setEnabled(true);
        menuFilters.setEnabled(true);
        menuKind.setEnabled(true);
        if(menuHistory.isMenuComponent(collectionMenuHistory[0]))
        {
            menuHistory.setEnabled(true);
        }
        menuHelp.setEnabled(true);
    }

    public void showImage(boolean b)
    {
        if(de==null)
        {
            try
            {
                if(img.getHeight(null)==-1)
                {
                    throw new NoFileException("Файл не найден");
                }else
                {
                    nameFilter="";
                    image=null;
                    imageOriginal=img;
                    if(b)
                    {
                        fileNameOpen=null;
                        textFieldCondition.setText("");
                    }else
                    {
                        textFieldCondition.setText(fileNameOpen);
                    }
                    widthImg=img.getWidth(this);
                    heightImg=img.getHeight(this);
                    createColumnRowCorners();
                    isTempFileFilterWrite=true;
                    countSharpness=0;
                    if(((Toolkit.getDefaultToolkit().getScreenSize().width)>(widthImg+60+50))&&((Toolkit.getDefaultToolkit().getScreenSize().height)>(heightImg+135+50)))
                    {
                        setSizeOperImages();
                    }else
                    {
                        setFullScreenOperImages();
                    }
                    if(b)
                    {
                        isFileSaveAs=false;
                    }
                    showView();
                }
            }catch(NoFileException nfe)
            {
                if(b)
                {
                    img=null;
                    de=new DialogErrorDownloadFile(this,"Произошла ошибка",true,nfe.getMessage(),ff);
                    de.setSize(380,120);
                    de.setLocationRelativeTo(null);
                    de.setVisible(true);
                }else
                {
                    de=new DialogErrorOpenFile(this,"Произошла ошибка",true,nfe.getMessage(),ff);
                    de.setSize(380,120);
                    de.setLocationRelativeTo(null);
                    de.setVisible(true);
                    img=null;
                }
            }finally
            {
                //
            }
        }else
        {
            img=null;
        }
    }

    public void createColumnRowCorners()
    {
        column=new Ruler(Ruler.HORIZONTAL,true,img.getWidth(this));
        column.setPreferredWidth(img.getWidth(this));
        row=new Ruler(Ruler.VERTICAL,true,img.getHeight(this));
        row.setPreferredHeight(img.getHeight(this));

        buttonCorner=new JPanel();
        isMetric=new JToggleButton("дм",true);
        isMetric.setFont(new Font("SansSerif",Font.PLAIN,11));
        isMetric.setMargin(new Insets(2,2,2,2));
        isMetric.addItemListener(new ItemListener()
        {

            public void itemStateChanged(ItemEvent e)
            {
                if(e.getStateChange()==ItemEvent.SELECTED)
                {
                    row.setIsMetric(true);
                    column.setIsMetric(true);
                    isMetric.setText("дм");
                }else
                {
                    row.setIsMetric(false);
                    column.setIsMetric(false);
                    isMetric.setText("см");
                }
            }
        });

        buttonCorner.add(isMetric);
    }

    public void setColumnRowCorners()
    {
        sp.setColumnHeaderView(column);
        sp.setRowHeaderView(row);

        sp.setCorner(JScrollPane.UPPER_LEFT_CORNER,buttonCorner);
        sp.setCorner(JScrollPane.LOWER_LEFT_CORNER,new Corner());
        sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER,new Corner());
    }

    public void showView()
    {
        panel.removeAll();
        panel.repaint();

        sp=new JScrollPane();
        if(isScroll)
        {
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }else
        {
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }
        sp.setSize(panel.getSize());

        if(isRulers)
        {
            setColumnRowCorners();
        }

        panel.setLayout(new BorderLayout());
        panel.add("Center",sp);
        if(isImageLoaded)
        {
            iv=new ImageViewer(isImageLoaded,img,new Dimension(img.getWidth(this),img.getHeight(this)));
        }else
        {
            iv=new ImageViewer(isImageLoaded,new Dimension(panel.getWidth(),panel.getHeight()));
        }
        iv.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {

            public void mouseMoved(java.awt.event.MouseEvent evt)
            {
                panelMouseMoved(evt);
            }
        });
        sp.add(iv);
        //sp.doLayout();
        sp.setViewportView(iv);
        //iv.setBorder(BorderFactory.createLineBorder(Color.black));
        if(isImageLoaded)
        {
            if(threadDownload!=null&&threadDownload.isAlive())
            {
                setEnabledMenu();
                threadDownload.stop();
                threadDownload=null;
            }
        }
        panel.repaint();
    }

    public void clearingPanel()
    {
        panel.removeAll();
        panel.repaint();
    }

    public void Save()
    {
        if(img!=null)
        {
            try
            {
                bufImg=new BufferedImage(widthImg,heightImg,BufferedImage.TYPE_INT_RGB);
                bufImg.getGraphics().drawImage(img,0,0,null);
                ImageIO.write(bufImg,suffixOpen,new File(fileNameOpen));
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Не удается сохранить файл","Произошла ошибка",JOptionPane.ERROR_MESSAGE);
            }
            isFileSave=true;
        }
    }

    public void Save(String s1,String s2)
    {
        if(img!=null)
        {
            try
            {
                bufImg=new BufferedImage(widthImg,heightImg,BufferedImage.TYPE_INT_RGB);
                bufImg.getGraphics().drawImage(img,0,0,null);
                ImageIO.write(bufImg,s2,new File(s1));
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Не удается сохранить файл","Произошла ошибка",JOptionPane.ERROR_MESSAGE);
            }
            textFieldCondition.setText(s1);
            fileNameOpen=s1;
            isFileSave=true;
            isFileSaveAs=true;
        }
    }

    public String getSuffix(String fullName) throws UnknownFormatException,NotSupportedFormatException
    {
        indexSuffix=fullName.lastIndexOf(".");
        if(indexSuffix==-1)
        {
            throw new UnknownFormatException("Неизвестный формат файла");
        }
        str=fullName.substring(indexSuffix+1);
        if(str.equalsIgnoreCase("jpg")||str.equalsIgnoreCase("jpeg")||str.equalsIgnoreCase("gif")||str.equalsIgnoreCase("png"))
        {
        }else
        {
            throw new NotSupportedFormatException("Формат файла не поддерживается");
        }

        return str;
    }

    public void SaveAs()
    {
        if(img!=null)
        {
            if(jfch.showSaveDialog(this)==jfch.APPROVE_OPTION)
            {
                File f=new File(jfch.getSelectedFile().getParent());
                try
                {
                    if(!f.isDirectory())
                    {
                        throw new NoDirectoryException("Не удается найти указанный путь");
                    }else
                    {
                        fileNameSave=jfch.getSelectedFile().getPath();
                    }
                }catch(NoDirectoryException nde)
                {
                    JOptionPane.showMessageDialog(null,"Не удается найти указанный путь","Произошла ошибка",JOptionPane.ERROR_MESSAGE);
                    return;
                }finally
                {
                    //
                }
            }else
            {
                return;
            }
            indexSuffix=fileNameSave.lastIndexOf(".");
            if(indexSuffix!=-1)
            {
                suffixSave=fileNameSave.substring(indexSuffix+1);
                if(suffixSave.equalsIgnoreCase("jpg")||suffixSave.equalsIgnoreCase("jpeg")||suffixSave.equalsIgnoreCase("gif")||suffixSave.equalsIgnoreCase("png"))
                {
                    Save(fileNameSave,suffixSave);
                }else
                {
                    if(jfch.getFileFilter().getDescription()!="Все файлы изображений (JPG, GIF, PNG)")
                    {
                        Save(fileNameSave+"."+jfch.getFileFilter().getDescription().substring(7,10),jfch.getFileFilter().getDescription().substring(7,10));
                    }else
                    {
                        Save(fileNameSave+"."+suffixOpen,suffixOpen);
                    }
                }
            }else
            {
                if(jfch.getFileFilter().getDescription()!="Все файлы изображений (JPG, GIF, PNG)")
                {
                    Save(fileNameSave+"."+jfch.getFileFilter().getDescription().substring(7,10),jfch.getFileFilter().getDescription().substring(7,10));
                }else
                {
                    Save(fileNameSave+"."+suffixOpen,suffixOpen);
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        toolBarCoord = new javax.swing.JToolBar();
        textFieldCondition = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        textFieldCoord = new javax.swing.JTextField();
        panelMenu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuFileOpen = new javax.swing.JMenuItem();
        menuFileDownload = new javax.swing.JMenuItem();
        menuFileSave = new javax.swing.JMenuItem();
        menuFileSaveAs = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuFileSearch = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuFileExit = new javax.swing.JMenuItem();
        menuKind = new javax.swing.JMenu();
        menuKindRoler = new javax.swing.JMenuItem();
        menuKindScroll = new javax.swing.JMenuItem();
        menuFilters = new javax.swing.JMenu();
        menuFiltersBrightness = new javax.swing.JMenuItem();
        menuFiltersContrast = new javax.swing.JMenuItem();
        menuFiltersSharpness = new javax.swing.JMenuItem();
        menuFilterBlur = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        filterGray = new javax.swing.JMenuItem();
        filterNegative = new javax.swing.JMenuItem();
        menuExclude = new javax.swing.JMenu();
        filterNoRedFilter = new javax.swing.JMenuItem();
        filterNoGreenFilter = new javax.swing.JMenuItem();
        filterNoBlueFilter = new javax.swing.JMenuItem();
        menuInvert = new javax.swing.JMenu();
        menuInvertRed_Blue = new javax.swing.JMenuItem();
        menuInvertGreen_Red = new javax.swing.JMenuItem();
        menuInvertBlue_Green = new javax.swing.JMenuItem();
        filterGray_filterReset = new javax.swing.JPopupMenu.Separator();
        filterRecoil = new javax.swing.JMenuItem();
        filterReset = new javax.swing.JMenuItem();
        menuHistory = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        menuHelpHelp = new javax.swing.JMenuItem();
        menuHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        toolBarCoord.setFloatable(false);
        toolBarCoord.setRollover(true);

        textFieldCondition.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        textFieldCondition.setMinimumSize(new java.awt.Dimension(6, 25));
        textFieldCondition.setPreferredSize(new java.awt.Dimension(6, 25));
        toolBarCoord.add(textFieldCondition);
        toolBarCoord.add(jSeparator2);

        textFieldCoord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldCoord.setMaximumSize(new java.awt.Dimension(100, 25));
        textFieldCoord.setMinimumSize(new java.awt.Dimension(100, 25));
        textFieldCoord.setPreferredSize(new java.awt.Dimension(100, 25));
        toolBarCoord.add(textFieldCoord);

        menuFile.setText("Файл");
        menuFile.setMaximumSize(new java.awt.Dimension(61, 19));
        menuFile.setMinimumSize(new java.awt.Dimension(61, 19));
        menuFile.setPreferredSize(new java.awt.Dimension(61, 19));

        menuFileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuFileOpen.setText("Открыть");
        menuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileOpenActionPerformed(evt);
            }
        });
        menuFile.add(menuFileOpen);

        menuFileDownload.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        menuFileDownload.setText("Загрузить");
        menuFileDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileDownloadActionPerformed(evt);
            }
        });
        menuFile.add(menuFileDownload);

        menuFileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuFileSave.setText("Сохранить");
        menuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileSaveActionPerformed(evt);
            }
        });
        menuFile.add(menuFileSave);

        menuFileSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuFileSaveAs.setText("Сохранить как");
        menuFileSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileSaveAsActionPerformed(evt);
            }
        });
        menuFile.add(menuFileSaveAs);
        menuFile.add(jSeparator3);

        menuFileSearch.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuFileSearch.setText("Поиск");
        menuFileSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileSearchActionPerformed(evt);
            }
        });
        menuFile.add(menuFileSearch);
        menuFile.add(jSeparator1);

        menuFileExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        menuFileExit.setText("Выход");
        menuFileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileExitActionPerformed(evt);
            }
        });
        menuFile.add(menuFileExit);

        panelMenu.add(menuFile);

        menuKind.setText("Вид");
        menuKind.setMaximumSize(new java.awt.Dimension(51, 19));
        menuKind.setMinimumSize(new java.awt.Dimension(51, 19));
        menuKind.setPreferredSize(new java.awt.Dimension(51, 19));

        menuKindRoler.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menuKindRoler.setText("Линейки");
        menuKindRoler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKindRolerActionPerformed(evt);
            }
        });
        menuKind.add(menuKindRoler);

        menuKindScroll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menuKindScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/apply.gif"))); // NOI18N
        menuKindScroll.setText("Полосы скроллинга");
        menuKindScroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKindScrollActionPerformed(evt);
            }
        });
        menuKind.add(menuKindScroll);

        panelMenu.add(menuKind);

        menuFilters.setText("Фильтры");
        menuFilters.setMaximumSize(new java.awt.Dimension(81, 19));
        menuFilters.setMinimumSize(new java.awt.Dimension(81, 19));

        menuFiltersBrightness.setText("Яркость");
        menuFiltersBrightness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFiltersBrightnessActionPerformed(evt);
            }
        });
        menuFilters.add(menuFiltersBrightness);

        menuFiltersContrast.setText("Контраст");
        menuFiltersContrast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFiltersContrastActionPerformed(evt);
            }
        });
        menuFilters.add(menuFiltersContrast);

        menuFiltersSharpness.setText("Резкость");
        menuFiltersSharpness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFiltersSharpnessActionPerformed(evt);
            }
        });
        menuFilters.add(menuFiltersSharpness);

        menuFilterBlur.setText("Размытие");
        menuFilterBlur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFilterBlurActionPerformed(evt);
            }
        });
        menuFilters.add(menuFilterBlur);
        menuFilters.add(jSeparator4);

        filterGray.setText("Чёрно-белое");
        filterGray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterGrayActionPerformed(evt);
            }
        });
        menuFilters.add(filterGray);

        filterNegative.setText("Негатив");
        filterNegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNegativeActionPerformed(evt);
            }
        });
        menuFilters.add(filterNegative);

        menuExclude.setText("Исключить");

        filterNoRedFilter.setText("Красный");
        filterNoRedFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNoRedFilterActionPerformed(evt);
            }
        });
        menuExclude.add(filterNoRedFilter);

        filterNoGreenFilter.setText("Зелёный");
        filterNoGreenFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNoGreenFilterActionPerformed(evt);
            }
        });
        menuExclude.add(filterNoGreenFilter);

        filterNoBlueFilter.setText("Синий");
        filterNoBlueFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterNoBlueFilterActionPerformed(evt);
            }
        });
        menuExclude.add(filterNoBlueFilter);

        menuFilters.add(menuExclude);

        menuInvert.setText("Инвертировать");

        menuInvertRed_Blue.setText("Красный <-> Синий");
        menuInvertRed_Blue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInvertRed_BlueActionPerformed(evt);
            }
        });
        menuInvert.add(menuInvertRed_Blue);

        menuInvertGreen_Red.setText("Зелёный <-> Красный");
        menuInvertGreen_Red.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInvertGreen_RedActionPerformed(evt);
            }
        });
        menuInvert.add(menuInvertGreen_Red);

        menuInvertBlue_Green.setText("Синий <-> Зелёный");
        menuInvertBlue_Green.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInvertBlue_GreenActionPerformed(evt);
            }
        });
        menuInvert.add(menuInvertBlue_Green);

        menuFilters.add(menuInvert);
        menuFilters.add(filterGray_filterReset);

        filterRecoil.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        filterRecoil.setText("Отмена");
        filterRecoil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterRecoilActionPerformed(evt);
            }
        });
        menuFilters.add(filterRecoil);

        filterReset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        filterReset.setText("Сброс");
        filterReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterResetActionPerformed(evt);
            }
        });
        menuFilters.add(filterReset);

        panelMenu.add(menuFilters);

        menuHistory.setText("История");
        menuHistory.setEnabled(false);
        menuHistory.setMaximumSize(new java.awt.Dimension(75, 19));
        menuHistory.setMinimumSize(new java.awt.Dimension(75, 19));
        menuHistory.setPreferredSize(new java.awt.Dimension(75, 19));
        panelMenu.add(menuHistory);

        menuHelp.setText("Справка");
        menuHelp.setMaximumSize(new java.awt.Dimension(75, 19));
        menuHelp.setMinimumSize(new java.awt.Dimension(75, 19));
        menuHelp.setPreferredSize(new java.awt.Dimension(75, 19));

        menuHelpHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuHelpHelp.setText("Вызов справки");
        menuHelpHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHelpHelpActionPerformed(evt);
            }
        });
        menuHelp.add(menuHelpHelp);

        menuHelpAbout.setText("О программе");
        menuHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHelpAboutActionPerformed(evt);
            }
        });
        menuHelp.add(menuHelpAbout);

        panelMenu.add(menuHelp);

        setJMenuBar(panelMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(toolBarCoord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolBarCoord, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuFileExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileExitActionPerformed
    {//GEN-HEADEREND:event_menuFileExitActionPerformed
        Exit();
    }//GEN-LAST:event_menuFileExitActionPerformed

    private void menuFileOpenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileOpenActionPerformed
    {//GEN-HEADEREND:event_menuFileOpenActionPerformed
        Open();
    }//GEN-LAST:event_menuFileOpenActionPerformed

    private void menuKindRolerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuKindRolerActionPerformed
    {//GEN-HEADEREND:event_menuKindRolerActionPerformed
        if(isRulers)
        {
            isRulers=false;
            menuKindRoler.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        }else
        {
            isRulers=true;
            menuKindRoler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/apply.gif")));
        }

        if(img!=null)
        {
            showView();
        }
    }//GEN-LAST:event_menuKindRolerActionPerformed

    private void menuFileSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileSaveActionPerformed
    {//GEN-HEADEREND:event_menuFileSaveActionPerformed
        Save();
    }//GEN-LAST:event_menuFileSaveActionPerformed

    private void menuFileSaveAsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileSaveAsActionPerformed
    {//GEN-HEADEREND:event_menuFileSaveAsActionPerformed
        SaveAs();
    }//GEN-LAST:event_menuFileSaveAsActionPerformed

    private void filterGrayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterGrayActionPerformed
    {//GEN-HEADEREND:event_filterGrayActionPerformed
        applyFilters("filters.GrayFilter");
    }//GEN-LAST:event_filterGrayActionPerformed

    private void filterResetActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterResetActionPerformed
    {//GEN-HEADEREND:event_filterResetActionPerformed
        if(image!=null)
        {
            nameFilter="";
            img=imageOriginal;
            showView();
            image=null;
            isFileSave=true;
            countSharpness=0;
        }
    }//GEN-LAST:event_filterResetActionPerformed

    private void filterNegativeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterNegativeActionPerformed
    {//GEN-HEADEREND:event_filterNegativeActionPerformed
        applyFilters("filters.InvertFilter");
    }//GEN-LAST:event_filterNegativeActionPerformed

    private void menuHelpAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuHelpAboutActionPerformed
    {//GEN-HEADEREND:event_menuHelpAboutActionPerformed
        JOptionPane.showMessageDialog(null,"Программа: OperImages\nВерсия: 1.1\n\nАвтор: Дмитрий Игнатенко\nhttp://www.dmitriy-ignatenko.ru/\n\n2010 Aptech Саратов","OperImages",JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_menuHelpAboutActionPerformed

    private void menuFileDownloadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileDownloadActionPerformed
    {//GEN-HEADEREND:event_menuFileDownloadActionPerformed
        Download();
    }//GEN-LAST:event_menuFileDownloadActionPerformed

    private void menuFiltersBrightnessActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFiltersBrightnessActionPerformed
    {//GEN-HEADEREND:event_menuFiltersBrightnessActionPerformed
        applyFilters("filters.BrightnessUpFilter");
    }//GEN-LAST:event_menuFiltersBrightnessActionPerformed

    private void menuFiltersContrastActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFiltersContrastActionPerformed
    {//GEN-HEADEREND:event_menuFiltersContrastActionPerformed
        applyFilters("filters.ContrastUpFilter");
    }//GEN-LAST:event_menuFiltersContrastActionPerformed

    private void menuFiltersSharpnessActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFiltersSharpnessActionPerformed
    {//GEN-HEADEREND:event_menuFiltersSharpnessActionPerformed
        if(isTempFileFilterWrite)
        {
            countSharpness++;
            applyFilters("filters.SharpnessUpFilter");
        }
    }//GEN-LAST:event_menuFiltersSharpnessActionPerformed

    private void menuKindScrollActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuKindScrollActionPerformed
    {//GEN-HEADEREND:event_menuKindScrollActionPerformed
        if(isScroll)
        {
            isScroll=false;
            menuKindScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
        }else
        {
            isScroll=true;
            menuKindScroll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/apply.gif")));
        }

        if(img!=null)
        {
            showView();
        }
    }//GEN-LAST:event_menuKindScrollActionPerformed

    private void filterRecoilActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterRecoilActionPerformed
    {//GEN-HEADEREND:event_filterRecoilActionPerformed
        if(image!=null)
        {
            img=image;
            writeTempFileFilter();
            showView();
            nameFilter="";
            countSharpness--;
        }
    }//GEN-LAST:event_filterRecoilActionPerformed

    private void menuHelpHelpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuHelpHelpActionPerformed
    {//GEN-HEADEREND:event_menuHelpHelpActionPerformed
        dh=new DialogHelp(this,"Справка OperImages",false);
        dh.setSize(680,640);
        dh.setLocationRelativeTo(null);
        dh.setVisible(true);
    }//GEN-LAST:event_menuHelpHelpActionPerformed

    private void menuFilterBlurActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFilterBlurActionPerformed
    {//GEN-HEADEREND:event_menuFilterBlurActionPerformed
        if(isTempFileFilterWrite)
        {
            countSharpness++;
            applyFilters("filters.SharpnessDownFilter");
        }
    }//GEN-LAST:event_menuFilterBlurActionPerformed

    private void menuFileSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuFileSearchActionPerformed
    {//GEN-HEADEREND:event_menuFileSearchActionPerformed
        Search();
    }//GEN-LAST:event_menuFileSearchActionPerformed

    private void filterNoRedFilterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterNoRedFilterActionPerformed
    {//GEN-HEADEREND:event_filterNoRedFilterActionPerformed
        applyFilters("filters.NoRedFilter");
    }//GEN-LAST:event_filterNoRedFilterActionPerformed

    private void filterNoGreenFilterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterNoGreenFilterActionPerformed
    {//GEN-HEADEREND:event_filterNoGreenFilterActionPerformed
        applyFilters("filters.NoGreenFilter");
    }//GEN-LAST:event_filterNoGreenFilterActionPerformed

    private void filterNoBlueFilterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_filterNoBlueFilterActionPerformed
    {//GEN-HEADEREND:event_filterNoBlueFilterActionPerformed
        applyFilters("filters.NoBlueFilter");
    }//GEN-LAST:event_filterNoBlueFilterActionPerformed

    private void menuInvertRed_BlueActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuInvertRed_BlueActionPerformed
    {//GEN-HEADEREND:event_menuInvertRed_BlueActionPerformed
        applyFilters("filters.Red_BlueFilter");
    }//GEN-LAST:event_menuInvertRed_BlueActionPerformed

    private void menuInvertGreen_RedActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuInvertGreen_RedActionPerformed
    {//GEN-HEADEREND:event_menuInvertGreen_RedActionPerformed
        applyFilters("filters.Green_RedFilter");
    }//GEN-LAST:event_menuInvertGreen_RedActionPerformed

    private void menuInvertBlue_GreenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_menuInvertBlue_GreenActionPerformed
    {//GEN-HEADEREND:event_menuInvertBlue_GreenActionPerformed
        applyFilters("filters.Blue_GreenFilter");
    }//GEN-LAST:event_menuInvertBlue_GreenActionPerformed
//
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem filterGray;
    private javax.swing.JPopupMenu.Separator filterGray_filterReset;
    private javax.swing.JMenuItem filterNegative;
    private javax.swing.JMenuItem filterNoBlueFilter;
    private javax.swing.JMenuItem filterNoGreenFilter;
    private javax.swing.JMenuItem filterNoRedFilter;
    private javax.swing.JMenuItem filterRecoil;
    private javax.swing.JMenuItem filterReset;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenu menuExclude;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileDownload;
    private javax.swing.JMenuItem menuFileExit;
    private javax.swing.JMenuItem menuFileOpen;
    private javax.swing.JMenuItem menuFileSave;
    private javax.swing.JMenuItem menuFileSaveAs;
    private javax.swing.JMenuItem menuFileSearch;
    private javax.swing.JMenuItem menuFilterBlur;
    private javax.swing.JMenu menuFilters;
    private javax.swing.JMenuItem menuFiltersBrightness;
    private javax.swing.JMenuItem menuFiltersContrast;
    private javax.swing.JMenuItem menuFiltersSharpness;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuHelpAbout;
    private javax.swing.JMenuItem menuHelpHelp;
    private javax.swing.JMenu menuHistory;
    private javax.swing.JMenu menuInvert;
    private javax.swing.JMenuItem menuInvertBlue_Green;
    private javax.swing.JMenuItem menuInvertGreen_Red;
    private javax.swing.JMenuItem menuInvertRed_Blue;
    private javax.swing.JMenu menuKind;
    private javax.swing.JMenuItem menuKindRoler;
    private javax.swing.JMenuItem menuKindScroll;
    private javax.swing.JPanel panel;
    private javax.swing.JMenuBar panelMenu;
    private javax.swing.JTextField textFieldCondition;
    private javax.swing.JTextField textFieldCoord;
    private javax.swing.JToolBar toolBarCoord;
    // End of variables declaration//GEN-END:variables

    public void windowOpened(WindowEvent e)
    {
    }

    public void windowClosing(WindowEvent e)
    {
        Exit();
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowDeactivated(WindowEvent e)
    {
    }

    public void componentResized(ComponentEvent e)
    {
        if(img!=null)
        {
            showView();
        }
    }

    public void componentMoved(ComponentEvent e)
    {
    }

    public void componentShown(ComponentEvent e)
    {
        if(img!=null)
        {
            showView();
        }
    }

    public void componentHidden(ComponentEvent e)
    {
    }

    private void panelMouseMoved(java.awt.event.MouseEvent evt)
    {
        if(isImageLoaded)
        {
            if((evt.getX()<widthImg)&&(evt.getY()<heightImg))
            {
                textFieldCoord.setText((evt.getX()+1)+" , "+(evt.getY()+1));
            }
        }else
        {
            textFieldCoord.setText("");

        }
    }

    public void applyFilters(String n)
    {
        if(img!=null)
        {
            try
            {

                image=img;
                iff=(InterfaceForFilters)Class.forName(n).newInstance();
                img=iff.applyFilter(image);
                nameFilter=n;
                if(isFileSaveAs)
                {
                    isFileSave=false;
                }
                writeTempFileFilter();
                showView();
            }catch(InstantiationException ex)
            {
                Logger.getLogger(FiltersForm.class.getName()).log(Level.SEVERE,null,ex);
            }catch(IllegalAccessException ex)
            {
                Logger.getLogger(FiltersForm.class.getName()).log(Level.SEVERE,null,ex);
            }catch(ClassNotFoundException ex)
            {
                Logger.getLogger(FiltersForm.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }

    public void writeTempFileFilter()
    {
        if((nameFilter.equals("filters.SharpnessUpFilter")||nameFilter.equals("filters.SharpnessDownFilter"))&&(countSharpness>0))
        {
            if(tempFileFilter!=null)
            {
                tempFileFilter.delete();
                tempFileFilter=null;
            }
            try
            {
                bufImg=new BufferedImage(widthImg,heightImg,BufferedImage.TYPE_INT_RGB);
                bufImg.getGraphics().drawImage(img,0,0,null);
                ImageIO.write(bufImg,suffixOpen,tempFileFilter=File.createTempFile("tff",".tmp",new File(System.getProperty("java.io.tmpdir","."))));
            }catch(Exception e)
            {
                isTempFileFilterWrite=false;
            }

            if(isTempFileFilterWrite)
            {
                img=Toolkit.getDefaultToolkit().getImage(tempFileFilter.getPath());
                try
                {
                    mt=new MediaTracker(this);
                    mt.addImage(img,0);
                    mt.waitForAll();
                }catch(InterruptedException ex)
                {
                    Logger.getLogger(FiltersForm.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    public void addInHistory()
    {
        if((img!=null)&&(fileNameOpen!=null))
        {

            ///
            for(int i=0;i<indexCollectionMenuHistory;i++)
            {
                if(fileNameOpen.equals(collectionMenuHistory[i].getText()))
                {
                    menuHistory.remove(collectionMenuHistory[i]);
                    for(;i<collectionMenuHistory.length-1;i++)
                    {
                        collectionMenuHistory[i]=collectionMenuHistory[i+1];
                    }
                    if(countMenuHistory>9)
                    {
                        countMenuHistory=9;
                    }else
                    {
                        indexCollectionMenuHistory--;
                        countMenuHistory--;
                    }
                    break;
                }
            }
            ///

            if(countMenuHistory>9)
            {
                menuHistory.remove(collectionMenuHistory[0]);
                for(int i=0;i<(collectionMenuHistory.length-1);i++)
                {
                    collectionMenuHistory[i]=collectionMenuHistory[i+1];
                }
            }

            menuHistory.setEnabled(true);
            collectionMenuHistory[indexCollectionMenuHistory]=new JMenuItem(fileNameOpen);
            collectionMenuHistory[indexCollectionMenuHistory].addActionListener(new java.awt.event.ActionListener()
            {

                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    addInHistory();
                    fileNameOpen=evt.getActionCommand();
                    getImage();
                }
            });
            menuHistory.add(collectionMenuHistory[indexCollectionMenuHistory],0);
            if(indexCollectionMenuHistory<9)
            {
                indexCollectionMenuHistory++;
            }
            countMenuHistory++;
        }
    }

    public void run()
    {
        isImageLoaded=false;
        showView();
        fileURL=null;
        de=null;
        DialogDownloadFile ddf;
        ddf=new DialogDownloadFile(this,"Загрузить изображение (JPG JPEG GIF PNG)",true,ff);
        ddf.setSize(380,160);
        ddf.setLocationRelativeTo(null);
        ddf.setVisible(true);
        if(fileURL!=null)
        {
            try
            {
                suffixOpen=getSuffix(fileURL);
            }catch(UnknownFormatException ufe)
            {
                de=new DialogErrorDownloadFile(this,"Произошла ошибка",true,ufe.getMessage(),ff);
                de.setSize(380,120);
                de.setLocationRelativeTo(null);
                de.setVisible(true);

                return;
            }catch(NotSupportedFormatException nsfe)
            {
                de=new DialogErrorDownloadFile(this,"Произошла ошибка",true,nsfe.getMessage(),ff);
                de.setSize(380,120);
                de.setLocationRelativeTo(null);
                de.setVisible(true);

                return;
            }finally
            {
                //
            }

            try
            {
                url=new URL(fileURL);
            }catch(MalformedURLException ex)
            {
                if(img!=null)
                {
                    isImageLoaded=true;
                    showView();
                }else
                {
                    clearingPanel();
                }
            }
            img=Toolkit.getDefaultToolkit().getImage(url);
            try
            {
                mt=new MediaTracker(this);
                mt.addImage(img,0);
                mt.waitForAll();
                isImageLoaded=true;
                isTempFileDownloadWrite=true;
            }catch(Exception e)
            {
                if(img!=null)
                {
                    isImageLoaded=true;
                    showView();
                }else
                {
                    clearingPanel();
                }
            }

            if(tempFileDownload!=null)
            {
                tempFileDownload.delete();
                tempFileDownload=null;
            }
            try
            {
                bufImg=new BufferedImage(img.getWidth(this),img.getHeight(this),BufferedImage.TYPE_INT_RGB);
                bufImg.getGraphics().drawImage(img,0,0,null);
                ImageIO.write(bufImg,suffixOpen,tempFileDownload=File.createTempFile("tfd",".tmp",new File(System.getProperty("java.io.tmpdir","."))));
            }catch(Exception e)
            {
                isTempFileDownloadWrite=false;
            }

            if(isTempFileDownloadWrite)
            {
                img=Toolkit.getDefaultToolkit().getImage(tempFileDownload.getPath());
                try
                {
                    mt=new MediaTracker(this);
                    mt.addImage(img,0);
                    mt.waitForAll();
                }catch(InterruptedException ex)
                {
                    Logger.getLogger(FiltersForm.class.getName()).log(Level.SEVERE,null,ex);
                }
            }

            showImage(true);
        }
    }

    public void Search()
    {
        addInHistory();
        if((!isFileSave)&&(fileNameOpen!=null))
        {
            switch(JOptionPane.showConfirmDialog(null,"Сохранить изменение в файле?\n"+fileNameOpen,"Файл изменён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    Save();
                    break;
                case 1:
                    isFileSave=true;
                    break;
                case 2:
                    return;
            }
        }
        if(!isFileSaveAs)
        {
            switch(JOptionPane.showConfirmDialog(null,"Хотите сохранить загруженный файл?","Файл не сохранён",JOptionPane.YES_NO_CANCEL_OPTION))
            {
                case 0:
                    SaveAs();
                    break;
                case 1:
                    isFileSaveAs=true;
                    break;
                case 2:
                    return;
            }
        }
        sf=new SearchForm("Поиск файлов",ff);
        sf.setSize(640,320);
        sf.setLocationRelativeTo(null);
        sf.setVisible(true);
    }
}
