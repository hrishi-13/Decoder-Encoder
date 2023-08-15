import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


class graph extends JFrame
{
    static String str;
    graph(){

        System.out.println("Generated Data  :  " + str);
        System.out.println("Longest Pallindrome Lwngth  :  "+ encoder.longetPallindromeSequence );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(980,980);
       
        this.setVisible(true);
       
    }
    

    public void paint(Graphics g1) {
         Graphics2D g = (Graphics2D) g1;

         int x1 = 20, y1 = 480;
         int y_axis = y1;

         Stroke k = g.getStroke();
         float[] dash1 = { 2f, 0f, 2f };

       
         BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1,2f);
         g.setStroke(bs1);
         for(int i = 0; i<32; i++)
         {
             x1 +=30;
             g.drawLine(x1, y1+110, x1 , y1-110);
         }
         x1 = 20;
         g.setStroke(k);
         g.drawLine(0, 480, 980, 480);
         g.drawLine(20, 0, 20, 960);
        
         char[] ch = str.toCharArray();
         int i=0;
         if(encoder.currentEncodingScheme == encoder.NRZ_L)
         {
            while(i<ch.length)
            {
              if(ch[i]=='1')
              { 
                        if(i>0 &&  ch[i]!=ch[i-1])
                          g.drawLine(x1, y1, x1, y1-60);
                        g.drawLine(x1, y1-60,x1+30,y1-60);
                        if(i+1==ch.length || ch[i]!=ch[i+1])
                          g.drawLine(x1+30, y1-60, x1+30, y1);
                        x1=x1+30;
              }
              else
              {
                        if(i>0 && ch[i]!=ch[i-1])
                          g.drawLine(x1, y1, x1, y1+60);
                        g.drawLine(x1, y1+60,x1+30,y1+60);
                        if(i+1==ch.length || ch[i]!=ch[i+1])
                          g.drawLine(x1+30, y1+60, x1+30, y1);
                        x1=x1+30;
              }
              i++;
             }
         }
         
         else if(encoder.currentEncodingScheme == encoder.NRZ_I)
         {
             //System.out.println("hello");
            if(ch[0] == '0')
            {
                        g.drawLine(x1, y1+60,x1+30,y1+60);
                        if(i+1==ch.length || ch[i+1] == '1')
                            g.drawLine(x1+30, y1+60, x1+30, y1);
                        x1 = x1+30;
                        y1 += 60;
            }
            else if(ch[0] == '1')
            {
                        g.drawLine(x1, y1-60,x1+30,y1-60);
                        if(i+1 == ch.length || ch[i+1] == '1')
                          g.drawLine(x1+30, y1-60, x1+30, y1);
                        x1 = x1+30;
                        y1 -= 60;
            }
            i++;
            while(i<ch.length)
            {
                if(ch[i] == '0')
                {
                            g.drawLine(x1, y1,x1+30,y1);
                            x1=x1+30;
                            if(i+1 == ch.length || ch[i+1] == '1')
                            {
                                g.drawLine(x1, y1, x1, y_axis);
                            }    
                            
                }
                else if(ch[i] == '1')
                {
                            g.drawLine(x1, y_axis, x1, y_axis + (y_axis - y1));
                            y1 = y_axis + (y_axis - y1);
                            g.drawLine(x1, y1,x1+30,y1);
                            x1 = x1 + 30;
                            if(i+1 == ch.length || ch[i+1] == '1')
                              g.drawLine(x1, y1, x1, y_axis);
                }
                i++;
                
            }
            
         }
          
