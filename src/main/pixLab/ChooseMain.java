package pixLab;

/**
 * Created by John on 1/13/2015.
 */
public class ChooseMain
{
    public static void main(String[] args)
    {
        String main = args[0];
        if (main.equals("IntArrayWorkerTester"))
            IntArrayWorkerTester.main(new String[0]);
        else if (main.equals("PictureTester"))
            PictureTester.main(new String[0]);
    }
}
