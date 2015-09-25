package demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import demo.model.ResultadoGeolocation;

@Service
public class GeoLocationService {

	private final String USER_AGENT = "Mozilla/5.0";

	public ResultadoGeolocation converterEnderecoParaCoordenadas(String endereco)
			throws IOException {

		endereco = endereco.replace(" ", "+");

		String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
				+ endereco + "&key=AIzaSyDxYTWGtd5b6T_g_T88okS3p-2sJ9x_8Xg";

		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		ResultadoGeolocation resultado = new Gson().fromJson(
				response.toString(), ResultadoGeolocation.class);

		return resultado;

	}

}
