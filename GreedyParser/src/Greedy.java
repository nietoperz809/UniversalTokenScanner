import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by
 */
public class Greedy
{
    ArrayList<String> tokens = new ArrayList<>();
    String combined;

    public Greedy(String in)
    {
        combined = in;

        tokens.add("pe");
        tokens.add("pet");
        tokens.add("peter");

        tokens.sort (Comparator.comparingInt(String::length).reversed());
    }

    public ArrayList<String> getList()
    {
        ArrayList<String> out = new ArrayList<>();
        String copy = combined;
        for (int s=0; s<combined.length(); s++)
        {
            int nearPos = Integer.MAX_VALUE;
            String nearTok = null;
            for (String token : tokens)
            {
                int n = copy.indexOf(token);
                if (n >= 0 && n < nearPos)
                {
                    nearPos = n;
                    nearTok = token;
                }
            }
            if (nearTok != null)
            {
                if (nearPos != 0)
                {
                    out.add (copy.substring(0, nearPos));
                    copy = copy.substring(nearPos);
                    //System.out.println(nearPos);
                }
                out.add(nearTok);
                copy = copy.substring(nearTok.length());
            }
        }
        return out;
    }


    public static void main (String[] args)
    {
        Greedy g = new Greedy("apetpeterpepepetepetepepetexxxpe");
        g.getList().forEach(System.out::println);
    }
}
