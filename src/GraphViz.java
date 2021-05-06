//Modified by Ada Clevinger a fair bit for the GraphViz Java Dot library to streamline some things

//GraphViz.java - a simple API to call dot from Java programs

/*$Id$*/
/*
******************************************************************************
*                                                                            *
*                    (c) Copyright Laszlo Szathmary                          *
*                                                                            *
* This program is free software; you can redistribute it and/or modify it    *
* under the terms of the GNU Lesser General Public License as published by   *
* the Free Software Foundation; either version 2.1 of the License, or        *
* (at your option) any later version.                                        *
*                                                                            *
* This program is distributed in the hope that it will be useful, but        *
* WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
* or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public    *
* License for more details.                                                  *
*                                                                            *
* You should have received a copy of the GNU Lesser General Public License   *
* along with this program; if not, write to the Free Software Foundation,    *
* Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                              *
*                                                                            *
******************************************************************************
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


/**
* <dl>
* <dt>Purpose: GraphViz Java API
* <dd>
*
* <dt>Description:
* <dd> With this Java class you can simply call dot
*      from your Java programs.
* <dt>Example usage:
* <dd>
* <pre>
*    GraphViz gv = new GraphViz();
*    gv.addln(gv.start_graph());
*    gv.addln("A -> B;");
*    gv.addln("A -> C;");
*    gv.addln(gv.end_graph());
*    System.out.println(gv.getDotSource());
*
*    String type = "gif";
*    File out = new File("out." + type);   // out.gif in this example
*    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
* </pre>
* </dd>
*
* </dl>
*
* @version v0.5.1, 2013/03/18 (March) -- Patch of Juan Hoyos (Mac support)
* @version v0.5, 2012/04/24 (April) -- Patch of Abdur Rahman (OS detection + start subgraph + 
* read config file)
* @version v0.4, 2011/02/05 (February) -- Patch of Keheliya Gallaba is added. Now you
* can specify the type of the output file: gif, dot, fig, pdf, ps, svg, png, etc.
* @version v0.3, 2010/11/29 (November) -- Windows support + ability to read the graph from a text file
* @version v0.2, 2010/07/22 (July) -- bug fix
* @version v0.1, 2003/12/04 (December) -- first release
* @author  Laszlo Szathmary (<a href="jabba.laci@gmail.com">jabba.laci@gmail.com</a>)
*/
public class GraphViz
{
//---  Constants   ----------------------------------------------------------------------------
	
	/** The image size in dpi. 96 dpi is normal size. Higher values are 10% higher each. Lower values are 10% lower each. dpi patch by Peter Mueller*/
	private static final int[] dpiSizes = {46, 51, 57, 63, 70, 78, 86, 96, 106, 116, 128, 141, 155, 170, 187, 206, 226, 249};
	
//---  Instance Variables   -------------------------------------------------------------------

	/** String holding information about the client's operating system.*/
	private static String osName;
	/** String object holding the directory where temporary files will be created.*/
	private static String tempDir;
	/** String object holding the directory of your dot program.*/
	private static String DOT;
 	/** String object representing the source of the graph written in dot language. */ 
 	private static StringBuilder graph;
	/** int value designating the current location in the dpiSizes array.*/
	private static int currentDpiPos;

//--- Operations  -----------------------------------------------------------------------------

	public static int generateDotImage(String workingPath, String dotCode, String name, int dpi) {
		tempDir = workingPath;
		graph = new StringBuilder();
		currentDpiPos = 7;
	 	setOSDotPath();
		 
		File hold = new File(workingPath);
		hold.mkdir();
		graph.append(dotCode);
		for(int i = 0; i < dpi; i++) {
			increaseDpi();
		}
		File out = new File(hold.getAbsolutePath() + "/" + name + ".png");
		return writeGraphToFile(getGraph(getDotSource(), "png"), out);
	}
	
 	public static boolean verifyDotPath() {
 		setOSDotPath();
 		try {
 			new Properties() {
 			     private final static long serialVersionUID = 1L; {
			        load(new FileInputStream(DOT));
 			     }
 			 };
 		}
 		catch(Exception e) {
 			return false;
 		}
	 	return true;
 	}
 	
 	/**
 	 * Increase the image size (dpi) if possible.
 	 */
 	
 	private static void increaseDpi() {
	     if (currentDpiPos < (dpiSizes.length - 1) ) {
	         ++currentDpiPos;
	     }
 }

 	/**
 	 * 	Writes the graph's image in a file via a File object.
 	 * 
 	 * @param img - A byte array containing the image of the graph.
 	 * @param to - A File object to where we want to write.
 	 * @return - Returns an int value designating the results of the operation: Success: 1, Failure: -1
 	 */
 	
