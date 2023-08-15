import java.util.*;

public class decoder {
    static String digitalData = graph.str;
    static Scanner sc;
    static boolean willDecode()
    {
        
        System.out.println("Do you want to decode this digital signal");
        System.out.println("1. No");
        System.out.println("2. Yes");
        sc = new Scanner(System.in);
        int input = sc.nextInt();
        if(encoder.currentEncodingScheme == encoder.AMI && input == 2)
        {
            System.out.println("Sorry this decoding is not supported");
        }
        switch (input) {
            case 2:
                return true;
                
        
            default:
                return false;
        }
    }

    static void getDecodingScheme()
    {
        sc = new Scanner(System.in);
        int input;
        System.out.println("Choose a scheme in which the signal is to be decoded ...");
        /*
        if(encoder.currentEncodingScheme == encoder.AMI)
        {
            if(encoder.isScramblerUsed)
            {
                if(encoder.scramblingScheme == encoder.SCRAMBLER_B8ZS)
                {
                    System.out.println("1. AMI");
                    System.out.println("2. AMI-HDB3");
                    input = sc.nextInt();
                    switch (input) {
                        case 2: encoder.isDecoderScramblerUsed = true;
                                encoder.decodingScheme = encoder.AMI;
                                encoder.decodingScrambler = encoder.SCRAMBLER_HDB3;
                            break;
                    
                        default:
                                encoder.isDecoderScramblerUsed = false;
                                encoder.decodingScheme = encoder.AMI;
                            break;
                    }
                }
                else 
                {
                    System.out.println("1. AMI");
                    System.out.println("2. AMI-B8ZS");
                    input = sc.nextInt();
                    switch (input) {
                        case 2: encoder.isDecoderScramblerUsed = true;
                                encoder.decodingScheme = encoder.AMI;
                                encoder.decodingScrambler = encoder.SCRAMBLER_B8ZS;
                            break;
                    
                        default:
                                encoder.isDecoderScramblerUsed = false;
                                encoder.decodingScheme = encoder.AMI;
                            break;
                    }
                }
            }
            else 
            {
                System.out.println("1. AMI-HDB3");
                System.out.println("2. AMI-B8ZS");
                input = sc.nextInt();
                switch (input) {
                    case 2: encoder.isDecoderScramblerUsed = true;
                            encoder.decodingScheme = encoder.AMI;
                            encoder.decodingScrambler = encoder.SCRAMBLER_B8ZS;
                        break;
                
                    default:
                            encoder.isDecoderScramblerUsed = true;
                            encoder.decodingScheme = encoder.AMI;
                            encoder.decodingScrambler = encoder.SCRAMBLER_HDB3;
                        break;
                }
            }
        }                     */
        if(encoder.currentEncodingScheme == encoder.NRZ_L)
        {
            System.out.println("1. NRZ-I");
            System.out.println("2. Manchester");
            System.out.println("3. Differential Manchester");
            input = sc.nextInt();
            switch (input) {
                case 2:
                    encoder.decodingScheme = encoder.MANCHESTER;
                    break;
                case 3:
                    encoder.decodingScheme = encoder.DIFFERENTIAL_MANCHESTER;
                    break;
                default:
                    encoder.decodingScheme = encoder.NRZ_I;
                    break;
            }
        }
        else if(encoder.currentEncodingScheme == encoder.NRZ_I)
        {
            System.out.println("1. NRZ-L");
            System.out.println("2. Manchester");
            System.out.println("3. Differential Manchester");
            input = sc.nextInt();
            switch (input) {
                case 2:
                    encoder.decodingScheme = encoder.MANCHESTER;
                    break;
                case 3:
                    encoder.decodingScheme = encoder.DIFFERENTIAL_MANCHESTER;
                    break;
                default:
                    encoder.decodingScheme = encoder.NRZ_L;
                    break;
            }
        }
        else if(encoder.currentEncodingScheme == encoder.MANCHESTER)
        {
            System.out.println("1. NRZ-L");
            System.out.println("2. NRZ-I");
            System.out.println("3. Differential Manchester");
            input = sc.nextInt();
            switch (input) {
                case 2:
                    encoder.decodingScheme = encoder.NRZ_I;
                    break;
                case 3:
                    encoder.decodingScheme = encoder.DIFFERENTIAL_MANCHESTER;
                    break;
                default:
                    encoder.decodingScheme = encoder.NRZ_L;
                    break;
            }
        }
        else if(encoder.currentEncodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
        {
            System.out.println("1. NRZ-L");
            System.out.println("2. NRZ-I");
            System.out.println("3. Manchester");
            input = sc.nextInt();
            switch (input) {
                case 2:
                    encoder.decodingScheme = encoder.NRZ_I;
                    break;
                case 3:
                    encoder.decodingScheme = encoder.MANCHESTER;
                    break;
                default:
                    encoder.decodingScheme = encoder.NRZ_L;
                    break;
            }
            
        }
    }
  
    
    static void Decode()
    {
        char[] decodedSignal;

        if(encoder.currentEncodingScheme == encoder.NRZ_L)
        {
            if(encoder.decodingScheme == encoder.NRZ_I)
            {
                decodedSignal = new char[30];
                decodedSignal[0] = digitalData.charAt(0);
                for(int i=1; i<30;i++)
                {
                    if(digitalData.charAt(i-1) == digitalData.charAt(i))
                     decodedSignal[i] = '0';
                    else 
                     decodedSignal[i] = '1';
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
            else if(encoder.decodingScheme == encoder.MANCHESTER)
            {
                System.out.println("Decoded Data  " + digitalData );

            }
            else if(encoder.decodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
            {
                decodedSignal = new char[30];
                decodedSignal[0] = digitalData.charAt(0);
                for(int i=1; i<30;i++)
                {
                    if(digitalData.charAt(i-1) == digitalData.charAt(i))
                     decodedSignal[i] = '0';
                    else 
                     decodedSignal[i] = '1';
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
        }

        else if(encoder.currentEncodingScheme == encoder.NRZ_I)
        {
            if(encoder.decodingScheme == encoder.NRZ_L)
            {
                decodedSignal = new char[30];
                int count = 0;
                for(int i = 0; i<30; i++)
                {
                    if(digitalData.charAt(i) == '1')
                     count++;
                    if(count%2 == 1)
                    {
                        decodedSignal[i] = '1';
                    }
                    else
                    {
                        decodedSignal[i] = '0';
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
            else if(encoder.decodingScheme == encoder.MANCHESTER)
            {
                decodedSignal = new char[30];
                int count = 0;
                for(int i = 0; i<30; i++)
                {
                    if(digitalData.charAt(i) == '1')
                     count++;
                    if(count%2 == 1)
                    {
                        decodedSignal[i] = '1';
                    }
                    else
                    {
                        decodedSignal[i] = '0';
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
            else if(encoder.decodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
            {
                System.out.println("Decoded Data  " + digitalData );
            }

        }
        else if(encoder.currentEncodingScheme == encoder.MANCHESTER)
        {
            if(encoder.decodingScheme == encoder.NRZ_L)
            {
                decodedSignal = new char[60];
                int d=0;
                for(int i =0; i<30; i++)
                {
                    if(digitalData.charAt(i) == '0')
                    {
                        decodedSignal[d++] = '0';
                        decodedSignal[d++] = '1';
                    }
                    else 
                    {
                        decodedSignal[d++] = '1';
                        decodedSignal[d++] = '0';
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<60;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
            else if(encoder.decodingScheme == encoder.NRZ_I)
            {
                char[] decoded = new char[60];
                int d=0;
                for(int i =0; i<30; i++)
                {
                    if(digitalData.charAt(i) == '0')
                    {
                        decoded[d++] = '0';
                        decoded[d++] = '1';
                    }
                    else 
                    {
                        decoded[d++] = '1';
                        decoded[d++] = '0';
                    }
                }
                decodedSignal = new char[60];
                decodedSignal[0] =decoded[0];
                for(int i=1; i<60;i++)
                {
                    if(decoded[i-1] == decoded[i])
                     decodedSignal[i] = '0';
                    else 
                     decodedSignal[i] = '1';
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<60;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();
            }
            else if(encoder.decodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
            {
                decodedSignal = new char[30];
                decodedSignal[0] = digitalData.charAt(0);
                for(int i =1; i<30; i = i++)
                {
                    if(digitalData.charAt(i-1) == digitalData.charAt(i))
                    {
                        decodedSignal[i] = '0';
                    }
                    else
                    {
                        decodedSignal[i] = '1';
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();

            }
        }
        else if(encoder.currentEncodingScheme == encoder.DIFFERENTIAL_MANCHESTER)
        {
            if(encoder.decodingScheme == encoder.NRZ_L)
            {
                decodedSignal = new char[60];
                int d = 2;
                if(digitalData.charAt(0) == '0')
                {
                    decodedSignal[0] = '0';
                    decodedSignal[1] = '1';
                }
                else
                {
                    decodedSignal[0] = '1';
                    decodedSignal[1] = '0';
                }
                for(int i = 1; i < 30; i++)
                {
                    if(digitalData.charAt(i) == '0')
                    {
                        decodedSignal[d] = decodedSignal[d-2];
                        d++;
                        decodedSignal[d] = decodedSignal[d-2];
                        d++;
                    }
                    else
                    {
                        decodedSignal[d] = decodedSignal[d-1];
                        d++;
                        decodedSignal[d] = decodedSignal[d-3];
                        d++;
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<60;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();

            }
            else if(encoder.decodingScheme == encoder.NRZ_I)
            {
                char[] decoded = new char[60];
                int d = 2;
                if(digitalData.charAt(0) == '0')
                {
                    decoded[0] = '0';
                    decoded[1] = '1';
                }
                else
                {
                    decoded[0] = '1';
                    decoded[1] = '0';
                }
                for(int i = 1; i < 30; i++)
                {
                    if(digitalData.charAt(i) == '0')
                    {
                        decoded[d] = decoded[d-2];
                        d++;
                        decoded[d] = decoded[d-2];
                        d++;
                    }
                    else
                    {
                        decoded[d] = decoded[d-1];
                        d++;
                        decoded[d] = decoded[d-3];
                        d++;
                    }
                }
                decodedSignal = new char[60];
                decodedSignal[0] = decoded[0];
                for(int i=1; i<60;i++)
                {
                    if(decoded[i-1] == decoded[i])
                     decodedSignal[i] = '0';
                    else 
                     decodedSignal[i] = '1';
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<60;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();

            }
            else if(encoder.decodingScheme == encoder.MANCHESTER)
            {
                decodedSignal = new char[30];
                int count = 0;
                for(int i = 0; i<30; i++)
                {
                    if(digitalData.charAt(i) == '1')
                     count++;
                    if(count%2 == 1)
                    {
                        decodedSignal[i] = '1';
                    }
                    else
                    {
                        decodedSignal[i] = '0';
                    }
                }
                System.out.print("Decoded Data  " );
                for(int i=0; i<30;i++)
                {
                    System.out.print(decodedSignal[i]);
                }
                System.out.println();   
            }
        }
     /*   else if(encoder.currentEncodingScheme == encoder.AMI && encoder.decodingScheme == encoder.AMI)
        {
            if(encoder.isScramblerUsed)
            {
                if(encoder.scramblingScheme == encoder.SCRAMBLER_B8ZS)
                {
                    if(encoder.isDecoderScramblerUsed && encoder.decodingScrambler == encoder.SCRAMBLER_HDB3)
                    {
                        
                    }
                    else
                    {
                        // Convert to pure AMI
                    }
                }
                else if(encoder.scramblingScheme == encoder.SCRAMBLER_HDB3)
                {
                    if(encoder.isDecoderScramblerUsed && encoder.decodingScrambler == encoder.SCRAMBLER_B8ZS)
                    {
                        
                    }
                    else
                    {
                        // Convert to pure AMI
                    }
                }

            }
            else
            {
                if(encoder.decodingScrambler == encoder.SCRAMBLER_B8ZS)
                {

                }
                else if(encoder.decodingScrambler == encoder.SCRAMBLER_HDB3)
                {

                }
            }
        } */
    }
}
