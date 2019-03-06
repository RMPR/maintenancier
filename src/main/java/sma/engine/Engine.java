/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma.engine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.jpl7.Query;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rufus
 */
public class Engine {

	static {
		Path path = Paths.get(System.getProperty("user.dir"), "tools.pl");
		if (!Files.exists(Paths.get(System.getProperty("user.dir"), "tools.pl"))) {
			try {
				InputStream in = Engine.class.getResourceAsStream("maintenance2.pl");
				Files.copy(in, path);
			} catch (IOException ex) {
				Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		String query = "consult('" + path.toString().replace('\\', '/') + "')";

		Query consult = new Query(query);
		System.out.println(consult.hasSolution() ? "success" : "error");

		Query go = new Query("go");
		System.out.println(go.hasSolution());
	}

	public static void play() {

	}

	public static void handle(String question) throws UnsupportedEncodingException {
		System.out.println("> " + question);
		Engine.get("oui.");
	}
	
	public static void send(String solution){
		System.out.println("> " +solution);
	}
	
	public static void get(String  response){
		System.setIn(new ByteArrayInputStream(response.getBytes()));
	} 

	public static String readT() throws InterruptedException {
		Scanner s = new Scanner(System.in);
		return s.next();
	}
}
