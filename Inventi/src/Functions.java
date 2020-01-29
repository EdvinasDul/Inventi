
import java.util.List;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Edvinas
 */
public class Functions {
    
    private static final String URL = "http://localhost:8080/homework/webapi/statements/";
    
    public static String getBalance(String path_extension) throws IOException{

        String path = URL + path_extension;
            
	HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		
	connection.setRequestMethod("GET");

	int responseCode = connection.getResponseCode();
	if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
		response += scanner.nextLine();
		response += "\n";
            }
            scanner.close();

            return response;
	}
		
	// an error happened
	return null;
}
 
    public static ArrayList<String> getAccountsList() throws MalformedURLException, IOException{
        
        ArrayList<String> list = new ArrayList<String>();
        list.add("- none -");
        
        String path = URL + "accounts";
            
	HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		
	connection.setRequestMethod("GET");

	int responseCode = connection.getResponseCode();
	if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
		response += scanner.nextLine();
		response += "\n";
            }
            scanner.close();

            String[] arrOfStr = response.substring(1, response.length()-2).split("},");
            for(String item : arrOfStr){
                JSONObject jsonObject = new JSONObject(item+"}");
                int acc = jsonObject.getInt("account_number");
                list.add(String.valueOf(acc));
            }
            
            return list;
            
	}
        
        
        
        return list;
    }
    
    public static List<Data> getAllStatementsById(int id) throws MalformedURLException, IOException, ParseException{
        
        List<Data> data = new ArrayList<>();
        
        String path = URL + id + "/statements";

	HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
		
	connection.setRequestMethod("GET");

	int responseCode = connection.getResponseCode();
	if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
		response += scanner.nextLine();
		response += "\n";
            }
            scanner.close();
            
            String[] arrOfStr = response.substring(1, response.length()-2).split("},");
            
            
            if(arrOfStr.length != 1 && !arrOfStr[0].equals("")){
                for(String item : arrOfStr){
                JSONObject jsonObject = new JSONObject(item+"}");
                String td = jsonObject.getString("date").substring(0, jsonObject.getString("date").length()-1);
                
                Data temp = new Data(jsonObject.getInt("account_number"), td, jsonObject.getString("beneficiary"), 
                        jsonObject.getString("comment"), jsonObject.getDouble("amount"), jsonObject.getString("currency"));
                
                data.add(temp);
                }
            }
            // throws error
            return data;
            
	}
        
        return data;
    }
 
    public static void importCsvToDatabase(Data data) throws MalformedURLException, ProtocolException, IOException{
        
        String path = URL + "post";
        
        HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();

	connection.setRequestMethod("POST");
		
	String postData = "account_number=" + URLEncoder.encode(String.valueOf(data.getAccount_number()));
	postData += "&beneficiary=" + URLEncoder.encode(data.getBeneficiary());
	postData += "&comment=" + URLEncoder.encode(data.getComment());
	postData += "&amount=" + URLEncoder.encode(String.valueOf(data.getAmount()));
	postData += "&currency=" + URLEncoder.encode(data.getCurrency());
	postData += "&date=" + URLEncoder.encode(data.getDate());
		
        System.out.println(postData);
        
	connection.setDoOutput(true);
	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	wr.write(postData);
	wr.flush();
		
	int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            System.out.println("POST was successful.");
	}
	else if(responseCode == 401){
            System.out.println("Something went wrong.");
	}
    }
    
}
