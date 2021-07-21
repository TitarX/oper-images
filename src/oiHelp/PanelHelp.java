/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oiHelp;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author TitarX
 */
public class PanelHelp extends JPanel
{

    Image imgDownload;
    Image imgSearch;
    Image imgFilters;
    MediaTracker mt;

    public PanelHelp()
    {
        setPreferredSize(new Dimension(640,1600));
        imgDownload=new ImageIcon(getClass().getResource("download.jpg")).getImage();
        imgSearch=new ImageIcon(getClass().getResource("search.jpg")).getImage();
        imgFilters=new ImageIcon(getClass().getResource("filters.jpg")).getImage();

        try
        {
            mt=new MediaTracker(this);
            mt.addImage(imgDownload,0);
            mt.addImage(imgSearch,1);
            mt.addImage(imgFilters,2);
            mt.waitForAll();
        }catch(InterruptedException ex)
        {
            Logger.getLogger(PanelHelp.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setFont(new Font("SansSerif",Font.BOLD,18));
        g.drawString("OperImages",10,30);
        g.setFont(new Font("SansSerif",Font.PLAIN,16));
        g.drawString("Программа для просмотра и фильтрации изображений",10,60);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Программа поддерживает три графических формата (JPG, GIF, PNG),",10,120);
        g.drawString("расширение открываемого и загружаемого файла должно быть одно из (.jpg, .jpeg, .gif, .png).",10,140);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("Открытие",10,180);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Для открытия изображения из файла выберите в меню 'Файл' пункт 'Открыть'.",10,200);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("Загрузка",10,240);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Для загрузки изображения из интернета выберите в меню 'Файл' пункт 'Загрузить',",10,260);
        g.drawImage(imgDownload,10,270,this);
        g.drawString("и в открывшемся диалоговом окне укажите URL изображения как показано в примере",10,450);
        g.drawString("после чего нажмите кнопку 'Загрузить'.",10,470);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("Сохранение",10,510);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Для сохранения открытого и изменённого изображения в тот же файл выберите",10,530);
        g.drawString("в меню 'Файл' пункт 'Сохранить'.",10,550);
        g.drawString("Для сохранения открытого и изменённого изображения в другой файл,",10,570);
        g.drawString("а так же для сохранения загруженного изображения из интернета в файл,",10,590);
        g.drawString("выберите в меню 'Файл' пункт 'Сохранить как'.",10,610);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("Поиск",10,650);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawImage(imgSearch,10,660,this);
        g.drawString("Для поиска файла на локальном компьютере выберите в меню 'Файл' пункт 'Поиск',",10,970);
        g.drawString("в открывшемся диалоговом окне укажите имя искомого файла",10,990);
        g.drawString("и путь к директории от которой следует начать поиск, после чего нажмите кнопку 'Начать поиск'.",10,1010);
        g.drawString("Если найденный файл имеет один из поддерживаемых программой графический формат,",10,1030);
        g.drawString("то его можно открыть выбрав его в результатах поиска и нажав кнопку 'Открыть выбранный файл'.",10,1050);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("Фильтры",10,1090);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Фильтры которые можно применить к открытому изображению в программе представлены в меню 'Фильтры'.",10,1110);
        g.drawImage(imgFilters,10,1120,this);
        g.drawString("Выбор пункта 'Отмена' отменяет последний применённый к изображению фильтр.",10,1410);
        g.drawString("Выбор пункта 'Сброс' сбрасывает все применённые к изображению фильтры,",10,1430);
        g.drawString("в программе отобразится исходный вариант изображения",10,1450);
        g.setFont(new Font("SansSerif",Font.BOLD,12));
        g.drawString("История",10,1490);
        g.setFont(new Font("SansSerif",Font.PLAIN,12));
        g.drawString("Путь к текущему изображению попадает в меню 'История' при открытии или загрузки нового изображения,",10,1510);
        g.drawString("в дольнейшем есть возможность вернуться к изображению путь которого есть в меню 'История',",10,1530);
        g.drawString("выбрав соответствующий пункт.",10,1550);
    }
}
