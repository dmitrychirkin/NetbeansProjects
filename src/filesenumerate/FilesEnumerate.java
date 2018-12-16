/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesenumerate;

import java.util.ArrayList;
import java.io.File;
import sondapro.ru.AnsiColor;

/**
 *
 * @author Dmitry Chirkin
 * @version 1.0.0 from 15.12.2018
 */
public class FilesEnumerate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String parentDir = "/home/prog/Templates/test";
        ArrayList<String> exten = new ArrayList<String>();
        exten.add(".tpl");
        exten.add(".WSQ");
        
        System.out.println(AnsiColor.GREEN + "Start of program" + AnsiColor.RESET);
    
        ArrayList<folderInfo> fInfo = new ArrayList<folderInfo>();
        fInfo = listDirectory(parentDir,exten,true);
        for(int i = 0; i < fInfo.size(); i++)
        {
            folderInfo fif = fInfo.get(i);
            int files_count = fif.filesList.size();
            if(files_count > 0 )
            {
                System.out.println(AnsiColor.BLUE + "Folder : " + fif.folderName);
                for(int j = 0; j < files_count; j++)
                    System.out.println(AnsiColor.MAGENTA + "\tFile : " + fif.filesList.get(j));
            }
        }
        System.out.println(AnsiColor.GREEN + AnsiColor.BLINK + "End of program" + AnsiColor.RESET);
    }
    
    /**
     * keeps information folder content about.
     */
    static class folderInfo
    {
        public String folderName;
        public ArrayList<String> filesList;
        
        public folderInfo()
        {
            folderName = "";
            filesList = new ArrayList<String>();
        }
    }
    
    static public ArrayList<folderInfo> listDirectory(String parentDirectory, ArrayList<String> ext, boolean listSubdirs)
    {
        ArrayList<folderInfo> retList = new ArrayList<folderInfo>();
        folderInfo thisFolder = new folderInfo();
        int index = parentDirectory.lastIndexOf("/");
        if( index == -1 || index == 0)
            index = parentDirectory.lastIndexOf("\\");
        if( index > 0 )
            thisFolder.folderName = parentDirectory.substring(index+1);
        else
            thisFolder.folderName = parentDirectory;
        
        File directory = new File(parentDirectory);
        File[] fList = directory.listFiles();
        for (File file : fList)
        {
            if (file.isFile())
            {
                for(int i = 0; i < ext.size(); i++)
                {
                    if(file.getName().endsWith(ext.get(i)))
                    {
                        thisFolder.filesList.add(file.getAbsolutePath());
                        break;
                    }
                }
            }
            else if (file.isDirectory() && listSubdirs)
            {
                retList.addAll( listDirectory(file.getAbsolutePath(),ext,listSubdirs));
            }
        }
        retList.add(thisFolder);
        return retList;
    }
}
