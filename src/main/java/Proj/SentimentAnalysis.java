package Proj;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map;
import java.util.Scanner;

public class SentimentAnalysis {






    public static void Apply(String path) throws IOException
    {
        try{
            int count=0;
            String tline;

            ArrayList<String> stopwords= new ArrayList<String>();
            BufferedReader stop = new BufferedReader(new FileReader("Data\\stopwords.txt"));
            String line = "";
            while ((line = stop.readLine()) != null)
            {
                stopwords.add(line);
            }


            Map<String, String> map = new HashMap<String, String>();
            BufferedReader in = new BufferedReader(new FileReader("Data\\AFINN"));

            line="";
            while ((line = in.readLine()) != null) {
                String parts[] = line.split("\t");
                map.put(parts[0], parts[1]);
                count++;
            }
            in.close();
            //   System.out.println(map.toString());



            Scanner inputStream= new Scanner(new FileReader(path));
            while(inputStream.hasNextLine())
            {
                float score=0;
                tline= inputStream.nextLine();
                String[] word=tline.split(" ");



                for(int i=0; i<word.length;i++)
                {
                    if(stopwords.contains(word[i].toLowerCase()))
                    {

                    }
                    else{
                        if(map.get(word[i])!=null)
                        {
                            String wordscore= map.get(word[i].toLowerCase());
                            score=(float) score + Integer.parseInt(wordscore);
                        }}}
                Map<String, Float> sentiment= new HashMap<String, Float>();
                sentiment.put(tline, score);
                System.out.println(sentiment.toString());


            }





        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();

        }
    }}