 	private static int writeGraphToFile(byte[] img, File to){
 		try {
 			FileOutputStream fos = new FileOutputStream(to);
 			fos.write(img);
 			fos.close();
 		} 
 		catch (IOException e) {
 			e.printStackTrace();
 			return -1;
 		}
 		return 1;
 	}

 	/**
 	 * Writes the source of the graph in a file, and returns the written file
 	 * as a File object.
 	 * 
 	 * @param str -  String value designating the source of the graph (in dot language).
 	 * @return The file (as a File object) that contains the source of the graph.
 	 */
 	
 	private static File writeDotSourceToFile(String str) throws java.io.IOException {
 		File temp;
 		try {
 			temp = File.createTempFile("dorrr",".dot", new File(tempDir));
 			FileWriter fout = new FileWriter(temp);
 			fout.write(str);
            BufferedWriter br = new BufferedWriter(new FileWriter("dotsource.dot"));
            br.write(str);
            br.flush();
            br.close();
            fout.close();
 		}
 		catch (Exception e) {
 			e.printStackTrace();
 			System.err.println("Error: I/O error while writing the dot source to temp file!");
 			return null;
 		}
 		return temp;
 	}

//--- Getter Methods  -------------------------------------------------------------------------
 
 	/**
 	 * Getter method to access the currently selected dpi setting.
 	 * 
 	 * @return - Returns an int value representing the dpi value of the image to be generated.
 	 */
 	
 	private static int getImageDpi() {
 		return dpiSizes[currentDpiPos];
 	}

 	/**
 	 * Returns the graph's source description in dot language.
 	 * 
 	 * @return - Returns a String value representing the source of the graph in dot language.
 	 */
 
 	private static String getDotSource() {
     return graph.toString();
 	}

 	/**
 	 * Returns the graph as an image in binary format.
 	 * 
 	 * @param dot_source - String value representing the source of the graph to be drawn.
 	 * @param type - String value representing the type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
 	 * @return - Returns a byte array containing the image of the graph.
 	 */
 	
 	private static byte[] getGraph(String dot_source, String type) {
		File dot;
		byte[] img_stream = null;
		try {
			dot = writeDotSourceToFile(dot_source);
			if (dot != null) {
				img_stream = get_img_stream(dot, type);
				if (dot.delete() == false) 
					System.err.println("Warning: " + dot.getAbsolutePath() + " could not be deleted!");
				return img_stream;
			}
			return null;
		} 
		catch (IOException e) { 
			e.printStackTrace();
			return null; 
		}
 	}

 	/**
 	 * It will call the external dot program, and return the image in binary format.
 	 * 
 	 * @param dot - File object containing the source of the graph (in dot language).
 	 * @param type - String value representing the type of the output image to be produced, e.g.: gif, dot, fig, pdf, ps, svg, png.
 	 * @return - Returns a byte array representing the image of the graph in the designated format.
 	 */
 	
 	private static byte[] get_img_stream(File dot, String type) {
 		File img;
 		byte[] img_stream = null;

 		try {
 			img = File.createTempFile("graph_", "." + type, new File(tempDir));
 			Runtime rt = Runtime.getRuntime();

 			// patch by Mike Chenault
 			String[] args = {DOT, "-T" + type, "-Gdpi=" + getImageDpi(), dot.getAbsolutePath(), "-o", img.getAbsolutePath()};
 			
 			Process p = rt.exec(args);
 			
 			Scanner sc = new Scanner(p.getErrorStream());
 			
 			p.waitFor();
 			
 			while(sc.hasNext())
 				System.out.println(sc.nextLine());

 			FileInputStream in = new FileInputStream(img.getAbsolutePath());
 			img_stream = new byte[in.available()];
 			in.read(img_stream);
 			// Close it if we need to
 			if( in != null )
 				in.close();

 			if (img.delete() == false) 
 				System.err.println("Warning: " + img.getAbsolutePath() + " could not be deleted!");
 		} catch (IOException ioe) {
 			System.err.println("Error:    in I/O processing of tempfile in dir " + tempDir +"\n");
 			System.err.println("       or in calling external command");
 			ioe.printStackTrace();
 		} catch (InterruptedException ie) {
 			System.err.println("Error: the execution of the external program was interrupted");
 			ie.printStackTrace();
     }

     return img_stream;
 }

//--- Setter Methods  -------------------------------------------------------------------------

 	private static void setOSDotPath() {
 		 osName = System.getProperty("os.name").replaceAll("\\s","");
 		 if(osName.contains("Windows")) {
 			 osName = "Windows";
 	 		 DOT = "GraphViz/bin/dot.exe";
 		 }
 		 else if(osName.contains("Mac")) {
 			 osName = "Mac";
 	 		 DOT = "GraphViz/bin/dot.exe";
 		 }
 		 else {
 			 osName = "Linux I guess";
 	 		 DOT = "GraphViz/bin/dot.exe";
 		 }
 		 //TODO: All OS handling
 	}
 
} // end of class GraphViz