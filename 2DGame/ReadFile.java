import java.io.*;
import java.text.*;
import javax.swing.*;
/**
 * Write a description of class ReadFile here.
 * 
 * @author Nick Meyer
 * @version Beta
 */
public class ReadFile
{
    private static FileInputStream in;
    private static BufferedWriter out;
    private static int[] door = new int[2];
    public static void setReadInput(String s, JPanel m)
    {
        ((MainPanel)m).loadComponents(readInput(s), door[0], door[1]);
    }
    public static Component[] readInput(String s)
    {
        int[] doorArray = new int[2];
        Component[] ans = null;
        int line = 0;
        int index = 0;
        int order = 1;
        try {
            in = new FileInputStream(s);}
        catch (FileNotFoundException e){}
        try {
            int c = 0;
            int x1, y1;
            int i = 0;
            while((c=in.read())!=13)
            {
                index*=10;
                index+=c-48;
            }
            in.read();
            ans = new Component[index];
            index = 0;
            line++;
            while ((c=in.read())!=-1)
            {
                if (c==13) c = in.read();
                if (c==10) line++;
                else
                {
                    if (c!=13&&c!=10)
                    {
                        if (line==6)
                        {
                            for (int iterator = 0; iterator < 2; iterator++)
                            {
                                if (iterator<1) i+=c-48;
                                while ((c=in.read())!=44)
                                {
                                    i*=10;
                                    i+=c-48;
                                }
                                doorArray[iterator] = i;
                                i = 0;
                            }
                        }
                        else
                        {
                            while (c!=44)
                            {
                                i*=10;
                                i+= c-48;
                                c = in.read();
                            }
                            c = in.read();
                            x1 = i;
                            i = 0;
                            while (c!=44)
                            {
                                i*=10;
                                i+= c-48;
                                c = in.read();
                            }
                            y1 = i;
                            i = 0;
                            if (line==1)
                            {
                                c = in.read();
                                ans[index] = new Star(x1,y1,order);
                                if (c==46)
                                    order++;
                                index++;
                            }
                            if (line==2)
                            {
                                boolean[] last = new boolean[2];
                                for (int iterator = 0; iterator < 2; iterator++)
                                {
                                    c = in.read();
                                    last[iterator] = (c-48==1);
                                    c = in.read();
                                }
                                ans[index] = new Wall(x1,y1,last[0],last[1]);
                                index++;
                            }
                            if (line==3)
                            {
                                int[] last = new int[2];
                                for (int iterator = 0; iterator < 2; iterator++)
                                {
                                    while ((c=in.read())!=44)
                                    {
                                        i*=10;
                                        i+=c-48;
                                    }
                                    last[iterator] = i;
                                    i = 0;
                                }
                                ans[index] = new DeathStar(x1,y1,last[0],last[1]);
                                index++;
                            }
                            if (line==4)
                            {
                                double[] last = {0.0,0.0};
                                for (int iterator = 0; iterator < 2; iterator++)
                                {
                                    while ((c=in.read())!=46)
                                    {
                                        i*=10;
                                        i+=c-48;
                                    }
                                    last[iterator]+=i;
                                    i = 0;
                                    double i2 = 0.1;
                                    while ((c=in.read())!=44)
                                    {
                                        last[iterator]+=i2*(c-48);
                                        i2/=10;
                                    }
                                }
                                while ((c=in.read())!=44)
                                {
                                    i*=10;
                                    i+=c-48;
                                }
                                ans[index] = new Bomb(x1,y1,last[0],last[1],i);
                                i = 0;
                                index++;
                            }
                            if (line==5)
                            {
                                int[] last = new int[2];
                                for (int iterator = 0; iterator < 2; iterator++)
                                {
                                    while ((c=in.read())!=44)
                                    {
                                        i*=10;
                                        i+=c-48;
                                    }
                                    last[iterator] = i;
                                    i = 0;
                                }
                                ans[index] = new Teleporter(x1,y1,last[0],last[1]);
                                index++;
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e){}
        finally
        {
            try{
                if (!(in==null))
                in.close();
            }
            catch (IOException e){}
        }
        door[0] = doorArray[0];
        door[1] = doorArray[1];
        return ans;
    }
    public static void writeOutput(Component[] e, String filename, int doorX, int doorY)
    {
        File f = new File(filename);
        try {
            if (!f.exists()) f.createNewFile();
            out = new BufferedWriter(new FileWriter(filename));
            out.write(""+e.length);
            out.write(13);
            out.write(10);
            int i = 0;
            while (i<e.length&&e[i].type==0)
            {
                out.write(e[i].x+","+e[i].y+",");
                if (e[i+1].type==0)
                    if (e[i].order==e[i+1].order)
                        out.write("/");
                    else
                        out.write(".");
                i++;
            }
            out.write(13);
            out.write(10);
            while (i<e.length&&e[i].type==1)
            {
                Wall a = (Wall)e[i];
                out.write(e[i].x+","+e[i].y+","+bool2int(a.deadly)+","+bool2int(a.wide)+",");
                i++;
            }
            out.write(13);
            out.write(10);
            while (i<e.length&&e[i].type==2)
            {
                DeathStar a = (DeathStar)e[i];
                out.write(e[i].x+","+e[i].y+","+a.dX+","+a.dY+",");
                i++;
            }
            out.write(13);
            out.write(10);
            while (i<e.length&&e[i].type==3)
            {
                Bomb a = (Bomb)e[i];
                DecimalFormat df = new DecimalFormat("#.00");
                out.write(e[i].x+","+e[i].y+","+df.format(a.startTime)+","+df.format(a.explodeTime)+","+a.magnitude+",");
                i++;
            }
            out.write(13);
            out.write(10);
            while (i<e.length&&e[i].type==4)
            {
                Teleporter a = (Teleporter)e[i];
                out.write(e[i].x+","+e[i].y+","+a.x2+","+a.y2+",");
                i++;
            }
            out.write(13);
            out.write(10);
            out.write(doorX+","+doorY+",");
            out.close();
        }
        catch (IOException ffuuu){}
    }
    public static int bool2int(boolean a){if(a)return 1;return 0;}
}