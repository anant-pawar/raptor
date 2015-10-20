import io.vertx.core.json.Json;
import org.raptor.core.Raptor;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Anant on 11-07-2015.
 */
public class Runner {
    public static void main(String[] args)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String reader;
            String result = new String();

            Json json = new Json();

            while ((reader = br.readLine()) != null) {
                result += reader;
            }

            Raptor raptor = new Raptor();
            raptor.run(result);
        }catch (Exception exception)
        {

        }
    }
}