         else if(encoder.currentEncodingScheme == encoder.MANCHESTER)
         {
             while(i<ch.length)
             {
                 if(ch[i] == '0')
                 {
                     if((i>0) && (ch[i-1] == '0'))
                      g.drawLine(x1, y1-60, x1, y1+60);
                     g.drawLine(x1, y1+60, x1+15 , y1+60);
                     g.drawLine(x1+15, y1-60, x1+15, y1+60);
                     g.drawLine(x1+15, y1-60, x1+30 , y1-60);
                     x1 += 30;

                 }
                 else if(ch[i] == '1')
                 {
                     if((i>0) && (ch[i-1] == '1'))
                        g.drawLine(x1, y1-60, x1, y1+60);
                      g.drawLine(x1, y1-60, x1+15 , y1-60);
                      g.drawLine(x1+15, y1-60, x1+15, y1+60);
                      g.drawLine(x1+15, y1+60, x1+30 , y1+60);
                      x1 += 30;
                 }
                 i++;
             }
         }
         else if(encoder.currentEncodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
         {
             int sign = 1;                         // sign = -1 for down  and sign = 1 for up
             
            if(ch[0] == '0')
            {
                g.drawLine(x1, y1+60, x1+15 , y1+60);
                g.drawLine(x1+15, y1-60, x1+15, y1+60);
                g.drawLine(x1+15, y1-60, x1+30 , y1-60);
                x1 += 30;
                sign = -1 ;

            }
            else if(ch[0] == '1')
            {
                 g.drawLine(x1, y1-60, x1+15 , y1-60);
                 g.drawLine(x1+15, y1-60, x1+15, y1+60);
                 g.drawLine(x1+15, y1+60, x1+30 , y1+60);
                 x1 += 30;
                 sign = +1;
            }
            i++;
            while(i< ch.length)
            {
                
                if(ch[i] == '0')
                {
                    g.drawLine(x1, y1-60, x1, y1+60);
                    g.drawLine(x1, y1+(-sign*60), x1+15 , y1+(-sign*60));
                    g.drawLine(x1+15, y1-60, x1+15, y1+60);
                    g.drawLine(x1+15, y1+(sign*60), x1+30 , y1+(sign*60));
                    x1 += 30;

                }
                else if(ch[i] == '1')
                {
                     g.drawLine(x1, y1+((sign*60)), x1+15 , y1+((sign*60)));
                     g.drawLine(x1+15, y1-60, x1+15, y1+60);
                     g.drawLine(x1+15, y1-(+(sign*60)), x1+30 , y1+(-(sign*60)));
                     x1 += 30;
                     sign = -sign;
                     //System.out.print("hi  ");
                }
                i++;
            }
         }
         else if(encoder.currentEncodingScheme == encoder.AMI)
         {
             if(!encoder.isScramblerUsed)
             {
                int sign = -1;
                while(i < ch.length)
                {
                    if(ch[i] == '0')
                    {
                        x1 += 30;
                    }
                    else if(ch[i] == '1')
                    {
                        g.drawLine(x1, y1, x1, y1+(sign*60));
                        g.drawLine(x1, y1+(sign*60), x1+30, y1+(sign*60));
                        g.drawLine(x1+30, y1, x1+30, y1+(sign*60));
                        x1 += 30;
                        sign = -sign;
                    }
                    i++;
                }
            }
            else if(encoder.scramblingScheme == encoder.SCRAMBLER_B8ZS)
            {
                    int sign = -1;
                    str = str.replace("00000000", "000VB0VB");
                    ch = str.toCharArray();
                    while(i < ch.length)
                    {
                      if(ch[i] == '0')
                      {
                         x1 += 30;
                      }
                      else if(ch[i] == '1' || ch[i] =='B')
                      {
                            g.drawLine(x1, y1, x1, y1+(sign*60));
                            g.drawLine(x1, y1+(sign*60), x1+30, y1+(sign*60));
                            g.drawLine(x1+30, y1, x1+30, y1+(sign*60));
                            x1 += 30;
                            sign = -sign;
                      }
                      else if(ch[i] == 'V')
                      {
                            sign = -sign;  
                            g.drawLine(x1, y1, x1, y1+(sign*60));
                            g.drawLine(x1, y1+(sign*60), x1+30, y1+(sign*60));
                            g.drawLine(x1+30, y1, x1+30, y1+(sign*60));
                            x1 += 30;
                            sign = -sign;

                      }
                      i++;
                    }

                }
                else if(encoder.scramblingScheme == encoder.SCRAMBLER_HDB3)
                {
                    int sign = -1;
                    int count=0;
                    
                    while(str.indexOf("0000")>=0)
                    {
                        i = 0;
                        count =0;
                        int index =str.indexOf("0000");
                       // System.out.println(index);
                        for(int p =0; p<index; p++)
                        {
                            if(ch[p] == '0')
                             count++;
                        }
                        if((index - count)%2 == 0)
                        {
                            str = str.replaceFirst("0000", "B00V");
                        }
                        else
                        {
                            str = str.replaceFirst("0000", "000V");
                        } 
                        ch = str.toCharArray();
                    }
                    while(i < ch.length)
                    {
                      if(ch[i] == '0')
                      {
                         x1 += 30;
                      }
                      else if(ch[i] == '1' || ch[i] =='B')
                      {
                            g.drawLine(x1, y1, x1, y1+(sign*60));
                            g.drawLine(x1, y1+(sign*60), x1+30, y1+(sign*60));
                            g.drawLine(x1+30, y1, x1+30, y1+(sign*60));
                            x1 += 30;
                            sign = -sign;
                      }
                      else if(ch[i] == 'V')
                      {
                            sign = -sign;  
                            g.drawLine(x1, y1, x1, y1+(sign*60));
                            g.drawLine(x1, y1+(sign*60), x1+30, y1+(sign*60));
                            g.drawLine(x1+30, y1, x1+30, y1+(sign*60));
                            x1 += 30;
                            sign = -sign;

                      }
                      i++;
                    }
                    
                

                
             }

         }

    }
}

public class encoder {

    final static int NRZ_L = 1 ;                                // Default Encoding Scheme
    final static int NRZ_I = 2 ;
    final static int MANCHESTER = 3;
    final static int DIFFERENTIAL_MANCHESTER = 4;
    final static int AMI = 5;

