package convolucao;

import java.awt.image.BufferedImage;


public class Convolucao
{
    
    private int matriz[][];
    private BufferedImage original;

    public Convolucao(int[][] matriz, BufferedImage original) 
    {
        this.matriz = matriz;
        this.original = original;
        
    }

    public Convolucao() 
    {
        
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public BufferedImage getOriginal() {
        return original;
    }

    public void setOriginal(BufferedImage original) {
        this.original = original;
    }
    
    public BufferedImage TonsDeCinza()
    {
        int v[] = new int [3];
        BufferedImage TonsDecinza = new BufferedImage(original.getWidth(), original.getHeight(),BufferedImage.TYPE_INT_RGB );
        for(int i=0; i < original.getHeight(); i++)
        {
            for(int j=0; j < original.getWidth(); j++)
            {
                original.getRaster().getPixel(j, i, v);
                
                v[0] = (v[0] + v[1] + v[2])/3;
                v[1] = v[0];
                v[2] = v[0];
                
                TonsDecinza.getRaster().setPixel(j, i, v);
            }
        }
        return TonsDecinza;
    }
    public BufferedImage PercorreImagem()
    {
        BufferedImage convoluida = new BufferedImage(original.getWidth(), original.getHeight(),BufferedImage.TYPE_INT_RGB );
        convoluida.createGraphics().drawImage(original, null, 0, 0);
        int v[] = new int[3];
        for(int i=0; i < original.getHeight(); i++)
        {
            for(int j=0; j < original.getWidth(); j++)
            {
                v[0] = Convolucao(Matriz(i,j));
                v[1] = v[0];
                v[2] = v[0];
                convoluida.getRaster().setPixel(j, i, v);
            }
        }
        return convoluida;
    }
    public int Convolucao(int matriz2[][])
    {
            int soma = 0;
        
            for(int i = 0; i <= 2; i++ )
            {
                for(int j = 0; j <= 2; j++ )
                {
                   soma = soma+ (matriz[i][j] * matriz2[i][j]);
                }
            }
            if(soma < 0)
            {
                return 0;
            }
            else if(soma > 255)
            {
                return 255;
            }
            else
            {
                return soma;
            }
    }
            
    public int [][] Matriz(int i, int j)
    {
        int M = i-1;
        int N = j-1;
        
        int m[][] = new int [3][3];
        
        for(int k=0; k <= 2; k++)
        {
            for(int l=0;l <= 2; l++)
            {
                if(M<0 || N<0 || M >= original.getHeight() || N >= original.getWidth())
                {
                    m[k][l] = 0;
                }
                else
                {
                    m[k][l] = original.getRaster().getPixel(N, M, new int [3])[0];
                           
                }
                     
                N++; 
            }
            M++;
        }
        return m;
    }
}
