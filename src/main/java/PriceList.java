import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PriceList{

    public JsonArray datasets = new JsonArray();

    public void PriceList(String filename) throws FileNotFoundException {
        File file = new File(filename);

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean flag = true;
            List<String> cols = null;

            while((line = br.readLine()) != null) {
                if(flag = true) {
                    //Processing Header
                    flag = false;
                    cols = Arrays.asList(line.split(","));
                }
                else {
                    JsonObject obj = new JsonObject();
                    List<String> data = Arrays.asList(line.split(","));
                    for(int i = 0; i < cols.size(); i++) {
                        obj.addProperty(cols.get(i),data.get(i));
                    }
                    datasets.add(obj);
                }

            }
        } catch (IOException e) {
            System.out.println("Can't read " + filename);
        }
    }

    public String print() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        return gson.toJson(datasets);
    }

}
