import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by
 */
public class Greedy
{
    ArrayList<String> tokens;
    String combined;

    public Greedy(String in, String[] tokenList)
    {
        tokens = new ArrayList<String>(Arrays.asList(tokenList));
        combined = in;
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
                }
                out.add(nearTok);
                copy = copy.substring(nearTok.length());
                if (copy.isEmpty())
                    break;
            }
        }
        if (!copy.isEmpty())
            out.add(copy);
        return out;
    }


    public static void main (String[] args)
    {
        String[] tokens = {"pe", "pet", "peter"};
        String input = "apetpeterpepepetepetepepetexxxpell";
        Greedy g = new Greedy(input, tokens);
        g.getList().forEach(System.out::println);
    }
}
