

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;



public class ApiJSON {

    /**
     * Retorna el JSON de la pelicula
     * LLibreria que sencarrega de gestionar les peticions GET
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();//retorna un JSON
    }

    public static void main(String[] args){
        String s = "";

        for (int i = 0; i < 10; i++) {
            String peticio = "http://pokeapi.co/api/v1/pokemon/"+i;

            try {
                s = getHTML(peticio);
                //System.out.println(s);
                SJPokeApi(s);

            } catch (Exception e) {
                System.out.println("La peli " + i + " no existeix");
            }
        }
    }

    public static void SJPokeApi(String cadena){
        Object obj = JSONValue.parse(cadena);
        JSONObject jobj = (JSONObject) obj;//contiene toda la informacion del JSON
        //String pkNom = (String)jobj.get("name");           //IMPORTANT SEGUENT PAS
        //Integer id = Integer.parseInt((String)jobj.get("national_id"));     //IMPORTANT SEGUENT PAS
        System.out.println(jobj.get("national_id"));
        System.out.println(jobj.get("name"));
        System.out.println("----Tipos----");

        JSONArray jarray = (JSONArray)jobj.get("types");

        for(int i = 0; i < jarray.size(); i++){
            JSONObject jobjda = (JSONObject) jarray.get(i);
            System.out.println("\t" + jobjda.get("name"));
        }
    }
}



/*DESFASE OBJETO RELACIONAL (pregunta de examen)

publi class Pk{
    String nombre = null;
    Integer id = null;
    this.tipos = tip


}
*/