    final static int RANDOM_SEQUENCE = 1;                       // Default Data Generator
    final static int RANDOM_SEQUENCE_FIXED_SUBSEQUENCE = 2;

    final static int SCRAMBLER_B8ZS = 1;                        // Default Scrambler Scheme
    final static int SCRAMBLER_HDB3 = 2;
    
    static int currentSequence = RANDOM_SEQUENCE;
    static int currentEncodingScheme = NRZ_L;
    static boolean isScramblerUsed = false;                      
    static int scramblingScheme = SCRAMBLER_B8ZS;

    static boolean isDecoderScramblerUsed = false;                //Default
    static int decodingScrambler = SCRAMBLER_B8ZS;               //Default
    static int decodingScheme = NRZ_L;                          // Default
    
    static int longetPallindromeSequence = 1;                    // Default
    static Scanner sc; 

    // Select the sequence of data.
    public static int getSequence()
    {
        System.out.println("Welcome....");
        System.out.println("1. Random Sequence  ");
        System.out.println("2. Random Sequence with fixed subsequences");
        sc = new Scanner(System.in);
        int input = sc.nextInt();
        
        switch (input) {
            case 1:
                return RANDOM_SEQUENCE;
            case 2:
                return RANDOM_SEQUENCE_FIXED_SUBSEQUENCE;

            default:
                System.out.println("Error in input.... Please run the program again. Thank You ");
                System.exit(2);
        }
        return 1;

    }

    //generates the digital data
    public static void digitalDataGenerator(int sequence)
    {
      if(sequence == RANDOM_SEQUENCE)  
      {
            Random rand = new Random();
            graph.str = Integer.toString(rand.nextInt(2));
            for(int i=1; i<30; i++)
            {
                graph.str =graph.str.concat(Integer.toString(rand.nextInt(2)));
            }
      }
      else 
      {
          int i = 0;
          Random rand = new Random();
          int l = rand.nextInt(22);
          graph.str = Integer.toString(rand.nextInt(2));
          for(i=1; i<l; i++)
          {
                graph.str =graph.str.concat(Integer.toString(rand.nextInt(2)));
          }
          graph.str = graph.str.concat("00000000");
          for( i += 6; i<30; i++)
          {
                graph.str =graph.str.concat(Integer.toString(rand.nextInt(2)));
          }
      }
    }


    //Get encoding Scheme
    public static int getEncodingScheme()
    {
        System.out.println("Please Select the encoding scheme");
        System.out.println("1. NRZ-L");
        System.out.println("2. NRZ-I");
        System.out.println("3. Manchester");
        System.out.println("4. Differential Manchester");
        System.out.println("5. AMI");
        System.out.println();
        System.out.println();
        System.out.println("Your input  :");
       
        sc = new Scanner(System.in);
        int input = sc.nextInt();
        switch (input) {
            case 1:
                //System.out.println("hi");
                return NRZ_L;
            case 2:
                //System.out.println("hello");
                return NRZ_I;
            case 3:
                return MANCHESTER;
            case 4:
                return DIFFERENTIAL_MANCHESTER;
            case 5:
                return AMI;

            default:
               // System.out.println("hey");
                return NRZ_L;
        }
    
        
    }

    public static boolean isScramblerNeeded()
    {
        System.out.println("");
        System.out.println("");
        System.out.println("Is Scrambling needed ?");
        System.out.println("1. No");
        System.out.println("2. Yes");
        System.out.println("Your input  :");
        sc = new Scanner(System.in);
        int input = sc.nextInt();
        switch (input) {
            case 1:
                return false;
            case 2:
                return true;
        }
        return false;

    }

    // Returns the scrambling scheme to be used.
    public static int getScramblingScheme()
    {
        System.out.println("");
        System.out.println("");
        System.out.println("Select a scrambling scheme");
        System.out.println("1. B8ZS Scheme");
        System.out.println("2. HDB3 Scheme");
        System.out.println("Your input  :");
        sc = new Scanner(System.in);
        int input = sc.nextInt();
        switch (input) {
            case 1:
                return SCRAMBLER_B8ZS;
            case 2:
                return SCRAMBLER_HDB3;
        }
        return SCRAMBLER_B8ZS;

    }
    public static void main(String[] args){
        currentSequence = getSequence();
        digitalDataGenerator(currentSequence);
        Map<String, Integer> mapping = new HashMap<>();
        longetPallindromeSequence = pallindrome.getLongestPallindrome(graph.str,0, graph.str.length()-1, mapping);    
        currentEncodingScheme = getEncodingScheme();
        if(currentEncodingScheme == AMI)
        {
            if(isScramblerUsed = isScramblerNeeded())
            {
                scramblingScheme = getScramblingScheme();
            }
        }
        boolean x;
         if(x =decoder.willDecode())
         {
            decoder.getDecodingScheme();
         }
         

        new graph();
        if(x)
         {
            decoder.Decode();
         }
        
    }
}
